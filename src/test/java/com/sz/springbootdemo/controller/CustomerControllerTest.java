package com.sz.springbootdemo.controller;


import com.sz.springbootdemo.entity.Customer;
import com.sz.springbootdemo.service.CustomerService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;



@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerService customerService;


  @Test
  public void testGetAllCustomers() throws Exception {
    // 创建一些模拟的客户数据
    Customer customer1 = new Customer();
    customer1.setId(1);
    customer1.setFirstName("John");
    customer1.setLastName("Doe");
    customer1.setEmailId("john@example.com");
    customer1.setPhoneNumber("1234567890");
    customer1.setCity("New York");

    Customer customer2 = new Customer();
    customer2.setId(2);
    customer2.setFirstName("Jane");
    customer2.setLastName("Doe");
    customer2.setEmailId("jane@example.com");
    customer2.setPhoneNumber("0987654321");
    customer2.setCity("Los Angeles");


    List<Customer> customers = Arrays.asList(customer1, customer2);

    // 设置 customerService 的模拟行为
    when(customerService.getAllCustomers()).thenReturn(customers);

    // 进行测试并验证结果
    mockMvc.perform(get("/customers")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].firstName", is("John")))
            .andExpect(jsonPath("$[1].firstName", is("Jane")));
  }


}
