package com.gtmap.wechat.support.entity;

/** 
 * �����ƣ�OttType
 * ��������OTT����Դʵ����
 * �����ˣ�byj
 * ����ʱ�䣺2014-12-23
 * �޸��ˣ�
 * �޸�ʱ�䣺
 * �޸ı�ע��
 * @version 1.0 
 */
public class OttType {
	private String typeCode;
	private String typeName;
	
	public OttType(String typeCode, String typeName) {
		super();
		this.typeCode = typeCode;
		this.typeName = typeName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
