package com.labormanagement.java.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.labormanagement.java.entity.Role;
import com.labormanagement.java.entity.TimeSheet;
import com.labormanagement.java.entity.User;
import com.labormanagement.java.service.JobManagerService;
import com.labormanagement.java.service.JobWorkloadService;
import com.labormanagement.java.service.MachineManagerService;
import com.labormanagement.java.service.MachineWorkloadService;
import com.labormanagement.java.service.RoleService;
import com.labormanagement.java.service.TimeSheetService;
import com.labormanagement.java.service.UserService;

import java.text.ParseException;
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
	
	private User user;
	
	private void getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		user = (User) principal;
	}
	
	private void checkuser() {
			getUser();
	}
	
	@RequestMapping("/")
	public String indexPage() {
		return "login";
	}
	
	@RequestMapping("/logout-process")
	public String logout() {
		return "/logout";
	}
	
	@RequestMapping("/login-successful")
	public ModelAndView welcome() {
		ModelAndView mView = new ModelAndView("HomePage");
		checkuser();
		mView.addObject("username", user.getUsername());
		return mView;
	}
	
	@RequestMapping("/home")
	public ModelAndView home() {
		checkuser();
		ModelAndView mView = new ModelAndView("HomePage");
		mView.addObject("username", user.getUsername());
		return mView;
	}
	
	@RequestMapping(value = "/Admin/showJobCode", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView getJobManager(){
		ModelAndView mv = new ModelAndView("JobManager");
		List<JobManager> list = jobManagerService.findAll();
		mv.addObject("JobManager", list);
		mv.addObject("size", String.valueOf(list.size()));
		mv.addObject("username", user.getUsername());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/showMachineCode", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView getMachineManager(){
		ModelAndView mv = new ModelAndView("MachineMangement");
		List<MachineManager> list = machineManagerService.findAll();
		mv.addObject("MachineManager", list);
		mv.addObject("size", list.size());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		User user2 = (User) principal;
		mv.addObject("username", user2.getUsername());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/showTimeSheet", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView getTimeSheet(){
		ModelAndView mv = new ModelAndView("TimesheetApproval");
		List<TimeSheet> list = timeSheetService.findAll();
		mv.addObject("TimeSheet", list);
		mv.addObject("size", list.size());
		checkuser();
		mv.addObject("username", user.getUsername());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/approvalTimeSheet/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView approvalTimeSheet(@PathVariable long id){
		TimeSheet timeSheet = timeSheetService.findById(id);
		timeSheet.setStatus("Approval");
		timeSheetService.updateTimeSheet(timeSheet);
		ModelAndView mv = new ModelAndView("TimesheetApproval");
		List<TimeSheet> list = timeSheetService.findAll();
		mv.addObject("TimeSheet", list);
		mv.addObject("size", list.size());
		checkuser();
		mv.addObject("username", user.getUsername());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/editJob/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView editJob(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("JobManagerEdit");
		mv.addObject("jobId", id);
		mv.addObject("JobManager", new JobManager());
		checkuser();
		mv.addObject("username", user.getUsername());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/editJob/{id}", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView editJobConfirm(@PathVariable long id, JobManager job) throws ParseException {
		job.setJobId(id);
		jobManagerService.update(job);
		ModelAndView mv = new ModelAndView("JobManager");
		List<JobManager> list = jobManagerService.findAll();
		mv.addObject("JobManager", list);
		mv.addObject("size", String.valueOf(list.size()));
		checkuser();
		mv.addObject("username", user.getUsername());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/createJob", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView createJob() {
		ModelAndView mv = new ModelAndView("JobManagerCreate");
		mv.addObject("JobManager", new JobManager());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/createJob", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView createTimesheetConfirm(JobManager jobManager) throws ParseException {
		jobManagerService.createJobManager(jobManager);
		ModelAndView mv = new ModelAndView("JobManager");
		List<JobManager> list = jobManagerService.findAll();
		mv.addObject("JobManager", list);
		mv.addObject("size", String.valueOf(list.size()));
		return mv;
	}
	
	@RequestMapping(value = "/Admin/editMachine/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView editMachine(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("MachineManagerEdit");
		mv.addObject("MachineId", id);
		mv.addObject("Machine", new MachineManager());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/editMachine/{id}", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('admin')")
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
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView createMachine() {
		ModelAndView mv = new ModelAndView("MachineCreate");
		mv.addObject("Machine", new MachineManager());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/createMachine", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView createMachineConfirm(MachineManager machineManager) throws ParseException {
		machineManagerService.createMachineManager(machineManager);
		ModelAndView mv = new ModelAndView("MachineMangement");
		List<MachineManager> list = machineManagerService.findAll();
		mv.addObject("MachineManager", list);
		mv.addObject("size", list.size());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/deleteMachine/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView deleteMachine(@PathVariable long id) {
		machineManagerService.deleteById(id);
		ModelAndView mv = new ModelAndView("MachineMangement");
		List<MachineManager> list = machineManagerService.findAll();
		mv.addObject("MachineManager", list);
		mv.addObject("size", list.size());
		return mv;
	}
	
	@RequestMapping(value = "/Admin/deleteJob/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('admin')")
	public ModelAndView deleteJob(@PathVariable long id) {
		jobManagerService.deleteById(id);
		ModelAndView mv = new ModelAndView("JobManager");
		List<JobManager> list = jobManagerService.findAll();
		mv.addObject("JobManager", list);
		mv.addObject("size", String.valueOf(list.size()));
		return mv;
	}
	
	@RequestMapping(value = "/Normal/showTimeSheet", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('contactor')")
	public ModelAndView getTimeSheetNormal(){
		ModelAndView mv = new ModelAndView("TimeSheetApprovalNormal");
		List<TimeSheet> list = user.getSheets();
		mv.addObject("TimeSheet", list);
		mv.addObject("size", list.size());
		checkuser();
		mv.addObject("username", user.getUsername());
		return mv;
	}
	
	@RequestMapping(value = "/Normal/createTimesheet", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('contactor')")
	public ModelAndView createTimesheet() {
		ModelAndView mv = new ModelAndView("TimeSheetSubmission2");
		mv.addObject("JobWorkload", new JobWorkload());
		mv.addObject("MachineWorkload", new MachineWorkload());
		mv.addObject("jobs", jobManagerService.findAll());
		mv.addObject("machines", machineManagerService.findAll());
		return mv;
	}
	
	@RequestMapping(value = "/Normal/createTimesheet", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('contactor')")
	public ModelAndView createTimesheetConfirm(JobWorkload jobWorkload, MachineWorkload machineWorkload,
			@RequestParam("siteCode") String siteCode, @RequestParam("Job") String jobCode,
			@RequestParam("Machine") String machineCode, @RequestParam("hourMachine") double hour) throws ParseException {
		if(user == null) {
			return new ModelAndView("login");
		}
		
		Date date = new Date();
		TimeSheet timeSheet = new TimeSheet(date, siteCode);
		MachineManager machineManager = machineManagerService.findByCode(machineCode);
		machineWorkload.setHours(hour);
		machineWorkload.setMachineManager(machineManager);
		machineWorkloadService.createMachineWorkload(machineWorkload);
		
		JobManager jobManager = jobManagerService.findByJobCode(jobCode);
		jobWorkload.setJobManager(jobManager);
		jobWorkloadService.createJobWorkload(jobWorkload);
		
		timeSheet.setJobWorkload(jobWorkload);
		timeSheet.setMachineWorkload(machineWorkload);
		timeSheet.setUser(user);
		timeSheetService.createTimeSheet(timeSheet);
		
		List<TimeSheet> timeSheets = user.getSheets();
		timeSheets.add(timeSheet);
		user.setSheets(timeSheets);
		userService.updateUser(user);
		
		ModelAndView mv = new ModelAndView("TimeSheetApprovalNormal");
		List<TimeSheet> list = timeSheetService.findAll();
		mv.addObject("TimeSheet", list);
		mv.addObject("size", list.size());
		return mv;
	}
	
	@RequestMapping(value = "/createUserPage", method = RequestMethod.GET)
	public ModelAndView createUserPage() {
		ModelAndView mv = new ModelAndView("CreateUser");
		mv.addObject("NewUser", new User());
		return mv;
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ModelAndView createUserConfirm(User createdUser) throws ParseException {
		Role role = roleService.findByName(createdUser.getRoleName());
		createdUser.setRole(role);
		userService.createUser(createdUser);
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@RequestMapping(value = "/403")
    public ModelAndView error() {
		ModelAndView mv = new ModelAndView("Error"); 
        return mv;
    }
}
