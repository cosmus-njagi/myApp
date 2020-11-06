package com.afrisoln;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class Homecontroller {
	@Autowired		//method for calling the useRservice claSS
	private Userservice userservice;
	@RequestMapping("/dashboard")
	public String welcome(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return"welcome";
	}
	@RequestMapping("/register")
	public String registration(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_REGISTER");
		return"welcome";
	}
	
	@PostMapping("/save-user")
	public String registeruser(@ModelAttribute User user,BindingResult bindingresult,HttpServletRequest request) {
		userservice.savemyuser(user);
		request.setAttribute("mode","MODE_HOME");
		return "welcome";
	}
	
	@GetMapping("/show-users")
	public String showallusers(HttpServletRequest request) {
		request.setAttribute("users", userservice.showallusers());
		request.setAttribute("mode", "ALL_USERS");
		return "welcome";
	}
	
	@RequestMapping("/delete-user")
	public String deleteuser(@RequestParam int id,HttpServletRequest request) {
		userservice.deletemyuser(id);
		request.setAttribute("users", userservice.showallusers());
		request.setAttribute("mode", "ALL_USERS");
		return"welcome";
	}
	
	@RequestMapping("/edit-user")
	public ModelAndView edituser(@RequestParam int id,HttpServletRequest request) {
		ModelAndView mv=new ModelAndView("welcome");
		User user=userservice.get(id);
		mv.addObject("user",user);
		request.setAttribute("users", userservice.showallusers());
		request.setAttribute("mode", "MODE_UPDATE");
		return mv;
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN");
		return "welcome";
	}
	
	@RequestMapping("login-user")
	public String loginuser(@ModelAttribute User user,HttpServletRequest request) {
		if(userservice.findByUsernameAndPassword(user.getUsername(), user.getPassword())!=null) {
			return "home";
		}
		else {
			request.setAttribute("error", "Invalid username or password");
			request.setAttribute("mode", "MODE_LOGIN");
			return "welcome";
		}
	}
	
	@RequestMapping("/search")
public ModelAndView search(@RequestParam String keyword) {
		ModelAndView mv=new ModelAndView();
		List<User>result=userservice.search(keyword);
				mv.addObject("result",result);
				return mv;
	
}
	
}



