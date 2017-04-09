package com.danny.abolish;

public class ProductListEntity {

  String ID;
  String name;
  String note;
  String location;
  String belingcompany;
  String belingcompanyID;

  public String getID() {
    return ID;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getBelingcompany() {
    return belingcompany;
  }

  public void setBelingcompany(String belingcompany) {
    this.belingcompany = belingcompany;
  }

  public String getBelingcompanyID() {
    return belingcompanyID;
  }

  public void setBelingcompanyID(String belingcompanyID) {
    this.belingcompanyID = belingcompanyID;
  }

  public void resetEntity() {
    setID(null);
    setName(null);
    setNote(null);
    setLocation(null);
    setBelingcompany(null);
    setBelingcompanyID(null);
  }

}
