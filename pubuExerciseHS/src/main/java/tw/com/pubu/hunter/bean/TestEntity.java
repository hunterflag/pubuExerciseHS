package tw.com.pubu.hunter.bean;

import tw.idv.hunter.tool.HunterDebug;

public class TestEntity {

	private String fieldName1 = "entityDefaultValue1";
	private String fieldName2 = "entityDefaultValue2";
	
	public TestEntity() {
		HunterDebug.traceMessage();
	}

	public TestEntity(String fieldName1) {
		HunterDebug.traceMessage();
		this.fieldName1 =fieldName1; 
	}

	public TestEntity(String fieldName1, String fieldName2) {
		HunterDebug.traceMessage();
		this.fieldName1 = fieldName1; 
		this.fieldName2 = fieldName2; 
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
