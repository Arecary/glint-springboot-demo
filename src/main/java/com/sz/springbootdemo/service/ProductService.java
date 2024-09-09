package com.sz.springbootdemo.service;

import com.sz.springbootdemo.dao.ProductDao;
import com.sz.springbootdemo.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  @Autowired
  private ProductDao productDao;

  public List<Product> getAllProducts() {
    return productDao.getAllProducts();
  }

  public Product getProductById(Integer id) {
    return productDao.getProductById(id);
  }

  // 保存产品并返回保存后的产品
  public Product saveProduct(Product product) {
    return productDao.saveProduct(product);  // 调用 DAO 方法并返回保存后的产品
  }

  public void deleteProduct(Integer id) {
    productDao.deleteProduct(id);
  }

  // 更新产品信息
  public Product updateProduct(Integer id, Product productDetails) {
    Product product = productDao.getProductById(id);
    if (product != null) {
      product.setName(productDetails.getName());
      product.setDescription(productDetails.getDescription());
      product.setCategory(productDetails.getCategory());
      product.setColor(productDetails.getColor());
      product.setInStock(productDetails.getInStock());
      product.setPrice(productDetails.getPrice());
      productDao.updateProduct(product);  // 调用 DAO 更新方法
    }
    return product;
  }
}
