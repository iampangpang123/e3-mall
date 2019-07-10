package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;

/** 
* @ClassName: ContentCategoryService 
* @Description: TODO(内容分类的接口) 
* @author 唐 
* @date 2018年12月29日 下午3:09:39  
*/
public interface ContentCategoryService {

	/**  
	* @Description: TODO(查询内容分类列表) 
	* @return List<EasyUITreeNode>    返回类型 
	*/
	List<EasyUITreeNode> getContentCatList(long parentId);
	
	/**  
	* @Description: TODO(内容分类添加的service方法) 
	* @return E3Result    返回类型 
	*/
	public E3Result addContentCategory(long parentId, String name);
}
