package com.danny.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.danny.core.Const.Const;
import com.danny.core.Repository.DropboxDao;
import com.danny.core.entity.CompanyListEntity;
import com.danny.core.entity.ProductDetailEntity;
import com.danny.core.entity.ProductListEntity;

@Configuration
@Component
@EnableAutoConfiguration
public class CompanyInformationServiceImpl {
  @Autowired
  DropboxDao javaDropbox;
  @Autowired
  Const consta;


  /**
   * 
   * @param jsonString
   * @return CompanyList
   * @return 0 : ID(Company ID)
   * @return 1 : name
   * 
   * 
   */

  public ArrayList<CompanyListEntity> getCompanyList() {
    String companyListPath = Const.STRING_SLASH + Const.SIMPLE_STORAGE_JD + Const.STRING_SLASH
        + Const.COMPANY_LIST_FOR_DROPBOX + Const.TXT_EXTENTION;
    String jsonStringFromDB = "";
    try {
      jsonStringFromDB = javaDropbox.startGetFile(companyListPath);
      jsonStringFromDB = setValidJSONArrayFormat(jsonStringFromDB);

      ArrayList<CompanyListEntity> companyList = new ArrayList<>();
      JSONArray jsonArray = null;
      try {
        jsonArray = new JSONArray(jsonStringFromDB);

        if (null != jsonArray) {
          int jsonArrayLength = jsonArray.length();

          for (int i = 0; i < jsonArrayLength; i++) {
            try {
              JSONObject jsonObject = jsonArray.getJSONObject(i);
              CompanyListEntity temp = new CompanyListEntity();

              temp.setID(Integer.toString(jsonObject.getInt("ID")));
              temp.setCompanyName(jsonObject.getString("name"));
              if (temp.getID().length() > 0 && temp.getCompanyName().length() > 0) {
                System.out.println(temp.getID() + "/" + temp.getCompanyName() + "/"
                    + temp.getCompanyName().getBytes());

                companyList.add(temp);
              }
            } catch (Exception e) {
            }

          }
          return companyList;
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  /**
   * 
   * @param companyName
   * @param javaDropbox
   * @return ProductListByCompanyName
   * @return 0 : ID
   * @return 1 : name
   * @return 2 : note
   * @return 3 : location
   * @return 4 : belingcompany
   * @return 5 : belingcompanyID
   * 
   */
  public ArrayList<ProductListEntity> getProductListByCompanyName(String companyName) {
    String companyProductListPath =
        Const.STRING_SLASH + Const.SIMPLE_STORAGE_JD + Const.STRING_SLASH + companyName
            + Const.STRING_SLASH + Const.PRODUCT_LIST_FOR_DROPBOX + Const.TXT_EXTENTION;
    String jsonStringFromDB = "";

    try {
      jsonStringFromDB = javaDropbox.startGetFile(companyProductListPath);
      jsonStringFromDB = setValidJSONArrayFormat(jsonStringFromDB);

      ArrayList<ProductListEntity> productList = new ArrayList<>();
      JSONArray jsonArray = null;
      try {
        jsonArray = new JSONArray(jsonStringFromDB);

        if (null != jsonArray) {
          int jsonArrayLength = jsonArray.length();

          for (int i = 0; i < jsonArrayLength; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ProductListEntity temp = new ProductListEntity();

            temp.setID(Integer.toString(jsonObject.getInt("ID")));
            temp.setName(jsonObject.getString("name"));
            temp.setNote(jsonObject.getString("note"));
            temp.setLocation(jsonObject.getString("location"));
            temp.setBelingcompany(jsonObject.getString("belingcompany"));
            temp.setBelingcompanyID(Integer.toString(jsonObject.getInt("belingcompanyID")));
            if (temp.getID().length() > 0 && temp.getName().length() > 0) {
              productList.add(temp);
            }

          }
          return productList;
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  /**
   * 
   * @param companyName, productName
   * @param javaDropbox
   * @return ProductDetailByProductName
   * @return 0 : ID
   * @return 1 : name
   * @return 2 : number of product in storage
   * @return 3 : change number
   * @return 4 : type of change(0 -> add, 1 --> reduce, 2 --> change number directly)
   * @return 5 : belingcompanyID
   * @return 6 : Time Stamp
   */
  public ArrayList<ProductDetailEntity> getProductDetailByProductName(String companyName,
      String productName) {
    String companyProductDetailPath = Const.STRING_SLASH + Const.SIMPLE_STORAGE_JD
        + Const.STRING_SLASH + companyName + Const.STRING_SLASH + productName + Const.TXT_EXTENTION;
    String jsonStringFromDB = "";

    try {
      jsonStringFromDB = javaDropbox.startGetFile(companyProductDetailPath);
      jsonStringFromDB = setValidJSONArrayFormat(jsonStringFromDB);

      ArrayList<ProductDetailEntity> productDetail = new ArrayList<>();
      JSONArray jsonArray = null;
      try {
        jsonArray = new JSONArray(jsonStringFromDB);

        if (null != jsonArray) {
          int jsonArrayLength = jsonArray.length();

          for (int i = 0; i < jsonArrayLength; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ProductDetailEntity temp = new ProductDetailEntity();

            temp.setProductID(Integer.toString(jsonObject.getInt("product_ID")));
            temp.setName(jsonObject.getString("name"));
            temp.setHowMany(Integer.toString(jsonObject.getInt("howmany")));
            temp.setNumberOfChange(Integer.toString(jsonObject.getInt("number_of_change")));
            temp.setTypeOfChange(Integer.toString(jsonObject.getInt("type_of_change")));
            temp.setTypeOfChangeName(getTypeOfChangeName(temp.getTypeOfChange()));
            temp.setBelingCompanyID(Integer.toString(jsonObject.getInt("belingcompanyID")));

            temp.setTimeStamp(
                getTimeStringByGiven(jsonObject.getInt("year"), jsonObject.getInt("month"),
                    jsonObject.getInt("day"), jsonObject.getInt("hour"), jsonObject.getInt("min")));
            temp.setTimeStampWithFormat(
                getTimeStringWithFormat(jsonObject.getInt("year"), jsonObject.getInt("month"),
                    jsonObject.getInt("day"), jsonObject.getInt("hour"), jsonObject.getInt("min")));

            if (temp.getProductID().length() > 0 && temp.getName().length() > 0
                && temp.getHowMany().length() > 0 && temp.getNumberOfChange().length() > 0
                && temp.getTypeOfChange().length() > 0 && temp.getBelingCompanyID().length() > 0) {
              productDetail.add(temp);
            }

          }
          return productDetail;
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  private String setValidJSONArrayFormat(String rawDataFromDB) {

    if (null != rawDataFromDB && rawDataFromDB.length() > 0) {
      StringBuilder result = new StringBuilder("[");
      result.append(rawDataFromDB);
      result.append("]");
      return result.toString();
    }
    return "[]";
  }

  private String getTimeStringByGiven(int year, int month, int day, int hour, int min) {
    StringBuilder time = new StringBuilder();

    time.append(year);
    time.append(month);
    time.append(day);
    time.append(hour);
    time.append(min);

    return time.toString();

  }

  private String getTimeStringWithFormat(int year, int month, int day, int hour, int min) {
    StringBuilder time = new StringBuilder();

    time.append(year);
    time.append(Const.STRING_SLASH);
    time.append(month);
    time.append(Const.STRING_SLASH);
    time.append(day);
    time.append(Const.STRING_SPACE);
    time.append(hour);
    time.append(Const.STRING_COMA);
    time.append(min);

    return time.toString();

  }

  private String getTypeOfChangeName(String typeOfChange) {

    if (null != typeOfChange && typeOfChange.length() > 0) {
      if (typeOfChange.equals(Const.TYPE_OF_CHANGE_0)) {
        return Const.TYPE_OF_CHANGE_0_NAME;
      } else if (typeOfChange.equals(Const.TYPE_OF_CHANGE_1)) {
        return Const.TYPE_OF_CHANGE_1_NAME;
      } else if (typeOfChange.equals(Const.TYPE_OF_CHANGE_2)) {
        return Const.TYPE_OF_CHANGE_2_NAME;
      }

    }
    return "";
  }


}
