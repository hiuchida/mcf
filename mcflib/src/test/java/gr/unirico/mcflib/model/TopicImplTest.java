package gr.unirico.mcflib.model;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class TopicImplTest {
	private static Logger logger = LoggerFactory.getLogger(TopicImplTest.class);
	public static final String TESTURL = "testUrl";
	private TopicImpl t;

	@Before
	public void setUp() throws Exception {
		t = new TopicImpl(NodeImplTest.TESTPREVID, NodeImplTest.TESTID, "topicTest", NodeImplTest.TESTTIMESTAMP, "running");
		logger.debug("setUp:");
	}

	@After
	public void tearDown() throws Exception {
		logger.debug("tearDown:");
	}

	@Test
	public void testNew() throws Exception {
		TopicImpl topic = new TopicImpl("test");
		TestCase.assertEquals("running", topic.getStatus());
		TestCase.assertEquals("", topic.getUrl());
		TestCase.assertEquals(0, topic.getList().size());
	}

	@Test
	public void testToList() throws Exception {
		t.setUrl(TopicImplTest.TESTURL);
		List<String> l = t.toList();
		TestCase.assertEquals(8, l.size());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.TopicImpl", l.get(0));
		TestCase.assertEquals("previd:000001", l.get(1));
		TestCase.assertEquals("id:000002", l.get(2));
		TestCase.assertEquals("name:topicTest", l.get(3));
		TestCase.assertEquals("timestamp:2018-01-01", l.get(4));
		TestCase.assertEquals("status:running", l.get(5));
		TestCase.assertEquals("url:testUrl", l.get(6));
		TestCase.assertTrue(l.get(7).startsWith("hash:"));
	}

	@Test
	public void testToList2() throws Exception {
		CommentImpl c = new CommentImpl("first-id", "000002", "commentTest", "2018-01-01");
		c.setUserid(CommentImplTest.TESTUSER);
		c.setComment(CommentImplTest.TESTCOMMENT);
		t.setUrl(TopicImplTest.TESTURL);
		t.addValidate(c);
		List<String> l = t.toList();
		TestCase.assertEquals(16, l.size());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.TopicImpl", l.get(0));
		TestCase.assertEquals("previd:000001", l.get(1));
		TestCase.assertEquals("id:000002", l.get(2));
		TestCase.assertEquals("name:topicTest", l.get(3));
		TestCase.assertEquals("timestamp:2018-01-01", l.get(4));
		TestCase.assertEquals("status:running", l.get(5));
		TestCase.assertEquals("url:testUrl", l.get(6));
		TestCase.assertEquals("\tclass:gr.unirico.mcflib.model.CommentImpl", l.get(7));
		TestCase.assertEquals("\tprevid:first-id", l.get(8));
		TestCase.assertEquals("\tid:000002", l.get(9));
		TestCase.assertEquals("\tname:commentTest", l.get(10));
		TestCase.assertEquals("\ttimestamp:2018-01-01", l.get(11));
		TestCase.assertEquals("\tuserid:testuser", l.get(12));
		TestCase.assertEquals("\tcomment:testComment", l.get(13));
		TestCase.assertTrue(l.get(14).startsWith("\thash:"));
		TestCase.assertTrue(l.get(15).startsWith("hash:"));
	}

	@Test
	public void testToDigestString() throws Exception {
		t.setUrl(TopicImplTest.TESTURL);
		String hash = "qRDxrAlb8P7KZHGcBPenMLClJKEDP7G1DtQlTW7Q2BhjszYUd14yEy7gsFyzpDVqS3ohv+S5fUgOFUm+7DziWA==";
		TestCase.assertEquals(hash, t.toDigestString());
	}

	@Test
	public void testAdd() throws Exception {
		CommentImpl c = new CommentImpl(NodeImplTest.TESTPREVID, NodeImplTest.TESTID, "commentTest", NodeImplTest.TESTTIMESTAMP);
		TestCase.assertEquals(0, t.getList().size());
		t.add(c);
		TestCase.assertEquals(1, t.getList().size());
	}

	@Test
	public void testArchive() throws Exception {
		t.setUrl(TopicImplTest.TESTURL);
		t.archive(false, t, NodeImplTest.PREVID);
		TestCase.assertEquals(NodeImplTest.PREVID, t.getPrevid());
		TestCase.assertEquals("complete", t.getStatus());
	}

	@Test
	public void testArchive2() throws Exception {
		t.setUrl(TopicImplTest.TESTURL);
		t.archive(true, t, NodeImplTest.TESTPREVID);
	}

	@Test
	public void testSetUserid() throws Exception {
		t.setUrl(TopicImplTest.TESTURL);
		t.archive(false, t, NodeImplTest.PREVID);
		try {
			t.setUrl(TopicImplTest.TESTURL);
			TestCase.fail();
		} catch (Exception e) {
		}
	}

}
