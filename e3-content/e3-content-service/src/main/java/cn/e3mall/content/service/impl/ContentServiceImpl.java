package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;

/**
 * @ClassName: ContentServiceImpl
 * @Description: TODO(内容管理的service)
 * @author 唐
 * @date 2018年12月29日 下午5:51:16
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;

	/*
	 * (non-Javadoc)
	 * 
	 * @Description: TODO(新增内容的实现类，比如在前台展示页面，增加一个信息。是这个方法的作用)
	 * 
	 * @see
	 * cn.e3mall.content.service.ContentService#addContent(cn.e3mall.pojo.TbContent)
	 */
	@Override
	public E3Result addContent(TbContent tbContent) {
		// 补全属性
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		// 插入数据
		contentMapper.insert(tbContent);
		//缓存同步
		jedisClient.hdel(CONTENT_LIST, tbContent.getCategoryId().toString());

		return E3Result.ok();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @Description: TODO(轮播图：根据内容分类id查询内容列表,把内容展示到首页.然后数据放到redis缓存中，解决并发访问对数据库的压力)
	 * 
	 * @see cn.e3mall.content.service.ContentService#getContentListById(long)
	 */
	@Override
	public List<TbContent> getContentListById(long cid) {
		// 查询缓存
		try {
			String json = jedisClient.hget(CONTENT_LIST, cid + "");
			// 判断json是否为空，如果不是空的，说明缓存中存在，把缓存中数据取出来，但是他是string类型的，要转成jeson
			if (StringUtils.isNotBlank(json)) {
				// 把json转换成list
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbContentExample contentExample = new TbContentExample();
		Criteria criteria = contentExample.createCriteria();
		criteria.andCategoryIdEqualTo(cid);

		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(contentExample);
		// 向缓存中添加数据
		try {
			// 后面两个形参都要转成String类型的
			jedisClient.hset(CONTENT_LIST, cid + "", JsonUtils.objectToJson(list));// 吧list变成jeson串
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
