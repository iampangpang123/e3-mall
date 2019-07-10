package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbItem;

public interface ContentService {

	/**  
	* @Description: TODO(接口：新增一个content内容，比如在前台展示页面，增加一个信息。是这个方法的作用) 
	* @return E3Result    返回类型 
	*/
	E3Result addContent(TbContent tbContent);
	/**  
	* @Description: TODO(interface：轮播图实现) 
	* @return List<TbContent>    返回类型 
	*/
	List<TbContent> getContentListById(long cid);
}
