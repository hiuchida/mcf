package gr.unirico.mcflib.impl;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.api.Topic;
import gr.unirico.mcflib.util.FileUtil;
import junit.framework.TestCase;

public class McfApiImplTest {
	private static Logger logger = LoggerFactory.getLogger(McfApiImplTest.class);
	private McfApiImpl a;
	
	@Before
	public void setUp() throws Exception {
		a = new McfApiImpl("data");
		String dir = a.getDataDir();
		int cnt = FileUtil.deleteFiles(dir);
		logger.debug("setUp: deleteFiles={}", cnt);
	}

	@After
	public void tearDown() throws Exception {
		String dir = a.getDataDir();
		int cnt = FileUtil.deleteFiles(dir);
		logger.debug("tearDown: deleteFiles={}", cnt);
	}

	@Test
	public void testGetDataDir() throws Exception {
		McfApiImpl api = new McfApiImpl("data");
		TestCase.assertEquals("data", api.getDataDir());
	}

	@Test
	public void testNewComment() throws Exception {
		Comment c = a.newComment("testuser1", "testcomment");
		TestCase.assertEquals("testcomment", c.getName());
		TestCase.assertEquals("testuser1", c.getUserid());
	}

	@Test
	public void testNewTopic() throws Exception {
		Topic t = a.newTopic("testsite", "testurl");
		TestCase.assertEquals("testsite", t.getName());
		TestCase.assertEquals("testurl", t.getUrl());
	}

	@Test
	public void testGetTopicList() throws Exception {
		List<Topic> l = a.getTopicList();
		TestCase.assertEquals(0, l.size());
	}

	@Test
	public void testGetArchivedTopicList() throws Exception {
		List<Topic> l = a.getArchivedTopicList();
		TestCase.assertEquals(0, l.size());
	}

	@Test
	public void testArchiveTopic() throws Exception {
		Topic t = a.newTopic("testsite", "testurl");
		a.writeTopic(t);
		Topic t2 = a.readTopic(t.getId());
		a.archiveTopic(t2);
	}

}
