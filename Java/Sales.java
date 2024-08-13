package com.cathaybk.practice.nt50350.b;

public class Sales extends Employee {
	private int bouns;
	private int payment;

	public int getBouns() {
		return bouns;
	}

	public void setBouns(int bouns) {
		this.bouns = bouns;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public Sales(String name, String department, int salary, int performance) {
		super(name, department, salary);
		this.bouns = (int) (performance*0.05);
		this.payment = salary+bouns;
	}

	@Override
	public void printInfo() {
		super.printInfo();
		System.out.printf("業績獎金: %s\n總計: %s\n", getBouns(), payment);
	}
}
