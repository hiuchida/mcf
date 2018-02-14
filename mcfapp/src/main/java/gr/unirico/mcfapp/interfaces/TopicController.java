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
import gr.unirico.mcflib.api.Topic;

@Controller
@RequestMapping("/topics")
public class TopicController {
    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);

	@Autowired
	TopicService topicService;

	@GetMapping
	public ModelAndView list() {
		logger.info("list: /topics");
		ModelAndView mav = new ModelAndView("v1/fragment/topic::topic");
		mav.addObject("topics", topicService.getTopicList());
		return mav;
	}

	@GetMapping("/{topicId}")
	public ModelAndView index(@PathVariable("topicId") String topicId) {
		logger.info("index: /topics/{}", topicId);
		try {
			ModelAndView mav = new ModelAndView("v1/topics");
			Topic topic = topicService.getTopic(topicId);
			mav.addObject("data", topicService.getMapFromTopic(topic));
			return mav;
		} catch (Exception e) {
			logger.error("Error in getTopic", e);
			return new ModelAndView("redirect:/");
		}
	}

	@GetMapping("/{topicId}/comments")
	public ModelAndView comments(@PathVariable("topicId") String topicId) {
		logger.info("comments: /topics/{}/comments", topicId);
		ModelAndView mav = new ModelAndView("v1/fragment/comment :: comment");
		try {
			Topic topic = topicService.getTopic(topicId);
			mav.addObject("data", topicService.getMapFromTopic(topic));
		} catch (Exception e) {
			logger.error("Error in getTopic", e);
		}
		return mav;
	}

}
