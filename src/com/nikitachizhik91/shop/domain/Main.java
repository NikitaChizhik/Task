package com.nikitachizhik91.shop.domain;

import java.util.Map;

import com.nikitachizhik91.shop.model.RentUnit;
import com.nikitachizhik91.shop.model.SportEquipment;

public class Main {

	private final static String DATA_FILE_PATH = "/home/nikita/git/Task/resources/SportFile";

	public static void main(String args[]) {

		Shop shop = null;
		try {
			shop = new Shop(DataReader.fromFile(DATA_FILE_PATH));

			System.out.println("first state__________");

			showAllEquipment(shop.getGoods());

			String iWantHat = "Hat".toLowerCase();
			System.out.println();
			System.out.println("shows the equipment with the chosen item.");
			shop.showEquipmentUnitsWithTitle(iWantHat);

			// let's choose item with id 4.
			System.out.println("\n We chose the id 4.");
			int unitId = 4;
			shop.chooseUnit(unitId);

			System.out.println("\n Current state of the shop's goods__________");
			showAllEquipment(shop.getGoods());
			System.out.println("\n Rented items:__________");
			showAllRentedItems(shop.rentUnit);

			System.out.println("\n\n\n\n\n Second state__________");

			showAllEquipment(shop.getGoods());

			String iWantCoat = "Coat".toLowerCase();
			System.out.println();
			System.out.println("shows the equipment with the chosen item.");
			shop.showEquipmentUnitsWithTitle(iWantCoat);

			System.out.println("\n We chose the id 1.");
			unitId = 1;
			shop.chooseUnit(unitId);

			System.out.println("\n Current state of the shop's goods__________");
			showAllEquipment(shop.getGoods());
			System.out.println("\n Rented items:__________");
			showAllRentedItems(shop.rentUnit);

		} catch (DomainException e) {
			e.printStackTrace();
		}
	}

	private static void showAllRentedItems(RentUnit rentUnit) {
		if (rentUnit.getRentUnits() == null) {
			System.out.println("There is no rented units.");
		}
		for (SportEquipment unit : rentUnit.getRentUnits()) {
			if (unit == null) {
				return;
			}
			System.out.println(unit);
		}
	}

	private static void showAllEquipment(Map<SportEquipment, Integer> goods) {
		for (Map.Entry<SportEquipment, Integer> entry : goods.entrySet()) {
			System.out.println(entry.getKey() + "   number of units:" + entry.getValue());
		}
	}
}
