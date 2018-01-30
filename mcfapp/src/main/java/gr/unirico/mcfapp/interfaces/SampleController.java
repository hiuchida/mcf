package gr.unirico.mcfapp.interfaces;

import gr.unirico.mcfapp.application.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sample")
public class SampleController {

	@Autowired
	SampleService sampleService;

	@GetMapping
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("v1/sample");
		return mav;
	}

	@GetMapping("/histories")
	public ModelAndView getHistories() {
		ModelAndView mav = new ModelAndView("v1/history :: card");

		// プロパティに値を入れる
		mav.addObject("histories", sampleService.getHistories());
		return mav;
	}
}
