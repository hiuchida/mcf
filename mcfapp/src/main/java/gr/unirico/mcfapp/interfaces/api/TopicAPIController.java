package gr.unirico.mcfapp.interfaces.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.unirico.mcfapp.application.ArchiveService;
import gr.unirico.mcfapp.application.TopicService;

@RestController
@RequestMapping("/api/topics")
public class TopicAPIController {
	private static final Logger logger = LoggerFactory.getLogger(TopicAPIController.class);

	@Autowired
	TopicService topicService;

	@Autowired
	ArchiveService archiveService;

	@PostMapping
	public ResponseEntity<?> saveTopic(@RequestBody Map<String, String> data) {
		String sitename = data.get("name");
		String siteurl = data.get("url");
		try {
			topicService.createTopic(sitename, siteurl);
		} catch (Exception e) {
			logger.error("Error in createTopic", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/{topicId}/comment")
	public ResponseEntity<?> saveComment(@PathVariable("topicId") String tid, @RequestBody Map<String, String> data) {
		String userid = data.get("userid");
		String comment = data.get("comment");

		try {
			topicService.addComment(tid, userid, comment);
		} catch (Exception e) {
			logger.error("Error in addComment", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{topicId}/archive")
	public ResponseEntity<?> archive(@PathVariable("topicId") String tid) {
		try {
			archiveService.archiveTopic(topicService.getTopic(tid));
		} catch (Exception e) {
			logger.error("Error in archiveTopic", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		Map<String, String> map = new HashMap<>();
		map.put("archiveId", tid);
		return ResponseEntity.ok(map);
	}

}
