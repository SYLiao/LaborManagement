package com.labormanagement.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.labormanagement.java.entity.JobManager;
import com.labormanagement.java.entity.TimeSheet;
import com.labormanagement.java.service.TimeSheetService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/")
public class RestDispatch {
	
	@Autowired
	private TimeSheetService timeSheetService;
	
	@RequestMapping("/")
	public String indexPage() {
		return "This is first page.";
	}
	
	@RequestMapping(value = "/Rest/showTimeSheet", method = RequestMethod.GET)
	@ResponseBody
	public List<TimeSheet> getJobManager(){
		return timeSheetService.findAll();
	}
	
}
