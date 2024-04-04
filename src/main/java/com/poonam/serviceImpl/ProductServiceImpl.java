package com.poonam.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Provider.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService.Work;
import org.hibernate.internal.build.AllowSysOut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.poonam.dao.ProductDao;
import com.poonam.entity.Category;
import com.poonam.entity.Product;
import com.poonam.entity.Supplier;
import com.poonam.exception.NonNullableAssociationException;
import com.poonam.exception.ResourceAlreadyExistException;
import com.poonam.exception.ResourceNotExistsException;
import com.poonam.exception.SomethingWentWrongException;
import com.poonam.model.CategoryModel;
import com.poonam.model.ProductModel;
import com.poonam.model.SupplierModel;
import com.poonam.service.CategoryService;
import com.poonam.service.ProductService;
import com.poonam.service.SupplierService;

import net.bytebuddy.asm.Advice.Return;



@Component
public class ProductServiceImpl implements ProductService {



	@Autowired
	private ProductDao dao;
	@Autowired
	private SupplierService service;
	@Autowired
	private CategoryService service1;

	/*@Override
	public String addProduct(Product product) {

	    return dao.addProduct(product);
	}*/
	@Autowired
	private ModelMapper mm;

	@Override
	public int addProduct(ProductModel productmodel) {
		try {

			if (productmodel.getProductName() == null || productmodel.getProductName().isEmpty()) {
				throw new IllegalArgumentException("Product name cannot be null or empty.");
			}

			Product Mapping=mm.map(productmodel, Product.class);

			return dao.addProduct(Mapping);

		} catch (TransientPropertyValueException e) {
			throw new NonNullableAssociationException("Non-nullable association not properly set: " + e.getMessage());
		}

	}

	@Override
	public ProductModel getProductById(long productId) {
		Product product=dao.getProductById(productId);
		if(product!=null)
		{

			ProductModel productmodel=mm.map(product, ProductModel.class);

			return productmodel;
		}
		else
		{
			throw new ResourceNotExistsException("resource not exists with ID:" +productId);

		}

	}
	@Override
	public List<ProductModel> getAllProducts() {

		List<Product>Productlist=dao.getAllProducts();
		List<ProductModel>list= new ArrayList<>();
		if(!Productlist.isEmpty())
		{
			for (Product product : Productlist) {
				ProductModel productModel=mm.map(product, ProductModel.class);
				list.add(productModel);

			}}
		else {
			throw new ResourceNotExistsException("supplier not exists");
		}
		return list;
	}


	@Override
	public String deleteProduct(long productId) {

		return dao.deleteProduct(productId);

	}

	@Override
	public int updateProduct(ProductModel productModel) {

		Product Mapping=mm.map(productModel, Product.class);

		return dao.updateProduct(Mapping);

	}



	@Override
	public double getMaxPrice() {
		double maxPrice=dao.getMaxPrice();
		if(maxPrice>0)
		{

			return maxPrice;
		}

		else 
		{
			throw new ResourceNotExistsException("Product not found");
		}

	}



	@Override
	public List<String> getMaxProduct() {


		return dao.getMaxPriceProduct();


	}
	@Override
	public Product getProductByName(String productName) {
		Product productEntity = dao.getProductByName(productName);
		if (productEntity != null) {
			return productEntity;
		} else {
			throw new ResourceNotExistsException("Product not exists with name " + productName);
		}
	}

	@Override
	public List<Product> sortProducts(String orderType, String field) {
		return dao.sortProducts(orderType, field);
	}


	public String uploadSheet(MultipartFile file) {
		String filename = file.getOriginalFilename();
		int addedCount = 0;
		int rowNum = 1; // Initialize row number counter

		Map<Integer, Map<String, String>> badRecords = new HashMap<>(); // Initialize badRecords map

		try {
			byte[] data = file.getBytes();
			FileOutputStream fos = new FileOutputStream("src/main/resources" + File.separator + filename);
			fos.write(data);
			fos.close();
			
			
			int existingCount = dao.getAllProducts().size();
			List<ProductModel> productModelList = readExcel("src/main/resources" + File.separator + filename, badRecords);
			Set<Integer> existingRowNumbers = new HashSet<>();
			Set<String> existingProductNames = new HashSet<>();
			Set<String> duplicateProductNames = new HashSet<>();

			for (ProductModel productModel : productModelList) {
				String productName = productModel.getProductName();

				if (existingProductNames.contains(productName)) {
					duplicateProductNames.add(productName); 
					continue;
				}

				try {

					Product existingProduct = dao.getProductByName(productName);
					if (existingProduct != null) {
						existingProductNames.add(productName); 
						existingRowNumbers.add(rowNum); 
						rowNum++;
						continue;
					}
					addProduct(productModel);
					addedCount++;
					existingRowNumbers.add(rowNum); 
					rowNum++; 
					existingProductNames.add(productName);

				} catch (ResourceAlreadyExistException e) {


				} catch (Exception e) {

				}
			}

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("\"Uploaded records in db\": ").append(addedCount).append("\n");
			stringBuilder.append("\"Total exist record in db\": ").append(existingCount).append("\n");
			stringBuilder.append("\"Row num, exist record in db\": ").append(existingRowNumbers).append("\n");
			stringBuilder.append("\"Total record in sheet\": ").append(productModelList.size() + badRecords.size()).append("\n");
			stringBuilder.append("\"Total excluded duplicate records\": ").append(duplicateProductNames.size() ).append("\n");
			stringBuilder.append("\"Total Bad records\": ").append(badRecords.size() ).append("\n");
			stringBuilder.append("\"Bad record row number\": ").append("\n");
			for (Map.Entry<Integer, Map<String, String>> entry : badRecords.entrySet()) {
				stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
			}


			String summaryOutput = stringBuilder.toString();
			System.out.println(summaryOutput);

			return summaryOutput;

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong while processing the file.");
		}
	}




	public List<ProductModel> readExcel(String filelocation, Map<Integer, Map<String, String>> badRecords) throws InvalidFormatException, IOException {
		List<ProductModel> productModelList = new ArrayList<>();
		
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(filelocation);
			Workbook workbook = WorkbookFactory.create(fis);

			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rows = sheet.rowIterator();

			while (rows.hasNext()) {
				Row row = rows.next();
				int rowNum = row.getRowNum();
				if (rowNum == 0) {
					continue; // Skip header row
				}

				ProductModel productModel = new ProductModel();
				boolean isValidRow = true; 
				Map<String, String> issues = new HashMap<>(); 

				Iterator<Cell> cells = row.cellIterator();

				while (cells.hasNext()) {
					Cell cell = cells.next();
					int columnIndex = cell.getColumnIndex();

					switch (columnIndex) {
					case 0: {
						    String productName = cell.getStringCellValue().trim();
						    if (cell.getCellType() == CellType.BLANK) {
						        isValidRow = false;
						        issues.put("Product Name", "Blank cell found instead of a string");
						    } else if (cell.getCellType() == CellType.NUMERIC) {
						        isValidRow = false;
						        issues.put("Product Name", "Numeric value found instead of a string");
						    } else {
						        if (productName == null || productName.equals("")) {
						            isValidRow = false;
						            issues.put("Product Name", "Invalid product Name");
						        } else {
						            productModel.setProductName(productName);
						            //System.out.println(productName); // Assuming you want to print the product name
						        }
						    }
						    break;
						}

					
					case 1: {
						long supplierId = (long) cell.getNumericCellValue();
						try {
							SupplierModel supplierModel = service.getSupplierById(supplierId);
							if (supplierModel == null) {
								isValidRow = false;
								issues.put("supplierId", "Supplier not exists: " + supplierId);
							} else {
								productModel.setSupplier(supplierModel);
							}
						} catch (ResourceNotExistsException e) {
							isValidRow = false;
							issues.put("supplierId", e.getMessage());
						}
						break;
					}

					case 2: {
						long categoryId = (long) cell.getNumericCellValue();
						try {
							CategoryModel categoryModel = service1.getCategoryById(categoryId);
							if (categoryModel == null) {
								isValidRow = false;
								issues.put("categoryId", "Category not exists: " + categoryId);
							} else {
								productModel.setCategory(categoryModel);
							}
						} catch (ResourceNotExistsException e) {
							isValidRow = false;
							issues.put("categoryId", e.getMessage());
						}
						break;
					}

					case 3: {
						int productQty = (int) cell.getNumericCellValue();
						if (productQty <= 0) {
							isValidRow = false;
							issues.put("productQty", "Product quantity cannot be negative");
							break;
						}
						productModel.setProductQty(productQty);
						break;
					}
					case 4: {
						double productPrice = cell.getNumericCellValue();
						if (productPrice <= 0) {
							isValidRow = false;
							issues.put("productPrice", "Product price cannot be negative");
							break;
						}
						productModel.setProductPrice(productPrice);
						break;
					}
					case 5: {
						Date date = cell.getDateCellValue();
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						String formattedDate = dateFormat.format(date);
						productModel.setMfgDate(formattedDate);
						break;
					}

					case 6: {
						Date date = cell.getDateCellValue();
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						String formattedDate = dateFormat.format(date);
						productModel.setExpDate(formattedDate);
						break;
					}
					case 7: {
						int deliveryCharges=(int) cell.getNumericCellValue();
						if(deliveryCharges<=0)
						{
							isValidRow=false;
							issues.put("deliveryCharges", "Delivery cannot be negative");
						}
						productModel.setDeliveryCharges((int) cell.getNumericCellValue());
						break;
					}
					}
				}

				if (!isValidRow) {

					badRecords.put(rowNum, issues);
				} else {
					productModelList.add(productModel);
				}

			}
			System.out.println(productModelList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong while reading the Excel file.");
		} 

		return productModelList;
	}
}






