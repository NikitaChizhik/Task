package com.nikitachizhik91.model;

public class SportEquipment extends Entity implements Comparable<SportEquipment> {

	private int price;
	private Category category;

	public SportEquipment(int id, String title, int price, Category category) {
		super(id, title);
		this.price = price;
		this.category = category;
	}

	@Override
	public int compareTo(SportEquipment unit) {
		return this.getTitle().compareTo(unit.getTitle());
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + price;
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
		SportEquipment other = (SportEquipment) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (price != other.price)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("[id=%-5d title=%-10s price=%-10d category=%-10s]", getId(), getTitle(), getPrice(),
				getCategory().getTitle());
	}

}
