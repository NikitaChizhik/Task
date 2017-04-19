package com.nikitachizhik91.logic;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.nikitachizhik91.model.Customer;
import com.nikitachizhik91.model.SportEquipment;

public class Main {

	public static void main(String[] args) {

		Shop shop = null;
		try {

			shop = new Shop();

			show(shop.getGoods());
			show(shop.getCustomers());

			String title = "Hat".toLowerCase();
			System.out.println("Finding Equipment with title=" + title);

			Set<SportEquipment> requiredItems = shop.findSportEquipment(title);
			show(requiredItems);

			SportEquipment equipment = null;

			for (SportEquipment item : requiredItems) {
				equipment = item;
			}

			equipment = shop.findSportEquipmentById(equipment.getId());

			Customer customer = shop.getCustomers().get(0);
			int quantityToTake = 1;

			if (shop.rentSportEquipment(customer.getId(), equipment.getId(), quantityToTake)) {

				shop.remove(equipment, quantityToTake);

				System.out.println(customer.getName() + " took " + quantityToTake + " " + equipment.getTitle()
						+ ". He didn't take more than 3 " + title + " and there was " + quantityToTake + " " + title
						+ " in stock.\n");
			}

			show(shop.getGoods());
			show(shop.getCustomers());

			System.out
					.println("______________________________________________________________________________________");
			title = "COaT".toLowerCase();
			System.out.println("Finding Equipment with title=" + title);

			requiredItems = shop.findSportEquipment(title);
			show(requiredItems);

			equipment = null;

			for (SportEquipment item : requiredItems) {
				equipment = item;
			}

			equipment = shop.findSportEquipmentById(equipment.getId());

			customer = shop.getCustomers().get(1);
			quantityToTake = 3;

			if (shop.rentSportEquipment(customer.getId(), equipment.getId(), quantityToTake)) {

				shop.remove(equipment, quantityToTake);

				System.out.println(customer.getName() + " took " + quantityToTake + " " + equipment.getTitle()
						+ ". He didn't take more than 3 " + title + " and there was " + quantityToTake + " " + title
						+ " in stock.\n");
			}

			show(shop.getGoods());
			show(shop.getCustomers());

			Map<SportEquipment, Integer> treeMap = new TreeMap<>(shop.getGoods());
			show(treeMap);

		} catch (DomainException e) {
			e.printStackTrace();
		}
	}

	private static void show(Set<SportEquipment> units) {

		for (SportEquipment unit : units) {
			System.out.println(unit);
		}
		System.out.println("\n\n\n");
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
