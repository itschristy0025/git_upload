package com.cathaybk.practice.nt50350.b;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class practice2 {
	public static void main(String[] args) {
		Random randNum = new Random();
		Set<Integer> orderSet = new TreeSet<>();
		Set<Integer> unorderSet = new HashSet<>();

		while (unorderSet.size() < 6) {
			int randomLotto = randNum.nextInt(49) + 1;
			unorderSet.add(randomLotto);
			orderSet.add(randomLotto);
		}

		// 排序前
		StringBuilder sb = new StringBuilder();
		for (Integer eachunorderSet : unorderSet) {
			sb.append(eachunorderSet).append(" ");
		}
		System.out.println("排序前:" + sb.toString());
		sb.setLength(0);

		// 排序後
		for (Integer eachorderSet : orderSet) {
			sb.append(eachorderSet).append(" ");
		}
		System.out.println("排序後:" + sb.toString());
		sb.setLength(0);
	}

}
