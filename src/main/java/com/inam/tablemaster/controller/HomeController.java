package com.inam.tablemaster.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.inam.tablemaster.models.LoginUser;
import com.inam.tablemaster.models.TableMaster;
import com.inam.tablemaster.models.User;
import com.inam.tablemaster.services.TableMasterService;
import com.inam.tablemaster.services.UserService;

@Controller
public class HomeController {
	
	private final TableMasterService nameServ;
	private final UserService userServ;
	
	// Constructor
	public HomeController(TableMasterService nameServ, UserService userServ) {
		super();
		this.nameServ = nameServ;
		this.userServ = userServ;
	}
	
	// **********************************
	// ********** Login and Reg *********
	// **********************************
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}	
	
	// Route to register
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser,
		BindingResult result, Model model, HttpSession session) {

		// TO-DO Later -- call a register method in the service
		// to do some extra validations and create a new user!

		userServ.register(newUser, result);

		//
		// session.setAttribute("user_name", newUser.getUserName());

		if (result.hasErrors()) {
			// Be sure to send in the empty LoginUser before
			// re-rendering the page.
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}

		session.setAttribute("user_name", newUser.getUserName());

		// No errors!
		// TO-DO Later: Store their ID from the DB in session,
		// in other words, log them in.

		Long userID = newUser.getId();

		session.setAttribute("user_id", userID);

		return "redirect:/dashboard";
	}	
	
	// Route to Log in
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
		BindingResult result, Model model, HttpSession session) {

		// Add once service is implemented:
		// User user = userServ.login(newLogin, result);
		User user = userServ.login(newLogin, result);

		if (result.hasErrors()) {

			model.addAttribute("newUser", new User());

			return "index.jsp";
		}

		// No errors!
		// TO-DO Later: Store their ID from the DB in session,
		// in other words, log them in.
		session.setAttribute("user_name", user.getUserName());

		session.setAttribute("user_id", user.getId());

		return "redirect:/dashboard";

		}

	// Route to Log out
	@GetMapping("/logout")
	public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/";
	}
	
	
	// **********************************
	// ********** Dashboard *************
	// **********************************
	@GetMapping("/dashboard")
	public String dashboard(@ModelAttribute("tableMaster") TableMaster tableMaster, Model model, HttpSession session) {

		// If id is not in session, redirect to log in page.
		// To Stop users from going to routes without logging in.
		if (session.getAttribute("user_id") == null) {

			return "redirect:/";

		} else {

			// Getting the Id of from session
			Long userID = (Long) session.getAttribute("user_id");
			
			
			// Storing session ID into model -- sending it to JSP.
			//model.addAttribute("userID", userID);
			
			
			// Find user by Id
			User currentUser = userServ.findOne(userID);

			//
			tableMaster.setUser(currentUser);
			
			/////////
			
			// Send our Tables to the JSP using Model model
			model.addAttribute("allNames", nameServ.allNames());
			
			// Storing session ID into model -- sending it to JSP.
			//model.addAttribute("userID", userID);
			
			return "dashboard.jsp";
		}
	}
	
	// **********************************
	// ****Displaying all the Tables****
	// **********************************
	@GetMapping("/allTables")
	public String allTables(@ModelAttribute("tableMaster") TableMaster tableMaster, Model model, HttpSession session) {

		// If id is not in session, redirect to log in page.
		// To Stop users from going to routes without logging in.
		if (session.getAttribute("user_id") == null) {

			return "redirect:/";

		} else {

			// Getting the Id of from session
			Long userID = (Long) session.getAttribute("user_id");

			// Find user by Id
			User currentUser = userServ.findOne(userID);

			//
			tableMaster.setUser(currentUser);
			
			// Send our Tables to the JSP using Model model
			model.addAttribute("allNames", nameServ.allNames());

			return "allTables.jsp";
		}
	}
	
	
	// **********************************
	// ******* Creating a new Table ******
	// **********************************
	@GetMapping("/newTable")
	public String newTable(@ModelAttribute("tableMaster") TableMaster tableMaster, Model model, HttpSession session) {

		// If id is not in session, redirect to log in page.
		// To Stop users from going to routes without logging in.
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}

		// model.addAttribute("allNames", nameServ.allNames());

		return "newTable.jsp";
	}
	
	// Post Route to create a new Show.
	@PostMapping("/processTable")
	public String postingTable(@Valid @ModelAttribute("tableMaster") TableMaster tableMaster, BindingResult result, Model model,
			HttpSession session) {

		if (result.hasErrors()) {
			model.addAttribute("allNames", nameServ.allNames());
			return "newTable.jsp";

		} else {

			// Getting the Id of from session
			Long userID = (Long) session.getAttribute("user_id");

			// Find user by Id
			User currentUser = userServ.findOne(userID);

			// 
			tableMaster.setUser(currentUser);

			// Create a new Table Name
			nameServ.create(tableMaster);
			
			return "redirect:/dashboard";
		}

	}
	
	// **********************************
	// ******* Deleting a Table **********
	// **********************************
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, HttpSession session) {

		// If id is not in session, redirect to log in page.
		// To Stop users from going to routes without logging in.
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}

		nameServ.delete(id);

		return "redirect:/dashboard";
	}	
	
	
	// **********************************
	// ******* Editing a name ***********
	// **********************************
	@GetMapping("/updateTable/{id}")
	public String updateTable(@PathVariable("id") Long id, @ModelAttribute("tableMaster") TableMaster tableMaster, Model model,
			HttpSession session) {

		// If id is not in session, redirect to log in page.
		// To Stop users from going to routes without logging in.
		if (session.getAttribute("user_id") == null) {

			return "redirect:/";
		}

		model.addAttribute("tableMaster", nameServ.findOne(id));

		return "updateTable.jsp";
	}

	// Process the Post to update Table details
	@RequestMapping(value = "/updatingTable/{id}", method = RequestMethod.PUT)
	public String updatingShow(@Valid @ModelAttribute("tableMaster") TableMaster tableMaster, BindingResult result,
			HttpSession session) {
		
		
		// If errors render update table page
		if (result.hasErrors()) {
			return "updateTable.jsp";

		} else {
			// Getting the Id of from session
			Long userID = (Long) session.getAttribute("user_id");

			// Find user by Id
			User currentUser = userServ.findOne(userID);

			//
			tableMaster.setUser(currentUser);
			
			
			//	tvShow.setUser(tableMaster.getUser());
			nameServ.update(tableMaster);

			return "redirect:/dashboard";

		}

	}
	

}


