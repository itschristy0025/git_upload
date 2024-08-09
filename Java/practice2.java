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
		for (int i = 0; i < 6; i++) {
			int randomLotto = randNum.nextInt(49) + 1;
			orderSet.add(randomLotto);
			unorderSet.add(randomLotto);
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

////		System.out.println(StringUtils.strip(orderSet.toString(),"[]"));
//System.out.println("排序前: " + orderSet.toString());
//System.out.println("排序後: "+ unorderSet);
//
//while () {}
//System.out.printf("排序後:%s ", eachunorderSet.);
//
//	}

//			
//			if (lottomap.contains(randomLotto))
//			lottomap.add(randomLotto);
//			System.out.println(lottomap);
//

//Map<Integer,Integer> lottomap =new HashMap<>();
//
//for (int i=0;lottomap.size()<6; i++) {
//	int randomLotto =randNum.nextInt(49)+1;
//	lottomap.put(randomLotto, i);
//}
//System.out.println(lottomap.get(lottomap));