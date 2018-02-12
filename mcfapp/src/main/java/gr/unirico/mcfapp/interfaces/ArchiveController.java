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

@Controller
@RequestMapping("/archives/{archiveId}")
public class ArchiveController {

	private static final Logger logger = LoggerFactory.getLogger(ArchiveController.class);

	// 本番用
	@Autowired
	ArchiveService archiveService;

	// Mock
//	@Autowired
//	MockService mockService;

	@GetMapping
	public ModelAndView index(@PathVariable("archiveId") String archiveId) {
		ModelAndView mav = new ModelAndView("v1/archives");
		mav.addObject("data", archiveService.getArchivedTopicData(archiveId));
		logger.info("show archive [targetId: "  + archiveId + "]");
		return mav;
	}
}
