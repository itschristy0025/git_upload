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
	private static final String CONN_URL = "jdbc:oracle:thin:@//localhost:1521/XE"; 
	private static final String USERNAME = "student";
	private static final String PASSWORD_STRING = "student123456";
	private static final String SELECT_SQL = "select * from STUDENT.CARS";

	public static void main(String[] args) {
		List<Map<String, String>> carsList = new ArrayList<>();

		// 建立連線
		try (Connection conn = DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD_STRING);

				PreparedStatement pstmt = conn.prepareStatement(SELECT_SQL); 
		) {
			// ResultSet儲存查詢結果
			ResultSet rs = pstmt.executeQuery();
			// 每筆資料包裝成Map後再放入List中
			while (rs.next()) {
				Map<String, String> carMap = new HashMap<>();
				carMap.put("MANUFACTURER", rs.getString("MANUFACTURER"));
				carMap.put("TYPE", rs.getString("TYPE"));
				carMap.put("MIN_PRICE", rs.getString("MIN_PRICE"));
				carMap.put("PRICE", rs.getString("PRICE"));
				carsList.add(carMap);
			}
			// foreach逐一拿取List中Map的資料
			for (Map<String, String> car : carsList) {
				System.out.println(car);
			}
			// 關閉資源
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
