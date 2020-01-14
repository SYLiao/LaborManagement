package com.labormanagement.java.reposit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labormanagement.java.entity.TimeSheet;

@Repository
public interface TimeSheetRepository extends CrudRepository<TimeSheet, Long>{

}
