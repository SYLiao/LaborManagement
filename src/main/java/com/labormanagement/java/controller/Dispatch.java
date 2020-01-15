package com.labormanagement.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.labormanagement.java.entity.JobManager;
import com.labormanagement.java.entity.JobWorkload;
import com.labormanagement.java.entity.MachineManager;
import com.labormanagement.java.entity.MachineWorkload;
import com.labormanagement.java.entity.TimeSheet;
import com.labormanagement.java.service.JobManagerService;
import com.labormanagement.java.service.JobWorkloadService;
import com.labormanagement.java.service.MachineManagerService;
import com.labormanagement.java.service.MachineWorkloadService;
import com.labormanagement.java.service.RoleService;
import com.labormanagement.java.service.TimeSheetService;
import com.labormanagement.java.service.UserService;

import javassist.expr.NewArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class Dispatch {
	
	@Autowired
	private TimeSheetService timeSheetService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private JobManagerService jobManagerService;
	
	@Autowired
	private JobWorkloadService jobWorkloadService;
	
	@Autowired
	private MachineWorkloadService machineWorkloadService;
	
	@Autowired
	private MachineManagerService machineManagerService;
	
	@RequestMapping("/")
	public ModelAndView indexPage() {
		ModelAndView mv = new ModelAndView("JobManager");
		List<JobManager> list = jobManagerService.findAll();
		mv.addObject("JobManager", list);
		mv.addObject("size", String.valueOf(list.size()));
		return mv;
	}
	
	@RequestMapping(value = "/Admin/showJobCode", method = RequestMethod.GET)
	public ModelAndView getJobManager(){
		ModelAndView mv = new ModelAndView("JobManager");
		List<JobManager> list = jobManagerService.findAll();
		mv.addObject("JobManager", list);
		mv.addObject("size", String.valueOf(list.size()));
		return mv;
	}
	
	@RequestMapping(value = "/Admin/showMachineCode", method = RequestMethod.GET)
	public ModelAndView getMachineManager(){
		ModelAndView mv = new ModelAndView("MachineMangement");
		List<MachineManager> list = machineManagerService.findAll();
		mv.addObject("MachineManager", list);
		mv.addObject("size", list.size());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/showTimeSheet", method = RequestMethod.GET)
	public ModelAndView getTimeSheet(){
		ModelAndView mv = new ModelAndView("TimesheetApproval");
		List<TimeSheet> list = timeSheetService.findAll();
		mv.addObject("TimeSheet", list);
		mv.addObject("size", list.size());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/approvalTimeSheet/{id}", method = RequestMethod.GET)
	public ModelAndView approvalTimeSheet(@PathVariable long id){
		TimeSheet timeSheet = timeSheetService.findById(id);
		timeSheet.setStatus("Approval");
		timeSheetService.updateTimeSheet(timeSheet);
		ModelAndView mv = new ModelAndView("TimesheetApproval");
		List<TimeSheet> list = timeSheetService.findAll();
		mv.addObject("TimeSheet", list);
		mv.addObject("size", list.size());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/editJob/{id}", method = RequestMethod.GET)
	public ModelAndView editJob(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("JobManagerEdit");
		mv.addObject("jobId", id);
		mv.addObject("JobManager", new JobManager());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/editJob/{id}", method = RequestMethod.POST)
	public ModelAndView editJobConfirm(@PathVariable long id, JobManager job) throws ParseException {
		job.setJobId(id);
		jobManagerService.update(job);
		ModelAndView mv = new ModelAndView("JobManager");
		List<JobManager> list = jobManagerService.findAll();
		mv.addObject("JobManager", list);
		mv.addObject("size", String.valueOf(list.size()));
		return mv;
	}
	
	@RequestMapping(value = "/Admin/createJob", method = RequestMethod.GET)
	public ModelAndView createJob() {
		ModelAndView mv = new ModelAndView("JobManagerCreate");
		mv.addObject("JobManager", new JobManager());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/createJob", method = RequestMethod.POST)
	public ModelAndView createTimesheetConfirm(JobManager jobManager) throws ParseException {
		jobManagerService.createJobManager(jobManager);
		ModelAndView mv = new ModelAndView("JobManager");
		List<JobManager> list = jobManagerService.findAll();
		mv.addObject("JobManager", list);
		mv.addObject("size", String.valueOf(list.size()));
		return mv;
	}
	
	@RequestMapping(value = "/Admin/editMachine/{id}", method = RequestMethod.GET)
	public ModelAndView editMachine(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("MachineManagerEdit");
		mv.addObject("MachineId", id);
		mv.addObject("Machine", new MachineManager());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/editMachine/{id}", method = RequestMethod.POST)
	public ModelAndView editMachineConfirm(@PathVariable long id, MachineManager machine) throws ParseException {
		machine.setMachineId(id);
		machineManagerService.updateMachineManager(machine);
		ModelAndView mv = new ModelAndView("MachineMangement");
		List<MachineManager> list = machineManagerService.findAll();
		mv.addObject("MachineManager", list);
		mv.addObject("size", list.size());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/createMachine", method = RequestMethod.GET)
	public ModelAndView createMachine() {
		ModelAndView mv = new ModelAndView("MachineCreate");
		mv.addObject("Machine", new MachineManager());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/createMachine", method = RequestMethod.POST)
	public ModelAndView createMachineConfirm(MachineManager machineManager) throws ParseException {
		machineManagerService.createMachineManager(machineManager);
		ModelAndView mv = new ModelAndView("MachineMangement");
		List<MachineManager> list = machineManagerService.findAll();
		mv.addObject("MachineManager", list);
		mv.addObject("size", list.size());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/deleteMachine/{id}", method = RequestMethod.GET)
	public ModelAndView deleteMachine(@PathVariable long id) {
		machineManagerService.deleteById(id);
		ModelAndView mv = new ModelAndView("MachineMangement");
		List<MachineManager> list = machineManagerService.findAll();
		mv.addObject("MachineManager", list);
		mv.addObject("size", list.size());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/deleteJob/{id}", method = RequestMethod.GET)
	public ModelAndView deleteJob(@PathVariable long id) {
		jobManagerService.deleteById(id);
		ModelAndView mv = new ModelAndView("JobManager");
		List<JobManager> list = jobManagerService.findAll();
		mv.addObject("JobManager", list);
		mv.addObject("size", String.valueOf(list.size()));
		return mv;
	}
	
	@RequestMapping(value = "/Normal/showTimeSheet", method = RequestMethod.GET)
	public ModelAndView getTimeSheetNormal(){
		ModelAndView mv = new ModelAndView("TimeSheetApprovalNormal");
		List<TimeSheet> list = timeSheetService.findAll();
		mv.addObject("TimeSheet", list);
		mv.addObject("size", list.size());
		return mv;
	}
	
	@RequestMapping(value = "/Normal/createTimesheet", method = RequestMethod.GET)
	public ModelAndView createTimesheet() {
		ModelAndView mv = new ModelAndView("TimeSheetSubmission2");
		mv.addObject("JobWorkload", new JobWorkload());
		mv.addObject("MachineWorkload", new MachineWorkload());
		return mv;
	}
	
	@RequestMapping(value = "/Normal/createTimesheet", method = RequestMethod.POST)
	public ModelAndView createTimesheetConfirm(JobWorkload jobWorkload, MachineWorkload machineWorkload,
			@RequestParam("siteCode") String siteCode, @RequestParam("Job") String jobCode,
			@RequestParam("Machine") String machineCode) throws ParseException {
		Date date = new Date();
		TimeSheet timeSheet = new TimeSheet(date, siteCode);
		MachineManager machineManager = machineManagerService.findByCode(machineCode);
		machineWorkload.setMachineManager(machineManager);
		machineWorkloadService.createMachineWorkload(machineWorkload);
		
		JobManager jobManager = jobManagerService.findByJobCode(jobCode);
		jobWorkload.setJobManager(jobManager);
		jobWorkloadService.createJobWorkload(jobWorkload);
		
		timeSheet.setJobWorkload(jobWorkload);
		timeSheet.setMachineWorkload(machineWorkload);
		timeSheetService.createTimeSheet(timeSheet);
		ModelAndView mv = new ModelAndView("TimeSheetApprovalNormal");
		List<TimeSheet> list = timeSheetService.findAll();
		mv.addObject("TimeSheet", list);
		mv.addObject("size", list.size());
		return mv;
	}
}
