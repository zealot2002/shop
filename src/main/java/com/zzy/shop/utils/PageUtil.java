package com.zzy.shop.utils;

import java.util.ArrayList;
import java.util.List;

public class PageUtil<T> {
	
	public List<T> getList(List<T> sourceList,int pageNum,int pageSize) throws Exception{
		if(pageNum<1) {
			throw new Exception("pageNum must more than 1!");
		}
		if(pageSize<1) {
			throw new Exception("pageSize must more than 1!");
		}
		if(sourceList==null) {
			throw new Exception("PageUtil: sourceList is null!");
		}
		List<T> targetList = new ArrayList<>();
		int start = (pageNum-1)*pageSize;
		int end = start+pageSize;
		if(start<sourceList.size()) {
			for(int i=start;i<end;i++) {
				targetList.add(sourceList.get(i));
			}
		}
		return targetList;
	}
}
