/*
	アーカイブリスト画面を表示するコントローラ
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

import gr.unirico.mcfapp.application.ArchiveService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/archives/{archiveId}")
public class ArchiveController {
	private static final Logger logger = LoggerFactory.getLogger(ArchiveController.class);

	// 本番用
	@Autowired
	ArchiveService archiveService;

	@GetMapping
	public ModelAndView index(@PathVariable("archiveId") String archiveId) {
		Map<String, Object> data = new HashMap<>();

		try {
			data = archiveService.getArchivedTopicData(archiveId);
		} catch (Exception e) {
			logger.error("Error in getArchivedTopic", e);
			return new ModelAndView("redirect:/");
		}

		ModelAndView mav = new ModelAndView("v1/archives");
		mav.addObject("data", data);
		logger.info("show archive [targetId: "  + archiveId + "]");
		return mav;
	}

}
