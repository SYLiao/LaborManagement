package com.labormanagement.java.reposit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labormanagement.java.entity.JobManager;

@Repository
public interface JobManagerRepository extends CrudRepository<JobManager, Long>{

}
