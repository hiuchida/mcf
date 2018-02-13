package gr.unirico.mcfapp.interfaces.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import gr.unirico.mcfapp.application.ArchiveService;
import gr.unirico.mcfapp.application.TopicService;

@RestController
@RequestMapping("/api/topics")
public class TopicAPIController {
	//private static final Logger logger = LoggerFactory.getLogger(TopicAPIController.class);

	@Autowired
	TopicService topicService;

	@Autowired
	ArchiveService archiveService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView getTopics(){
		ModelAndView mav = new ModelAndView("v1/fragment/topic::topic");
		mav.addObject("topics", topicService.getTopicList());
		return mav;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void saveTopic( @RequestBody Map<String, String> data) {
		String sitename = data.get("name");
		String siteurl = data.get("url");
		topicService.createTopic(sitename, siteurl);
	}

	@GetMapping("{topicId}/comments")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView getComments(@PathVariable("topicId") String tid){
		ModelAndView mav = new ModelAndView("v1/fragment/comment::comment");
		mav.addObject("data", topicService.getTopicData(tid));
		return mav;
	}

	@PostMapping("/{topicId}/comment")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveComment(@PathVariable("topicId") String tid, @RequestBody Map<String, String> data) {
		String userid = "testuser1"; //TODO ログインしたユーザID
		String comment = data.get("data");
		topicService.addComment(tid, userid, comment);
		
	}

	@GetMapping("/{topicId}/archive")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> archive(@PathVariable("topicId") String tid) {
		archiveService.archiveTopic(topicService.getTopic(tid));
		Map<String, String> map = new HashMap<>();
		map.put("archiveId", tid);
		return map;
	}

}
