package org.rogan.findandreplace.service;
/*
 * ���ܣ����ڴ����й��ַ������Һ��滻�㷨
 * */
public class MatchFun {
	public StringBuffer regStr;
	//����strFind����ʵ���ַ������ң�����ƥ��Ĵ���
	public int strFind(String s1, String s2, int pos){
		
		//����i��j�ֱ��ʾ������ģʽ���е�ǰ�ַ�����λ�ã�k��ʾƥ�������pos���������п�ʼ�Ƚϵ�λ��
		int i,j,k=0;
		i=0;
		//i = pos;
		j = 0;
		//�����㷨,�ܺ����
		while(i < s1.length() && j < s2.length()){
			if(s1.charAt(i) == s2.charAt(j)){
				
				++i; ++j;
				if(j == s2.length()){ //����ַ���ƥ��ɹ���ƥ�������1
					k = k + 1;
					i = i -j + 1;  //i����Ųһλ
					j = 0;
				}
			}
			else{
				i = i -j +1;
				j = 0;
			}
		}
		
		return k;  //���ز���ƥ��Ĵ���
	}
	
	//strReplace����ʵ���ַ����滻�����������滻�Ĵ���
	public int strReplace(String s1,String s2,String s3,int pos){
		int i,j,k=0;
		i = 0;
		j = 0;
		
		regStr = new StringBuffer(s1);  //��s1ת��ΪStringBuffer��
		while(i < regStr.length() && j < s2.length()){
			if(regStr.charAt(i) == s2.charAt(j)){
				++i; ++j;
				if(j == s2.length()){  //���ҵ��ˣ�׼���滻
					k = k+1;
					regStr.replace(i-j, i, s3);  //��s3�滻
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
