package com.poonam.entity;


import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "product")
public class Product {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long productId;

	    @NotBlank
	    @Column(nullable = false, unique = true)
	    private String productName;
	    @NotNull
	    @OneToOne
	    @JoinColumn(name = "supplier_id")
	    private Supplier supplier;
	    @NotNull
	    @OneToOne
	    @JoinColumn(name = "category_id")
	    private Category category;

	    @NotNull
	    @Column(nullable = false, unique = false)
	    private int productQty;

	    @NotNull
	    @Column(nullable = false, unique = false)
	    private double productPrice;

	    @NotNull
	    @Column(nullable = false)
	    private String mfgDate;

	    @NotNull
	    @Column(nullable = false)
	    private String expDate;

	    @NotNull
	    @Column(nullable = false)
	    private int deliveryCharges;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}


	
	public Product(long productId, String productName, Supplier supplier, Category category,
			int productQty, double productPrice, String mfgDate, String expDate, int deliveryCharges) {
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



	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public int getDeliveryCharges() {
		return deliveryCharges;
	}


	public void setDeliveryCharges(int deliveryCharges) {
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


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productQty=" + productQty
				+ ", productPrice=" + productPrice + ", mfgDate=" + mfgDate + ", expDate=" + expDate + "]";
	}
	
	
	

}
