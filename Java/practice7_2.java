package com.cathaybk.practice.nt50350.b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class practice7_2 {

	private static final String CONN_URL = "jdbc:oracle:thin:@//localhost:1521/XE";
	private static final String USERNAME = "student";
	private static final String PASSWORD = "student123456";
	private static final String INSERT_SQL = "insert into STUDENT.CARS (MANUFACTURER, TYPE, MIN_PRICE, PRICE) values (?, ?, ?, ?)";
	private static final String SELECT_SQL = "select * from STUDENT.CARS where MANUFACTURER = ? and TYPE = ?";
	private static final String DELETE_SQL = "delete from STUDENT.CARS where MANUFACTURER =? and TYPE =?";
	private static final String UPDATE_SQL = "update STUDENT.CARS set MIN_PRICE = ?, PRICE =? where MANUFACTURER =? and TYPE = ?";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("請選擇以下指令輸入:select, insert, update, delete");
		String order = scanner.next(); // switch case寫 程式好讀
		switch (order) {
		case "select":
			doQuery();
			break;
		case "insert":
			doInsert();
			break;
		case "update":
			doUpdate();
			break;
		case "delete":
			doDelete();
			break;
		}
		scanner.close();
	}

	// 新增資料方法
	private static void doInsert() {
		PreparedStatement pstmt = null;
		// try-with-resources自動關閉 connection,scanner
		try (Connection conn = DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
				Scanner scanner = new Scanner(System.in);) {

			conn.setAutoCommit(false);
			System.out.println("請輸入製造商:");
			String manufacturer = scanner.next();
			System.out.println("請輸入類型:");
			String type = scanner.next();
			System.out.println("請輸入底價:");
			String minprice = scanner.next();
			System.out.println("請輸入售價:");
			String price = scanner.next();

			pstmt = conn.prepareStatement(INSERT_SQL);
			pstmt.setString(1, manufacturer);
			pstmt.setString(2, type);
			pstmt.setString(3, minprice);
			pstmt.setString(4, price);
			pstmt.executeUpdate();

			conn.commit();
			System.out.println("新增成功");

		} catch (SQLException sqle) {
			System.out.println("新增失敗，原因:" + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
	}

	// 查詢資料方法
	private static void doQuery() {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		// try catch只要一層
		try (Connection conn = DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
				Scanner scanner = new Scanner(System.in);) {

			System.out.println("請輸入製造商:");
			String manufacturer = scanner.next();
			System.out.println("請輸入類型:");
			String type = scanner.next();

			pstmt = conn.prepareStatement(SELECT_SQL);
			pstmt.setString(1, manufacturer);
			pstmt.setString(2, type);

			// 查詢結果
			rs = pstmt.executeQuery();

			// sb連接字串
			StringBuilder sb = new StringBuilder();

			if ((rs.getRow()) > 1) { // rs.getRow():rs內總筆數
				sb.append("查詢結果:");
				while (rs.next()) {
					sb.append("製造商:").append(rs.getString("MANUFACTURER")).append("，類型:").append(rs.getString("TYPE"))
							.append("，底價:").append(rs.getString("MIN_PRICE")).append("，售價:")
							.append(rs.getString("PRICE")).append("\n");
				}
				System.out.println(sb.toString());
			} else {
				System.out.println("查無結果");
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (rs != null) { // rs在這要可以拿到要先在全域宣告
				try {
					rs.close();
				} catch (SQLException sqle) {

					sqle.printStackTrace();
				}
			}
			if (pstmt != null) { // pstmt在這要可以拿到要先在全域宣告
				try {
					pstmt.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

		}

	}

	// 刪除資料方法
	private static void doDelete() {
		PreparedStatement pstmt = null;

		try (Connection conn = DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
				Scanner scanner = new Scanner(System.in);) {

			conn.setAutoCommit(false);
			System.out.println("請輸入製造商:");
			String manufacturer = scanner.next();
			System.out.println("請輸入類型:");
			String type = scanner.next();

			pstmt = conn.prepareStatement(DELETE_SQL);
			pstmt.setString(1, manufacturer);
			pstmt.setString(2, type);

			if ((pstmt.executeUpdate()) < 1) {
				System.out.println("刪除失敗，查無資料");
				return;
			}
			System.out.println("刪除成功");
			conn.commit();

		} catch (SQLException sqle) {
			System.out.println("刪除失敗，原因:" + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
	}

	// 更新資料方法
	private static void doUpdate() {
		PreparedStatement pstmt = null;

		try (Connection conn = DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
				Scanner scanner = new Scanner(System.in);) {

			conn.setAutoCommit(false);
			System.out.println("請輸入製造商:");
			String manufacturer = scanner.next();
			System.out.println("請輸入類型:");
			String type = scanner.next();
			System.out.println("請輸入底價:");
			String minprice = scanner.next();
			System.out.println("請輸入售價:");
			String price = scanner.next();

			pstmt = conn.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, minprice);
			pstmt.setString(2, price);
			pstmt.setString(3, manufacturer);
			pstmt.setString(4, type);

			if (pstmt.executeUpdate() > 0) {
				conn.commit();
				System.out.println("更新成功");
			} else {
				System.out.println("更新失敗，資料不存在");
				return;
			}

		} catch (SQLException sqle) {
			System.out.println("更新失敗，原因:" + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
	}
}