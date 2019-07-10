package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemDesc;

public interface ItemService {

	/**
	 **   
	 * @Description:通过id得到商品
	 * @param: @param itemId
	 * @param: @return      
	 * @return: TbItem      
	 */
	TbItem getItemById(long itemId);
	/**
	 **   
	 * @Description:
	 * @param: @param page
	 * @param: @param rows
	 * @param: @return      
	 * @return: EasyUIDataGridResult      
	 */
	EasyUIDataGridResult getItemList(int page, int rows);
	/**  
	* @Description: TODO(商品添加) 
	* @return E3Result    返回类型 
	*/
	E3Result addItem(TbItem tbItem,String desc);
	/**
	 **   
	 * @Description:通过id得到商品描述
	 * @param: @param itemId
	 * @param: @return      
	 * @return: TbItemDesc      
	 */
	TbItemDesc getItemDescById(long itemId);

}

