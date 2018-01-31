/*
	コメントリスト画面を表示するコントローラ
 */

package gr.unirico.mcfapp.interfaces;

import gr.unirico.mcfapp.application.MockService;
import gr.unirico.mcfapp.application.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/topics/{topicId}")
public class TopicController {

	// 本番用
	@Autowired
	TopicService topicService;

	// Mock
	@Autowired
	MockService mockService;

	@GetMapping
	public ModelAndView index(@PathVariable("topicId") String topicId) {
		ModelAndView mav = new ModelAndView("v1/comments");
		mav.addObject("data", mockService.getArchive(topicId, "https://www.unirita.co.jp/" + topicId));
		return mav;
	}

	@GetMapping("/comments")
	public ModelAndView getHistories() {
		ModelAndView mav = new ModelAndView("v1/fragment/comment :: comment");
		mav.addObject("data", mockService.getComments());
		return mav;
	}

}
