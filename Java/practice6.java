package com.cathaybk.practice.nt50350.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile, StandardCharsets.UTF_8))) {
			String headLine = br.readLine();
			if (headLine != null) {
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
				System.out.print("no headerline found.");
			}

			// 資料排序
			Collections.sort(dataList, new Comparator<Map<String, String>>() {

				@Override
				public int compare(Map<String, String> o1, Map<String, String> o2) {
					return o2.get("Price").compareTo(o1.get("Price"));
				}
			});

			// 資料輸出csv檔
			try (
					BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, StandardCharsets.UTF_8))) {
				// 寫入表頭
				if (header != null) {
					bw.write(String.join(",", header));
					bw.newLine();
					// 寫入數據
					for (Map<String, String> data : dataList) {
						List<String> inputList = new ArrayList<>();
						inputList.add(data.get("Price"));
						inputList.add(data.get("Type"));
						inputList.add(data.get("Min.Price"));
						inputList.add(data.get("Price"));
						bw.write(String.join(",", inputList));
						bw.newLine();
					}
					System.out.println("成功");
				} else {
					System.out.println("There is no headline");
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}
}
