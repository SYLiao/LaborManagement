package com.labormanagement.java.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labormanagement.java.entity.TimeSheet;
import com.labormanagement.java.reposit.TimeSheetRepository;

@Service
public class TimeSheetService {
	
	@Autowired
	private TimeSheetRepository timeSheetRepository;
	
	public void createTimeSheet(TimeSheet timeSheet) {
		timeSheetRepository.save(timeSheet);
	}
	
	public TimeSheet findById(long timeSheetId) {
		TimeSheet timeSheet = timeSheetRepository.findById(timeSheetId).orElse(null);
		return timeSheet;
	}
	
	public List<TimeSheet> findByStatus(String status) {
		Iterable<TimeSheet> timesheets = timeSheetRepository.findAll();
		List<TimeSheet> result = new ArrayList<TimeSheet>();
		for(TimeSheet timeSheet : timesheets) {
			if(timeSheet.getStatus().equals(status)) {
				result.add(timeSheet);
			}
		}
		return result;
	}
	
	public List<TimeSheet> findByDate(Date date){
		Iterable<TimeSheet> timesheets = timeSheetRepository.findAll();
		List<TimeSheet> resultList = new ArrayList<>();
		for(TimeSheet timeSheet : timesheets) {
			if(timeSheet.getCreatDate().equals(date)) {
				resultList.add(timeSheet);
			}
		}
		return resultList;
	}
	
	public List<TimeSheet> findAll(){
		return (List<TimeSheet>) timeSheetRepository.findAll();
	}
	
	public void updateTimeSheet(TimeSheet timeSheet) {
		TimeSheet timeSheet2 = timeSheetRepository.findById(timeSheet.getTimeSheetId()).orElse(null);
		if(timeSheet2 != null) {
			timeSheetRepository.save(timeSheet);
		}
	}
	
	public void deleteTimeSheet(long timeSheetId) {
		timeSheetRepository.deleteById(timeSheetId);
	}
	
	public void deleteAll() {
		timeSheetRepository.deleteAll();
	}
}
