package com.nikitachizhik91.logic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.nikitachizhik91.model.Category;
import com.nikitachizhik91.model.Customer;
import com.nikitachizhik91.model.RentUnit;
import com.nikitachizhik91.model.SportEquipment;

public class ShopTest {

	Shop shop;

	@Before
	public void init() throws DomainException {

		shop = new Shop();
	}

	@Test(expected = DomainException.class)
	public void findSportEquipmentById_Zero_MustThrowException() throws DomainException {

		shop.findSportEquipmentById(0);
	}

	@Test
	public void findSportEquipmentById() throws DomainException {

		Category category = new Category(26, "little");
		SportEquipment expected = new SportEquipment(25, "hat", 92, category);

		assertEquals(expected, shop.findSportEquipmentById(25));
	}

	@Test
	public void findCustomer_ById() {

		RentUnit rentUnit = new RentUnit();
		Category category = new Category(2, "medium");
		SportEquipment unit = new SportEquipment(1, "sweater", 1000, category);
		rentUnit.addUnit(unit);

		Customer expected = new Customer(2, "Peter", rentUnit);

		assertEquals(expected, shop.findCustomer(2));
	}
}
