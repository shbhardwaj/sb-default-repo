import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.sb.model.Bill;
import com.sb.model.Customer;
import com.sb.model.ImportTax;
import com.sb.model.Item;
import com.sb.model.SaleTax;


public class IntegrationTest {
	/*
	 	except books,
food, and medical products that are exempt

		Input 1:
		1 book at 12.49
		1 music CD at 14.99
		1 chocolate bar at 0.85

		Output 1:
		1 book : 12.49
		1 music CD: 16.49
		1 chocolate bar: 0.85
		Sales Taxes: 1.50
		Total: 29.83
	*/
	
	@Test
	public void inputOne(){
		Customer customer = new Customer("Default");
		Item book = new Item("Book", new BigDecimal("12.49"), 1, SaleTax.EXEMPT, ImportTax.EXEMPT);
		Item musicCD = new Item("Music CD", new BigDecimal("14.99"), 1, SaleTax.BASIC, ImportTax.EXEMPT);
		Item choclateBar = new Item("Choclate Bar", new BigDecimal("0.85"), 1, SaleTax.EXEMPT, ImportTax.EXEMPT);
		List<Item> items = Arrays.asList(book,musicCD,choclateBar);
		
		Bill bill = new Bill(customer,items);
		
		Assert.assertEquals(new BigDecimal("1.50"), bill.getTotalSalesTax());
		Assert.assertEquals(new BigDecimal("29.83"), bill.getTotalAmount());
	}
	
	/*
		Input 2:
		1 imported box of chocolates at 10.00
		1 imported bottle of perfume at 47.50
		
		Output 2:
		1 imported box of chocolates: 10.50
		1 imported bottle of perfume: 54.65
		Sales Taxes: 7.65
		Total: 65.15
	  
	 */
	
	@Test
	public void inputTwo(){
		Customer customer = new Customer("Default");
		Item importedChoclates = new Item("Imported Choclates", new BigDecimal("10.00"), 1, SaleTax.EXEMPT, ImportTax.BASIC);
		Item importedPerfume = new Item("Imported Perfume", new BigDecimal("47.50"), 1, SaleTax.BASIC, ImportTax.BASIC);
		List<Item> items = Arrays.asList(importedChoclates,importedPerfume);
		
		Bill bill = new Bill(customer,items);
		
		Assert.assertEquals(new BigDecimal("7.65"), bill.getTotalSalesTax());
		Assert.assertEquals(new BigDecimal("65.15"), bill.getTotalAmount());
	}
	
	/*
		Input 3:
		1 imported bottle of perfume at 27.99
		1 bottle of perfume at 18.99
		1 packet of headache pills at 9.75
		1 box of imported chocolates at 11.25
		
		 Output 3:
		1 imported bottle of perfume: 32.19
		1 bottle of perfume: 20.89
		1 packet of headache pills: 9.75
		1 imported box of chocolates: 11.85
		Sales Taxes: 6.70
		Total: 74.68
	 */
	
	@Test
	public void inputThree(){
		Customer customer = new Customer("Default");
		Item importedPerfume = new Item("Imported Perfume", new BigDecimal("27.99"), 1, SaleTax.BASIC, ImportTax.BASIC);
		Item perfume = new Item("Perfume", new BigDecimal("18.99"), 1, SaleTax.BASIC, ImportTax.EXEMPT);
		Item headachePills = new Item("Headache Pills", new BigDecimal("9.75"), 1, SaleTax.EXEMPT, ImportTax.EXEMPT);
		Item importedChoclates = new Item("Imported Choclates", new BigDecimal("11.25"), 1, SaleTax.EXEMPT, ImportTax.BASIC);
		
		List<Item> items = Arrays.asList(importedPerfume,perfume,headachePills,importedChoclates);
		
		Bill bill = new Bill(customer,items);
		
		Assert.assertEquals(new BigDecimal("6.70"), bill.getTotalSalesTax());
		Assert.assertEquals(new BigDecimal("74.68"), bill.getTotalAmount());
	}
	
	@Test
	public void xxx(){
		BigDecimal basePrice = new BigDecimal("14.49");
		//SaleTax.BASIC, ImportTax.EXEMPT
		BigDecimal tax1 = basePrice.multiply(SaleTax.BASIC.getTaxRate());
		BigDecimal tax2 = basePrice.multiply(ImportTax.EXEMPT.getTaxRate());
		System.out.println("SalesTax1 == "+tax1);
		System.out.println("SalesTax1 == "+tax2);
		BigDecimal roundUpSalesTax1 = roundUpSalesTax(tax1);
		BigDecimal roundUpSalesTax2 = roundUpSalesTax(tax2);
		System.out.println("roundUpSalesTax1 == "+roundUpSalesTax1);
		System.out.println("roundUpSalesTax2 == "+roundUpSalesTax2);
		BigDecimal roundUpSalesTax3 = roundUpSalesTax(tax1.add(tax2));
		System.out.println("roundUpSalesTax3 == "+roundUpSalesTax3);
	}
	
	@Test
	public void zzz(){
		BigDecimal basePrice = new BigDecimal("1.499");
		BigDecimal roundUpSalesTax = roundUpSalesTax(basePrice);
		Assert.assertEquals(new BigDecimal("1.50"), roundUpSalesTax);
	}
	
	private BigDecimal roundUpSalesTax(BigDecimal value) {
		double scaledNumber = value.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100;
		double resolution = scaledNumber % 5;
		if (resolution != 0) {
			scaledNumber += 5 - resolution;
		}
		double result = scaledNumber / 100;
		String resultVal = new DecimalFormat("#.00").format(result);
		System.out.println(resultVal);
		return new BigDecimal(resultVal);
}
	
}
