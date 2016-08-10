package com.psl.controller;

import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Exceptions.DataNotFoundException;

import com.psl.entity.BBook;
import com.psl.entity.Book;
import com.psl.entity.Customer;
import com.psl.service.LibraryService;
@Controller
public class LibraryController {
@Autowired
LibraryService libraryService;
	
	@RequestMapping({"/","/welcome"})
public String displayWelcomePage(){
	return "welcome";
}
	@RequestMapping({"/home"})
	public String displayHomePage(){
		return "home";
	}
	
	
	@RequestMapping("/login")
	public String displayLoginPage(@RequestParam("name") String name,@RequestParam("password") String password,Map<String,String> map){
		if(name!="" && password!=""){
		try{
			int valid=libraryService.validateLogin(name,password);
			if(valid==1){
				
				return "home";
			}
			else{
				map.put("message","invalid username or password");
				return "welcome";
			}
		}catch(DataNotFoundException e){
				map.put("message","invalid username or password");
				return "welcome";
			}
		}else{
			map.put("message","enter username and password");
			return "welcome";
		}
		}
		
		
		
		
		
	
	@RequestMapping(value=("issue"),method=RequestMethod.GET)
	public ModelAndView getCustomerId(){
		ModelAndView mv=new ModelAndView("customer_id");
		return mv;

		
	}
	
	
	@RequestMapping(value=("borrow"),method=RequestMethod.GET)
	public ModelAndView borrowBook(@RequestParam("cust_id") String id){
		int cust_id=Integer.parseInt(id);
		ModelAndView mv=new ModelAndView("borrow");
		
		int result=libraryService.validateCustomer(cust_id);	
		if(result==1){
			mv.setViewName("borrow");
			List<BBook> booklist=libraryService.getAllBook();
			mv.addObject("booklist",booklist);
			mv.addObject("cust_id",cust_id);
			return mv;
		}else{
			mv.setViewName("customer_id");
			mv.addObject("message","invalid customer id");
			return mv;
		}
		
		
		
	}
	
	@RequestMapping(value=("borrow/{book_name}/{customer_id}"),method=RequestMethod.GET)
	public String borrowBook(@PathVariable String book_name,@PathVariable String customer_id,RedirectAttributes redirectAttributes){
		
	int result=libraryService.borrow((book_name), Integer.parseInt(customer_id));
	redirectAttributes.addFlashAttribute("message", "book with id"+result+" issued successfully");
		 return "redirect:/home";
	}
	@RequestMapping(value=("/return"),method=RequestMethod.GET)
	public String returnBook(){
		return "return";
	}
	
	@RequestMapping(value=("/return"),method=RequestMethod.POST)
	public ModelAndView returnBook(@RequestParam("id") String id){
		ModelAndView mav=new ModelAndView("return");
		if(id!="" && id.matches("[0-9]*")){
			try{
		int fine=libraryService.returnbook(Integer.parseInt(id));
		
		
		mav.addObject("message","fine amount="+fine);
			}catch(DataNotFoundException e){
				mav.addObject("message","book with   "+id+"already returned");
			}
		return mav;
		}else {
			mav.addObject("message","enter valid id");
			return mav;
		}
	}
	
	
	@RequestMapping(value=("add"),method=RequestMethod.GET)
	public ModelAndView addBook(){
		ModelAndView mv=new ModelAndView("add");
		
		return mv;
	}
	
	
	@RequestMapping(value=("add"),method=RequestMethod.POST)
	public ModelAndView addBook(@RequestParam("name") String name,@RequestParam("copies") String copies){
		ModelAndView mv=new ModelAndView("add");
		if(name!="" && name.matches("[a-zA-z ]*")){	
			
			Book book=new Book();
			book.setName(name);
			book.setIsavailable(true);
			int n=Integer.parseInt(copies);
		for(int i=0;i<n;i++){
		libraryService.addbook(book);
		}
		mv.addObject("message","book added succesfully");
		return mv;
	}else{
		mv.addObject("message","enter valid name");
		return mv;
	}
	}
	
	
	@RequestMapping(value=("/remove"),method=RequestMethod.GET)
	public ModelAndView remove(){
		ModelAndView mv=new ModelAndView("remove");
		List<BBook> booklist=libraryService.getAllBook();
		mv.addObject("booklist",booklist);
		return mv;
	}
	
	
	@RequestMapping(value=("/remove"),method=RequestMethod.POST)
	public String removeBook(@RequestParam("id") int id,RedirectAttributes redirectAttributes){
		
		int result=libraryService.removeBook(id);
		if(result>0){
		redirectAttributes.addFlashAttribute("message", "successfull");
		return "redirect:/remove";
	
		}else{
			redirectAttributes.addFlashAttribute("message", "unsuccessfull");
			return "redirect:/remove";	
		}
	}
	
	@RequestMapping(value=("register"),method=RequestMethod.GET)
	public ModelAndView register(){
		ModelAndView mv=new ModelAndView("registerCustomer");
		
		return mv;
	}
	@RequestMapping(value=("register"),method=RequestMethod.POST)
	public ModelAndView register(@RequestParam("name") String name){
		ModelAndView mv=new ModelAndView("registerCustomer");
		int id=libraryService.registerCustomer(name);
		if(id>0){
		mv.addObject("message","Your id is "+id);
		return mv;
		}else{
			mv.addObject("message","already existing username ");
			return mv;
		}
	}
	
	@RequestMapping(value=("customer"),method=RequestMethod.GET)
	public ModelAndView customer(){
		ModelAndView mv=new ModelAndView("Customer");
		List<Customer> custlist=libraryService.getAllCustomer();
		mv.addObject("custlist",custlist);
		return mv;
		}
	@RequestMapping(value=("view"),method=RequestMethod.GET)
	public ModelAndView view(){
		ModelAndView mv=new ModelAndView("book");
		List<Book> booklist=libraryService.getBooks();
		mv.addObject("booklist",booklist);
		return mv;
		}
	
	
	
	
}
