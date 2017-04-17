package com.nikitachizhik91.logic;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.nikitachizhik91.model.Category;
import com.nikitachizhik91.model.Customer;
import com.nikitachizhik91.model.RentUnit;
import com.nikitachizhik91.model.SportEquipment;

public final class JsonDataReader {

	private final static Logger log = LogManager.getLogger(JsonDataReader.class.getName());

	public JsonDataReader() {
	}

	public static Map<SportEquipment, Integer> fromGoods(String path) throws DomainException {

		Map<SportEquipment, Integer> goods = new HashMap<>();
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(path));

			JSONArray jsonData = (JSONArray) obj;

			Iterator<JSONObject> iterator = jsonData.iterator();
			int goodsQuantity = 1;
			int id = 0;

			while (iterator.hasNext()) {

				JSONObject next = iterator.next();

				String title = (String) next.get("title");
				String category = (String) next.get("category");
				String priceString = (String) next.get("price");
				int price = Integer.parseInt(priceString);

				SportEquipment sportEquipment = new SportEquipment(++id, title, price, new Category(++id, category));

				if (goods.containsKey(sportEquipment)) {
					goods.put(sportEquipment, goods.get(sportEquipment) + 1);
				} else {
					goods.put(sportEquipment, goodsQuantity);
				}

			}

		} catch (IOException | ParseException e) {
			log.error("Cannot read data from file with path=" + path, e);
			throw new DomainException("Cannot read data from file with path=" + path, e);
		}

		return goods;
	}

	public static List<Customer> fromCustomers(String path) throws DomainException {

		List<Customer> customers = new ArrayList<>();
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(path));

			JSONArray jsonData = (JSONArray) obj;

			Iterator<JSONObject> iterator = jsonData.iterator();
			int goodsQuantity = 1;
			int id = 0;

			while (iterator.hasNext()) {

				JSONObject nextItem = iterator.next();

				String name = (String) nextItem.get("name");
				JSONArray rent = (JSONArray) nextItem.get("rentUnit");
				RentUnit rentUnit = new RentUnit();

				Iterator<JSONObject> iter = rent.iterator();
				while (iter.hasNext()) {
					JSONObject nextUnit = iter.next();

					String title = (String) nextUnit.get("title");
					String priceString = (String) nextUnit.get("price");
					String categoryName = (String) nextUnit.get("category");
					int price = Integer.parseInt(priceString);

					SportEquipment unit = new SportEquipment(++id, title, price, new Category(++id, categoryName));
					rentUnit.addUnit(unit);
				}
				Customer customer = new Customer(id, name, rentUnit);

				customers.add(customer);
			}

		} catch (IOException | ParseException e) {
			log.error("Cannot read data from file with path=" + path, e);
			throw new DomainException("Cannot read data from file with path=" + path, e);
		}

		return customers;
	}
}
