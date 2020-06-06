package com.hf.ciker.user.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @desc 日志工具类
 * @author ciker
 * @date 2020-04-02 20:52
 *
 */
public class LogUtil {

	private LogUtil() {}
	
	private static StackTraceElement findCaller() {
		StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
		if(callStack != null) {
			for(int i = 1; i<callStack.length; i++) {
				StackTraceElement stackTraceEle = callStack[i];
				if(Objects.equals(LogUtil.class.getName(), stackTraceEle.getClassName())) {
					continue;
				}
				return stackTraceEle;
			}
		}
		return null;
	}
	
	private static Logger logger() {
		StackTraceElement caller = findCaller();
		if(caller != null) {
			return LoggerFactory.getLogger(getLogShow(caller));
		}
		return LoggerFactory.getLogger(LogUtil.class);
	}
	
	private static String getClassName(StackTraceElement caller) {
		StringBuilder sbu = new StringBuilder();
		String[] names = caller.getClassName().split("\\.");
		for(int i=0; i < names.length-1; i++) {
			sbu.append(names[i].charAt(0)).append(".");
		}
		sbu.append(names[names.length-1]);
		return sbu.toString();
	}
	
	private static String getLogShow(StackTraceElement caller) {
		StringBuilder sbu = new StringBuilder();
		sbu.append(getClassName(caller));
		sbu.append(".").append(caller.getMethodName()).append("() ");
		sbu.append("[").append("line:").append(caller.getLineNumber()).append("]");
		return sbu.toString();
	}
	
	
	public static void debug(String msg) {
		logger().debug(msg);
	}
	
	public static void debug(String msg, Object... objs) {
		logger().debug(msg, objs);
	}
	
	public static void info(String msg) {
		logger().info(msg);
	}
	
	public static void info(String msg, Object... objs) {
		logger().info(msg, objs);
	}
	
	public static void warn(String msg) {
		logger().warn(msg);
	}
	
	public static void warn(String msg, Object... objs) {
		logger().warn(msg, objs);
	}
	
	public static void error(String msg) {
		logger().error(msg);
	}
	
	public static void error(String msg, Object... objs) {
		logger().error(msg, objs);
	}
	
}
