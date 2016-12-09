package shoptask;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import shoptask.model.RentUnit;
import shoptask.model.SportEquipment;

public class Shop {
	// зачем они мне дали такую мапу я незнаю ерунда какая-то. не понимаю как ее
	// заюзать.
	// вот сегодня целый день пытался использовать ее в качетсве варианта 3
	// ниже.
	// но вот вечер и я сейчас вот ошибку искал час - забыл что елси ключ
	// одинаковый
	// то мапа перезаписыывает этот обьект. вот сейчас сижу и думаю))

	// 1)map-int = quantity of all items i zachem? ne effectivno
	// 2)int-map = int eto id. norm(?) ya bi lusthe id v samom producte propisal
	// realno.
	// 3)map-int = int quantity of each item(hat - 20) a shto objects tam vse
	// kak v sete?unikalni?
	// objecti odnakovie neznal shto v mape po key drug druga stiraut.
	private Map<SportEquipment, Integer> goods;
	// is it ok with visibility that it is public in this case?
	// norm esli ya rent unit srazu sdes sozdau tak proshe.
	// to shto ldya rent unita 1 class zeliy norm?
	public RentUnit rentUnit;

	public Shop(Map<SportEquipment, Integer> goods) {
		this.goods = goods;
		this.rentUnit = new RentUnit();
	}

	private void deleteEquipmentFromDataBase(SportEquipment sportEquipment) {

		Iterator<Entry<SportEquipment, Integer>> iterator = goods.entrySet().iterator();

		while (iterator.hasNext()) {

			Entry<SportEquipment, Integer> nextEntry = iterator.next();

			if (nextEntry.getKey().equals(sportEquipment)) {
				System.out.println("DELETE-->" + sportEquipment);
				iterator.remove();
				System.out.println("sportEquipment database has been updated.");
			}

		}
	}

	private SportEquipment findSportEquipment(int id) {
		for (Map.Entry<SportEquipment, Integer> entry : goods.entrySet()) {

			if (entry.getKey().getId() == id) {

				return entry.getKey();
			}

		}

		System.out.println("returned null. there is no such equipment.");
		return null;
	}

	private int findIndex(SportEquipment[] units) {
		int index = 0;
		for (SportEquipment unit : units) {
			if (unit == null) {
				return index;
			}
			index++;
		}

		return index;
	}

	public void showMeEquipmentUnitsWithTitle(String iWantHat) throws Exception {

		if (goods == null) {
			throw new Exception("There is no goods in this shop. ;(");
		}

		for (Map.Entry<SportEquipment, Integer> entry : goods.entrySet()) {
			if (entry.getKey().getTitle().equals(iWantHat)) {
				System.out.println(entry.getKey() + "    number of unis:" + entry.getValue());

			}

		}

	}

	public void iChooseUnitWithId(int unitId) throws Exception {
		if (rentUnit.getSportEquipmentUnits() == null) {
			throw new Exception("there is no rented Equipment **the massive is empty!");
		}
		SportEquipment[] units = rentUnit.getSportEquipmentUnits();
		/**
		 * finds empty space where to put the item.
		 */
		int indexOfEmptySpace = findIndex(units);

		// legche vse taki zdes etot massiv sdelat s rent unit ato zeliy massiv
		// perezapisivat snova.

		/**
		 * palce the rented item.
		 */
		units[indexOfEmptySpace] = findSportEquipment(unitId);
		rentUnit.setSportEquipmentUnits(units);
		/**
		 * delete item from databse.
		 */
		deleteEquipmentFromDataBase(units[indexOfEmptySpace]);

	}

	public Map<SportEquipment, Integer> countUnitsQuantity() {

		if (goods.isEmpty()) {

			System.out.println("the MAP is empty!");
			// tak pravilno delat? ili exeption kidat?
			return null;
		}
		// fill correctly
		int counter;
		for (Map.Entry<SportEquipment, Integer> entry : goods.entrySet()) {
			counter = 0;
			for (Map.Entry<SportEquipment, Integer> entry2 : goods.entrySet()) {
				if (entry.getKey().getTitle().equals(entry2.getKey().getTitle())) {
					++counter;
				}
			}
			for (Map.Entry<SportEquipment, Integer> entry3 : goods.entrySet()) {
				if (entry.getKey().equals(entry3.getKey())) {
					entry3.setValue(counter);
				}
			}

		}

		return goods;
	}

	// public void sortByTitle() {
	// Map<SportEquipment, Integer> goods2 = goods;
	// Map<SportEquipment, Integer> treeMap = new TreeMap<SportEquipment,
	// Integer>(goods2);
	// for (Entry<SportEquipment, Integer> item : treeMap.entrySet()) {
	// System.out.println(item.getKey() + "   numbers:" + item.getValue());
	// }

	// TreeMap<SportEquipment, Integer> treeMap = new
	// TreeMap<SportEquipment, Integer>(new Comparator<SportEquipment>() {
	//
	// @Override
	// public int compare(SportEquipment o1, SportEquipment o2) {
	//
	// return compare(o1, o2);
	// }
	//
	// });
	//
	// goods=treeMap;
	// }

	// get set...
	public Map<SportEquipment, Integer> getGoods() {
		return goods;
	}

	public void setGoods(Map<SportEquipment, Integer> goods) {
		this.goods = goods;
	}

	public RentUnit getRentUnit() {
		return rentUnit;
	}

	public void setRentUnit(RentUnit rentUnit) {
		this.rentUnit = rentUnit;
	}

}
