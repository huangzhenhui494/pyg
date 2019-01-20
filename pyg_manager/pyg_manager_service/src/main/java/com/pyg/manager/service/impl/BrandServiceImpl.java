package com.pyg.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pyg.manager.service.BrandService;
import com.pyg.mapper.TbBrandMapper;
import com.pyg.pojo.TbBrand;
import com.pyg.pojo.TbBrandExample;
import com.pyg.pojo.TbBrandExample.Criteria;
import com.pyg.utils.PageResult;
import com.pyg.utils.PygResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author huangzhenhui
 * @date 2019-01-04 17:39
 */
@Service
public class BrandServiceImpl implements BrandService {

	//注入mapper接口代理对象
	@Autowired
	private TbBrandMapper brandMapper;
	
	/**
	 * 	需求:查询所有品牌数据
	 */
	@Override
	public List<TbBrand> findAll() {
		return brandMapper.selectByExample(new TbBrandExample());
	}

	/**
	 * 	需求:分页查询 
	 * 	参数:Integer Page,Integer rows
	 * 	返回值:分页包装类对象
	 */
	@Override
	public PageResult findPage(Integer page, Integer rows) {
		//	创建example对象
		TbBrandExample example = new TbBrandExample();
		//	使用插件设置分页,在查询之前设置
		PageHelper.startPage(page, rows);
		//	查询品牌数据,这样的话已经分页了,会被拦截器拦截设置分页
		//List={page[total,pageSize],list}
		List<TbBrand> list = brandMapper.selectByExample(example);
		//	创建pageInfo对象,获取查询的分页数据
		PageInfo<TbBrand> pageInfo = new PageInfo<TbBrand>(list);
		
		return new PageResult(pageInfo.getTotal(), list);
	}

	/**
	 * 	需求:添加品牌
	 * 	参数:TbBrand brand
	 * 	返回值:PygResult
	 */
	@Override
	public PygResult add(TbBrand brand) {
		//保存品牌数据
		try {
			brandMapper.insertSelective(brand);
			//保存成功
			return new PygResult(true,"保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			//保存失败
			return new PygResult(false, "保存失败");
		}
	}

	
	/**
	 * 	需求:根据id查询品牌数据
	 * 	参数:Long id
	 * 	返回值:TbBrand
	 */
	@Override
	public TbBrand findOne(Long id) {
		// 根据id查询品牌数据
		return brandMapper.selectByPrimaryKey(id);
	}

	/**
	 * 	需求:根据传来的对象更新品牌数据
	 * 	参数:TbBrand brand
	 * 	返回值:PygResult
	 */
	@Override
	public PygResult update(TbBrand brand) {
		// 更新品牌数据,根据主键修改,选择性修改
		try {
			brandMapper.updateByPrimaryKeySelective(brand);
			//修改成功
			return new PygResult(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new PygResult(false, "修改失败");
		}
	}

	/**
	 *	需求:品牌条件查询
	 *	参数:TbBrand brand
	 *	返回值:pageResult
	 */
	@Override
	public PageResult findBrandByPage(TbBrand brand, Integer page, Integer rows) {
		//	创建example对象
		TbBrandExample example = new TbBrandExample();
		//	创建criteria对象
		Criteria createCriteria = example.createCriteria();
		//	设置参数
		//	判断参数是否存在
		if(brand.getName() != null && !"".equals(brand.getName())){
			//模糊查询
			createCriteria.andNameLike("%"+brand.getName()+"%");
		}
		if(brand.getFirstChar() != null && !"".equals(brand.getFirstChar())) {
			createCriteria.andFirstCharEqualTo(brand.getFirstChar());
		}
		
		//	查询之前,必须设置分页
		PageHelper.startPage(page,rows);
		
		//	执行查询
		List<TbBrand> list = brandMapper.selectByExample(example);
		PageInfo<TbBrand> pageInfo = new PageInfo<TbBrand>(list);
		//	创建PageInfo,获取分页数据
		//	返回分页的包装类对象
		return new PageResult(pageInfo.getTotal(), list);
	}

	/**
	 * 	需求:根据id删除品牌
	 * 	参数:Long[] ids
	 *	返回值:PygResult
	 */
	@Override
	public PygResult deleteById(Long[] ids) {
		try {
			//	循环需要删除的id
			for (Long id : ids) {
				//	单独删除一个对象	
				brandMapper.deleteByPrimaryKey(id);
			}
			return new PygResult(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new PygResult(false, "删除失败");
		}
	}
	

}
