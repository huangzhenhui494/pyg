package com.pyg.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pyg.content.service.ContentService;
import com.pyg.mapper.TbContentMapper;
import com.pyg.pojo.TbContent;
import com.pyg.pojo.TbContentExample;
import com.pyg.pojo.TbContentExample.Criteria;
import com.pyg.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	/**
	 * 查询全部
	 */
	@Override
	public List<TbContent> findAll() {
		return contentMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<TbContent> page=   (Page<TbContent>) contentMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbContent content) {
	    // 清空缓存
        redisTemplate.boundHashOps("index_cache").delete(content.getCategoryId()+"");
		contentMapper.insert(content);
	}


	/**
	 * 修改
     * 1.分类id可能发生了变化
     * 2.id没法发生变化
	 */
	@Override
	public void update(TbContent content){
	    // 如果修改的分类id发生了变化,根据当前id查询广告对象
        TbContent tbContent = contentMapper.selectByPrimaryKey(content.getId());
        // 清空缓存(原来的缓存)
        redisTemplate.boundHashOps("index_cache").delete(tbContent.getCategoryId()+"");
		contentMapper.updateByPrimaryKey(content);
	}

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbContent findOne(Long id){
		return contentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
		    // 查询广告内容对象
            TbContent content = contentMapper.selectByPrimaryKey(id);
            // 清空缓存
            redisTemplate.boundHashOps("index_cache").delete(content.getCategoryId()+"");
            contentMapper.deleteByPrimaryKey(id);
		}
	}


		@Override
	public PageResult findPage(TbContent content, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		TbContentExample example=new TbContentExample();
		Criteria criteria = example.createCriteria();

		if(content!=null){
						if(content.getTitle()!=null && content.getTitle().length()>0){
				criteria.andTitleLike("%"+content.getTitle()+"%");
			}
			if(content.getUrl()!=null && content.getUrl().length()>0){
				criteria.andUrlLike("%"+content.getUrl()+"%");
			}
			if(content.getPic()!=null && content.getPic().length()>0){
				criteria.andPicLike("%"+content.getPic()+"%");
			}
			if(content.getContent()!=null && content.getContent().length()>0){
				criteria.andContentLike("%"+content.getContent()+"%");
			}
			if(content.getStatus()!=null && content.getStatus().length()>0){
				criteria.andStatusLike("%"+content.getStatus()+"%");
			}

		}

		Page<TbContent> page= (Page<TbContent>)contentMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}


	// 注入redis模板对象
	@Autowired
	private RedisTemplate redisTemplate;

    /**
     * 需求:根据分类id查询不同区域的广告内容信息
     * 参数:Long categoryId
     * 业务思想:
     * 页面广告被分为不同的类型,不同类型的广告通过不同的分类id进行加载
     * 大广告:1
     * 今日推荐:2
     * 返回值:List<TbContent>
     * 查询业务:
     * 1.查询有效的广告 status 为 1
     * 2.广告排序问题
	 * 广告数据的加载添加缓存
	 * 添加缓存原因:
	 * 1.门户系统并发压力较高,频繁读取数据库的广告数据,
	 * 为了减轻数据库压力,添加缓存
	 * 缓存服务器:redis服务器
	 * 数据结构:hash数据结构
	 * key:不同导航页的缓存:index_cache  food_cache
	 * field:categoryId(页面不同区域缓存)
	 * value:缓存数据值
	 * 缓存业务流程:
	 * 1.先查询redis服务器
	 * 2.如果缓存中没有数据,查询数据库并同时把查询的数据放入缓存
	 * 3,如果缓存中有数据,直接返回即可,不再查询数据库
     */
	@Override
	public List<TbContent> findContentListByCategoryId(Long categoryId) {

		try {
			// 先查询缓存
			List<TbContent> adList = (List<TbContent>) redisTemplate.boundHashOps("index_cache").get(categoryId+"");
			// 判断缓存数据是否存在
			if(adList!=null && adList.size()>0){
				// 存在
				return adList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 创建广告内容表的example对象
		TbContentExample tbContentExample = new TbContentExample();
		// 创建criteria
		Criteria criteria = tbContentExample.createCriteria();
		// 设置查询参数
        // 外键
		criteria.andCategoryIdEqualTo(categoryId);
		// 查询有效广告
        criteria.andStatusEqualTo("1");
        // 设置排序
        tbContentExample.setOrderByClause("sort_order");
		// 调用mapper
		List<TbContent> list = contentMapper.selectByExample(tbContentExample);

		// 添加缓存数据
		redisTemplate.boundHashOps("index_cache").put(categoryId+"",list);

		return list;
	}

}
