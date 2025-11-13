package practice;

import org.testng.annotations.Test;

public class WorkingWithHelperAttributes {

	@Test
	public void productCreation() {
		System.out.println("productCreation");
	}
	
	@Test(dependsOnMethods = {"productCreation","updateProduct"})
	public void deleteProduct() {
		System.out.println("deleteProduct");
	}
	
	@Test(dependsOnMethods = "productCreation")
	public void updateProduct() {
		System.out.println("updateProduct");
	}
}
