package com.es.spider;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.nodes.Document;

public class FileDown {
	
	//图片下载
	public static void imageDown(String img, String dir, String imgName) {
		InputStream inputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			// 建立连接,获得输入流
			URL url = new URL(img);
			URLConnection connect = url.openConnection();
			connect.setConnectTimeout(1000);
			connect.setReadTimeout(5000);
			connect.connect();
			inputStream = connect.getInputStream();
			// 若文件夹不存在创建文件夹
			File file = new File(dir);
			if (!file.exists()) {
				file.mkdir();
			}
			// 创建输出流
			String filename = dir + "/" + imgName;
			fileOutputStream = new FileOutputStream(new File(filename));
			byte[] buffer = new byte[102400];
			int length = 0;
			while ((length = inputStream.read(buffer, 0, buffer.length)) != -1) {
				fileOutputStream.write(buffer, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					fileOutputStream = null;
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					inputStream = null;
				}
			}
		}
	}
	
	//doc下载
	public static void docWrite(Document doc) throws IOException {
		FileWriter fileWriter = new FileWriter("d://spider.html");
		fileWriter.write(doc.toString());
		fileWriter.flush();
		fileWriter.close();
	}
}
