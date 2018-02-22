/*
	トップ画面を表示するコントローラ
	トピックとアーカイブの一覧を表示する
 */
package gr.unirico.mcfapp.interfaces;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gr.unirico.mcfapp.application.ArchiveService;
import gr.unirico.mcfapp.application.TopicService;

@Controller
@RequestMapping("/")
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	TopicService topicService;

	@Autowired
	ArchiveService archiveService;

	@GetMapping
	public ModelAndView index() {
		logger.info("index: /");
		ModelAndView mav = new ModelAndView("v1/home");
		mav.addObject("topics", topicService.getTopicList());
		mav.addObject("archives", archiveService.getArchivedTopicList());
		return mav;
	}

}
