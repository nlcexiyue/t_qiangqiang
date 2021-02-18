package com.qiangqiang.config;

import java.io.*;


public class EncodingDetect {

	
	/**
	 * 得到文件的编码
	 * @param filePath 文件路径
	 * @return 文件的编码
	 */
	public static String getJavaEncode(String filePath){
		BytesEncodingDetect s = new BytesEncodingDetect(); 
		String fileCode = BytesEncodingDetect.javaname[s.detectEncoding(new File(filePath))];
		return fileCode;
	}
	
	public static void readFile(String file, String code) {
 
		BufferedReader fr;
		try {
			String myCode = code!=null&&!"".equals(code) ? code : "UTF8";
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), myCode);
 
			fr = new BufferedReader(read);
			String line = null;
			int flag=1;
			// 读取每一行，如果结束了，line会为空
			while ((line = fr.readLine()) != null && line.trim().length() > 0) {
				if(flag==1) {
				    line=line.substring(1);//去掉文件头
				    flag++;
			    }
				// 每一行创建一个Student对象，并存入数组中
				System.out.println(line);
			}
			fr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
}

