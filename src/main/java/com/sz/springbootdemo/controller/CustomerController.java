package com.sz.springbootdemo.controller;

import com.sz.springbootdemo.entity.Customer;
import com.sz.springbootdemo.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {


  @Autowired
  private CustomerService customerService;

  // 获取所有客户
  @GetMapping
  public List<Customer> getAllCustomers() {
    return customerService.getAllCustomers();
  }

  // 根据 ID 获取客户
  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
    Customer customer = customerService.getCustomerById(id);
    return ResponseEntity.ok(customer);
  }

  // 创建新客户
  @PostMapping
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    Customer savedCustomer = customerService.saveCustomer(customer);
    return ResponseEntity.ok(savedCustomer);
  }

  // 更新客户信息
  @PutMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customerDetails) {
    Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
    return ResponseEntity.ok(updatedCustomer);
  }

  // 删除客户
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
    customerService.deleteCustomer(id);
    return ResponseEntity.noContent().build();
  }


}
