package com.assignment.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.assignment.entities.Division;
import com.assignment.entities.Employee;
import com.assignment.entities.InputForDivision;

@Repository
public class DivisionDaoImpl //implements DivisionDao
{
	@PersistenceContext
	private EntityManager entityManager;
	
	public String createDiviosn(InputForDivision divison) {
		// TODO Auto-generated method stub
		Configuration cfg = new Configuration();
		//cfg.setProperties(application.properties);
		cfg.addAnnotatedClass(Division.class);
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.saveOrUpdate(new Division(divison));
		return "SUCCESS";
	}
	
	public List<Employee> findEmployeesbyDivision(Long divisionID){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> q = cb.createQuery(Employee.class);
		Root<Employee> c = q.from(Employee.class);
		q.select(c);
		//ParameterExpression<Integer> p = cb.parameter(Integer.class);
		q.where(cb.equal(c.get("division").get("id"), divisionID));
		TypedQuery<Employee> tq = entityManager.createQuery(q);
		return tq.getResultList();
		}
	
	
	public List<Object[]> findNumberOfEmployeesByManager(){
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
	        Root<Employee> employee = query.from(Employee.class);
	        Root<Employee> manager = query.from(Employee.class);
	        query.groupBy(employee.get("managerId"));
	        query.multiselect(manager.get("employeeName"),
	       		  criteriaBuilder.count(employee));
	        query.where(criteriaBuilder.equal(employee.get("managerId"), manager.get("empId")));
	        TypedQuery<Object[]> typedQuery = entityManager.createQuery(query);
	        List<Object[]> resultList = typedQuery.getResultList();
		return resultList;
	}

	public List<Employee> findManagersInDiv(Long divisionID) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> q = cb.createQuery(Employee.class);
		Root<Employee> c = q.from(Employee.class);
		Root<Employee> cMan = q.from(Employee.class);
		q.select(c);
		q.where(cb.equal(c.get("division").get("id"), divisionID),
					cb.equal(cMan.get("empId"), c.get("managerId")));
		TypedQuery<Employee> tq = entityManager.createQuery(q);
		return tq.getResultList();
		}

	public List<Object[]> findEmployeeStatsByDivision() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<Employee> employee = query.from(Employee.class);
        Root<Division> div = query.from(Division.class);
        query.groupBy(employee.get("division").get("id"));
        query.multiselect(div.get("divisionName"), criteriaBuilder.count(employee));
        query.where(criteriaBuilder.equal(employee.get("division").get("id"), div.get("id")));
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(query);
        List<Object[]> resultList = typedQuery.getResultList();
		return resultList;
	}

}
