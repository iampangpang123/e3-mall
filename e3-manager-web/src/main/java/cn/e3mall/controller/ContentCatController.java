package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
/**
 * 
* @ClassName: ContentCatController 
* @Description: TODO(内容分类的controller) 
* @author 唐 
* @date 2018年12月29日 下午3:24:03
 */
/**  
* @Description: TODO() 
* @return void    返回类型 
*/
@Controller
public class ContentCatController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	/**  
	* @Description: TODO(查询内容分类) 
	* @return List<EasyUITreeNode>    返回类型 
	*/
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(name="id",defaultValue="0")Long parentId){
		List<EasyUITreeNode> contentCatList = contentCategoryService.getContentCatList(parentId);
		
		return contentCatList;
		
	}

	/**  
	* @Description: TODO(添加内容费分类的controller) 
	* @return E3Result    返回类型 
	*/
	@RequestMapping(value="/content/category/create",method=RequestMethod.POST)
	@ResponseBody
	public E3Result createCategory(Long parentId, String name) {
		E3Result result = contentCategoryService.addContentCategory(parentId, name);
		return result;
	}

}
