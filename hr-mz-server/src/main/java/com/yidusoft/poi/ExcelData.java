package com.yidusoft.poi;

import java.io.Serializable;
import java.util.List;

/**
 * User: libf
 * Time: 2018-11-26 15:44
 */
public class ExcelData implements Serializable {
	private static final long serialVersionUID = 4444017239100620999L;

	// 表头
	private List<String> titles;

	// 数据
	private List<List<Object>> rows;

	// 页签名称
	private String name;

	//单元格类型:文本，日期，双精度浮点数，浮点数，整数
	private List<String> cellType;

	public List<String> getTitles() {
		return titles;
	}

	public void setTitles(List<String> titles) {
		this.titles = titles;
	}

	public List<List<Object>> getRows() {
		return rows;
	}

	public void setRows(List<List<Object>> rows) {
		this.rows = rows;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getCellType() {
		return cellType;
	}

	public void setCellType(List<String> cellType) {
		this.cellType = cellType;
	}
}
