package tw.com.pubu.hunter.bean;

import tw.idv.hunter.tool.HunterDebug;

public class TestEntity {

	private String fieldName1 = "fieldName1";
	private String fieldName2 = "fieldName2";
	
	public TestEntity() {
		super();
		HunterDebug.traceMessage();
	}

	public String getFieldName1() {
		HunterDebug.traceMessage();
		return fieldName1;
	}

	public void setFieldName1(String fieldName1) {
		HunterDebug.traceMessage();
		this.fieldName1 = fieldName1;
	}

	public String getFieldName2() {
		HunterDebug.traceMessage();
		return fieldName2;
	}

	public void setFieldName2(String fieldName2) {
		HunterDebug.traceMessage();
		this.fieldName2 = fieldName2;
	}
	

}
