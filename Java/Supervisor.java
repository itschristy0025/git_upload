package com.cathaybk.practice.nt50350.b;

public class Supervisor extends Employee {
	private int payment;

	public Supervisor(String name, String department, int salary) {
		super(name, department, salary);
		this.payment=salary;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	@Override
	public void printInfo() {
		System.out.printf("薪資單\n");
		System.out.printf("姓名: %s 工作部門: %s\n", getName(), getDepartment());
		System.out.printf("月薪: %s\n", getSalary());
		System.out.printf("總計: %s\n", payment);

	}

}
