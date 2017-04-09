package com.danny.core.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "company_list")
public class CompanyID {

  @Id
  public String id;

  @Field(value = "company_id")
  public String companyID;
  @Field(value = "company_name")
  public String companyName;

  public CompanyID() {}

  public CompanyID(String companyName, String companyID) {
    this.companyID = companyID;
    this.companyName = companyName;
  }

  @Override
  public String toString() {
    return String.format("CompanyID[id=%s, company_ID='%s', company_name='%s']", id, companyID,
        companyName);
  }

}

