package com.cathaybk.practice.nt50350.b;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HRmain {
	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Sales("張志城", "信用卡部", 35000, 6000));
		employeeList.add(new Sales("林大鈞", "信用卡部", 38000, 4000));
		employeeList.add(new Supervisor("李中白", "資訊部", 65000));
		employeeList.add(new Supervisor("林小中", "理財部", 80000));

		for (Employee employee : employeeList) {
			employee.printInfo();
		}
		File f = new File("C:\\Users\\Admin\\Desktop\\HRmain.csv");
		FileWriter fw = null;
		BufferedWriter bw = null;
		FileOutputStream out = null;
		OutputStreamWriter osw = null;

		try {
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			out = new FileOutputStream(f);
			byte[] uft8bom = { (byte) 0xef, (byte) 0xbb, (byte) 0xbf };
			out.write(uft8bom);
			osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
			bw = new BufferedWriter(osw);

			for (Employee employee : employeeList) {
				if (employee instanceof Sales) {
					Sales sales = (Sales) employee;
					bw.write(employee.getName() + "," + sales.getPayment());
					bw.newLine();
				} else {
					Supervisor supervisor = (Supervisor) employee;
					bw.write(employee.getName() + "," + supervisor.getPayment());
					bw.newLine();
				}
			}

		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
				if (fw != null) {
					try {
						fw.close();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			}
		}

	}

}

//
//import java.io.BufferedWriter;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class HRmain {
//	public static void main(String[] args) {
//		List<Employee> employeeList = new ArrayList<>();
//		employeeList.add(new Sales("張志城", "信用卡部", 35000, 6000));
//		employeeList.add(new Sales("林大鈞", "保代部", 38000, 4000));
//		employeeList.add(new Supervisor("李中白", "資訊部", 65000));
//		employeeList.add(new Supervisor("林小中", "理財部", 80000));
//		
//		
//		for (Employee employee : employeeList) {
//			employee.printInfo();
//		}
//		
//		String filePath = "C:\\Users\\Admin\\Desktop\\output.csv";
//		try (BufferedWriter writer = new BufferedWriter(
//				new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"))) {
//
//
//			for (Employee eacheEmployee : employeeList) {
//				writer.write(eacheEmployee.getName());
//				writer.newLine();
//			}
//
//			writer.flush();
//			System.out.println("成功");
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
//}
