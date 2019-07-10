package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.search.service.SearchItemService;

/**
 * @ClassName: SearchItemController
 * @Description: TODO(导入商品数据到索引库)
 * @author 唐
 * @date 2019年1月14日 下午1:35:12
 */
@Controller
public class SearchItemController {
	@Autowired
	private SearchItemService searchItemService;

	@RequestMapping("/index/item/import")
	@ResponseBody
	public E3Result importItemList() {
		E3Result importAllItems = searchItemService.importAllItems();
		return importAllItems;
	}

}
