package com.nikitachizhik91.shop.domain;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.shop.model.RentUnit;
import com.nikitachizhik91.shop.model.SportEquipment;

public class Shop {

	private final static Logger log = LogManager.getLogger(Shop.class.getName());

	private Map<SportEquipment, Integer> goods;

	public RentUnit rentUnit;

	public Shop(Map<SportEquipment, Integer> goods) {
		this.goods = goods;
		this.rentUnit = new RentUnit();
	}

	public void chooseUnit(int unitId) throws DomainException {
		log.trace("Started chooseUnit() method.");

		if (rentUnit.getRentUnits() == null) {
			log.error("There is no rented Equipment.");
			throw new DomainException("There is no equipment to rent.");
		}

		SportEquipment unit = findSportEquipmentbyId(unitId);
		log.info("Found unit=" + unit + " by id=" + unitId);

		rentUnit.addUnit(unit);
		log.info("Added unit=" + unit);

		deleteEquipmentFromDataBase(unit);
		log.info("Deleted unit=" + unit);

		log.trace("Finished chooseUnit() method.");
	}

	private void deleteEquipmentFromDataBase(SportEquipment sportEquipment) {

		Iterator<Entry<SportEquipment, Integer>> iterator = goods.entrySet().iterator();

		while (iterator.hasNext()) {

			Entry<SportEquipment, Integer> nextEntry = iterator.next();

			if (nextEntry.getKey().equals(sportEquipment)) {

				goods.put(sportEquipment, nextEntry.getValue() - 1);

				System.out.println("Deleted unit=" + sportEquipment);

				System.out.println("sportEquipment database has been updated.");
			}
		}
	}

	private SportEquipment findSportEquipmentbyId(int id) {

		for (Map.Entry<SportEquipment, Integer> entry : goods.entrySet()) {

			if (entry.getKey().getId() == id) {

				return entry.getKey();
			}

		}

		System.out.println("There is no such equipment.");
		return null;
	}

	public void showEquipmentUnitsWithTitle(String iWantHat) throws DomainException {

		if (goods == null) {
			log.error("There is no goods in this shop.");
			throw new DomainException("There is no goods in this shop.");
		}

		for (Map.Entry<SportEquipment, Integer> entry : goods.entrySet()) {
			if (entry.getKey().getTitle().equals(iWantHat)) {
				System.out.println(entry.getKey() + "    number of unis:" + entry.getValue());
			}
		}
	}

	public Map<SportEquipment, Integer> countUnitsQuantity() {

		if (goods.isEmpty()) {

			// or throw exception+log?

			// log.error("There is no goods=" + goods);
			// throw new DomainException("There is no goods=" + goods);

			System.out.println("There is no goods=" + goods);

			return null;
		}

		return goods;
	}

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

	@Override
	public String toString() {
		return "Shop [goods=" + goods + ", rentUnit=" + rentUnit + "]";
	}

}
