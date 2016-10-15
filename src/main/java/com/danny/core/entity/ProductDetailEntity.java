package com.danny.core.entity;

public class ProductDetailEntity {

  String productID;
  String name;
  String howMany;
  String numberOfChange;
  String typeOfChange;
  String typeOfChangeName;
  String belingCompanyID;
  String timeStamp;
  String timeStampWithFormat;

  public String getProductID() {
    return productID;
  }

  public void setProductID(String productID) {
    this.productID = productID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getHowMany() {
    return howMany;
  }

  public void setHowMany(String howMany) {
    this.howMany = howMany;
  }

  public String getNumberOfChange() {
    return numberOfChange;
  }

  public void setNumberOfChange(String numberOfChange) {
    this.numberOfChange = numberOfChange;
  }

  public String getTypeOfChange() {
    return typeOfChange;
  }

  public void setTypeOfChange(String typeOfChange) {
    this.typeOfChange = typeOfChange;
  }

  public String getTypeOfChangeName() {
    return typeOfChangeName;
  }

  public void setTypeOfChangeName(String typeOfChangeName) {
    this.typeOfChangeName = typeOfChangeName;
  }

  public String getBelingCompanyID() {
    return belingCompanyID;
  }

  public void setBelingCompanyID(String belingCompanyID) {
    this.belingCompanyID = belingCompanyID;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  public String getTimeStampWithFormat() {
    return timeStampWithFormat;
  }

  public void setTimeStampWithFormat(String timeStampWithFormat) {
    this.timeStampWithFormat = timeStampWithFormat;
  }

  public void resetEntity() {
    setProductID(null);
    setName(null);
    setHowMany(null);
    setNumberOfChange(null);
    setTypeOfChange(null);
    setBelingCompanyID(null);
    setTimeStamp(null);
    setTimeStampWithFormat(null);
    setTypeOfChangeName(null);
  }

}
