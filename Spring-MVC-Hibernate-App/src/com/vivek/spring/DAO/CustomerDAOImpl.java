package com.vivek.spring.DAO;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.vivek.spring.hibernate.entity.Customer;


@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	
	//need to inject the session factory
	
	
@Autowired
private SessionFactory sessionFactory;

	@Override
	
	public List<Customer> getCustomers() {
		
		Session session = sessionFactory.getCurrentSession();
		String search=null;
		//create a query
		Query<Customer> theQuery = session.createQuery("from Customer  order by firstName "	, Customer.class
				);
		//theQuery.setParameter("search", "%"+search+"%")	;	
		//excute the query and get the result
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		//save to the data base
		
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, theId);
		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("delete from Customer where id=:id");
		q.setParameter("id", theId);
		q.executeUpdate();
	}

	@Override
	public List<Customer> getSearchCustomerList(String search) {
		// TODO Auto-generated method stub
		//String search=null;
		Session session = sessionFactory.getCurrentSession();
		//create a query
		Query<Customer> theQuery = session.createQuery("from Customer  where firstName like :search or lastName like :search or email like :search order by firstName "	, Customer.class
				);
		theQuery.setParameter("search", "%"+search+"%")	;
		List<Customer> customers = theQuery.getResultList();
		return customers;
	}
	


}
