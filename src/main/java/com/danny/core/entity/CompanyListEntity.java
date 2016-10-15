package com.danny.core.entity;

public class CompanyListEntity {

  String ID;

  String companyName;

  public String getID() {
    return ID;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public void resetEntity() {
    setID(null);
    setCompanyName(null);
  }

}
