package com.nikitachizhik91.shop.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.shop.model.Customer;
import com.nikitachizhik91.shop.model.SportEquipment;

public class Shop {

	private final static Logger log = LogManager.getLogger(Shop.class.getName());

	private Map<SportEquipment, Integer> goods;

	List<Customer> customers;

	public Shop(Map<SportEquipment, Integer> goods) {
		this.goods = goods;
		customers = new ArrayList<>();
	}

	public Customer findCustomer(Customer customerArg) {
		// nazvanie?
		Customer found = null;

		for (Customer customer : customers) {
			if (customer.equals(customerArg)) {
				found = customer;
			}
		}

		return found;
	}

	public void remove(SportEquipment sportEquipment, int quantity) {

		goods.put(sportEquipment, goods.get(sportEquipment) - quantity);

	}

	public boolean rentEquipmentToCustomer(SportEquipment sportEquipment, Integer quantityArg, Customer customerArg) {

		int quantity = quantityArg.intValue();

		int inStock = goods.get(sportEquipment);
		if (!((inStock - quantity) >= 0)) {
			System.out.println("You want " + quantity + " " + sportEquipment.getTitle() + " but in stock are only "
					+ inStock);
			System.out.println();
			return false;
		}

		Customer customer = findCustomer(customerArg);

		int customerQuantity = findSportEquipmentQuantityInCustomer(sportEquipment, customer);
		if ((customerQuantity + quantity) > 3) {

			System.out.println("We can't rent you more than 3 items.You chose " + quantityArg
					+ " but you already have " + customerQuantity + ".You can only take " + (3 - customerQuantity));

			return false;
		}

		for (int i = 0; i < quantity; i++) {

			customer.getRentUnit().addUnit(sportEquipment);
		}
		return true;
	}

	private int findSportEquipmentQuantityInCustomer(SportEquipment sportEquipment, Customer customer) {

		SportEquipment[] rentUnits = customer.getRentUnit().getRentUnits();
		int quantity = 0;

		for (SportEquipment unit : rentUnits) {
			if (unit == sportEquipment) {
				quantity++;
			}
		}
		return quantity;
	}

	public Customer addCustomer(Customer customer) {

		customers.add(customer);

		return customer;
	}

	public List<Customer> findAllCustomers() {
		return customers;
	}

	public Map<SportEquipment, Integer> findAllSportEquipment() {
		return goods;
	}

	public SportEquipment findSportEquipmentById(int id) throws DomainException {
		log.trace("Started findSportEquipmentById() method.");

		if (goods == null) {
			log.error("There is no equipment to rent.");
			throw new DomainException("There is no equipment to rent.");
		}

		SportEquipment sportEquipment = null;

		for (Map.Entry<SportEquipment, Integer> entry : goods.entrySet()) {

			if (entry.getKey().getId() == id) {

				sportEquipment = entry.getKey();
			}
		}

		if (sportEquipment == null) {
			log.error("There is no SportEquipment with id=" + id);
			throw new DomainException("There is no SportEquipment with id=" + id);
		}
		log.info("Found SportEquipment=" + sportEquipment);

		log.trace("Finished findSportEquipmentById() method.");
		return sportEquipment;
	}

	public Map<SportEquipment, Integer> findEquipmentUnitsWithTitle(String title) throws DomainException {

		Map<SportEquipment, Integer> units = new HashMap<>();

		if (goods == null) {
			log.error("There is no goods in this shop.");
			throw new DomainException("There is no goods in this shop.");
		}

		for (Map.Entry<SportEquipment, Integer> entry : goods.entrySet()) {
			if (entry.getKey().getTitle().equals(title)) {
				units.put(entry.getKey(), entry.getValue());
			}
		}
		return units;
	}

}
