package com.danny.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.danny.core.Const.Const;
import com.danny.core.entity.CompanyID;
import com.danny.core.entity.ProductInventory;
import com.danny.core.mongodb.repository.CompanyIdRepository;
import com.danny.core.mongodb.repository.ProductInventoryRepository;



@Component
@EnableAutoConfiguration
public class InventoryService {
  @Autowired
  CompanyIdRepository companyIdRepository;

  @Autowired
  ProductInventoryRepository productInventoryRepository;

  // private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

  public String getDesc() {

    return Const.PROJECT_NAME;
  }

  public String getTitle(String name) {
    // logger.debug("getTitle() is executed! $name : {}", name);

    if (StringUtils.isEmpty(name)) {
      return Const.HOME_PAGE_GREETING;
    } else {
      return "Hello " + name;
    }
  }

  public ArrayList<CompanyID> getCompanyList() {
    try {
      return (ArrayList<CompanyID>) companyIdRepository.findAll();
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }

  public ArrayList<ProductInventory> getProductListByCompanyName(String companyName) {
    try {
      CompanyID companyID = companyIdRepository.findByCompanyName(companyName);
      if (StringUtils.isEmpty(companyID) == false) {
        return (ArrayList<ProductInventory>) productInventoryRepository
            .findByCompanyID(companyID.companyID);
      } else {
        throw new Exception();
      }
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }

}
