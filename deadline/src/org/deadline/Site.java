package org.deadline;

public class Site {
	private String name;
	private String money;
	private String amount;
	
	public Site(){}
	public Site(String name, String money, String amount){
		this.name = name;
		this.money = money;
		this.amount = amount;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getMoney() {
		return money;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAmount() {
		return amount;
	}
}
