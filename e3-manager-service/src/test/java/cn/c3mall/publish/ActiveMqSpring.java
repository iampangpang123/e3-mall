package cn.c3mall.publish;


import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ActiveMqSpring {

	@Test
	public void sendMessage() throws Exception {
		//初始化spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		//从容器中获得JmsTemplate对象。
		JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
		//从容器中获得一个Destination对象。
		Destination destination = (Destination) applicationContext.getBean("queueDestination");
		//发送消息
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("send activemq message");
			}
		});
	}
	@Test
	public void testQueueCusomoer() throws JMSException, IOException {// 点对点的.消费者接受消息
		// 1.创建一个连接工厂对象
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
		// 2.通过工厂对象得到connection，并且开启连接
		Connection connection = connectionFactory.createConnection();
		connection.start();
		// 3.通过connection得到session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);// 第一个参数代表是否开启事务，第二个参数是应答模式
		// 4.通过session对象得到Destination对象，使用queue形式
		Queue createQueue = session.createQueue("spring-queue");// 参数代表队列的名字
		// 5.使用session创建customer对象
		MessageConsumer consumer = session.createConsumer(createQueue);
		// 接受消息
		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message arg0) {
				// 打印結果
				TextMessage message = (TextMessage) arg0;
				String text;
				try {
					text = message.getText();
					System.out.println(text);
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
//等待接收小心
		System.in.read();

		consumer.close();
		session.close();
		connection.close();
	}

}
