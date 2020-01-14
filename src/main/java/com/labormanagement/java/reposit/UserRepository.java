package com.labormanagement.java.reposit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labormanagement.java.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
