package com.pyg.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.manager.service.BrandService;
import com.pyg.pojo.TbBrand;
import com.pyg.utils.PageResult;
import com.pyg.utils.PygResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangzhenhui
 * @date 2019-01-03 23:57
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
	
	//注入远程服务对象
	@Reference(timeout=10000000,retries=2)
	private BrandService brandService;

	/**
	 * 需求:查询所有的品牌数据
	 */
	
	@RequestMapping("/findAll")
	public List<TbBrand> findAll(){
		//调用远程服务对象方法
		List<TbBrand> list = brandService.findAll();
		return list;
	}
	
	/**
	 * 	需求:分页查询 
	 * 	参数:Integer Page,Integer rows
	 * 	返回值:分页包装类对象
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(Integer page,Integer rows) {
		//	调用远程服务
		PageResult result = brandService.findPage(page, rows);
		return result;
	}
	
	/**
	 * 	需求:添加品牌
	 * 	参数:TbBrand brand
	 * 	返回值:PygResult
	 */
	@RequestMapping("/add")
	//AngularJS前台传递来的是json对象,要@RequestBody接收
	public PygResult add(@RequestBody TbBrand brand) {
		//	调用远程服务
		PygResult pygResult = brandService.add(brand);
		return pygResult;
	}
	
	/**
	 * 	需求:根据id查询品牌数据
	 * 	参数:Long id
	 * 	返回值:TbBrand
	 */
	@RequestMapping("/findOne")
	public TbBrand findOne(Long id) {
		// 远程调用服务
		return brandService.findOne(id);
	}
	
	/**
	 * 	需求:根据传来的对象更新品牌数据
	 * 	参数:TbBrand brand
	 * 	返回值:PygResult
	 */
	@RequestMapping("/update")
	public PygResult update(@RequestBody TbBrand brand) {
		// 远程调用服务
		return brandService.update(brand);
	}
	
	/**
	 *	需求:品牌条件查询
	 *	参数:TbBrand brand
	 *	返回值:pageResult
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbBrand brand,Integer page,Integer rows) {
		// 远程调用服务
		return brandService.findBrandByPage(brand, page, rows);
	}
	
	/**
	 * 	需求:根据id删除品牌
	 * 	请求:dele?ids=??
	 * 	参数:Long[] ids
	 *	返回值:PygResult
	 */
	@RequestMapping("/dele")
	public PygResult dele(Long[] ids) {
		//	远程调用服务
		return brandService.deleteById(ids);
	}
	
}
