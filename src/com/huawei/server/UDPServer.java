package com.huawei.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

import com.huawei.domain.Sensor;


public class UDPServer {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		//�����˿�
		DatagramSocket server = new DatagramSocket(12345);
		//���Զ˿�
//		DatagramSocket server = new DatagramSocket(5050);
		byte[] recBuf = new byte[10];
		while (true) {
			DatagramPacket recPacket = new DatagramPacket(recBuf, recBuf.length);
			server.receive(recPacket);
			String[] dataArr=Byte2String.byte2String(recBuf);
			//����ͻ�������
			if(dataArr!=null){
				Sensor sensorData=new Sensor();
				sensorData.setDataType(dataArr[0]);
				sensorData.setValue(Double.parseDouble(dataArr[1]));
				sensorData.setDateCreated(new Date());
				sensorData.setIp(dataArr[2]);
				sensorData.setSign(false);
				//�������������������ݴ������ݿ�
				System.out.println("���յ�����");
				System.out.println("���������ͣ�"+sensorData.getDataType());
				System.out.println("������������"+sensorData.getValue());
				System.out.println("�������ڵ�ip��"+sensorData.getIp());
				System.out.println("�������ɼ�ʱ�䣺"+sensorData.getDateCreated());
			}
			
		}

	}
}
