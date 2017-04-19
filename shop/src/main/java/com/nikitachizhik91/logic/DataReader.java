package com.nikitachizhik91.logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.model.Category;
import com.nikitachizhik91.model.SportEquipment;

public final class DataReader {

	private final static Logger log = LogManager.getLogger(DataReader.class.getName());

	private DataReader() {
	}

	public static Map<SportEquipment, Integer> fromFile(String path) throws DomainException {

		Map<SportEquipment, Integer> goods = new HashMap<>();

		try (FileInputStream input = new FileInputStream(path);
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));

		) {
			String line = reader.readLine();

			int goodsQuantity = 1;
			int id = 1;

			while (line != null) {

				String[] items = line.split(" ");

				String category = items[0];
				String title = items[1];
				int price = Integer.parseInt(items[2]);

				SportEquipment sportEquipment = new SportEquipment(++id, title, price, new Category(id++, category));
				// counts items is it ok to put it here?
				if (goods.containsKey(sportEquipment)) {
					goods.put(sportEquipment, goods.get(sportEquipment) + 1);
				} else {
					goods.put(sportEquipment, goodsQuantity);
				}

				line = reader.readLine();
			}

		} catch (IOException e) {
			log.error("Cannot read data from file with path=" + path, e);
			throw new DomainException("Cannot read data from file with path=" + path, e);
		}

		return goods;
	}
}
