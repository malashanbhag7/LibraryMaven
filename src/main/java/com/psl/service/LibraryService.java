package com.psl.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Exceptions.DataNotFoundException;

import com.psl.dao.LibraryDao;
import com.psl.entity.Admin;
import com.psl.entity.BBook;
import com.psl.entity.Book;
import com.psl.entity.Customer;
import com.psl.entity.Transaction;

@Service
public class LibraryService {

	@Autowired
	LibraryDao librarydao;
	
	
	public List<BBook> getAllBook(){
		return librarydao.getAllBook();
	}
	
	public int borrow(String book_name,int customer_id){
		int book_id=librarydao.getBookId(book_name);
		
		System.out.println(book_id);
		
		Transaction transaction=new Transaction(); 
		
		
		
		transaction.setBook_id(book_id);
		transaction.setCustomer_id(customer_id);
		
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, 2);
		
		transaction.setDue_date(cal.getTime());
		
		int result=librarydao.bookBorrow(transaction);
		if(result>0){
			return book_id;
		}else{
			return 0;
		}
	}
	
	
	public int returnbook(int id){
	
		Date date=librarydao.bookReturn(id);
/*	System.out.println(date);
		Calendar today=Calendar.getInstance();
		Calendar duedate =Calendar.getInstance();
		
		
		duedate.setTime(date);
	
	
	long difference=today.getTimeInMillis()-duedate.getTimeInMillis();
	if(difference>0){
		//difference=(difference/(1000*60*60*24));

		return (int) (difference*10);*/
		
		Date today=new Date();
		
		long difference=today.getTime()-date.getTime();
		if(difference>0){
			return (int) TimeUnit.MILLISECONDS.toDays(difference)*10;
		
		
	}
	
	
	else{
		return 0;
	}
		
		
	}

	public int validateLogin(String name, String password)throws DataNotFoundException {
		Admin admin=librarydao.validateLogin(name,password);
		if((admin.getName().equals(name)) &&(admin.getPassword().equals(password))){
			return 1;
		}else{
			return 0;
		}
		
	}

	public void addbook(Book book) {
		librarydao.addBook(book);
		
	}

	public int removeBook(int id) {
		return librarydao.removeBook(id);
		
	}

	public int validateCustomer(int cust_id) {
return	librarydao.valiDateCustomer(cust_id);
	}

	public int registerCustomer(String name) {
		// TODO Auto-generated method stub
	int result=	librarydao.registerCustomer(name);
	if(result==1){
	return librarydao.getCustomer();
	}else{
		return 0;
	}
	}

	public List<Customer> getAllCustomer() {

return librarydao.getAllCustomer();
	}

	public List<Book> getBooks() {
		// TODO Auto-generated method stub
		return librarydao.getBooks();
	}
	
}
