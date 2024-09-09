package com.sz.springbootdemo.dao;

import com.sz.springbootdemo.entity.Product;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

  @Autowired
  private SessionFactory sessionFactory;

  public List<Product> getAllProducts() {
    Session session = sessionFactory.openSession();
    List<Product> products = session.createQuery("from Product", Product.class).list();
    session.close();
    return products;
  }

  public Product getProductById(Integer id) {
    Session session = sessionFactory.openSession();
    Product product = session.get(Product.class, id);
    session.close();
    return product;
  }

  // 保存或更新产品信息，返回保存后的产品
  public Product saveProduct(Product product) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    session.saveOrUpdate(product);
    transaction.commit();
    session.close();
    return product;  // 返回保存后的产品对象
  }

  public void deleteProduct(Integer id) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    Product product = session.get(Product.class, id);
    if (product != null) {
      session.delete(product);
    }
    transaction.commit();
    session.close();
  }

  public void updateProduct(Product product) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    session.update(product);  // 这里使用 Hibernate 的 `update` 方法更新产品信息
    transaction.commit();
    session.close();
  }
}
