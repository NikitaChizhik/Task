package com.nikitachizhik91.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.model.Customer;
import com.nikitachizhik91.model.RentUnit;
import com.nikitachizhik91.model.SportEquipment;

public class Shop {

	private final static Logger log = LogManager.getLogger(Shop.class.getName());

	private final static String JSON_GOODS = "/home/nikita/git/ntask/src/main/resouces/JsonGoods";
	private static final String JSON_CUSTOMERS = "/home/nikita/git/ntask/src/main/resouces/JsonCustomers";
	private static final int ALLOWED_QUANTITY = 3;

	private Map<SportEquipment, Integer> goods;
	private List<Customer> customers;

	public Shop() throws DomainException {

		goods = JsonDataReader.fromGoods(JSON_GOODS);
		customers = JsonDataReader.fromCustomers(JSON_CUSTOMERS);
	}

	public Set<SportEquipment> findSportEquipment(String title) {

		Set<SportEquipment> requiredEquipment = new HashSet<>();

		for (Map.Entry<SportEquipment, Integer> entry : goods.entrySet()) {

			if (entry.getKey().getTitle().equals(title) && entry.getValue() != 0) {
				requiredEquipment.add(entry.getKey());
			}
		}
		return requiredEquipment;
	}

	public SportEquipment findSportEquipmentById(int id) {

		for (Map.Entry<SportEquipment, Integer> entry : goods.entrySet()) {
			if (entry.getKey().getId() == id) {
				return entry.getKey();
			}
		}
		return null;
	}

	public boolean rentSportEquipment(int customerId, int equipmentId, int quantity) {
		log.trace("Started rentSportEquipment() method with parameters : customerId=" + customerId + " equipmentId="
				+ equipmentId + " quantity=" + quantity);

		log.trace("Checking for allowed quantity.Allowed is =" + ALLOWED_QUANTITY);

		if (quantity > ALLOWED_QUANTITY) {

			log.info("You cannot take more than 3 items.");
			System.out.println("You cannot take more than 3 items.");
			return false;
		}
		log.trace("Quantity is allowed");

		log.trace("Finding sportEquipment by id=" + equipmentId);

		SportEquipment sportEquipment = findSportEquipmentById(equipmentId);
		int inStock = goods.get(sportEquipment);

		log.trace("Checking for in Stock=" + inStock + " asked quantity=" + quantity);

		if (!((inStock - quantity) >= 0)) {

			System.out.println("You want " + quantity + " " + sportEquipment.getTitle() + " but in stock are only "
					+ inStock);
			System.out.println();

			return false;
		}

		log.trace("Finding sportEquipment by id=" + equipmentId);

		sportEquipment = findSportEquipmentById(equipmentId);

		log.trace("Found sportEquipment=" + sportEquipment);

		if (sportEquipment == null) {

			log.info("There is no such equipment");
			System.out.println("There is no such equipment");
			return false;
		}

		log.trace("Finding Customer by id=" + customerId);

		Customer customer = findCustomer(customerId);

		log.trace("Found Customer=" + customer);

		log.trace("Finding customer's RentUnit");

		RentUnit customerRentUnit = customer.getRentUnit();

		log.trace("Found customer's RentUnit=" + customerRentUnit);

		log.trace("Adding sportEquipment to customer's rentUnits.");

		for (int i = 0; i < quantity; i++) {
			customerRentUnit.addUnit(sportEquipment);
		}

		log.info("Rented " + quantity + " sportEquipment=" + sportEquipment + " to customer=" + customer);
		log.trace("Finished rentSportEquipment() method");

		return true;
	}

	public Customer findCustomer(int customerId) {

		for (Customer customer : customers) {

			if (customer.getId() == customerId) {
				return customer;
			}
		}

		return null;
	}

	public void remove(SportEquipment sportEquipment, int quantity) {

		goods.put(sportEquipment, goods.get(sportEquipment) - quantity);

	}

	public boolean addCustomer(Customer customer) {
		return customers.add(customer);
	}

	public boolean removeCustomer(Customer customer) {
		return customers.remove(customer);
	}

	public Map<SportEquipment, Integer> getGoods() {
		return goods;
	}

	public void setGoods(Map<SportEquipment, Integer> goods) {
		this.goods = goods;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
}
