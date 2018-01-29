package gr.unirico.mcfapp.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sample")
public class SampleController {

	@GetMapping
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("v1/sample");
		return mav;
	}
}
