package com.cathaybk.practice.nt50350.b;

public class happydog {

	public static void doQuery() {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Scanner scanner = null;

	    try {
	        // 建立資料庫連線
	        conn = DriverManager.getConnection(conn_url, username, password);
	        conn.setAutoCommit(false);

	        // 讀取用戶輸入
	        scanner = new Scanner(System.in);
	        System.out.println("請輸入製造商:");
	        String manufacturer = scanner.next();
	        System.out.println("請輸入類型:");
	        String type = scanner.next();

	        // 設置 SQL 查詢
	        String insert_sql = "SELECT * FROM cars WHERE MANUFACTURER = ? AND TYPE = ?";
	        pstmt = conn.prepareStatement(insert_sql);
	        pstmt.setString(1, manufacturer);
	        pstmt.setString(2, type);
	        
	        // 執行查詢
	        rs = pstmt.executeQuery();

	        // sb 連接字串
	        StringBuilder sb = new StringBuilder();
	        sb.append("查詢結果:");

	        if (rs != null) {
	            while (rs.next()) {
	                sb.append("製造商:").append(rs.getString("MANUFACTURER"))
	                  .append("，類型:").append(rs.getString("TYPE"))
	                  .append("，底價:").append(rs.getString("MIN_PRICE"))
	                  .append("，售價:").append(rs.getString("PRICE"))
	                  .append("\n");
	            }
	            System.out.println(sb.toString());
	        } else {
	            System.out.println("查無結果");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // 關閉 ResultSet
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        // 關閉 PreparedStatement
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        // 關閉 Connection
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        // 關閉 Scanner
	        if (scanner != null) {
	            scanner.close();
	        }
	    }
	}

}
