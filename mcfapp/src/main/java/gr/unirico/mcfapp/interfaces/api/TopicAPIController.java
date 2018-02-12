package gr.unirico.mcfapp.interfaces.api;

import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.api.McfApiFactory;
import gr.unirico.mcflib.api.Topic;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/topics")
public class TopicAPIController {

	McfApi api = McfApiFactory.getInstance();

	@GetMapping
	public ModelAndView getTopics(){
		List<Topic> topics = api.getTopicList();
		ModelAndView mav = new ModelAndView("v1/fragment/topic::topic");
		mav.addObject("topics", topics);
		return mav;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void saveTopic(@RequestBody Map<String, String> data) {
		String content = data.get("data");
		try {
			api.writeTopic(api.newTopic(content));
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@PostMapping("/{topicId}/comment")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveComment(@RequestBody Map<String, String> data) {
		String content = data.get("data");
		System.out.println(content);
	}

	@GetMapping("/{topicId}/archive")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> archive(@PathVariable("topicId") String topicId) {
		Map<String, String> map = new HashMap<>();
		map.put("archiveId", topicId);
		return map;
	}
}
