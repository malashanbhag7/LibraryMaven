package com.psl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import Exceptions.DataNotFoundException;

import com.psl.entity.Admin;
import com.psl.entity.BBook;
import com.psl.entity.Book;
import com.psl.entity.Customer;
import com.psl.entity.Transaction;
@Repository
public class LibraryDaoImpl extends JdbcDaoSupport implements LibraryDao {

	@Override
	public List<BBook> getAllBook() {
		String query="select name,count(name) from book where book.isavailable=true group by name";
		return getJdbcTemplate().query(query, new BookMapper());
		
	}

	
	public static class BookMapper implements RowMapper<BBook>{

		@Override
		public BBook mapRow(ResultSet rs, int arg1) throws SQLException {
			BBook book=new BBook();
			
			book.setName(rs.getString(1));
			book.setCopies(rs.getInt(2));
			return book;
		}
		
	}


	@Override
	public int bookBorrow(Transaction transaction) {
		String query="update book set isavailable=false where id="+transaction.getBook_id();
		getJdbcTemplate().update(query);
		 query="insert into transaction(bid,cid,duedate,isreturned) values(?,?,?,0)";
		return  getJdbcTemplate().update(query, transaction.getBook_id(),transaction.getCustomer_id(),transaction.getDue_date());
		
	}


	@Override
	public Date bookReturn(int id) {
		Transaction transaction=new Transaction();
		try{
	String query="update book set isavailable=true where id="+id;
	System.out.println("aaaaaaaaaaaaaaaaaaaa");
	getJdbcTemplate().update(query);
	
	  query="select * from transaction where isreturned=false and bid="+id;
	
	
	 transaction= getJdbcTemplate().queryForObject(query, new RowMapper<Transaction>(){

		@Override
		public Transaction mapRow(ResultSet rs, int arg1) throws SQLException {
			Transaction tr=new Transaction();
			tr.setDue_date(rs.getDate(3));
			return tr;
		}
		
	});
	query="update transaction set isreturned=true where bid="+id;
	getJdbcTemplate().update(query);
		}catch(EmptyResultDataAccessException e){
			throw new DataNotFoundException();
		}
	return transaction.getDue_date();
	}


	@Override
	public Admin validateLogin(String name, String password)throws DataNotFoundException {
		try{
		String query="select * from admin where name=?";
		return getJdbcTemplate().queryForObject(query,new RowMapper<Admin>(){

			@Override
			public Admin mapRow(ResultSet rs, int arg1) throws SQLException {
			Admin admin=new Admin();
			admin.setName(rs.getString(1));
			admin.setPassword(rs.getString(2));
			return admin;
			}
			
		},name);
		}catch(EmptyResultDataAccessException e){
			throw new DataNotFoundException();
		}
	}


	@Override
	public void addBook(Book book) {
	String query="insert into book(name,isavailable) values(?,?)";
	getJdbcTemplate().update(query,book.getName(),book.isIsavailable());
		
	}


	@Override
	public int removeBook(int id) {
		
		String query="delete  from book where isavailable=true and id="+id;
		return getJdbcTemplate().update(query);
		
	}


	@Override
	public int valiDateCustomer(int cust_id) {
		try{
		String query="select * from customer where id="+cust_id;

		getJdbcTemplate().queryForObject(query,new RowMapper<Customer>(){

			@Override
			public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Customer cst=new Customer();
				cst.setId(rs.getInt(1));
				cst.setName(rs.getString(2));
				return cst;
			}
			
		});
		}catch(EmptyResultDataAccessException e){
			return 0;
		}
		return 1;
	}


	@Override
	public int registerCustomer(String name) {
		try{
		String query="insert into customer(name) values(?)";
		return getJdbcTemplate().update(query, name);
		
		}catch(Exception e){
			return 0;
		}
					
		
	}


	@Override
	public int getCustomer() {
		String query="SELECT id FROM customer ORDER BY ID DESC LIMIT 1";	
		return getJdbcTemplate().queryForInt(query);
	}


	@Override
	public int getBookId(String book_name) {
		//String query="SELECT id FROM book    where name='"+book_name+"' and isavailable=true limit 1";	
		String query="SELECT id FROM book    where name=? and isavailable=true limit 1";	
		return getJdbcTemplate().queryForInt(query,book_name);
	}


	@Override
	public List<Customer> getAllCustomer() {
		
			String query="select * from customer ";

			return getJdbcTemplate().query(query,new RowMapper<Customer>(){

				@Override
				public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					Customer cst=new Customer();
					cst.setId(rs.getInt(1));
					cst.setName(rs.getString(2));
					return cst;
				}
				
			});
	}


	@Override
	public List<Book> getBooks() {
		String query="select * from book ";

		return getJdbcTemplate().query(query,new RowMapper<Book>(){

			@Override
			public Book mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Book book=new Book();
				book.setId(rs.getInt(1));
				book.setName(rs.getString(2));
				book.setIsavailable(rs.getBoolean(3));
				return book;
			}
			
		});
}
	
	
}		

	
