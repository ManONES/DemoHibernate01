package com.manycode.app.dao;


import java.util.List;
import com.manycode.app.model.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

 
@Repository
public class CustomerDaoImpl implements CustomerDao{
 
	private static final Logger LOGGER = LogManager.getLogger(CustomerDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
 
	public List<Customer> getAllCustomers() {
		LOGGER.info("....Entra a al getAllCustomers y crea session");
		Session session = this.sessionFactory.getCurrentSession();
		LOGGER.info("....Sale del crea session.");
		if (session.isConnected()) {
			LOGGER.info("....Está conectado.");
		} else {
			throw new NullPointerException("No está conectado el session");
		}
		List<Customer>  customerList = session.createQuery("from Customer").list();
		LOGGER.info("....Sale del getAllCustomers y crea session");		
		return customerList;
	}
	
	
 
	public Customer getCustomer(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Customer customer = (Customer) session.get(Customer.class, id);
		return customer;
	}
 
	public Customer addCustomer(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(customer);
		return customer;
	}
 
	public void updateCustomer(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(customer);
	}
 
	public void deleteCustomer(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Customer p = (Customer) session.load(Customer.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	} 
}