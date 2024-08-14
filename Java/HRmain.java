package com.cathaybk.practice.nt50350.b;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HRmain {
	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Sales("張志城", "信用卡部", 35000, 6000));
		employeeList.add(new Sales("林大鈞", "信用卡部", 38000, 4000));
		employeeList.add(new Supervisor("李中白", "資訊部", 65000));
		employeeList.add(new Supervisor("林小中", "理財部", 80000));

		// 打印薪資單
		for (Employee employee : employeeList) {
			employee.printInfo();

		}

		File file = new File("C:\\Users\\Admin\\Desktop\\HRmain.csv");

		// UTF-8 BOM 有些文字編輯器需要透過BOM來識別文件是UTF-8編碼
		byte[] utf8bom = { (byte) 0xef, (byte) 0xbb, (byte) 0xbf };

		// 有中文的檔案轉檔不能只用BufferedWriter,FileWriter
		try (FileOutputStream fos = new FileOutputStream(file);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8))) {
			
		Collections.sort(employeeList, new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				
				return o2.getDepartment().compareTo(o1.getDepartment());
			}
		});	

			// 寫入BOM到文件開頭
			fos.write(utf8bom);

			// 寫入name,payment
			
			for (Employee employee : employeeList) {
			String name = employee.getName();
				if (employee instanceof Sales) {
					Sales sales = (Sales) employee;
					bw.write(name+ "," + sales.getPayment());
					bw.newLine();
				} else {
					Supervisor supervisor = (Supervisor) employee;
					bw.write(name + "," + supervisor.getPayment());
					bw.newLine();
				}
				bw.flush();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}