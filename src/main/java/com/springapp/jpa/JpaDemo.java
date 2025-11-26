package com.springapp.jpa;

import com.springapp.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaDemo {
	public static void main(String[] args) {
		
		Employee insert = new Employee(5,"Devyesh yadav","devyesh@dxc.com","Integration",1500000);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(insert);
		em.getTransaction().commit();
		System.out.println(insert);
		
		//Employee emp = em.find(Employee.class, "1");
		//System.out.println(emp);
	}

}
