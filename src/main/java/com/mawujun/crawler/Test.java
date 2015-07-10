package com.mawujun.crawler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.client.utils.URLEncodedUtils;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		//%E5%8C%97%E4%BA%AC%E5%B8%82
		System.out.println(URLEncoder.encode("北京市","ISO-8859-1"));
	}

}
