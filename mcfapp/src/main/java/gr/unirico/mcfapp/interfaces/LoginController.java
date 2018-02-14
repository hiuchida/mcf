package gr.unirico.mcfapp.interfaces;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public ModelAndView login(
			@RequestParam(name = "redirectUri", required = false) String redirectUri,
			@RequestParam(name = "error", required = false) String error, HttpServletRequest request) throws Exception{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// FIXME
		if(authentication.getCredentials() != null) {
			ModelAndView mav = new ModelAndView("v1/login");

			if(StringUtils.isNotBlank(redirectUri)) {
				mav.addObject("redirectUri", redirectUri);
			} else {
				URL redirectUrl = new URL(request.getHeader("REFERER"));
				mav.addObject("redirectUri", redirectUrl.getPath());
			}

			if (error != null) {
				mav.addObject("error", "error");
			}
			return mav;
		}

		return new ModelAndView("redirect:/");
	}

}
