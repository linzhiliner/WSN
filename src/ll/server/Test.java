package ll.server;

import java.util.Date;

public class Test {
	public static void main(String[] args){
		
		System.out.println("接收到数据");
		System.out.println("传感器类型："+"temp");
		System.out.println("传感器读数："+"12");
		System.out.println("传感器节点ip："+"fe80::0012:4b00:027c:db0f/64");
		System.out.println("传感器采集时间："+new Date());
	}
}
