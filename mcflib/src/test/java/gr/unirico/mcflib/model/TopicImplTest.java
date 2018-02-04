package gr.unirico.mcflib.model;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TopicImplTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNew() throws Exception {
		TopicImpl t = new TopicImpl("test");
		TestCase.assertEquals("running", t.getStatus());
		TestCase.assertEquals("", t.getUrl());
		TestCase.assertEquals(0, t.getList().size());
	}

	@Test
	public void testToList() throws Exception {
		TopicImpl t = new TopicImpl("first-id", "id", "test", "timestamp", "running");
		t.setUrl("url");
		List<String> l = t.toList();
		TestCase.assertEquals(8, l.size());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.TopicImpl", l.get(0));
		TestCase.assertEquals("previd:first-id", l.get(1));
		TestCase.assertEquals("id:id", l.get(2));
		TestCase.assertEquals("name:test", l.get(3));
		TestCase.assertEquals("timestamp:timestamp", l.get(4));
		TestCase.assertEquals("status:running", l.get(5));
		TestCase.assertEquals("url:url", l.get(6));
		TestCase.assertEquals("hash:C5fzQy7AZRfUEsim1BkM6YMy0lANCB4seB8aXTBkCv9enbQ76vAmszTVrVN4A2S0rbUkDnlWcP4QYkkg0Taa6Q==", l.get(7));
	}

	@Test
	public void testToList2() throws Exception {
		CommentImpl c = new CommentImpl("first-id", "id", "test", "timestamp");
		c.setUserid("userid");
		c.setComment("comment");
		TopicImpl t = new TopicImpl("first-id", "id", "test", "timestamp", "running");
		t.setUrl("url");
		t.addValidate(c);
		List<String> l = t.toList();
		TestCase.assertEquals(16, l.size());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.TopicImpl", l.get(0));
		TestCase.assertEquals("previd:first-id", l.get(1));
		TestCase.assertEquals("id:id", l.get(2));
		TestCase.assertEquals("name:test", l.get(3));
		TestCase.assertEquals("timestamp:timestamp", l.get(4));
		TestCase.assertEquals("status:running", l.get(5));
		TestCase.assertEquals("url:url", l.get(6));
		TestCase.assertEquals("\tclass:gr.unirico.mcflib.model.CommentImpl", l.get(7));
		TestCase.assertEquals("\tprevid:first-id", l.get(8));
		TestCase.assertEquals("\tid:id", l.get(9));
		TestCase.assertEquals("\tname:test", l.get(10));
		TestCase.assertEquals("\ttimestamp:timestamp", l.get(11));
		TestCase.assertEquals("\tuserid:userid", l.get(12));
		TestCase.assertEquals("\tcomment:comment", l.get(13));
		TestCase.assertEquals("\thash:3+kzFyiFo9hScB4I2tSx60U8IAh6TIJkQNnij9FVyOte4X7Sa26dsK1oI/JDzZiKCp3klenmiESFpSP3fsLxlQ==", l.get(14));
		TestCase.assertEquals("hash:zcxZoAIa2jjbTqUYexkuiDacyLM+OlUe1ltZeZYQc0j8wDI469jZGTfKvJWCOVl7Y2KOHx1iAtxVTczkOlZLsw==", l.get(15));
	}

	@Test
	public void testToDigestString() throws Exception {
		TopicImpl t = new TopicImpl("first-id", "id", "test", "timestamp", "running");
		t.setUrl("url");
		TestCase.assertEquals("C5fzQy7AZRfUEsim1BkM6YMy0lANCB4seB8aXTBkCv9enbQ76vAmszTVrVN4A2S0rbUkDnlWcP4QYkkg0Taa6Q==", t.toDigestString());
	}

	@Test
	public void testAdd() throws Exception {
		CommentImpl c = new CommentImpl("first-id", "id", "test", "timestamp");
		TopicImpl t = new TopicImpl("first-id", "id", "test", "timestamp", "running");
		TestCase.assertEquals(0, t.getList().size());
		t.add(c);
		TestCase.assertEquals(1, t.getList().size());
	}

	@Test
	public void testArchive() throws Exception {
		TopicImpl t = new TopicImpl("first-id", "id", "test", "timestamp", "running");
		t.setUrl("url");
		t.archive(false, t, "previd");
		TestCase.assertEquals("previd", t.getPrevid());
		TestCase.assertEquals("complete", t.getStatus());
	}

	@Test
	public void testArchive2() throws Exception {
		TopicImpl t = new TopicImpl("previd", "id", "test", "timestamp", "running");
		t.setUrl("url");
		t.archive(true, t, "previd");
		TestCase.assertEquals("previd", t.getPrevid());
	}

	@Test
	public void testSetUserid() throws Exception {
		TopicImpl t = new TopicImpl("first-id", "id", "test", "timestamp", "running");
		t.setUrl("url");
		t.archive(false, t, "previd");
		try {
			t.setUrl("url");
			TestCase.fail();
		} catch (Exception e) {
		}
	}

}
