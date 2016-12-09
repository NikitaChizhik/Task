package shoptask;

import shoptask.model.RentUnit;
import shoptask.model.SportEquipment;

import java.io.FileNotFoundException;
import java.util.Map;

public class Main {

	private final static String DATA_FILE_PATH = "/home/nikita/eclipse/workspace/SportShop/src1/shoptask/SportFile";

	public static void main(String args[]) {

		try {
			Shop shop = new Shop(FileUtil.dataFromFile(DATA_FILE_PATH));
			shop.countUnitsQuantity();
			// reads all goods
			System.out.println("first state__________");
			//хотел отсортировать по тайтл забыл как. раньше делал. тут уже время нету надо завтра.
			//shop.sortByTitle();
			showAllList(shop.getGoods());

			String iWantHat = "Hat".toLowerCase();
			System.out.println();
			System.out.println("shows the equipment with the chosen item.");
			shop.showMeEquipmentUnitsWithTitle(iWantHat);
			// let's choose item with id 1.
			System.out.println();
			System.out.println("we chose the id. it is 1.");
			int unitId1 = 1;
			int unitId5 = 5;
			// maybe create class customer and he chooses smth like that?
			// как мне rent unit создатьи потом использовать лучше в шоп его
			// сразу запихнуть?

			shop.iChooseUnitWithId(unitId1);
			shop.iChooseUnitWithId(unitId5);
			shop.countUnitsQuantity();
			
			
			System.out.println();
			System.out.println("current state of the shop's goods__________");
			showAllList(shop.getGoods());
			System.out.println();
			System.out.println("rented items:__________");
			showAllRentItems(shop.rentUnit);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.getMessage();
		}

	}

	private static void showAllRentItems(RentUnit rentUnit) {
		if (rentUnit.getSportEquipmentUnits() == null) {
			System.out.println("there is no rented units.");
		}
		for (SportEquipment unit : rentUnit.getSportEquipmentUnits()) {
			System.out.println(unit);
		}
	}

	private static void showAllList(Map<SportEquipment, Integer> goods) {
		for (Map.Entry<SportEquipment, Integer> entry : goods.entrySet()) {
			System.out.println(entry.getKey() + "   number units:" + entry.getValue());
		}
	}

}
