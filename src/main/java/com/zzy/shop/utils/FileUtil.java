package com.zzy.shop.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil{
	
	public static List<String> getFileNameList(String dirPath) throws Exception{
		List<String> list = new ArrayList<>();
		File file = new File(dirPath);		//获取其file对象
		File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
		for(File f:fs){					//遍历File[]数组
			if(!f.isDirectory())		//若非目录(即文件)，则打印
				list.add(f.getName());
		}
		return list;
	}
}
