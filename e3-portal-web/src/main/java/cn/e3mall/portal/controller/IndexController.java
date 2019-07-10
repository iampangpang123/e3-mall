package cn.e3mall.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

/**
 * @ClassName: IndexController
 * @Description: TODO(商品首页展示的controller)
 * @author 唐
 * @date 2018年12月28日 下午5:03:11
 */

//说明一下controller与hanadler的区别
// controller指的是一个类，handler（处理器）指的是一个方法
@Controller
public class IndexController {
//	我们在c3-manager-web里面写商品展示的controller的@RequestMapping("/");
//	这是因为我们在springmvc配置的拦截路径是/
//	这里是不能写@RequestMapping("/")，因为这个我们的springmvc的拦截路径是   *.html,只会拦截.html结尾的
//	1.@RequestMapping("/index.html")
//	2.@RequestMapping("/index")
//上面两种写法也是可行的,这样直接写localhost:8083/就可以访问到
	/*
	 * 原理
	 * 1.因为web.xml配置的有引导页面，但是呢由于我们webapp下是没有index.html的。所有他找不到
	 * 2.然后呢他会自动的去前端控制器里面找，前端控制器把请求交给处理器适配器，然后我们的拦截器配置的拦截请求搞好是。html结尾的，然后index.html刚好与我们的请求对应，
	 * 3.就会调用showIndex方法，会返回index.html
	 * 4.经过视图解析器的处理，就找到了web——Inf下的index.jsp
	 */
	@Autowired
	private ContentService contenService;
	@Value("${CONTENT_LUNBO_CID}")
	private Long CONTENT_LUNBO_CID;
	@RequestMapping("/index")
	public String showIndex(Model model) {
		//展示首页之前显示查询内容列表，因为这些数据在数据库里面，你不查询展示的都是默认的
		//1.这里我们写死，因为数据库就只有89一个分类，其他的没图片，穷
		//contenService.getContentListById(89);
		//2.我们把cid配置在resource文件里面，以后好改
		List<TbContent> ad1List = contenService.getContentListById(CONTENT_LUNBO_CID);
		model.addAttribute("ad1List", ad1List);
		System.out.println("商品首页展示的showIndex()方法调用成功");
		return "index";
	}

}
