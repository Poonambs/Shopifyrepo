package com.poonam.model;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.poonam.entity.Category;
import com.poonam.entity.Supplier;

public class ProductModel {
	@Id
	private long productId;

	 @Pattern(  regexp ="[A-Za-z ]+", message="invalid name" )
    private String productName;
    private SupplierModel supplier;
    private CategoryModel category;
    @Min(value=1, message= "quantity sholud be 1 or greater than 1")
    private int productQty;
    @Min(value=1, message= "price sholud be 1 or greater than 1")
    private double productPrice;
    private String mfgDate;
    private String expDate;
    @Min(value=1, message= "Id sholud be 1 or greater than 1")
    private int deliveryCharges;
	public ProductModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductModel(long productId, @Pattern(regexp = "[A-Za-z ]+", message = "invalid name") String productName,
			SupplierModel supplier, CategoryModel category,
			@Min(value = 1, message = "quantity sholud be 1 or greater than 1") int productQty,
			@Min(value = 1, message = "price sholud be 1 or greater than 1") double productPrice, String mfgDate,
			String expDate, @Min(value = 1, message = "Id sholud be 1 or greater than 1") int deliveryCharges) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.supplier = supplier;
		this.category = category;
		this.productQty = productQty;
		this.productPrice = productPrice;
		this.mfgDate = mfgDate;
		this.expDate = expDate;
		this.deliveryCharges = deliveryCharges;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public SupplierModel getSupplier() {
		return supplier;
	}
	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}
	public CategoryModel getCategory() {
		return category;
	}
	public void setCategory(CategoryModel category) {
		this.category = category;
	}
	public int getProductQty() {
		return productQty;
	}
	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getMfgDate() {
		return mfgDate;
	}
	public void setMfgDate(String mfgDate) {
		this.mfgDate = mfgDate;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public int getDeliveryCharges() {
		return deliveryCharges;
	}
	public void setDeliveryCharges(int deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}
	@Override
	public String toString() {
		return "ProductModel [productId=" + productId + ", productName=" + productName + ", supplier=" + supplier
				+ ", category=" + category + ", productQty=" + productQty + ", productPrice=" + productPrice
				+ ", mfgDate=" + mfgDate + ", expDate=" + expDate + ", deliveryCharges=" + deliveryCharges + "]";
	}

    // Constructors, getters, and setters
    // Constructor
   
    

}