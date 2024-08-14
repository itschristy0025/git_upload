package com.cathaybk.practice.nt50350.b;

import java.util.Calendar;
import java.util.Scanner;

public class practice5 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("請輸入介於1到12間的整數"); 
		int month = scanner.nextInt();
		
		// 防呆機制，避免輸入其他數字
		if(month < 1 || month >12) {
			System.out.println("輸入錯誤，請輸入介於1到12間的整數");
			scanner.close();
			return;
		}
		
		// 取當前的時間
		Calendar calender = Calendar.getInstance(); // Calendar是抽象類別不能new物件，這裡其實是用子類別GregorianCalendar new
		int year = calender.get(Calendar.YEAR);

		// 設定月曆, set方法會改變calender本身的值
		calender.set(Calendar.MONTH, month - 1); // 月份從0開始算起，一月0,二月1...
		calender.set(Calendar.YEAR, year);

		calender.set(Calendar.DAY_OF_MONTH, 1); // DAY_OF_MONTH是這個月的第幾天

		int monthdays = calender.getActualMaximum(Calendar.DAY_OF_MONTH);

		// 製作圖表
		System.out.printf("%10s年%s月\n", year, month);
		System.out.println("------------------");
		System.out.println("日  一  二  三 四  五 六 ");
		System.out.println("==================");

		// 開始前的空格
		int firstday = calender.get(Calendar.DAY_OF_WEEK); // Calender.SUNDAY=1...
		for (int i = Calendar.SUNDAY; i < firstday; i++) {
			System.out.printf("%3s"," "); //改成%s 更容易對齊
		}

		// 製作當月的數字
		for (int day = 1; day <= monthdays; day++) {
			System.out.printf("%2s ", day);
			if ((day + firstday - 1) % 7 == 0) {
				System.out.println();
			}

		}
		System.out.println();
		scanner.close();

	}

}
