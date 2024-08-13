package com.cathaybk.practice.nt50350.b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class practice7 {
	public static void main(String[] args) {
		String connUrl = "jdbc:oracle:thin:@//localhost:1521/XE";
		List<Map<String, String>> carsList= new ArrayList<>();
		
		// 建立連線
		try(Connection conn = DriverManager.getConnection(connUrl, "student", "student123456");
				PreparedStatement pstmt = conn.prepareStatement("select * from STUDENT.CARS");
				){
		// ResultSet儲存查詢結果
			ResultSet rs = pstmt.executeQuery();
		// 每筆資料包裝成Map後再放入List中
			while(rs.next()) {
				Map<String,String> carMap = new HashMap<>();
				carMap.put("MANUFACTURER", rs.getString("MANUFACTURER"));
				carMap.put("TYPE", rs.getString("TYPE"));
				carMap.put("MIN_PRICE", rs.getString("MIN_PRICE"));
				carMap.put("PRICE", rs.getString("PRICE"));
				carsList.add(carMap);
			}
		// foreach逐一拿取List中Map的資料
			for(Map<String, String> car:carsList) {
				System.out.println(car);
			}
		// 關閉資源
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
