package com.pyg.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @author huangzhenhui
 * @date 2019-01-04 17:38
 */
public class PageResult implements Serializable {

	//mybatis分页插件 pagehlper插件
	//实现方式:
	//sql语句
	//1.limit
	//2.count
	//分页插件:只要配置插件sqlMapConfig
	//导入插件jar文件
	//总记录数
	private Long total;
	//总记录
	private List<?> rows;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public PageResult(Long total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
}
