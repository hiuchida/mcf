package gr.unirico.mcfapp.interfaces.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/sample")
public class SampleAPIController {

	/*
	DTOを作ってパラメータは管理しよう。
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Map<String, String> data) {
		String content = data.get("data");
		System.out.println(content);
	}
}
