package com.huawei.server;

public class Byte2String {
	public static String[] byte2String(byte[] byteArr) {
		String[] dataArr = new String[3];
		String ip = "fe80::";
		// ��0λΪ����������
		// 1�����¶ȣ�2����ʪ�ȣ�3�������
		switch (byteArr[0]) {
		case 1:
			dataArr[0] = "temp";
			break;
		case 2:
			dataArr[0] = "hum";
			break;
		case 3:
			dataArr[0] = "light";
			break;
		}
		// ��1λΪ������ֵ
		dataArr[1] = "" + byteArr[1];
		// ��2λ����9λΪ��������ַ
		String[] byteStr=new String[8];
		for (int i = 0; i < 8; i++) {
			int ipIntVal = byteArr[i+2];
			String ipHexStr = Integer.toHexString(ipIntVal);
			String ipStr;
			String addPre="0";
			if (ipIntVal < 0) {
				ipStr = ipHexStr.substring(ipHexStr.length() - 2, ipHexStr.length());
			} else if(ipIntVal<16){
				ipStr = addPre.concat(ipHexStr);
			}else{
				ipStr = ipHexStr;
			}

			byteStr[i]=ipStr;
		}
		for(int i=0;i<8;i=i+2){
			if (i < 6) {
				ip +=(byteStr[i]+byteStr[i+1]+":");
			} else {
				ip += (byteStr[i]+byteStr[i+1]+ "/64");
			}
		}
		dataArr[2] = ip;
		return dataArr;
	}
}
