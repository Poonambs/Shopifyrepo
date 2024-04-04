package com.poonam.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class CategoryModel {
	@Id
	 @Min(value=1, message= "Id sholud be 1 or greater than 1")
	private long categoryId;
	 
	@Pattern(  regexp ="[A-Za-z ]+", message="invalid name" )
     private String categoryName;
	
	@Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Invalid address format")
	private String description;
	
	private int discount;
	
	
	private int gst;

	public CategoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryModel(@Min(value = 1, message = "Id sholud be 1 or greater than 1") long categoryId,
			@Pattern(regexp = "[A-Za-z ]+", message = "invalid name") String categoryName,
			@Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Invalid address format") String description,
			@Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "invalid ") int discount,
			@Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "invalid gst") int gst) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.description = description;
		this.discount = discount;
		this.gst = gst;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getGst() {
		return gst;
	}

	public void setGst(int gst) {
		this.gst = gst;
	}

	@Override
	public String toString() {
		return "CategoryModel [categoryId=" + categoryId + ", categoryName=" + categoryName + ", description="
				+ description + ", discount=" + discount + ", gst=" + gst + "]";
	}
	
	

}
