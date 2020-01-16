package com.labormanagement.java.reposit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labormanagement.java.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

}
