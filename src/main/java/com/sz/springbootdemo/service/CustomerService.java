package com.sz.springbootdemo.service;

import com.sz.springbootdemo.dao.CustomerDao;
import com.sz.springbootdemo.entity.Customer;
import com.sz.springbootdemo.exception.EmailAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

  @Autowired
  private CustomerDao customerDao;

  public List<Customer> getAllCustomers() {
    return customerDao.getAllCustomers();
  }

  public Customer getCustomerById(Integer id) {
    return customerDao.getCustomerById(id);
  }

  // 保存客户并返回保存后的客户
  public Customer saveCustomer(Customer customer) {
    // 在保存客户之前检查是否有重复的 emailId
    List<Customer> customers = customerDao.getAllCustomers();
    for (Customer existingCustomer : customers) {
      if (existingCustomer.getEmailId().equals(customer.getEmailId())) {
        throw new EmailAlreadyExistsException("Email already exists: " + customer.getEmailId());
      }
    }
    return customerDao.saveCustomer(customer);  // 调用 DAO 方法并返回保存后的客户
  }

  public void deleteCustomer(Integer id) {
    customerDao.deleteCustomer(id);
  }

  // 更新客户信息
  public Customer updateCustomer(Integer id, Customer customerDetails) {
    Customer customer = customerDao.getCustomerById(id);
    if (customer != null) {
      customer.setFirstName(customerDetails.getFirstName());
      customer.setLastName(customerDetails.getLastName());
      customer.setEmailId(customerDetails.getEmailId());
      customer.setPhoneNumber(customerDetails.getPhoneNumber());
      customer.setCity(customerDetails.getCity());
      customerDao.updateCustomer(customer);  // 调用 DAO 更新方法
    }
    return customer;
  }
}
