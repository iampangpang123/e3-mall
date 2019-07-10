package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

/**
 * @ClassName: ContentController
 * @Description: TODO(内容管理的controller)
 * @author 唐
 * @date 2018年12月29日 下午5:56:13
 */
@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;

	/**
	 * @Description: TODO(新增内容的controller)
	 * @return E3Result 返回类型
	 */
	@RequestMapping(value="/content/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addContent(TbContent content) {
		E3Result result = contentService.addContent(content);
		return result;
	}

}
