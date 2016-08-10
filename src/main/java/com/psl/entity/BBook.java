package com.psl.entity;

public class BBook {
	private String name;
	private int Copies;
	@Override
	public String toString() {
		return "BBook [ name=" + name + ", Copies="
				+ Copies + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCopies() {
		return Copies;
	}
	public void setCopies(int copies) {
		Copies = copies;
	}
	
	

}
