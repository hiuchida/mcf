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

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/topics")
public class TopicController {
    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);

	// 本番用
	@Autowired
	TopicService topicService;

	@GetMapping("/topics")
	public ModelAndView list(){
		ModelAndView mav = new ModelAndView("v1/fragment/topic::topic");
		mav.addObject("topics", topicService.getTopicList());
		return mav;
	}

	@GetMapping("/{topicId}")
	public ModelAndView index(@PathVariable("topicId") String topicId) {
		Map<String, Object> topic = new HashMap<>();
		try {
			topic = topicService.getTopicData(topicId);
		} catch (Exception e) {
			logger.error("Error in getTopic", e);
			return new ModelAndView("redirect:/");
		}

		ModelAndView mav = new ModelAndView("v1/comments");
		mav.addObject("data", topic);
		logger.info("show topic [topic id: " + topicId + "]");
		return mav;
	}

	@GetMapping("/{topicId}/comments")
	public ModelAndView getHistories(@PathVariable("topicId") String topicId) {
		ModelAndView mav = new ModelAndView("v1/fragment/comment :: comment");
		try {
			mav.addObject("data", topicService.getTopicData(topicId));
			logger.info("show topic comments [topic id: " + topicId + "]");
		} catch (Exception e) {
			logger.error("Error in getTopic", e);
		}
		return mav;
	}

}
