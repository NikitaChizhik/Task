package com.nikitachizhik91.shop.domain;

import java.util.List;
import java.util.Map;

import com.nikitachizhik91.shop.model.Customer;
import com.nikitachizhik91.shop.model.RentUnit;
import com.nikitachizhik91.shop.model.SportEquipment;

public class Main {

	private final static String DATA_FILE_PATH = "/home/nikita/git/Task/resources/SportFile";

	public static void main(String args[]) {

		Shop shop = null;
		try {
			shop = new Shop(DataReader.fromFile(DATA_FILE_PATH));

			Customer customer = new Customer();
			customer.setId(1);
			customer.setName("Petr");
			RentUnit rentUnit = new RentUnit();
			customer.setRentUnit(rentUnit);

			shop.addCustomer(customer);

			Customer customer2 = new Customer();
			customer2.setId(2);
			customer2.setName("Paul");
			RentUnit rentUnit2 = new RentUnit();
			customer2.setRentUnit(rentUnit2);

			shop.addCustomer(customer2);

			show(shop.findAllCustomers());

			show(shop.findAllSportEquipment());

			String title = "Hat".toLowerCase();
			show(shop.findEquipmentUnitsWithTitle(title));

			int unitId = 4;
			SportEquipment sportEquipment = shop.findSportEquipmentById(unitId);

			int quantity = 3;
			if (shop.rentEquipmentToCustomer(sportEquipment, quantity, customer)) {
				shop.remove(sportEquipment, quantity);
			}
			show(shop.findAllSportEquipment());

			title = "Coat".toLowerCase();
			show(shop.findEquipmentUnitsWithTitle(title));

			unitId = 1;
			SportEquipment sportEquipment2 = shop.findSportEquipmentById(unitId);

			if (shop.rentEquipmentToCustomer(sportEquipment2, quantity, customer2)) {
				shop.remove(sportEquipment2, quantity);
			}

			//proveri pochemu en pokazivaet
			
			if (shop.rentEquipmentToCustomer(sportEquipment2, quantity, customer2)) {
				shop.remove(sportEquipment2, quantity);
			}

			//pokazat shto budet v raznix situaziaya ex goda beret mnogo tovarov massiv enlarge!
			
			show(shop.findAllCustomers());

			show(shop.findAllSportEquipment());

		} catch (DomainException e) {
			e.printStackTrace();
		}
	}

	private static void show(List<Customer> customers) {
		if (customers == null) {
			System.out.println("There is no customers.");
		}

		for (Customer customer : customers) {

			System.out.println(customer);
			System.out.println();
		}

		System.out.println("\n\n\n");
	}

	private static void show(Map<SportEquipment, Integer> goods) {

		for (Map.Entry<SportEquipment, Integer> entry : goods.entrySet()) {
			System.out.println(entry.getKey() + "   number of units:" + entry.getValue());
		}
		System.out.println("\n\n\n");
	}
}
