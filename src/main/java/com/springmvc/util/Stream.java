package com.springmvc.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Stream {
	
	public static  void OutputFile(String filePath) throws IOException {
		File file = new File(filePath);
		if(!file.exists()) {
			file.createNewFile();
		}
		// 取得文件名。
		String filename = file.getName();
		System.out.println("name=" + filename);
		String absolutePath = file.getAbsolutePath();
		System.out.println("absolutePath:" + absolutePath);
		// 以流的形式下载文件。
		InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		OutputStream out = new FileOutputStream(filePath);
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void downFile(String FilePath) throws IOException {
		OutputStream out = new FileOutputStream(FilePath);
		out.close();
	}
	public static void main(String[] args) throws IOException {
		String filePath = "E:\\zeasnAPK\\testA.txt";
		OutputFile(filePath);
		
	}

}
