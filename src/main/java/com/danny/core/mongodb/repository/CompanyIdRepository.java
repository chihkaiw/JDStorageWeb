package com.danny.core.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.danny.core.entity.CompanyID;


public interface CompanyIdRepository extends MongoRepository<CompanyID, String> {

  public CompanyID findByCompanyName(String companyName);

  // public List<CompanyID> findByID(String ID);

}
