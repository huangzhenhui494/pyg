package com.pyg.vo;

import com.pyg.pojo.TbSpecification;
import com.pyg.pojo.TbSpecificationOption;

import java.io.Serializable;
import java.util.List;

/**
 * @author huangzhenhui
 * @date 2019-01-06 15:12
 */
public class Specification implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6261430432713810611L;
	
	public Specification() {
		super();
	}

	public Specification(TbSpecification tbSpecification, List<TbSpecificationOption> specificationList) {
		super();
		this.tbSpecification = tbSpecification;
		this.specificationList = specificationList;
	}

	//包装规格对象
	private TbSpecification tbSpecification;
	
	//包装规格列表
	private List<TbSpecificationOption> specificationList;

	public TbSpecification getTbSpecification() {
		return tbSpecification;
	}

	public void setTbSpecification(TbSpecification tbSpecification) {
		this.tbSpecification = tbSpecification;
	}

	public List<TbSpecificationOption> getSpecificationList() {
		return specificationList;
	}

	public void setSpecificationList(List<TbSpecificationOption> specificationList) {
		this.specificationList = specificationList;
	}
	
	

}
