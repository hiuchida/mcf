/*
	アーカイブリスト画面を表示するコントローラ
 */
package gr.unirico.mcfapp.interfaces;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gr.unirico.mcfapp.application.ArchiveService;
import gr.unirico.mcfapp.application.TopicService;
import gr.unirico.mcflib.api.Topic;

@Controller
@RequestMapping("/archives")
public class ArchiveController {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	ArchiveService archiveService;

	@Autowired
	TopicService topicService;

	@GetMapping("/{archiveId}")
	public ModelAndView index(@PathVariable("archiveId") String archiveId) {
		logger.info("index: /archives/{}", archiveId);
		try {
			ModelAndView mav = new ModelAndView("v1/archives");
			Topic topic = archiveService.getArchivedTopic(archiveId);
			mav.addObject("data", topicService.getMapFromTopic(topic));
			return mav;
		} catch (Exception e) {
			logger.error("Error in getArchivedTopic", e);
			return new ModelAndView("redirect:/");
		}
	}

	@GetMapping("/{archiveId}/rawdata")
	public ModelAndView rawdata(@PathVariable("archiveId") String archiveId) {
		logger.info("index: /archives/{}/rawdata", archiveId);
		try {
			ModelAndView mav = new ModelAndView("v1/rawdata");
			Topic topic = archiveService.getArchivedTopic(archiveId);
			mav.addObject("data", topicService.getMapFromTopic(topic));
			mav.addObject("list", topic.toList());
			return mav;
		} catch (Exception e) {
			logger.error("Error in getArchivedTopic", e);
			return new ModelAndView("redirect:/");
		}
	}

}
