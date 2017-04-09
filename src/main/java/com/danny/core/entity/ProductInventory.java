package com.danny.core.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "product_storage_number")
public class ProductInventory {

  @Id
  public String id;

  @Field(value = "belingcompanyID")
  public String companyID;
  @Field(value = "howmany")
  public String howmany;
  @Field(value = "product_ID")
  public String productID;
  @Field(value = "name")
  public String productName;

  public ProductInventory() {}

  public ProductInventory(String companyID, String howmany, String productID, String productName) {
    this.companyID = companyID;
    this.howmany = howmany;
    this.productID = productID;
    this.productName = productName;
  }

  @Override
  public String toString() {
    return String.format(
        "ProductInventory[id=%s, belingcompanyID='%s', howmany='%s', product_ID='%s', name='%s']",
        id, companyID, howmany, productID, productName);
  }
}
