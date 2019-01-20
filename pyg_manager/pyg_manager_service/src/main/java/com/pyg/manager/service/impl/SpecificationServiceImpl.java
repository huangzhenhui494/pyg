package com.pyg.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pyg.manager.service.SpecificationService;
import com.pyg.mapper.TbSpecificationMapper;
import com.pyg.mapper.TbSpecificationOptionMapper;
import com.pyg.pojo.TbSpecification;
import com.pyg.pojo.TbSpecificationExample;
import com.pyg.pojo.TbSpecificationOption;
import com.pyg.pojo.TbSpecificationOptionExample;
import com.pyg.pojo.TbSpecificationOptionExample.Criteria;
import com.pyg.utils.PageResult;
import com.pyg.vo.Specification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 * 
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;

	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;

	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		// 保存规格
		// 获取规格对象
		TbSpecification tbSpecification = specification.getTbSpecification();
		// 添加,返回主键值,保存规格选项
		specificationMapper.insert(tbSpecification);
		// 获取规格选项集合,实现保存
		List<TbSpecificationOption> specificationList = specification.getSpecificationList();
		for (TbSpecificationOption tbSpecificationOption : specificationList) {
			// 设置外键
			tbSpecificationOption.setSpecId(tbSpecification.getId());
			// 保存
			specificationOptionMapper.insert(tbSpecificationOption);
		}
	}

	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification) {
		//	更新规格
		//	获取tbSpecification对象
		TbSpecification tbSpecification = specification.getTbSpecification();
		specificationMapper.updateByPrimaryKey(tbSpecification);
		//	更新规格选项
		//	获取规格选项
		List<TbSpecificationOption> list = specification.getSpecificationList();
		
		//	根据外键,
		//	获取example对象
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		//	创建Criteria对象
		Criteria criteria = example.createCriteria();
		//	设置查询参数
		criteria.andSpecIdEqualTo(tbSpecification.getId());
		
		//	执行删除操作
		specificationOptionMapper.deleteByExample(example);
		
		//	循环规格选项集合
		for (TbSpecificationOption tbSpecificationOption : list) {
			//	设置外键
			tbSpecificationOption.setSpecId(tbSpecification.getId());
			//	再插入规格数据
			specificationOptionMapper.insertSelective(tbSpecificationOption);
			
		}
		
	}

	/**
	 * 根据ID获取实体
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id) {
		// 	查询规格
		TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
		
		// 	查询规格选项
		//	获取example对象
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		//	创建Criteria对象
		Criteria criteria = example.createCriteria();
		//	设置查询参数
		criteria.andSpecIdEqualTo(id);
		List<TbSpecificationOption> tbSpecificationOptionList = specificationOptionMapper.selectByExample(example);
		// 封装Specification返回
		return new Specification(tbSpecification, tbSpecificationOptionList);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			//	先删除规格选项
			//	获取example对象
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			//	创建Criteria对象
			Criteria criteria = example.createCriteria();
			//	设置查询参数
			criteria.andSpecIdEqualTo(id);
			specificationOptionMapper.deleteByExample(example);
			//	再删除规格选项
			specificationMapper.deleteByPrimaryKey(id); 
		}
	}

	@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		TbSpecificationExample example = new TbSpecificationExample();
		TbSpecificationExample.Criteria criteria = example.createCriteria();

		if (specification != null) {
			if (specification.getSpecName() != null && specification.getSpecName().length() > 0) {
				criteria.andSpecNameLike("%" + specification.getSpecName() + "%");
			}

		}

		Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 需求:查询规格属性值,加载下拉列表
	 * 参数:无
	 * 返回值:List<Map>
	 * 方法:findSpecOptionList();
	 */
	@Override
	public List<Map> findSpecOptionList() {
		return specificationMapper.findSpecOptionList();
	}

}
