package com.sz.springbootdemo.dao;

import com.sz.springbootdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDao {

  @Autowired
  private SessionFactory sessionFactory;

  public List<Customer> getAllCustomers() {
    Session session = sessionFactory.openSession();
    List<Customer> customers = session.createQuery("from Customer", Customer.class).list();
    session.close();
    return customers;
  }

  public Customer getCustomerById(Integer id) {
    Session session = sessionFactory.openSession();
    Customer customer = session.get(Customer.class, id);
    session.close();
    return customer;
  }

  // 保存或更新客户信息，返回保存后的客户
  public Customer saveCustomer(Customer customer) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    session.saveOrUpdate(customer);  // saveOrUpdate 将会保存或更新客户对象
    transaction.commit();
    session.close();
    return customer;  // 返回保存后的客户对象
  }

  public void deleteCustomer(Integer id) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    Customer customer = session.get(Customer.class, id);
    if (customer != null) {
      session.delete(customer);
    }
    transaction.commit();
    session.close();
  }

  public void updateCustomer(Customer customer) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    session.update(customer);  // 这里使用 Hibernate 的 `update` 方法更新客户信息
    transaction.commit();
    session.close();
  }
}
