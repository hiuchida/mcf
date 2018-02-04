/*
	トップ画面を表示するコントローラ
	トピックとアーカイブの一覧を表示する
 */

package gr.unirico.mcfapp.interfaces;

import gr.unirico.mcfapp.application.ArchiveService;
import gr.unirico.mcfapp.application.MockService;
import gr.unirico.mcfapp.application.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	// 本番用
	@Autowired
	TopicService topicService;
	@Autowired
	ArchiveService archiveService;

	// Mock
//	@Autowired
//	MockService mockService;

	@GetMapping
	public ModelAndView index() {
		// ユーザ名を取得する
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getName());

		ModelAndView mav = new ModelAndView("v1/home");
		mav.addObject("topics", topicService.getTopicList());
		mav.addObject("archives", archiveService.getArchivedTopicList());

		return mav;
	}
}
