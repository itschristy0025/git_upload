package com.cathaybk.practice.nt50350.b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class happydog {
	public static void main(String[] args) {
		File inputFile = new File("C:\\Users\\Admin\\Downloads\\Java評量_第6題cars.csv");
		String[] header = null;
		List<Map<String, String>> dataList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile, StandardCharsets.UTF_8))) {
			String headLine;
			if ((headLine = br.readLine()) == null) {
				System.out.println("no header");
			} else {
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

				/*
				 * collect 將流的結果收集到某種集合中(無法確定流的順序，所以在流的地方做排序就好) Collectors.groupingBy收集器，收集了三個參數
				 * 1.分組依據 2.Map型態 3.下游收集器 item -> item.get("Manufacturer") 一種lambda表示式
				 * item是流中的每個元素（在這裡是每個 Map<String, String>）
				 * 
				 */

				Map<String, List<Map<String, String>>> listMap = dataList.stream() // 將列表轉換成流(一種Java8的引入特性，可以更方便的處理集合中的數據)
						.collect(Collectors.groupingBy(item -> item.get("Manufacturer"), TreeMap::new,
								Collectors.toList()));

				System.out.printf("%-5s %-5s %-5s %5s\n", "Manufacturer", "TYPE", "Min.PRICE", "Price");

				BigDecimal summ = BigDecimal.ZERO;
				BigDecimal sum = BigDecimal.ZERO;
				for (String aa : listMap.keySet()) {
					BigDecimal totalm = BigDecimal.ZERO;
					BigDecimal total = BigDecimal.ZERO;
					for (Map<String, String> bb : listMap.get(aa)) {
						
						System.out.printf("%-10s %-10s %6s %s\n", bb.get("Manufacturer"), bb.get("Type"),
								bb.get("Min.Price"), bb.get("Price"));

						BigDecimal minprice = new BigDecimal(bb.get("Min.Price"));
						BigDecimal price = new BigDecimal(bb.get("Price"));
						
						totalm = totalm.add(minprice); 
						total = total.add(price);	
					}
					System.out.printf("小計%25s %3s\n", totalm, total);
					summ= summ.add(totalm);
					sum = sum.add(total);
				}
				System.out.printf("合計%25s %3s\n", summ, sum);
			}

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
