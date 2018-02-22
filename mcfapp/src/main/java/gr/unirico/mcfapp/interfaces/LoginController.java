package gr.unirico.mcfapp.interfaces;

import java.lang.invoke.MethodHandles;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@GetMapping
	public ModelAndView login(
			@RequestParam(name = "redirectUri", required = false) String redirectUri,
			@RequestParam(name = "error", required = false) String error, HttpServletRequest request) throws Exception{
		logger.info("login: /login");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
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
