package com.psl.entity;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
	private int book_id;
private int customer_id;
	
private Date due_date;

public int getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(int customer_id) {
	this.customer_id = customer_id;
}
public int getBook_id() {
	return book_id;
}
public void setBook_id(int book_id) {
	this.book_id = book_id;
}
public Date getDue_date() {
	return due_date;
}
public void setDue_date(Date date) {
	this.due_date = date;
}

@Override
public String toString() {
	return "Transaction [customer_id=" + customer_id + ", book_id=" + book_id
			+ ", due_date=" + due_date + "]";
}

}
