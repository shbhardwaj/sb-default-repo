package com.sb.discount;

import java.math.BigDecimal;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.sb.model.Bill;
import com.sb.model.Customer;
import com.sb.model.CustomerType;
import com.sb.model.ItemType;
import com.sb.model.LineItem;

public class IntegrityTest {

	private Customer EMPLOYEE_CUSTOMER;
	private Customer AFFILIATE_CUSTOMER;
	private Customer LOYAL_CUSTOMER;
	private Customer NORMAL_CUSTOMER;

	private LineItem nonGroceryLessThanHundredItem;
	private LineItem nonGroceryMoreThanHundredItem;

	private LineItem groceryLessThanHundredItem;
	private LineItem groceryMoreThanHundredItem;
	
	@Before
	public void setUp() {
		EMPLOYEE_CUSTOMER = new Customer("Employee", CustomerType.EMPLOYEE);
		AFFILIATE_CUSTOMER = new Customer("Affiliate", CustomerType.AFFILIATE);
		LOYAL_CUSTOMER = new Customer("LoyalCustomer", CustomerType.LOYAL);
		NORMAL_CUSTOMER = new Customer("NormalCustomer", CustomerType.NORMAL);

		nonGroceryLessThanHundredItem = new LineItem("L1", ItemType.OTHERS, new BigDecimal("50"));
		nonGroceryMoreThanHundredItem = new LineItem("L2", ItemType.OTHERS, new BigDecimal("150"));
		groceryLessThanHundredItem = new LineItem("L3", ItemType.GROCERY, new BigDecimal("40"));
		groceryMoreThanHundredItem = new LineItem("L4", ItemType.GROCERY, new BigDecimal("140"));
	}

	@Test
	public void givenABillOfLessThanHundredForAnEmployeeThenEmployeeDiscountIsApplied() {
		Bill bill = new Bill(EMPLOYEE_CUSTOMER, Arrays.asList(nonGroceryLessThanHundredItem));
		Assert.assertEquals(new BigDecimal("35.0"), bill.netPayableAmount());
	}

	@Test
	public void givenABillOfMoreThanHundredForAnEmployeeThenEmployeeDiscountAndAmountDiscountAreApplied() {
		Bill bill = new Bill(EMPLOYEE_CUSTOMER, Arrays.asList(nonGroceryMoreThanHundredItem));
		Assert.assertEquals(new BigDecimal("100.0"), bill.netPayableAmount());
	}

	@Test
	public void givenABillOfLessThanHundredForAnAffiliateThenAffiliateDiscountIsApplied() {
		Bill bill = new Bill(AFFILIATE_CUSTOMER, Arrays.asList(nonGroceryLessThanHundredItem));
		Assert.assertEquals(new BigDecimal("45.0"), bill.netPayableAmount());
	}

	@Test
	public void givenABillOfMoreThanHundredForAnAffiliateThenAffiliateDiscountAndAmountDiscountAreApplied() {
		Bill bill = new Bill(AFFILIATE_CUSTOMER, Arrays.asList(nonGroceryMoreThanHundredItem));
		Assert.assertEquals(new BigDecimal("130.0"), bill.netPayableAmount());
	}

	@Test
	public void givenABillOfLessThanHundredForAnLoyalCustomerThenLoyalCustomerDiscountIsApplied() {
		Bill bill = new Bill(LOYAL_CUSTOMER, Arrays.asList(nonGroceryLessThanHundredItem));
		Assert.assertEquals(new BigDecimal("47.50"), bill.netPayableAmount());
	}

	@Test
	public void givenABillOfMoreThanHundredForAnLoyalCustomerThenLoyalCustomerDiscountAndAmountDiscountAreApplied() {
		Bill bill = new Bill(LOYAL_CUSTOMER, Arrays.asList(nonGroceryMoreThanHundredItem));
		Assert.assertEquals(new BigDecimal("137.50"), bill.netPayableAmount());
	}

	@Test
	public void givenABillOfLessThanHundredForAnNormalCustomerThenNoDiscountIsApplied() {
		Bill bill = new Bill(NORMAL_CUSTOMER, Arrays.asList(nonGroceryLessThanHundredItem));
		Assert.assertEquals(new BigDecimal("50"), bill.netPayableAmount());
	}

	@Test
	public void givenABillOfMoreThanHundredForAnNormalCustomerThenAmountDiscountIsApplied() {
		Bill bill = new Bill(NORMAL_CUSTOMER, Arrays.asList(nonGroceryMoreThanHundredItem));
		Assert.assertEquals(new BigDecimal("145"), bill.netPayableAmount());
	}

	@Test
	public void givenABillOfLessThanHundredForAnEmployeeWithGroceryAndNonGroceryItemsThenEmployeeDiscountIsAppliedOnNonGroceryItems() {
		Bill bill = new Bill(EMPLOYEE_CUSTOMER, Arrays.asList(nonGroceryLessThanHundredItem, groceryLessThanHundredItem));
		Assert.assertEquals(new BigDecimal("75.0"), bill.netPayableAmount());
	}

	@Test
	public void givenABillOfMoreThanHundredForAnEmployeeWithGroceryAndNonGroceryItemsThenEmployeeDiscountOnNonGroceryAndAmountDiscountAreApplied() {
		Bill bill = new Bill(EMPLOYEE_CUSTOMER, Arrays.asList(nonGroceryLessThanHundredItem, groceryMoreThanHundredItem));
		Assert.assertEquals(new BigDecimal("170.0"), bill.netPayableAmount());
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenABillWithNegativeLineItemPriceIsNotAllowed() {
		LineItem negativePriceItem = new LineItem("L5", ItemType.GROCERY, new BigDecimal("-140"));
		Bill bill = new Bill(EMPLOYEE_CUSTOMER, Arrays.asList(negativePriceItem));
	}
}
