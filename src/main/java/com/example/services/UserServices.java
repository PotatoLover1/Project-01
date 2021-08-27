package com.example.services;

import com.example.dao.UserDaoHibernate;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UsernameAlreadyExistsException;
import com.example.models.User;
import com.example.models.UserRole;

public class UserServices {
	
	private UserDaoHibernate uDao;
	
	public  UserServices(UserDaoHibernate u) {
		this.uDao = u;
	}
	
	public User signUp(String first, String last, String email,String password)throws UsernameAlreadyExistsException{
		User u = new User(first,last,email,password);
		uDao.createUser(u);
		u = (uDao.getUserByUserName(u.getUsername()));
		if(u==null) {
			System.out.println("Username already exist in database");
			throw new UsernameAlreadyExistsException();
		}
		return u;
	}
	
	public User signIn(String username,String password) throws InvalidCredentialsException{
		System.out.println("in uServ");
		System.out.println(username);
		System.out.println(password);
		User u = uDao.getUserByUserName(username);
		if(u==null) {
			System.out.println("invalid credentials");
			throw new InvalidCredentialsException();
		}
		if(!u.getPassword().equals(password)) {
			System.out.println("invalid credentials");
			throw new InvalidCredentialsException();
		}else {
			System.out.println("Logged in");
			return u;
		}
		
	}
	
	public User getUserById(int id) {
		return uDao.getUserById(id);
	}
	public User getUserByUsername(String username) {
		return uDao.getUserByUserName(username);
	}
	
	
}
