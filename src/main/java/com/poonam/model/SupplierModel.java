package com.poonam.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class SupplierModel {

	
	 @Min(value=1, message= "Id sholud be 1 or greater than 1")
		private long supplierId;

	 @Pattern(  regexp ="[A-Za-z ]+", message="invalid name" )
		private String supplierName;
		

	 @Pattern(  regexp ="[A-Za-z ]+", message="invalid city" )
		private String city;
		
	@Min(value=100000,message="invalid code") 
	@Max(value=999999,message="invalid code") 
		private int postalCode;
		
	 @Pattern(  regexp ="[A-Za-z ]+", message="invalid name" )
		private String country;
		
	 @Pattern(  regexp ="[1-9]\\d{9}", message="invalid code" ) 
		private String mobileNumber;

		public SupplierModel() {
			// TODO Auto-generated constructor stub
		}

		public SupplierModel(long supplierId, String supplierName, String city, int postalCode, String country,
				String mobileNumber) {
			super();
			this.supplierId = supplierId;
			this.supplierName = supplierName;
			this.city = city;
			this.postalCode = postalCode;
			this.country = country;
			this.mobileNumber = mobileNumber;
		}

		public long getSupplierId() {
			return supplierId;
		}

		public void setSupplierId(long supplierId) {
			this.supplierId = supplierId;
		}

		public String getSupplierName() {
			return supplierName;
		}

		public void setSupplierName(String supplierName) {
			this.supplierName = supplierName;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public int getPostalCode() {
			return postalCode;
		}

		public void setPostalCode(int postalCode) {
			this.postalCode = postalCode;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		@Override
		public String toString() {
			return "SupplierModel[supplierId=" + supplierId + ", supplierName=" + supplierName + ", city=" + city
					+ ", postalCode=" + postalCode + ", country=" + country + ", mobileNumber=" + mobileNumber + "]";
		}

	}


