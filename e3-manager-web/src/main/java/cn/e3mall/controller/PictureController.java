package cn.e3mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.common.utils.FastDFSClient;
import cn.e3mall.common.utils.JsonUtils;

/**
 * 
 * @ClassName: PictureController
 * @Description: TODO(图片上传的controller)
 * @author 唐
 * @date 2018年12月27日 下午8:12:31
 */
@Controller
public class PictureController {
	// 4.由于返回的url没有服务器的ip地址，我们还需要加载ip地址，这个配置文件我们在springmvc中已经加载了。通过value注解在成员变量中已经注入了，直接使用即可
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	/**  
	* @Description: TODO(新增商品上传图片的方法) 
	* @return String    返回类型 
	*/
	@RequestMapping(value = "/pic/upload", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8") // 指定响应结果的content-type：
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile) {// 前端页面上传的图片名字叫这个
		// @ResponseBody的意思不是转json，而是直接响应内容给浏览器，不经过视图解析器。
		// 如果是字符串类型，他就直接响应，是对象（对象不只是我们的pojo，还包括map之类的）才转成json
		try {
			// 1.创建上传服务器对象，加载配置文件
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
			// 2.得到文件的扩展名
			String originalFilename = uploadFile.getOriginalFilename();// 2.1先得到图片的名字
			String substring = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);// 2.2得到文件扩展名
			// 3.得到上传到服务器的url
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), substring);
			// 4.由于返回的url没有服务器的ip地址，我们还需要加载ip地址，这个配置文件我们在springmvc中已经加载了。通过value注解在成员变量中已经注入了，直接使用即可
			url = IMAGE_SERVER_URL + url;
			// 5.返回按照要求的JESON格式。他的要求是上传成功格式如下，不成功如异常里面的
			Map map = new HashMap();
			map.put("error", 0);
			map.put("url", url);
			// return
			// map;直接响应map，responesbody会把map转成json，Content-Type:application/json;是json类型
			// 但是有些json类型不同浏览器不兼容，所以我们改成string，文本格式
			return JsonUtils.objectToJson(map);// 返回字符串是Content-Type:text/plan;这个类型，兼容性好
		} catch (Exception e) {
			e.printStackTrace();
			Map map = new HashMap();
			map.put("error", 1);
			map.put("message", "上传失败");
			return JsonUtils.objectToJson(map);
		}

	}

}
