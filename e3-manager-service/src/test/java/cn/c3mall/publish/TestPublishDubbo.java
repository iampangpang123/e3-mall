package cn.c3mall.publish;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPublishDubbo {

	/**
	 * @Description: TODO(写这个方法的原因是因为，我们e3-manager配置tomcat的原因主要是因为我们要初始化spring容器，从而发布dubbo的服务只做这一件事
	 *               ，初始化容易我们可以用代码初始化，用tomcat发布项目太浪费资源了。2.手动运行这个方法后，就不需要在启动tomcat的e3-manager命令了 )
	 * @return void 返回类型
	 */
	@Test
	public void publishService() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		System.out.println("服务已经启动");
		System.in.read();// 在控制台输入一个字符或者回车，方法就会结束运行。
		System.out.println("服务已经关闭");

	}

}
