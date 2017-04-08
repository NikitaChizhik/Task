package com.nikitachizhik91.shop.domain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.nikitachizhik91.shop.model.Category;
import com.nikitachizhik91.shop.model.SportEquipment;

public final class JsonDataReader {

	private final static Logger log = LogManager.getLogger(JsonDataReader.class.getName());

	private JsonDataReader() {
	}

	public static Map<SportEquipment, Integer> fromFile(String path) throws DomainException {

		Map<SportEquipment, Integer> goods = new HashMap<>();
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(
					"/home/nikita/eclipse/workspace/RentSportShop_01_2/resources/JsonSportFile"));

			JSONArray jsonData = (JSONArray) obj;

			Iterator<JSONObject> iterator = jsonData.iterator();
			int goodsQuantity = 1;
			int id = 1;

			while (iterator.hasNext()) {

				JSONObject next = iterator.next();

				String title = (String) next.get("title");
				String category = (String) next.get("category");
				String priceString = (String) next.get("price");
				int price = Integer.parseInt(priceString);

				SportEquipment sportEquipment = new SportEquipment(id++, new Category(category), title, price);

				if (goods.containsKey(sportEquipment)) {
					goods.put(sportEquipment, goods.get(sportEquipment) + 1);
				} else {
					goods.put(sportEquipment, goodsQuantity);
				}

			}

		} catch (FileNotFoundException e) {
			log.error("Cannot read data from file with path=" + path, e);
			throw new DomainException("Cannot read data from file with path=" + path, e);
			// IOExce stho delaet?
		} catch (IOException e) {
			log.error("Cannot read data from file with path=" + path, e);
			throw new DomainException("Cannot read data from file with path=" + path, e);
		} catch (ParseException e) {
			log.error("Cannot read data from file with path=" + path, e);
			throw new DomainException("Cannot read data from file with path=" + path, e);
		}

		return goods;
	}
}
