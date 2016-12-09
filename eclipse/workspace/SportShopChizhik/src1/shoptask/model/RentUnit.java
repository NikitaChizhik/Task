package shoptask.model;

import java.util.Arrays;

public class RentUnit {

	// arraylist
	private SportEquipment[] sportEquipmentUnits;

	public RentUnit() {
		sportEquipmentUnits = new SportEquipment[10];
	}

	public RentUnit(SportEquipment[] sportEquipmentUnits) {
		this.sportEquipmentUnits = sportEquipmentUnits;
	}

	public SportEquipment[] getSportEquipmentUnits() {
		return sportEquipmentUnits;
	}

	public void setSportEquipmentUnits(SportEquipment[] sportEquipmentUnits) {
		this.sportEquipmentUnits = sportEquipmentUnits;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(sportEquipmentUnits);
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
		if (!Arrays.equals(sportEquipmentUnits, other.sportEquipmentUnits))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RentUnit [sportEquipmentUnits=" + Arrays.toString(sportEquipmentUnits) + ", getSportEquipmentUnits()="
				+ Arrays.toString(getSportEquipmentUnits()) + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
