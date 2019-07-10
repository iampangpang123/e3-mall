package cn.e3mall.search.service;

import cn.e3mall.common.pojo.SearchResult;

/** 
* @ClassName: SearchService 
* @Description: 商品搜索的service
* @author 唐 
* @date 2019年1月15日 下午4:37:46  
*/
public interface SearchService {

	SearchResult search (String keyWord, int page,int rows) throws Exception;//分别是搜索条件，当前页，每页纪录数

}
