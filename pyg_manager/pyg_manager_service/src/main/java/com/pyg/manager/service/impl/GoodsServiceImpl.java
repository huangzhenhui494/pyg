package com.pyg.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pyg.manager.service.GoodsService;
import com.pyg.mapper.*;
import com.pyg.pojo.*;
import com.pyg.pojo.TbGoodsExample.Criteria;
import com.pyg.utils.PageResult;
import com.pyg.utils.PygResult;
import com.pyg.vo.Goods;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private TbGoodsMapper goodsMapper;

	// 注入货品描述mapper接口代理对象
	@Autowired
	private TbGoodsDescMapper tbGoodsDescMapper;

	// 注入品牌mapper接口代理对象
	@Autowired
	private TbBrandMapper tbBrandMapper;

	// 注入分类mapper接口代理对象
	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	// 注入商品mapper接口代理对象
	@Autowired
	private TbItemMapper tbItemMapper;

	// 注入商家mapper接口代理对象
	@Autowired
	private TbSellerMapper tbSellerMapper;

	/**
	 * 查询全部
	 */
	@Override
	public List<TbGoods> findAll() {
		return goodsMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<TbGoods> page=   (Page<TbGoods>) goodsMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public PygResult add(Goods goods) {
		try {
			// 保存货品表数据
			// 获取货品对象
			TbGoods tbGoods = goods.getGoods();
			// 保存货品,返回主键
			goodsMapper.insertSelective(tbGoods);
			// 再保存货品描述表
			// 获取货品描述对象
			TbGoodsDesc goodsDesc = goods.getGoodsDesc();
			// 设置外键
			goodsDesc.setGoodsId(tbGoods.getId());
			// 保存
			tbGoodsDescMapper.insertSelective(goodsDesc);

            // 通过货品获取品牌数据
            TbBrand tbBrand = tbBrandMapper.selectByPrimaryKey(tbGoods.getBrandId());

            // 获取品牌分类三级id
            TbItemCat tbItemCat = tbItemCatMapper.selectByPrimaryKey(tbGoods.getCategory3Id());

            // 获取卖家名称
            TbSeller tbSeller = tbSellerMapper.selectByPrimaryKey(tbGoods.getSellerId());

            // 从商品描述对象中获取图片数据
            String itemImages = goodsDesc.getItemImages();
            // 判断描述对象中图片是否存在
            String url = null;
            if(itemImages != null && !"".equals(itemImages)){
                List<Map> lists = JSON.parseArray(itemImages, Map.class);
                url = (String) lists.get(0).get("url");

            }

			// 判断是否启用了规格
			if("1".equals(tbGoods.getIsEnableSpec())){
				// 再保存商品Sku表
				// 获取商品sku商品列表对象
				List<TbItem> list = goods.getItemList();
				// 循环sku存储对象iter
				for (TbItem item : list) {
					// 定义一个商品名称
					String skuName = "";

					// 从商品表中获取规格属性选项
					String spec = item.getSpec();
					// 把规格属性转换成map对象
					Map<String,String> specMap = JSON.parseObject(spec, Map.class);
					// [spec:"网络","电信2G"]
					// 循环specmap对象
					for (String s : specMap.keySet()) {
						// 组合商品名称
						skuName += specMap.get(s);
					}
					// 设置相关属性值
					// 商品id
					item.setGoodsId(tbGoods.getId());
					// 组合商品名称
					item.setTitle(tbGoods.getGoodsName()+skuName);
					// 设置图片
					item.setImage(url);

					// 保存品牌数据
					item.setBrand(tbBrand.getName());

					// 保存分类名称
					item.setCategory(tbItemCat.getName());
					// 保存分类id
					item.setCategoryid(tbItemCat.getId());
					// 卖家id
					item.setSellerId(tbGoods.getSellerId());
					// 卖家名称
					// 设置卖家店铺名称
					item.setSeller(tbSeller.getNickName());
					// 设置时间
					Date date = new Date();
					item.setCreateTime(date);
					item.setUpdateTime(date);
					// 保存
					tbItemMapper.insertSelective(item);
				}
			}else{

			    // 没有启用规格
                // 创建商品对象,保存商品数据
                TbItem item = new TbItem();
                // 保存商品id
				item.setGoodsId(tbGoods.getId());
                // 设置商品标题
                item.setTitle(tbGoods.getGoodsName());
				// 保存品牌数据
				item.setBrand(tbBrand.getName());
				// 保存分类名称
                item.setCategory(tbItemCat.getName());
                // 保存分类id
                item.setCategoryid(tbItemCat.getId());
                // 卖家id
                item.setSellerId(tbGoods.getSellerId());
                // 卖家名称
                // 设置卖家店铺名称
                item.setSeller(tbSeller.getNickName());
                // 状态
                // 是否默认
                // 设置时间
                Date date = new Date();
                item.setCreateTime(date);
                item.setUpdateTime(date);

                // 保存
				tbItemMapper.insertSelective(item);
            }



			// 保存成功
			return new PygResult(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new PygResult(false, "保存失败");
		}
	}


	/**
	 * 修改
	 */
	@Override
	public void update(TbGoods goods){
		goodsMapper.updateByPrimaryKey(goods);
	}

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbGoods findOne(Long id){
		return goodsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			goodsMapper.deleteByPrimaryKey(id);
		}
	}


		@Override
	public PageResult findPage(TbGoods goods, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		TbGoodsExample example=new TbGoodsExample();
		Criteria criteria = example.createCriteria();

		if(goods!=null){
						if(goods.getSellerId()!=null && goods.getSellerId().length()>0){
				criteria.andSellerIdEqualTo(goods.getSellerId());
			}
			if(goods.getGoodsName()!=null && goods.getGoodsName().length()>0){
				criteria.andGoodsNameLike("%"+goods.getGoodsName()+"%");
			}
			if(goods.getAuditStatus()!=null && goods.getAuditStatus().length()>0){
				criteria.andAuditStatusLike("%"+goods.getAuditStatus()+"%");
			}
			if(goods.getIsMarketable()!=null && goods.getIsMarketable().length()>0){
				criteria.andIsMarketableLike("%"+goods.getIsMarketable()+"%");
			}
			if(goods.getCaption()!=null && goods.getCaption().length()>0){
				criteria.andCaptionLike("%"+goods.getCaption()+"%");
			}
			if(goods.getSmallPic()!=null && goods.getSmallPic().length()>0){
				criteria.andSmallPicLike("%"+goods.getSmallPic()+"%");
			}
			if(goods.getIsEnableSpec()!=null && goods.getIsEnableSpec().length()>0){
				criteria.andIsEnableSpecLike("%"+goods.getIsEnableSpec()+"%");
			}
			if(goods.getIsDelete()!=null && goods.getIsDelete().length()>0){
				criteria.andIsDeleteLike("%"+goods.getIsDelete()+"%");
			}

		}

		Page<TbGoods> page= (Page<TbGoods>)goodsMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

}
