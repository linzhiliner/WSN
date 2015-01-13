package com.huawei.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

import com.huawei.domain.Sensor;


public class UDPServer {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		//正常端口
		DatagramSocket server = new DatagramSocket(12345);
		//测试端口
//		DatagramSocket server = new DatagramSocket(5050);
		byte[] recBuf = new byte[10];
		while (true) {
			DatagramPacket recPacket = new DatagramPacket(recBuf, recBuf.length);
			server.receive(recPacket);
			String[] dataArr=Byte2String.byte2String(recBuf);
			//处理客户端数据
			if(dataArr!=null){
				Sensor sensorData=new Sensor();
				sensorData.setDataType(dataArr[0]);
				sensorData.setValue(Double.parseDouble(dataArr[1]));
				sensorData.setDateCreated(new Date());
				sensorData.setIp(dataArr[2]);
				sensorData.setSign(false);
				//将传感器传过来的数据存入数据库
				System.out.println("接收到数据");
				System.out.println("传感器类型："+sensorData.getDataType());
				System.out.println("传感器读数："+sensorData.getValue());
				System.out.println("传感器节点ip："+sensorData.getIp());
				System.out.println("传感器采集时间："+sensorData.getDateCreated());
			}
			
		}

	}
}
