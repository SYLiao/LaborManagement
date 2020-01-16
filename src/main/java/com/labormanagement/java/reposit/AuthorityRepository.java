package com.labormanagement.java.reposit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labormanagement.java.entity.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long>{

}
