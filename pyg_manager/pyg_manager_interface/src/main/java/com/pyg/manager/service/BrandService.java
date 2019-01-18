package com.pyg.manager.service;

import com.pyg.pojo.TbBrand;
import com.pyg.utils.PageResult;
import com.pyg.utils.PygResult;

import java.util.List;

/**
 * @author huangzhenhui
 * @date 2019-01-04 16:47
 */
public interface BrandService {

	/**
	 * 	需求:查询所有品牌数据
	 */
	public List<TbBrand> findAll();
	
	/**
	 * 	需求:分页查询 
	 * 	参数:Integer Page,Integer rows
	 * 	返回值:分页包装类对象
	 */
	public PageResult findPage(Integer page, Integer rows);
	
	/**
	 * 	需求:添加品牌
	 * 	参数:TbBrand brand
	 * 	返回值:PygResult
	 */
	public PygResult add(TbBrand brand);
	
	/**
	 * 	需求:根据id查询品牌数据
	 * 	参数:Long id
	 * 	返回值:TbBrand
	 */
	public TbBrand findOne(Long id);
	
	/**
	 * 	需求:根据传来的对象更新品牌数据
	 * 	参数:TbBrand brand
	 * 	返回值:PygResult
	 */
	public PygResult update(TbBrand brand);
	
	/**
	 *	需求:品牌条件查询
	 *	参数:TbBrand brand
	 *	返回值:pageResult
	 */
	public PageResult findBrandByPage(TbBrand brand, Integer page, Integer rows);
	
	/**
	 * 	需求:根据id删除品牌
	 * 	参数:Long[] ids
	 *	返回值:PygResult
	 */
	public PygResult deleteById(Long[] id);
}
