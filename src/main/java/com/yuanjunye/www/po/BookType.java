package com.yuanjunye.www.po;

/**
 * 图书类型模型
 * @author hasee
 *
 */
public class BookType {

	private int typeId;
	private String typeName;
	
	/**
	 * 无参构造
	 */
	public BookType() {
		super();
	}


	public int getTypeId() {
		return typeId;
	}


	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
