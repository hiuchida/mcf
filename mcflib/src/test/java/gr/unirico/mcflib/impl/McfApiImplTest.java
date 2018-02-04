package gr.unirico.mcflib.impl;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.api.Topic;
import junit.framework.TestCase;

public class McfApiImplTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNew() throws Exception {
		McfApiImpl a = new McfApiImpl("data");
		TestCase.assertEquals("data", a.getDataDir());
	}

	@Test
	public void testNewComment() throws Exception {
		McfApiImpl a = new McfApiImpl("data");
		Comment c = a.newComment("test");
		TestCase.assertEquals("test", c.getName());
	}

	@Test
	public void testNewTopic() throws Exception {
		McfApiImpl a = new McfApiImpl("data");
		Topic t = a.newTopic("test");
		TestCase.assertEquals("test", t.getName());
	}

	@Test
	public void testGetTopicList() throws Exception {
		McfApiImpl a = new McfApiImpl("data");
		List<Topic> l = a.getTopicList();
		TestCase.assertEquals(0, l.size());
	}

	@Test
	public void testGetArchivedTopicList() throws Exception {
		McfApiImpl a = new McfApiImpl("data");
		List<Topic> l = a.getArchivedTopicList();
		TestCase.assertNotNull(l);
	}

	@Test
	public void testArchiveTopic() throws Exception {
		McfApiImpl a = new McfApiImpl("data");
		Topic t = a.newTopic("test");
		a.writeTopic(t);
		Topic t2 = a.readTopic(t.getId());
		a.archiveTopic(t2);
	}

}
