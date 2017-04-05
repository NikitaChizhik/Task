package com.nikitachizhik91.shop.model;

import java.util.Arrays;

public class RentUnit {

	private SportEquipment[] rentUnits;

	public RentUnit() {
		rentUnits = new SportEquipment[20];
	}

	public void addUnit(SportEquipment unit) {

		int indexOfEmptySpace = findIndex(rentUnits);

		rentUnits[indexOfEmptySpace] = unit;
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

	public RentUnit(SportEquipment[] rentUnits) {
		this.rentUnits = rentUnits;
	}

	public SportEquipment[] getRentUnits() {
		return rentUnits;
	}

	public void setRentUnits(SportEquipment[] rentUnits) {
		this.rentUnits = rentUnits;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(rentUnits);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentUnit other = (RentUnit) obj;
		if (!Arrays.equals(rentUnits, other.rentUnits))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RentUnit [units=" + Arrays.toString(rentUnits) + "]";
	}

}
