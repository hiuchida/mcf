package gr.unirico.mcflib.model;

import java.util.Iterator;
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
		t = new TopicImpl(NodeImplTest.TESTPREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTID, NodeImplTest.TOPICNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS, NodeImplTest.TESTPROOF);
		logger.debug("setUp:");
	}

	@After
	public void tearDown() throws Exception {
		logger.debug("tearDown:");
	}

	@Test
	public void testNew() throws Exception {
		TopicImpl topic = new TopicImpl("test");
		TestCase.assertEquals("", topic.getUrl());
		TestCase.assertEquals(0, topic.getList().size());
	}

	@Test
	public void testToList() throws Exception {
		t.setUrl(TopicImplTest.TESTURL);
		List<String> l = t.toList();
		TestCase.assertEquals(13, l.size());
		Iterator<String> i = l.iterator();
		TestCase.assertEquals("---", i.next());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.TopicImpl", i.next());
		TestCase.assertEquals("previd:000001", i.next());
		TestCase.assertEquals("prevhash:0", i.next());
		TestCase.assertEquals("id:000002", i.next());
		TestCase.assertEquals("name:topicTest", i.next());
		TestCase.assertEquals("timestamp:2018-01-01", i.next());
		TestCase.assertEquals("status:editing", i.next());
		TestCase.assertEquals("proof:0", i.next());
		TestCase.assertEquals("url:testUrl", i.next());
		TestCase.assertEquals("\t---", i.next());
		TestCase.assertEquals("\t---", i.next());

		TestCase.assertTrue(i.next().startsWith("hash:"));
	}

	@Test
	public void testToList2() throws Exception {
		CommentImpl c = new CommentImpl(NodeImplTest.FIRSTID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTID, NodeImplTest.COMMENTNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS, NodeImplTest.TESTPROOF);
		c.setUserid(CommentImplTest.TESTUSER);
		c.setComment(CommentImplTest.TESTCOMMENT);
		t.setUrl(TopicImplTest.TESTURL);
		t.addValidate(c);
		List<String> l = t.toList();
		TestCase.assertEquals(24, l.size());
		Iterator<String> i = l.iterator();
		TestCase.assertEquals("---", i.next());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.TopicImpl", i.next());
		TestCase.assertEquals("previd:000001", i.next());
		TestCase.assertEquals("prevhash:0", i.next());
		TestCase.assertEquals("id:000002", i.next());
		TestCase.assertEquals("name:topicTest", i.next());
		TestCase.assertEquals("timestamp:2018-01-01", i.next());
		TestCase.assertEquals("status:editing", i.next());
		TestCase.assertEquals("proof:0", i.next());
		TestCase.assertEquals("url:testUrl", i.next());
		TestCase.assertEquals("\t---", i.next());
		TestCase.assertEquals("\tclass:gr.unirico.mcflib.model.CommentImpl", i.next());
		TestCase.assertEquals("\tprevid:first-id", i.next());
		TestCase.assertEquals("\tprevhash:0", i.next());
		TestCase.assertEquals("\tid:000002", i.next());
		TestCase.assertEquals("\tname:commentTest", i.next());
		TestCase.assertEquals("\ttimestamp:2018-01-01", i.next());
		TestCase.assertEquals("\tstatus:fixed", i.next());
		TestCase.assertEquals("\tproof:0", i.next());
		TestCase.assertEquals("\tuserid:testuser", i.next());
		TestCase.assertEquals("\tcomment:testComment", i.next());
		TestCase.assertTrue(i.next().startsWith("\thash:"));
		TestCase.assertEquals("\t---", i.next());
		TestCase.assertTrue(i.next().startsWith("hash:"));
	}

	@Test
	public void testToDigestString() throws Exception {
		t.setUrl(TopicImplTest.TESTURL);
		String hash = "/n2oZTzaLCl/OEkV3Gqjr70H4zNEOBIg1uq1J+OwmOk=";
		TestCase.assertEquals(hash, t.toDigestString());
	}

	@Test
	public void testAdd() throws Exception {
		CommentImpl c = new CommentImpl(NodeImplTest.TESTPREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTID, NodeImplTest.COMMENTNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS, NodeImplTest.TESTPROOF);
		TestCase.assertEquals(0, t.getList().size());
		t.add(c);
		TestCase.assertEquals(1, t.getList().size());
	}

	@Test
	public void testArchive() throws Exception {
		t.setUrl(TopicImplTest.TESTURL);
		t.archive(false, t, NodeImplTest.PREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTPROOF);
		TestCase.assertEquals(NodeImplTest.PREVID, t.getPrevid());
		TestCase.assertEquals(NodeImplTest.FIRSTHASH, t.getPrevhash());
	}

	@Test
	public void testArchive2() throws Exception {
		t = new TopicImpl(NodeImplTest.TESTPREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTID, NodeImplTest.TOPICNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS, NodeImplTest.TESTPROOF2);
		t.setUrl(TopicImplTest.TESTURL);
		t.archive(true, t, NodeImplTest.TESTPREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTPROOF);
	}

	@Test
	public void testSetUserid() throws Exception {
		t.setUrl(TopicImplTest.TESTURL);
		t.archive(false, t, NodeImplTest.PREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTPROOF);
		try {
			t.setUrl(TopicImplTest.TESTURL);
			TestCase.fail();
		} catch (Exception e) {
		}
	}

}
