package com.psl.dao;

import java.util.Date;
import java.util.List;

import Exceptions.DataNotFoundException;

import com.psl.entity.Admin;
import com.psl.entity.BBook;
import com.psl.entity.Book;
import com.psl.entity.Customer;
import com.psl.entity.Transaction;

public interface LibraryDao {
	public List<BBook> getAllBook();
	public int bookBorrow(Transaction transaction);
	public Date bookReturn(int id);
	public Admin validateLogin(String name, String password)throws DataNotFoundException;
	public void addBook(Book book);
	public int removeBook(int id);
	public int valiDateCustomer(int cust_id);
	public int registerCustomer(String name);
	public int getCustomer();
	public int getBookId(String book_name);
	public List<Customer> getAllCustomer();
	public List<Book> getBooks();

}
