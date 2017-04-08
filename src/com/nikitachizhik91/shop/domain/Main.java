package com.nikitachizhik91.shop.domain;

import java.util.List;
import java.util.Map;

import com.nikitachizhik91.shop.model.Customer;
import com.nikitachizhik91.shop.model.RentUnit;
import com.nikitachizhik91.shop.model.SportEquipment;

public class Main {

	private final static String DATA_FILE_PATH = "/home/nikita/eclipse/workspace/RentSportShop_01/resources/SportFile";

	// stoit sysout commentirovat stho proisxodit v console shto bi ponyatnee
	// bilo?

	// nujno li 2 constructors 1.default.2with parameters

	// nazvanie papok norm project?
	// 2 metoda v main norm?

	// logirovanie vezde ili 1 metoda dostatojno. mojem ego check?
	// json
	// syso comments a lot of is it norm?

	public static void main(String args[]) {

		Shop shop = null;
		try {
			shop = new Shop(JsonDataReader.fromFile(DATA_FILE_PATH));
			// zapolnyt iz faila customer?
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

			System.out.println("All Customers:");
			show(shop.findAllCustomers());

			System.out.println("All SportEquipment:");
			show(shop.findAllSportEquipment());

			System.out
					.println("____________________________________________________________________________________________________________\n");

			String title = "Hat".toLowerCase();
			System.out.println("Finding Equipment with title=" + title);
			show(shop.findEquipmentUnitsWithTitle(title));

			int unitId = 13;
			SportEquipment sportEquipment = shop.findSportEquipmentById(unitId);

			int quantity = 1;
			if (shop.rentEquipmentToCustomer(sportEquipment, quantity, customer)) {
				shop.remove(sportEquipment, quantity);
				System.out.println(customer.getName() + " took " + quantity + " " + sportEquipment.getTitle()
						+ ". He didn't have more than 3 hats and there was 1 hat in stock.\n");
			}

			System.out.println("All Equipment:");
			show(shop.findAllSportEquipment());

			System.out
					.println("\nNEXT ACTION____________________________________________________________________________________________________________\n");

			title = "Coat".toLowerCase();
			System.out.println("Finding Equipment with title=" + title);
			show(shop.findEquipmentUnitsWithTitle(title));

			unitId = 1;
			quantity = 3;
			SportEquipment sportEquipment2 = shop.findSportEquipmentById(unitId);

			System.out.println("Paul wants to take 3 coats");
			if (shop.rentEquipmentToCustomer(sportEquipment2, quantity, customer2)) {
				shop.remove(sportEquipment2, quantity);
				System.out.println("Paul rented 3 coats. because he didn't have more than 3 coats.");
			}

			System.out
					.println("\nNEXT ACTION____________________________________________________________________________________________________________\n");

			quantity = 1;
			System.out.println("Paul wants to take 1 more coat.");
			if (shop.rentEquipmentToCustomer(sportEquipment2, quantity, customer2)) {
				shop.remove(sportEquipment2, quantity);
			}

			System.out
					.println("\nNEXT ACTION____________________________________________________________________________________________________________\n");

			title = "Gloves".toLowerCase();
			System.out.println("Finding Equipment with title=" + title);
			show(shop.findEquipmentUnitsWithTitle(title));

			unitId = 8;
			quantity = 3;
			SportEquipment sportEquipment3 = shop.findSportEquipmentById(unitId);

			System.out.println("Paul wants to take 3 gloves");
			if (shop.rentEquipmentToCustomer(sportEquipment3, quantity, customer2)) {
				shop.remove(sportEquipment3, quantity);
				System.out.println("Paul rented 3 gloves. now he has 6 rented items.");
			}

			System.out.println("\nAll Customers:");
			show(shop.findAllCustomers());

			System.out.println("All Equipment:");
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
