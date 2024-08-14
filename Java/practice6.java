package com.cathaybk.practice.nt50350.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class practice6 {
	public static void main(String[] args) {
		File inputFile = new File("C:\\Users\\Admin\\Downloads\\Java評量_第6題cars.csv");
		File outputFile = new File("\\Users\\Admin\\Desktop\\cars2.csv");
		List<Map<String, String>> dataList = new ArrayList<>();
		String[] header = null;

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile, StandardCharsets.UTF_8));
				BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, StandardCharsets.UTF_8))) {
			String headLine = br.readLine();
			if (headLine == null) {
				System.out.print("no headerline found.");
				return;
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
			}

			// 資料排序
			Collections.sort(dataList, new Comparator<Map<String, String>>() {

				@Override
				public int compare(Map<String, String> o1, Map<String, String> o2) {

					BigDecimal price1 = new BigDecimal(o1.get("Price"));
					BigDecimal price2 = new BigDecimal(o2.get("Price"));
					return price2.compareTo(price1);
				}
			});

			// 資料輸出csv檔

			// 寫入表頭
			if (header == null) {
				System.out.println("There is no headline");
				return;
			} else {
				StringBuilder sb = new StringBuilder();
				
				sb.append(header[0]).append(",").append(header[1]).append(",").append(header[2]).append(",")
				.append(header[3]);
				bw.write(sb.toString()); // 不建議用join，建議改使用StringBuilder
				bw.newLine();
				sb.setLength(0);
				// 寫入數據
				for (Map<String, String> data : dataList) {
					sb.append(data.get("Manufacturer")).append(",").append(data.get("Type")).append(",")
					.append(data.get("Min.Price")).append(",").append(data.get("Price"));
					bw.write(sb.toString()); 
					bw.newLine();
					sb.setLength(0);
				}
				System.out.println("csv檔輸出成功");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}
}
