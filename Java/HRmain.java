package com.cathaybk.practice.nt50350.b;

import java.beans.beancontext.BeanContext;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HRmain {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Sales("張志城", "信用卡部", 35000, 6000));
		employeeList.add(new Sales("林大鈞", "信用卡部", 38000, 4000));
		employeeList.add(new Supervisor("李中白", "資訊部", 65000));
		employeeList.add(new Supervisor("林小中", "理財部", 80000));

		// 打印薪資單
		for (Employee employee : employeeList) {
			employee.printInfo();

			File file = new File("C:\\Users\\Admin\\Desktop\\HRmain.csv");

			try (FileOutputStream out = new FileOutputStream(file);
					OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
					BufferedWriter bw = new BufferedWriter(osw)) {
				
				// 寫入UTF-8
				byte[] uft8bom = { (byte) 0xef, (byte) 0xbb, (byte) 0xbf };
				out.write(uft8bom);
				
				//寫入name payment
				for (Employee writeempEmployee : employeeList) {
					if(writeempEmployee instanceof Sales) {
						Sales sales = (Sales) writeempEmployee;
						bw.write(writeempEmployee.getName()+ "," +sales.getPayment());
						bw.newLine();
					}else {
						Supervisor supervisor = (Supervisor)writeempEmployee;
						bw.write(writeempEmployee.getName()+ ","+supervisor.getPayment());
						bw.newLine();
					}
					bw.flush();
				}

			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}


	}
}