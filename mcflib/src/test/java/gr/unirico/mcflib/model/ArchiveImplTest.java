package gr.unirico.mcflib.model;

import java.util.Iterator;
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
		a = new ArchiveImpl(NodeImplTest.TESTPREVID, NodeImplTest.TESTID, NodeImplTest.ARCHIVENAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS);
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
		TestCase.assertEquals(7, l.size());
		Iterator<String> i = l.iterator();
		TestCase.assertEquals("class:gr.unirico.mcflib.model.ArchiveImpl", i.next());
		TestCase.assertEquals("previd:000001", i.next());
		TestCase.assertEquals("id:000002", i.next());
		TestCase.assertEquals("name:archiveTest", i.next());
		TestCase.assertEquals("timestamp:2018-01-01", i.next());
		TestCase.assertEquals("status:editing", i.next());
		TestCase.assertTrue(i.next().startsWith("hash:"));
	}

	@Test
	public void testToList2() throws Exception {
		TopicImpl t = new TopicImpl(NodeImplTest.FIRSTID, NodeImplTest.TESTID, NodeImplTest.TOPICNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS);
		t.setUrl(TopicImplTest.TESTURL);
		a.addValidate(t);
		List<String> l = a.toList();
		TestCase.assertEquals(15, l.size());
		Iterator<String> i = l.iterator();
		TestCase.assertEquals("class:gr.unirico.mcflib.model.ArchiveImpl", i.next());
		TestCase.assertEquals("previd:000001", i.next());
		TestCase.assertEquals("id:000002", i.next());
		TestCase.assertEquals("name:archiveTest", i.next());
		TestCase.assertEquals("timestamp:2018-01-01", i.next());
		TestCase.assertEquals("status:editing", i.next());
		TestCase.assertEquals("\tclass:gr.unirico.mcflib.model.TopicImpl", i.next());
		TestCase.assertEquals("\tprevid:first-id", i.next());
		TestCase.assertEquals("\tid:000002", i.next());
		TestCase.assertEquals("\tname:topicTest", i.next());
		TestCase.assertEquals("\ttimestamp:2018-01-01", i.next());
		TestCase.assertEquals("\tstatus:fixed", i.next());
		TestCase.assertEquals("\turl:testUrl", i.next());
		TestCase.assertTrue(i.next().startsWith("\thash:"));
		TestCase.assertTrue(i.next().startsWith("hash:"));
	}

	@Test
	public void testToList3() throws Exception {
		CommentImpl c = new CommentImpl(NodeImplTest.FIRSTID, NodeImplTest.TESTID, NodeImplTest.COMMENTNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS);
		c.setUserid(CommentImplTest.TESTUSER);
		c.setComment(CommentImplTest.TESTCOMMENT);
		TopicImpl t = new TopicImpl(NodeImplTest.FIRSTID, NodeImplTest.TESTID, NodeImplTest.TOPICNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS);
		t.setUrl(TopicImplTest.TESTURL);
		t.addValidate(c);
		a.addValidate(t);
		List<String> l = a.toList();
		TestCase.assertEquals(24, l.size());
		Iterator<String> i = l.iterator();
		TestCase.assertEquals("class:gr.unirico.mcflib.model.ArchiveImpl", i.next());
		TestCase.assertEquals("previd:000001", i.next());
		TestCase.assertEquals("id:000002", i.next());
		TestCase.assertEquals("name:archiveTest", i.next());
		TestCase.assertEquals("timestamp:2018-01-01", i.next());
		TestCase.assertEquals("status:editing", i.next());
		TestCase.assertEquals("\tclass:gr.unirico.mcflib.model.TopicImpl", i.next());
		TestCase.assertEquals("\tprevid:first-id", i.next());
		TestCase.assertEquals("\tid:000002", i.next());
		TestCase.assertEquals("\tname:topicTest", i.next());
		TestCase.assertEquals("\ttimestamp:2018-01-01", i.next());
		TestCase.assertEquals("\tstatus:fixed", i.next());
		TestCase.assertEquals("\turl:testUrl", i.next());
		TestCase.assertEquals("\t\tclass:gr.unirico.mcflib.model.CommentImpl", i.next());
		TestCase.assertEquals("\t\tprevid:first-id", i.next());
		TestCase.assertEquals("\t\tid:000002", i.next());
		TestCase.assertEquals("\t\tname:commentTest", i.next());
		TestCase.assertEquals("\t\ttimestamp:2018-01-01", i.next());
		TestCase.assertEquals("\t\tstatus:fixed", i.next());
		TestCase.assertEquals("\t\tuserid:testuser", i.next());
		TestCase.assertEquals("\t\tcomment:testComment", i.next());
		TestCase.assertTrue(i.next().startsWith("\t\thash:"));
		TestCase.assertTrue(i.next().startsWith("\thash:"));
		TestCase.assertTrue(i.next().startsWith("hash:"));
	}

	@Test
	public void testToDigestString() throws Exception {
		String hash = "ro+JjpFvIpAVNZTwhG/9s3/Zi0mRq1oYiOcEo2cDnTQSj2DT1pgX3akWTbqrjy1Tr7/MWPrOO2agmsLBRpO1Jg==";
		TestCase.assertEquals(hash, a.toDigestString());
	}

	@Test
	public void testAdd() throws Exception {
		TopicImpl t = new TopicImpl(NodeImplTest.TESTPREVID, NodeImplTest.TESTID, NodeImplTest.TOPICNAME, NodeImplTest.TESTTIMESTAMP, "running");
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
