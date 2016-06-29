package org.rogan.findandreplace.service;
/*
 * 功能：用于处理有关字符串查找和替换算法
 * */
public class MatchFun {
	public StringBuffer regStr;
	//方法strFind用于实现字符串查找，返回匹配的次数
	public int strFind(String s1, String s2, int pos){
		
		//变量i和j分别表示主串和模式串中当前字符串的位置，k表示匹配次数，pos代表主串中开始比较的位置
		int i,j,k=0;
		i=0;
		//i = pos;
		j = 0;
		//核心算法,很好理解
		while(i < s1.length() && j < s2.length()){
			if(s1.charAt(i) == s2.charAt(j)){
				
				++i; ++j;
				if(j == s2.length()){ //如果字符串匹配成功，匹配次数加1
					k = k + 1;
					i = i -j + 1;  //i往后挪一位
					j = 0;
				}
			}
			else{
				i = i -j +1;
				j = 0;
			}
		}
		
		return k;  //返回查找匹配的次数
	}
	
	//strReplace用于实现字符串替换操作，返回替换的次数
	public int strReplace(String s1,String s2,String s3,int pos){
		int i,j,k=0;
		i = 0;
		j = 0;
		
		regStr = new StringBuffer(s1);  //将s1转换为StringBuffer型
		while(i < regStr.length() && j < s2.length()){
			if(regStr.charAt(i) == s2.charAt(j)){
				++i; ++j;
				if(j == s2.length()){  //查找到了，准备替换
					k = k+1;
					regStr.replace(i-j, i, s3);  //用s3替换
					j=0;
				}
			}
			else{
				i = i -j +1;  //
				j = 0;
			}
		}
		return k;
		
	}
	
	
}
