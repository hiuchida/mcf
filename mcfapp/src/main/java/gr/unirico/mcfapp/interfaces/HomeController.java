/*
	トップ画面を表示するコントローラ
	トピックとアーカイブの一覧を表示する
 */

package gr.unirico.mcfapp.interfaces;

import gr.unirico.mcfapp.application.ArchiveService;
import gr.unirico.mcfapp.application.MockService;
import gr.unirico.mcfapp.application.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
	// 本番用
	@Autowired
	TopicService topicService;
	@Autowired
	ArchiveService archiveService;

	// Mock
	@Autowired
	MockService mockService;

	@GetMapping
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("v1/home");
		mav.addObject("topics", mockService.listTopics());
		mav.addObject("archives", mockService.listTopics());
		return mav;
	}
}
