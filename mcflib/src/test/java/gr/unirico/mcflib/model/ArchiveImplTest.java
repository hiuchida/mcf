package gr.unirico.mcflib.model;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class ArchiveImplTest {
	private static Logger logger = LoggerFactory.getLogger(ArchiveImplTest.class);
	private ArchiveImpl a;

	@Before
	public void setUp() throws Exception {
		a = new ArchiveImpl(NodeImplTest.TESTPREVID, NodeImplTest.TESTID, "archiveTest", NodeImplTest.TESTTIMESTAMP);
		logger.debug("setUp:");
	}

	@After
	public void tearDown() throws Exception {
		logger.debug("tearDown:");
	}

	@Test
	public void testNew() throws Exception {
		ArchiveImpl archive = new ArchiveImpl("test");
		TestCase.assertEquals(0, archive.getList().size());
	}

	@Test
	public void testToList() throws Exception {
		List<String> l = a.toList();
		TestCase.assertEquals(6, l.size());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.ArchiveImpl", l.get(0));
		TestCase.assertEquals("previd:000001", l.get(1));
		TestCase.assertEquals("id:000002", l.get(2));
		TestCase.assertEquals("name:archiveTest", l.get(3));
		TestCase.assertEquals("timestamp:2018-01-01", l.get(4));
		TestCase.assertTrue(l.get(5).startsWith("hash:"));
	}

	@Test
	public void testToList2() throws Exception {
		TopicImpl t = new TopicImpl("first-id", "000002", "topicTest", "2018-01-01", "running");
		t.setUrl(TopicImplTest.TESTURL);
		a.addValidate(t);
		List<String> l = a.toList();
		TestCase.assertEquals(14, l.size());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.ArchiveImpl", l.get(0));
		TestCase.assertEquals("previd:000001", l.get(1));
		TestCase.assertEquals("id:000002", l.get(2));
		TestCase.assertEquals("name:archiveTest", l.get(3));
		TestCase.assertEquals("timestamp:2018-01-01", l.get(4));
		TestCase.assertEquals("\tclass:gr.unirico.mcflib.model.TopicImpl", l.get(5));
		TestCase.assertEquals("\tprevid:first-id", l.get(6));
		TestCase.assertEquals("\tid:000002", l.get(7));
		TestCase.assertEquals("\tname:topicTest", l.get(8));
		TestCase.assertEquals("\ttimestamp:2018-01-01", l.get(9));
		TestCase.assertEquals("\tstatus:complete", l.get(10));
		TestCase.assertEquals("\turl:testUrl", l.get(11));
		TestCase.assertTrue(l.get(12).startsWith("\thash:"));
		TestCase.assertTrue(l.get(13).startsWith("hash:"));
	}

	@Test
	public void testToList3() throws Exception {
		CommentImpl c = new CommentImpl("first-id", "000002", "commentTest", "2018-01-01");
		c.setUserid(CommentImplTest.TESTUSER);
		c.setComment(CommentImplTest.TESTCOMMENT);
		TopicImpl t = new TopicImpl("first-id", "000002", "topicTest", "2018-01-01", "running");
		t.setUrl(TopicImplTest.TESTURL);
		t.addValidate(c);
		a.addValidate(t);
		List<String> l = a.toList();
		TestCase.assertEquals(22, l.size());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.ArchiveImpl", l.get(0));
		TestCase.assertEquals("previd:000001", l.get(1));
		TestCase.assertEquals("id:000002", l.get(2));
		TestCase.assertEquals("name:archiveTest", l.get(3));
		TestCase.assertEquals("timestamp:2018-01-01", l.get(4));
		TestCase.assertEquals("\tclass:gr.unirico.mcflib.model.TopicImpl", l.get(5));
		TestCase.assertEquals("\tprevid:first-id", l.get(6));
		TestCase.assertEquals("\tid:000002", l.get(7));
		TestCase.assertEquals("\tname:topicTest", l.get(8));
		TestCase.assertEquals("\ttimestamp:2018-01-01", l.get(9));
		TestCase.assertEquals("\tstatus:complete", l.get(10));
		TestCase.assertEquals("\turl:testUrl", l.get(11));
		TestCase.assertEquals("\t\tclass:gr.unirico.mcflib.model.CommentImpl", l.get(12));
		TestCase.assertEquals("\t\tprevid:first-id", l.get(13));
		TestCase.assertEquals("\t\tid:000002", l.get(14));
		TestCase.assertEquals("\t\tname:commentTest", l.get(15));
		TestCase.assertEquals("\t\ttimestamp:2018-01-01", l.get(16));
		TestCase.assertEquals("\t\tuserid:testuser", l.get(17));
		TestCase.assertEquals("\t\tcomment:testComment", l.get(18));
		TestCase.assertTrue(l.get(19).startsWith("\t\thash:"));
		TestCase.assertTrue(l.get(20).startsWith("\thash:"));
		TestCase.assertTrue(l.get(21).startsWith("hash:"));
	}

	@Test
	public void testToDigestString() throws Exception {
		String hash = "faTSghfh1Q1iLWDjMOAlxRuWzyuHbw17QnpmQfH6GbXEmRgUxoM7On4WK9TaFYHGYySLAwO9nXKSouzfte7hrg==";
		TestCase.assertEquals(hash, a.toDigestString());
	}

	@Test
	public void testAdd() throws Exception {
		TopicImpl t = new TopicImpl(NodeImplTest.TESTPREVID, NodeImplTest.TESTID, "topicTest", NodeImplTest.TESTTIMESTAMP, "running");
		TestCase.assertEquals(0, a.getList().size());
		a.add(t);
		TestCase.assertEquals(1, a.getList().size());
	}

	@Test
	public void testArchive() throws Exception {
		a.archive(false, a, NodeImplTest.PREVID);
		TestCase.assertEquals(NodeImplTest.PREVID, a.getPrevid());
	}

	@Test
	public void testArchive2() throws Exception {
		a.archive(true, a, NodeImplTest.TESTPREVID);
	}

}
