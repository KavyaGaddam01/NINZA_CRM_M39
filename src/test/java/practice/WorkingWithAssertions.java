package practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WorkingWithAssertions {
	@Test
	public void test() {
		
		String s=null;

		System.out.println("start");
		SoftAssert soft = new SoftAssert();
		soft.assertNotNull(s);
		System.out.println("end");
		soft.assertAll();
	}
}
//System.out.println("start");
//Assert.assertEquals("hdfc", "hdfc");	
//System.out.println("end");