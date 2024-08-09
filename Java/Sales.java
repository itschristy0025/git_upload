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
		System.out.printf("薪資單\n");
		System.out.printf("姓名: %s 工作部門: %s\n", getName(), getDepartment());
		System.out.printf("月薪: %s\n", getSalary());
		System.out.printf("業績獎金: %s\n", getBouns());
		System.out.printf("總計: %s\n", payment);
	}
}
