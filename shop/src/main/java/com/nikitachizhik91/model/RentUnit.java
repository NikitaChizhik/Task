package com.nikitachizhik91.model;

import java.util.Arrays;

public class RentUnit {

	private SportEquipment[] units;
	private final static int INITIAL_CAPACITY = 5;

	public RentUnit() {
		units = new SportEquipment[INITIAL_CAPACITY];
	}

	public RentUnit(SportEquipment[] rentUnits) {
		this.units = rentUnits;
	}

	public void addUnit(SportEquipment unit) {

		for (int i = 0; i <= units.length; i++) {

			if (i == units.length) {
				SportEquipment[] increasedUnits = new SportEquipment[i + INITIAL_CAPACITY / 2];
				System.arraycopy(units, 0, increasedUnits, 0, units.length);
				units = increasedUnits;
				units[i] = unit;
				return;
			}

			if (units[i] == null) {
				units[i] = unit;
				return;
			}

		}
	}

	public SportEquipment[] getUnits() {
		return units;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(units);
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
		if (!Arrays.equals(units, other.units))
			return false;
		return true;
	}

	@Override
	public String toString() {

		StringBuilder stringBuilder = new StringBuilder();

		for (SportEquipment unit : units) {

			if (unit == null)
				break;

			stringBuilder.append(unit + "\n");
		}

		return stringBuilder.toString();
	}

}
