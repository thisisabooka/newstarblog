package com.fuyao.newstarblog.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * MD5盐值加密
 */
public class MD5Matcher {
	
	public static Object MD5(Object object) {
		String hashAlgorithmName = "MD5";
		Object credentials = object;
		Object salt = null;
		int hashIterations =1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		return result;
	}

}
