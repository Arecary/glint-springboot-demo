package com.sz.springbootdemo.controller;


import com.sz.springbootdemo.entity.Product;
import com.sz.springbootdemo.service.ProductService;

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
@RequestMapping("/products")
public class ProductController {


  @Autowired
  private ProductService productService;

  // 获取所有产品
  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  // 根据 ID 获取产品
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
    Product product = productService.getProductById(id);
    return ResponseEntity.ok(product);
  }

  // 创建新产品
  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    Product savedProduct = productService.saveProduct(product);
    return ResponseEntity.ok(savedProduct);
  }

  // 更新产品信息
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) {
    Product updatedProduct = productService.updateProduct(id, productDetails);
    return ResponseEntity.ok(updatedProduct);
  }

  // 删除产品
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }

}
