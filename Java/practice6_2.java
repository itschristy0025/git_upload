package com.cathaybk.practice.nt50350.b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class practice6_2 {
	public static void main(String[] args) {
		File inputFile = new File("C:\\Users\\Admin\\Downloads\\Java評量_第6題cars.csv");
		String[] header = null;
		List<Map<String, String>> dataList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile, StandardCharsets.UTF_8))) {
			String headLine;
			if ((headLine = br.readLine()) != null) {
				header = headLine.split(",");

				String line;
				while ((line = br.readLine()) != null) {
					String[] values = line.split(",");
					if (header.length == values.length) {
						Map<String, String> eachMap = new HashMap<>();
						for (int i = 0; i < header.length; i++) {
							eachMap.put(header[i], values[i]);
						}
						dataList.add(eachMap);
					} else {
						System.out.println("Skipping malformed line:" + line);
					}
				}

			} else {
				System.out.println("no header");
			}
			// 資料排序
			Collections.sort(dataList, new Comparator<Map<String, String>>() {

				@Override
				public int compare(Map<String, String> o1, Map<String, String> o2) {
					return o1.get("Manufacturer").compareTo(o2.get("Manufacturer"));
				}
			});

			// 先建立一個Map,用來裝最後分組的結果 {Acura,[{__=__,__=__},{__=__,__=__}(這就是carslist)]}
			Map<String, List<Map<String, String>>> result = new TreeMap<>(); // 有排序要用Treelist

			for (Map<String, String> carsMap : dataList) {
				String manufacturer = carsMap.get("Manufacturer");
				List<Map<String, String>> carsList = result.get(manufacturer); // 這邊是去判斷manufacturer有無出現過
				if (carsList == null) {
					carsList = new ArrayList<>();
					result.put(manufacturer, (List<Map<String, String>>) carsList);
				}
				carsList.add(carsMap);
			}
			System.out.printf("%-5s %-5s %-5s %5s\n","Manufacturer","TYPE","Min.PRICE","Price");

			BigDecimal totalminPrice = BigDecimal.ZERO;
			BigDecimal totalPrice = BigDecimal.ZERO;
			for (Entry<String, List<Map<String, String>>> entry : result.entrySet()) { // test

				List<Map<String, String>> carList = entry.getValue();



				BigDecimal etotalminPrice = BigDecimal.ZERO; // 進入下一個新迴圈時，又重新開始計算
				BigDecimal etotalPrice = BigDecimal.ZERO;

				for (Map<String, String> carMap : carList) { //entry不同時就會跳出迴圈
					BigDecimal minPrice = new BigDecimal(carMap.get("Min.Price"));
					BigDecimal Price = new BigDecimal(carMap.get("Price"));
	
					etotalminPrice = etotalminPrice.add(minPrice);
					etotalPrice = etotalPrice.add(Price);
					totalminPrice = totalminPrice.add(minPrice);
					totalPrice = totalPrice.add(Price);
					System.out.printf("%-10s %-10s %6s %s\n", carMap.get("Manufacturer"), carMap.get("Type"),
							carMap.get("Min.Price"), carMap.get("Price"));

				}
				System.out.printf("小計%25s %3s\n", etotalminPrice, etotalPrice);

			}

			System.out.printf("合計%25s %3s\n", totalminPrice, totalPrice);

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}