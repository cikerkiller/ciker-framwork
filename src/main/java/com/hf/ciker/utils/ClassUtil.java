package com.hf.ciker.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?> loadClass(String className, boolean isInitialized){
        Class<?> clz;
        try {
            clz = Class.forName(className,isInitialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class error",e);
            throw new RuntimeException(e);
        }
        return clz;
    }

    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet =     new HashSet<>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/")+"/");
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                if(url == null){
                    continue;
                }
                String protocol = url.getProtocol();
                if(StringUtils.equals(protocol,"file")){
                    String packagePath = url.getPath().replaceAll("%20", " ");
                    addClass(classSet,packagePath,packageName);
                }else if(StringUtils.equals(protocol,"jar")){
                    JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                    if(jarURLConnection != null){
                        JarFile jarFile = jarURLConnection.getJarFile();
                        if(jarFile != null){
                            Enumeration<JarEntry> entries = jarFile.entries();
                            while (entries.hasMoreElements()){
                                JarEntry jarEntry = entries.nextElement();
                                String jarEntryName = jarEntry.getName();
                                if(jarEntryName.endsWith(".class")){
                                    String className = jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/",".");
                                    doAddClass(classSet,className);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("getClassSet error",e);
            throw new RuntimeException(e);
        }
        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet,String packagePath,String packageName){
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return (pathname.isFile() && pathname.getName().endsWith(".class"))
                        || pathname.isDirectory();
            }
        });
        for(File file : files){
            String fileName = file.getName();
            if(file.isFile()){
                String className = fileName.substring(0,fileName.lastIndexOf("."));
                if(StringUtils.isNotEmpty(packageName)){
                    className = packageName +"."+ className;
                }
                doAddClass(classSet,className);
            }else{
                String subPackagePath = fileName;
                if(StringUtils.isNotEmpty(packagePath)){
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if(StringUtils.isNotEmpty(packageName)){
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet,subPackagePath,subPackageName);
            }
        }
    }
    private static void doAddClass(Set<Class<?>> classSet, String className){
        classSet.add(loadClass(className,false));
    }
}
