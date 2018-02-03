/*
	アーカイブリスト画面を表示するコントローラ
 */

package gr.unirico.mcfapp.interfaces;

import gr.unirico.mcfapp.application.ArchiveService;
import gr.unirico.mcfapp.application.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/archives/{archiveId}")
public class ArchiveController {

	// 本番用
	@Autowired
	ArchiveService archiveService;

	// Mock
	@Autowired
	MockService mockService;

	@GetMapping
	public ModelAndView index(@PathVariable("archiveId") String archiveId) {
		ModelAndView mav = new ModelAndView("v1/archives");
		mav.addObject("data", mockService.getArchive(archiveId, "https://www.unirita.co.jp/" + archiveId));
		return mav;
	}
}