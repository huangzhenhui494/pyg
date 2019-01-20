package com.pyg.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.manager.service.BrandService;
import com.pyg.manager.service.SpecificationService;
import com.pyg.manager.service.TypeTemplateService;
import com.pyg.pojo.TbTypeTemplate;
import com.pyg.utils.PageResult;
import com.pyg.utils.PygResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

	@Reference(timeout = 10000000)
	private TypeTemplateService typeTemplateService;

	// 引入品牌服务
	@Reference(timeout = 10000000)
	private BrandService brandService;

	// 引入规格服务对象
	@Reference(timeout = 10000000)
	private SpecificationService specificationService;

	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbTypeTemplate> findAll(){
		return typeTemplateService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return typeTemplateService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param typeTemplate
	 * @return
	 */
	@RequestMapping("/add")
	public PygResult add(@RequestBody TbTypeTemplate typeTemplate){
		try {
			typeTemplateService.add(typeTemplate);
			return new PygResult(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new PygResult(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param typeTemplate
	 * @return
	 */
	@RequestMapping("/update")
	public PygResult update(@RequestBody TbTypeTemplate typeTemplate){
		try {
			typeTemplateService.update(typeTemplate);
			return new PygResult(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new PygResult(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbTypeTemplate findOne(Long id){
		return typeTemplateService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public PygResult delete(Long [] ids){
		try {
			typeTemplateService.delete(ids);
			return new PygResult(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new PygResult(false, "删除失败");
		}
	}

	/**
	 * 查询+分页
	 * @param typeTemplate 分类模板
	 * @param page	页数
	 * @param rows	条数
	 * @return	返回
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbTypeTemplate typeTemplate, int page, int rows  ){
		return typeTemplateService.findPage(typeTemplate, page, rows);		
	}

	/**
	 * 需求:查询规格属性值,加载下拉列表
	 * 参数:无
	 * 返回值:List<Map>
	 * 方法:findSpecOptionList();
	 */
	@RequestMapping("/findSpecOptionList")
	public List<Map> findSpecOptionList(){
		// 调用远程服务
		List<Map> list = specificationService.findSpecOptionList();
		return list;
	}

	/**
	 * 需求:查询模板中存储关键规格属性
	 * 请求:findSpecOptionListByTypeId
	 * 参数:模板id
	 * 返回值:
	 */
	@RequestMapping("/findSpecOptionListByTypeId")
	public List<Map> findSpecOptionListByTypeId(Long id){
		// 调用远程服务对象查询规格属性值
		List<Map> list = typeTemplateService.findSpecOptionListById(id);
		return list;
	}


}
