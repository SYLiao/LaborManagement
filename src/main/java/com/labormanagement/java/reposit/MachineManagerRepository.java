package com.labormanagement.java.reposit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labormanagement.java.entity.MachineManager;

@Repository
public interface MachineManagerRepository extends CrudRepository<MachineManager, Long>{

}
