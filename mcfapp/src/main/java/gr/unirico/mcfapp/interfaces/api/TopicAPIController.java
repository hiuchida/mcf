package gr.unirico.mcfapp.interfaces.api;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	TopicService topicService;

	@Autowired
	ArchiveService archiveService;

	@PostMapping
	public ResponseEntity<?> saveTopic(@RequestBody Map<String, String> data) {
		logger.info("saveTopic: /api/topics");
		try {
			String sitename = data.get("name");
			String siteurl = data.get("url");
			topicService.createTopic(sitename, siteurl);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			logger.error("Error in createTopic", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/{topicId}/comment")
	public ResponseEntity<?> saveComment(@PathVariable("topicId") String topicId,
										 @RequestBody Map<String, String> data,
										 @AuthenticationPrincipal User user) {
		logger.info("saveComment: /api/topics/{}/comment", topicId);
		try {
			String comment = data.get("comment");
			topicService.addComment(topicId, user.getUsername(), comment);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			logger.error("Error in addComment", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{topicId}/archive")
	public ResponseEntity<?> archive(@PathVariable("topicId") String topicId) {
		logger.info("archive: /api/topics/{}/archive", topicId);
		try {
			Map<String, String> map = new HashMap<>();
			archiveService.archiveTopic(topicService.getTopic(topicId));
			map.put("archiveId", topicId);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			logger.error("Error in archiveTopic", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
