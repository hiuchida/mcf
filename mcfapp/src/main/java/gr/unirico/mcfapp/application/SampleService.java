package gr.unirico.mcfapp.application;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class SampleService {

	public List<Map<String, String>> getHistories() {
		List<Map<String, String>> list = new ArrayList<>();
		String preHash = new String();
		for(int i = 0; i < 3; i++) {
			String hash = UUID.randomUUID().toString();
			Map<String, String> map = new HashMap<>();
			map.put("content", String.valueOf(i));
			map.put("hash", hash);
			map.put("prehash", preHash);
			list.add(map);
			preHash = hash;
		}

		return list;
	}

}
