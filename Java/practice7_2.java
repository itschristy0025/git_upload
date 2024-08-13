package com.cathaybk.practice.nt50350.b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class practice7_2 {

	public static final String conn_url = "jdbc:oracle:thin:@//localhost:1521/XE";

	public static final String update_sql = "update STUDENT.CARS set MIN_PRICE = ? and PRICE =? where MANUFACTURER =? and TYPE = ?";
	public static final String delete_sql = "delete from STUDENT.CARS where MANUFACTURER =? and TYPE =?";
	public static final String username = "student";
	public static final String password = "student123456";

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("請選擇以下指令輸入:select, insert, update, delete");
			String point = scanner.next();
			if (point.equals("select")) {
				doQuery();
			} else if (point.equals("insert")) {
				doInsert();
			} else if (point.equals("update")) {
				doUpdate();
			} else if (point.equals("delete")) {
				doDelete();
			} else {
				System.out.println("輸入錯誤");
			}
		}

	}

	// 新增資料方法
	public static void doInsert() {
		// try-with-resources自動關閉 connection,scanner
		try (Connection conn = DriverManager.getConnection(conn_url, username, password);
				Scanner scanner = new Scanner(System.in);) {
			try {
				conn.setAutoCommit(false);
				System.out.println("請輸入製造商:");
				String manufacturer = scanner.next();
				System.out.println("請輸入類型:");
				String type = scanner.next();
				System.out.println("請輸入底價:");
				String minprice = scanner.next();
				System.out.println("請輸入售價:");
				String price = scanner.next();

				PreparedStatement pstmt = conn.prepareStatement(
						"insert into STUDENT.CARS (MANUFACTURER, TYPE, MIN_PRICE, PRICE) values (?, ?, ?, ?)");
				pstmt.setString(1, manufacturer);
				pstmt.setString(2, type);
				pstmt.setString(3, minprice);
				pstmt.setString(4, price);
				pstmt.executeUpdate();

				conn.commit();
				System.out.println("新增成功");
			} catch (Exception e) {
				System.out.println("新增失敗，原因:" + e.getMessage());
			}

		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		}
	}

	// 查詢資料方法
	public static void doQuery() {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		// try catch只要一層
		try (Connection conn = DriverManager.getConnection(conn_url, username, password);
				Scanner scanner = new Scanner(System.in);) {

			System.out.println("請輸入製造商:");
			String manufacturer = scanner.next();
			System.out.println("請輸入類型:");
			String type = scanner.next();

			pstmt = conn.prepareStatement("select * from STUDENT.CARS where MANUFACTURER = ? and TYPE = ?");
			pstmt.setString(1, manufacturer);
			pstmt.setString(2, type);

			// 查詢結果
			rs = pstmt.executeQuery();

			// sb連接字串
			StringBuilder sb = new StringBuilder();

			sb.append("查詢結果:");
			if (rs != null) {
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) { // rs在這要可以拿到要先在全域宣告
				try {
					rs.close();
				} catch (SQLException sqle) {
					// TODO: handle exception
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
	public static void doDelete() {
		PreparedStatement pstmt = null;

		try (Connection conn = DriverManager.getConnection(conn_url, username, password);
				Scanner scanner = new Scanner(System.in);) {

			try {
				conn.setAutoCommit(false);
				System.out.println("請輸入製造商:");
				String manufacturer = scanner.next();
				System.out.println("請輸入類型:");
				String type = scanner.next();

				pstmt = conn.prepareStatement("delete from STUDENT.CARS where MANUFACTURER =? and TYPE =?");
				pstmt.setString(1, manufacturer);
				pstmt.setString(2, type);
				if ((pstmt.executeUpdate()) < 1) {
					throw new SQLException("無此資料"); // 手動丟出，下面的SQLException會接到
				}
				System.out.println("刪除成功");
				conn.commit();

			} catch (SQLException sqle) {
				System.out.println("刪除失敗，原因:" + sqle.getMessage()); // getMessage接到參數
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
				}
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 更新資料方法
	public static void doUpdate() {
		PreparedStatement pstmt = null;

		try (Connection conn = DriverManager.getConnection(conn_url, username, password);
				Scanner scanner = new Scanner(System.in);) {
			try {
				conn.setAutoCommit(false);
				System.out.println("請輸入製造商:");
				String manufacturer = scanner.next();
				System.out.println("請輸入類型:");
				String type = scanner.next();
				System.out.println("請輸入底價:");
				String minprice = scanner.next();
				System.out.println("請輸入售價:");
				String price = scanner.next();

				pstmt = conn.prepareStatement(
						"update STUDENT.CARS set MIN_PRICE = ?, PRICE =? where MANUFACTURER =? and TYPE = ?");
				pstmt.setString(1, minprice);
				pstmt.setString(2, price);
				pstmt.setString(3, manufacturer);
				pstmt.setString(4, type);
				
				if ((pstmt.executeLargeUpdate())<1) {
					throw new SQLException("無此資料"); // 手動丟出，下面的SQLException會接到
				}
				
				conn.commit();
				System.out.println("更新成功");

			} catch (SQLException sqle) {
				System.out.println("更新失敗，原因:" + sqle.getMessage());
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
				}
			}

		} catch (SQLException sqle) {

		}
	}
}