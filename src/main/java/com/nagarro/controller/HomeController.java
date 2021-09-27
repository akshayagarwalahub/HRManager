package com.nagarro.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagarro.dao.EmployeeRepository;
import com.nagarro.dao.UserRepository;
import com.nagarro.entities.Employees;
import com.nagarro.entities.User;
import com.nagarro.helper.Message;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private String nameOfUser;
	
	@ModelAttribute
	public void addCommonData(Model model) {
		String userName = nameOfUser;
		model.addAttribute("user_name",userName);
	}
	
	
	@RequestMapping("/")
	public String redirectHome() {
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public String home(Model model,HttpSession session) {
		model.addAttribute("title", "Home-HR Manager Portal");
		model.addAttribute("user",new User());
		return "home";
	}
	
	@GetMapping("/displayemployees")
	public String displayEmployees(Model m) {
		m.addAttribute("title", "Display-HR Manager Portal");
		List<Employees> employees = employeeRepository.findAll();
		m.addAttribute("employees",employees);
		return "displayemployees";
	}
	
	@GetMapping("/editemployees")
	public String editEmployees(Model m) {
		m.addAttribute("title", "Edit-HR Manager Portal");
		List<Employees> employees = employeeRepository.findAll();
		m.addAttribute("employees",employees);
		return "editemployees";
	}
	
	@PostMapping("/validateemployees")
	public String validateEmployees(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "home";
		}
		
		System.out.println(user);
		
		boolean foundUser = false;
		List<User> users = userRepository.findAll();
		for(User obj: users) {
			if(obj.getUsername().equals(user.getUsername()) && obj.getPassword().equals(user.getPassword())) {
				foundUser = true;
				break;
			}
		}
		if(foundUser) {
			nameOfUser = user.getUsername();
			//session.setAttribute("message", new Message("Successfully Registered!!","alert-success"));
			return "redirect:/displayemployees";
		}else {
			session.setAttribute("message", new Message("Whoops Something went Wrong!!","alert-danger"));
			return "redirect:/home";
		}
 		
	}
	

	
	@GetMapping("/addemployees")
	public String addEmployees(Model m) {
		m.addAttribute("title", "Add Employee -HR Manager Portal");
		m.addAttribute("employees",new Employees());
		return "addemployees";
	}
	
	@PostMapping("/processemployees")
	public String processEmployees(@Valid @ModelAttribute Employees employee ,BindingResult result, Model m ,HttpSession session) {
		if(result.hasErrors()) {
			System.out.println(result);
			return "addemployees";
		}
		
		try {
			this.employeeRepository.save(employee);

			// session.setAttribute("message", new Message("Employee Successfully Added!!","alert-success"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.setAttribute("message", new Message("Whoops Something went Wrong!!","alert-danger"));
			return "addemployees";
		}
		
		return "redirect:/displayemployees";
	}
	
	//open update form handler

	
	@GetMapping("/update-employee/{employeeId}")
	public String getUpdateForm(@PathVariable("employeeId") Integer employeeId, Model m) {
		m.addAttribute("title", "Update Employee -HR Manager Portal");
		Employees employee = this.employeeRepository.findById(employeeId).get();
		m.addAttribute("employee",employee);
		return "editemployees";
	}
	
	@PostMapping("/update-employee/{employeeId}")
	public String updateForm(@PathVariable("employeeId") Integer employeeId, Model m) {
		m.addAttribute("title", "Update Employee -HR Manager Portal");
		Employees employee = this.employeeRepository.findById(employeeId).get();
		m.addAttribute("employee",employee);
		return "editemployees";
	}
	
	//update employee handler
	
	@PostMapping("/updateemployee")
	public String updateHandler(@Valid @ModelAttribute Employees employee,BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			System.out.println(result);
			return "redirect:/update-employee/" + employee.getEmployeeId();
		}
		
		try {
			this.employeeRepository.save(employee);
			// session.setAttribute("message", new Message("Employee Successfully Updated!!","alert-success"));
		} catch (Exception e) {
			// TODO: handle exception
			session.setAttribute("message", new Message("Whoops Something went Wrong!!","alert-danger"));
		}
		return "redirect:/displayemployees";
	}
	
	
}
