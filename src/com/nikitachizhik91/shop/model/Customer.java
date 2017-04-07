package com.nikitachizhik91.shop.model;

public class Customer {

	private int id;
	private String name;
	private RentUnit rentUnit;

	public Customer() {
	}

	public Customer(int id, String name, RentUnit rentUnit) {
		this.id = id;
		this.name = name;
		this.rentUnit = rentUnit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RentUnit getRentUnit() {
		return rentUnit;
	}

	public void setRentUnit(RentUnit rentUnit) {
		this.rentUnit = rentUnit;
	}

	@Override
	public String toString() {
		
		return String.format("id=%d name=%-10s", getId(), getName()) + "Rented units:" + rentUnit.toString();
	}

}
