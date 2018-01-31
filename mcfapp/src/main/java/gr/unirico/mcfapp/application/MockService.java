package gr.unirico.mcfapp.application;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class MockService {

	public List<Map<String, String>> listTopics() {
		List<Map<String, String>> topics = new ArrayList<>();
		for(int i = 1; i < 5; i++) {
			Map<String, String> map = new HashMap<>();
			map.put("id", String.valueOf(i));
			map.put("name", "https://www.unirita.co.jp/" + i);
			topics.add(map);
		}
		return topics;
	}

	public Map<String, Object> getArchive(String id, String name) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		map.put("comments", this.createComments(3));
		return map;
	}

	public Map<String, Object> getComments() {
		Map<String, Object> map = new HashMap<>();
		map.put("comments", this.createComments(3));
		return map;
	}

	List<Map<String, String>> createComments(int commentCounts) {
		List<Map<String, String>> list = new ArrayList<>();
		String preHash = new String();
		for(int i = 0; i < commentCounts; i++) {
			String hash = UUID.randomUUID().toString();
			Map<String, String> map = new HashMap<>();
			map.put("content", "コメント" + String.valueOf(i));
			map.put("hash", hash);
			map.put("prehash", preHash);
			list.add(map);
			preHash = hash;
		}

		return list;
	}
}
