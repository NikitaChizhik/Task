package shoptask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import shoptask.model.Category;
import shoptask.model.SportEquipment;

public final class FileUtil {

	private FileUtil() {
	}

	// глянуть как читать из файла в json формате.
	public static Map<SportEquipment, Integer> dataFromFile(String path) throws FileNotFoundException {

		Map<SportEquipment, Integer> dataFromFile = new HashMap<>();

		Scanner scanner = new Scanner(new File(path));
		int goodsQuantity = 1;
		int id = 0;
		// razniza mejdu flagom = boolean i break here: s metkoy?? est ili kak
		// ugodno. mojno etot metod po raznomu napisat.
		while (scanner.hasNext()) {

			String categoryName = scanner.next();
			String sportEquipmentTitle = scanner.next();
			int sportEquipmentPrice = scanner.nextInt();
			// id++ raznizi vrode zhe netU? est! man ((( nedumal chego-to
			SportEquipment sportEquipment = new SportEquipment(++id, new Category(categoryName), sportEquipmentTitle,
					sportEquipmentPrice);

			dataFromFile.put(sportEquipment, goodsQuantity);

		}
		// prosto proverka bila.
		// for (Map.Entry<SportEquipment, Integer> item :
		// dataFromFile.entrySet()) {
		// System.out.println(item);
		// }
		/**
		 * defines how much items of each Unit do we have.
		 * */
		

		return dataFromFile;
	}

	
}
