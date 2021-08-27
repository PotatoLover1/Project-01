package com.example.dao;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.Session;

import com.example.models.User;
import com.example.models.UserRole;
import com.example.utils.HibernateUtil;

public class UserDaoHibernate implements UserDao{

	@Override
	public List<User> getAllUsers(){
		Session ses = HibernateUtil.getSession();
		List<User> users = ses.createNativeQuery("select * from users",User.class).list();
		return users;
	}
	
	@Override
	public User getUserByUserName(String username) {
		Session ses = HibernateUtil.getSession();
		System.out.println("int get user by username");
		User user = ses.createQuery("from User where username=:username",User.class).setString("username", username).uniqueResult();
		return user;
	} 
	
	public User getUserById(int id) {
		Session ses = HibernateUtil.getSession();
		User user = ses.createQuery("from User where id="+id,User.class).uniqueResult();
		return user;
	}
	
	@Override
	public void createUser(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tran = ses.beginTransaction();
		ses.save(u);
		tran.commit();
	}
	
	@Override
	public void updateUser(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tran = ses.beginTransaction();
		ses.update(u);
		tran.commit();
	}
	
	@Override
	public void deleteUser(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tran = ses.beginTransaction();
		ses.delete(u);
		tran.commit();
	}

}
