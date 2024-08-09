package com.cathaybk.practice.nt50350.b;

public class practice1 {
	public static void main(String[] args) {

		for (int j = 0, k=1 ; j < 9; j++,k++) {
			for (int i = 2; i < 10; i++) {
				System.out.printf("%s*%s=%s\t", i, k, (i*k));
			}
			System.out.println();
		}
	}
}


