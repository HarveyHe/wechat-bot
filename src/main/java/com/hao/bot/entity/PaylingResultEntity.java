package com.hao.bot.entity;

import java.util.Map;

public class PaylingResultEntity {
	
		private Map<Integer,PaylingRecordEntity> records;
		
		private String  referencePeriods; 
		private String  referenceValue; 

		public Map<Integer, PaylingRecordEntity> getRecords() {
			return records;
		}

		public void setRecords(Map<Integer, PaylingRecordEntity> records) {
			this.records = records;
		}

		public String getReferencePeriods() {
			return referencePeriods;
		}

		public void setReferencePeriods(String referencePeriods) {
			this.referencePeriods = referencePeriods;
		}

		public String getReferenceValue() {
			return referenceValue;
		}

		public void setReferenceValue(String referenceValue) {
			this.referenceValue = referenceValue;
		}

		
		
		
		
		
	
	   
}
