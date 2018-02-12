/*
	コメントリスト画面を表示するコントローラ
 */
package gr.unirico.mcfapp.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gr.unirico.mcfapp.application.TopicService;

@Controller
@RequestMapping("/topics/{topicId}")
public class TopicController {
    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);

	// 本番用
	@Autowired
	TopicService topicService;

	@GetMapping
	public ModelAndView index(@PathVariable("topicId") String topicId) {
		ModelAndView mav = new ModelAndView("v1/comments");
		mav.addObject("data", topicService.getTopicData(topicId));
		logger.info("show topic [topic id: " + topicId + "]");
		return mav;
	}

	@GetMapping("/comments")
	public ModelAndView getHistories(@PathVariable("topicId") String topicId) {
		ModelAndView mav = new ModelAndView("v1/fragment/comment :: comment");
		mav.addObject("data", topicService.getTopic(topicId).getList());
		logger.info("show topic comments [topic id: " + topicId + "]");
		return mav;
	}

}
