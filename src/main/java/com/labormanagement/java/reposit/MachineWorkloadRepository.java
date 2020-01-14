package com.labormanagement.java.reposit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labormanagement.java.entity.MachineWorkload;

@Repository
public interface MachineWorkloadRepository extends CrudRepository<MachineWorkload, Long>{

}
