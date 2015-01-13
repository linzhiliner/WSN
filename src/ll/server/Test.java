package ll.server;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import ll.domain.Sensor;

public class Test{
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("file:E:/WSN/WebContent/WEB-INF/applicationContext.xml");
		SessionFactory sf = (SessionFactory) context.getBean("sessionFactory");
	
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Sensor s = new Sensor();
        s.setId(100L);
        s.setIp("fe80::0012:4b00:027d:dbfe/64");
        s.setSign(true);
        s.setValue(20);
        s.setDataType("2014-12-26 22:36:03");
        session.save(s);
        tx.commit();
		
	}
}
