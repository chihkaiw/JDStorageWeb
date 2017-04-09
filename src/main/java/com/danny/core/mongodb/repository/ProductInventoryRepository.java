package com.danny.core.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.danny.core.entity.ProductInventory;


public interface ProductInventoryRepository extends MongoRepository<ProductInventory, String> {

  public ProductInventory findByCompanyIDAndProductID(String product_ID, String companyID);

  public ProductInventory findByProductName(String productName);

  public List<ProductInventory> findByCompanyID(String companyID);

}
