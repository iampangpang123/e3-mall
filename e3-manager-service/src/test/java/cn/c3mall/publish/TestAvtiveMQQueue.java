package cn.c3mall.publish;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class TestAvtiveMQQueue {
	@Test
	public void testQueue() throws JMSException {// 点对点的.生产者发送消息
		// 1.创建一个连接工厂对象
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
		// 2.通过工厂对象得到connection，并且开启连接
		Connection connection = connectionFactory.createConnection();
		connection.start();
		// 3.通过connection得到session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);// 第一个参数代表是否开启事务，第二个参数是应答模式
		// 4.通过session对象得到Destination对象，使用queue形式
		Queue createQueue = session.createQueue("test-queue");// 参数代表队列的名字
		// 5.使用session创建Producter对象
		MessageProducer producer = session.createProducer(createQueue);
		// 6.使用session創建message對象，可以是使用TestMessage
		TextMessage createTextMessage = session.createTextMessage("消息内容是：hello");
		// 生產者發送消息
		producer.send(createTextMessage);
		producer.close();
		session.close();
		connection.close();
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
		Queue createQueue = session.createQueue("test-queue");// 参数代表队列的名字
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
