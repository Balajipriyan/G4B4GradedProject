package com.greatlearning.ems.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/list")
	public String listEmployee(Model model) {
		List<Employee> employees = employeeService.listAll();

		model.addAttribute("employees", employees);
		System.out.println("Employees-->" + employees);

		return "employeeslist";

	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Employee theEmployee = new Employee();

		theModel.addAttribute("Employee", theEmployee);

		return "Employee-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

		Employee theEmployee = employeeService.findById(theId);

		theModel.addAttribute("Employee", theEmployee);

		return "Employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@RequestParam("id") int id, @RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname, @RequestParam("email") String email) {

		System.out.println(id);
		Employee theEmployee;
		if (id != 0) {
			theEmployee = employeeService.findById(id);
			theEmployee.setFirstName(firstname);
			theEmployee.setLastName(lastname);
			theEmployee.setEmail(email);
		} else
			theEmployee = new Employee(firstname, lastname, email);

		employeeService.saveEmployee(theEmployee);

		return "redirect:/employees/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {

		employeeService.deleteById(theId);

		return "redirect:/employees/list";

	}

	@RequestMapping("/search")
	public String search(@RequestParam("firstName") String firstName, Model model) {

		if (firstName.trim().isEmpty()) {
			return "redirect:/employees/list";
		} else {
			List<Employee> employees = employeeService.searchBy(firstName);

			model.addAttribute("employees", employees);

			return "employeeslist";
		}

	}

	@RequestMapping(value = "/403")
	public ModelAndView handleForbiddenError(Principal user) {

		ModelAndView mv = new ModelAndView();

		mv.setViewName("403");

		String message = "";
		if (user != null) {

			message = "Hello " + user.getName() + ", "
					+ " You dont have permission to access the page / perform the operation";
		} else {

			message = "Hello " + ", " + " You dont have permission to access the page / perform the operation";
		}

		mv.addObject("msg", message);

		return mv;
	}

}
