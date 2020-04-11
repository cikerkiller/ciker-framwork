package com.hf.ciker.commons.utils;

import com.google.common.collect.Lists;
import com.hf.ciker.commons.exceptions.CikerRuntimeException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @desc 类加载工具类
 * @author ciker
 * @date 2020-04-11
 */
public class ClassUtil {

    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?> loadClass(String className, boolean isInitialized){
        Class<?> clz;
        try {
            clz = Class.forName(className,isInitialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            LogUtil.error("load class error, className : {}", className, e);
            throw new CikerRuntimeException("load class error, className : {}", e, className);
        }
        return clz;
    }

    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet =  new HashSet<>();
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
            LogUtil.error("getClassSet error, packageName : {}", packageName, e);
            throw new CikerRuntimeException("getClassSet error, packageName : {}", e, packageName);
        }
        return classSet;
    }

    private static List<File> fileFilter(String packagePath){
        File[] files = new File(packagePath)
                .listFiles( pathname -> (pathname.isFile()
                        && pathname.getName().endsWith(".class"))
                        || pathname.isDirectory()
                );
        return Lists.newArrayList(files);
    }

    private static void addClass(Set<Class<?>> classSet,String packagePath,String packageName){
        List<File> files = fileFilter(packagePath);
        files.forEach(file -> {
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
        });
    }
    private static void doAddClass(Set<Class<?>> classSet, String className){
        classSet.add(loadClass(className,false));
    }
}
