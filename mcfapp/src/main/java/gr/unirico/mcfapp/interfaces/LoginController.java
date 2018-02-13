package gr.unirico.mcfapp.interfaces;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public ModelAndView login(@RequestParam(name = "error", required = false) String error) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// FIXME
		if(authentication.getCredentials() != null) {
			ModelAndView mav = new ModelAndView("v1/login");
			if (error != null) {
				mav.addObject("error", "error");
			}
			return mav;
		}

		return new ModelAndView("redirect:/");
	}

}
