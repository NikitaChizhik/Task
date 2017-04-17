package com.nikitachizhik91.model;

public class Customer extends Entity {

	private RentUnit rentUnit;

	// getter sette k rentunir nado?
	// set name?
	public Customer(int id, String name, RentUnit rentUnit) {
		super(id, name);
		this.rentUnit = rentUnit;
	}

	public RentUnit getRentUnit() {
		return rentUnit;
	}

	public String getName() {
		return super.getTitle();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((rentUnit == null) ? 0 : rentUnit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (rentUnit == null) {
			if (other.rentUnit != null)
				return false;
		} else if (!rentUnit.equals(other.rentUnit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Customer : id=%-5d title=%-10s\nrentUnit=\n%-10s", getId(), getTitle(), getRentUnit());
	}
}
