package gr.unirico.mcflib.model;

import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class ArchiveImplTest {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private ArchiveImpl a;

	@Before
	public void setUp() throws Exception {
		a = new ArchiveImpl(NodeImplTest.TESTPREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTID, NodeImplTest.ARCHIVENAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS, NodeImplTest.TESTPROOF);
		logger.debug("setUp:");
	}

	@After
	public void tearDown() throws Exception {
		logger.debug("tearDown:");
	}

	@Test
	public void testNew() throws Exception {
		ArchiveImpl archive = new ArchiveImpl("test");
		TestCase.assertEquals(0, archive.getListsize());
		TestCase.assertEquals(0, archive.getList().size());
	}

	@Test
	public void testToList() throws Exception {
		List<String> l = a.toList();
		TestCase.assertEquals(12, l.size());
		Iterator<String> i = l.iterator();
		TestCase.assertEquals("---", i.next());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.ArchiveImpl", i.next());
		TestCase.assertEquals("previd:000001", i.next());
		TestCase.assertEquals("prevhash:0", i.next());
		TestCase.assertEquals("id:000002", i.next());
		TestCase.assertEquals("name:archiveTest", i.next());
		TestCase.assertEquals("timestamp:2018-01-01", i.next());
		TestCase.assertEquals("status:editing", i.next());
		TestCase.assertEquals("proof:0", i.next());
		TestCase.assertEquals("\t---", i.next());
		TestCase.assertEquals("\t---", i.next());
		TestCase.assertTrue(i.next().startsWith("hash:"));
	}

	@Test
	public void testToList2() throws Exception {
		TopicImpl t = new TopicImpl(NodeImplTest.FIRSTID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTID, NodeImplTest.TOPICNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS2, NodeImplTest.TESTPROOF);
		t.setUrl(TopicImplTest.TESTURL);
		a.addValidate(t);
		List<String> l = a.toList();
		TestCase.assertEquals(24, l.size());
		Iterator<String> i = l.iterator();
		TestCase.assertEquals("---", i.next());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.ArchiveImpl", i.next());
		TestCase.assertEquals("previd:000001", i.next());
		TestCase.assertEquals("prevhash:0", i.next());
		TestCase.assertEquals("id:000002", i.next());
		TestCase.assertEquals("name:archiveTest", i.next());
		TestCase.assertEquals("timestamp:2018-01-01", i.next());
		TestCase.assertEquals("status:editing", i.next());
		TestCase.assertEquals("proof:0", i.next());
		TestCase.assertEquals("\t---", i.next());
		TestCase.assertEquals("\tclass:gr.unirico.mcflib.model.TopicImpl", i.next());
		TestCase.assertEquals("\tprevid:first-id", i.next());
		TestCase.assertEquals("\tprevhash:0", i.next());
		TestCase.assertEquals("\tid:000002", i.next());
		TestCase.assertEquals("\tname:topicTest", i.next());
		TestCase.assertEquals("\ttimestamp:2018-01-01", i.next());
		TestCase.assertEquals("\tstatus:fixed", i.next());
		TestCase.assertEquals("\tproof:0", i.next());
		TestCase.assertEquals("\turl:testUrl", i.next());
		TestCase.assertEquals("\t\t---", i.next());
		TestCase.assertEquals("\t\t---", i.next());
		TestCase.assertTrue(i.next().startsWith("\thash:"));
		TestCase.assertEquals("\t---", i.next());
		TestCase.assertTrue(i.next().startsWith("hash:"));
	}

	@Test
	public void testToList3() throws Exception {
		CommentImpl c = new CommentImpl(NodeImplTest.FIRSTID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTID, NodeImplTest.COMMENTNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS2, NodeImplTest.TESTPROOF);
		c.setUserid(CommentImplTest.TESTUSER);
		TopicImpl t = new TopicImpl(NodeImplTest.FIRSTID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTID, NodeImplTest.TOPICNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS2, NodeImplTest.TESTPROOF);
		t.setUrl(TopicImplTest.TESTURL);
		t.addValidate(c);
		a.addValidate(t);
		List<String> l = a.toList();
		TestCase.assertEquals(34, l.size());
		Iterator<String> i = l.iterator();
		TestCase.assertEquals("---", i.next());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.ArchiveImpl", i.next());
		TestCase.assertEquals("previd:000001", i.next());
		TestCase.assertEquals("prevhash:0", i.next());
		TestCase.assertEquals("id:000002", i.next());
		TestCase.assertEquals("name:archiveTest", i.next());
		TestCase.assertEquals("timestamp:2018-01-01", i.next());
		TestCase.assertEquals("status:editing", i.next());
		TestCase.assertEquals("proof:0", i.next());
		TestCase.assertEquals("\t---", i.next());
		TestCase.assertEquals("\tclass:gr.unirico.mcflib.model.TopicImpl", i.next());
		TestCase.assertEquals("\tprevid:first-id", i.next());
		TestCase.assertEquals("\tprevhash:0", i.next());
		TestCase.assertEquals("\tid:000002", i.next());
		TestCase.assertEquals("\tname:topicTest", i.next());
		TestCase.assertEquals("\ttimestamp:2018-01-01", i.next());
		TestCase.assertEquals("\tstatus:fixed", i.next());
		TestCase.assertEquals("\tproof:0", i.next());
		TestCase.assertEquals("\turl:testUrl", i.next());
		TestCase.assertEquals("\t\t---", i.next());
		TestCase.assertEquals("\t\tclass:gr.unirico.mcflib.model.CommentImpl", i.next());
		TestCase.assertEquals("\t\tprevid:first-id", i.next());
		TestCase.assertEquals("\t\tprevhash:0", i.next());
		TestCase.assertEquals("\t\tid:000002", i.next());
		TestCase.assertEquals("\t\tname:commentTest", i.next());
		TestCase.assertEquals("\t\ttimestamp:2018-01-01", i.next());
		TestCase.assertEquals("\t\tstatus:fixed", i.next());
		TestCase.assertEquals("\t\tproof:0", i.next());
		TestCase.assertEquals("\t\tuserid:testuser", i.next());
		TestCase.assertTrue(i.next().startsWith("\t\thash:"));
		TestCase.assertEquals("\t\t---", i.next());
		TestCase.assertTrue(i.next().startsWith("\thash:"));
		TestCase.assertEquals("\t---", i.next());
		TestCase.assertTrue(i.next().startsWith("hash:"));
	}

	@Test
	public void testToDigestString() throws Exception {
		String hash = "DAJZr2Db8YbYDvUBIWSyXUB/h7CGO6q+HFCIws9QQIw=";
		TestCase.assertEquals(hash, a.toDigestString());
	}

	@Test
	public void testAdd() throws Exception {
		TopicImpl t = new TopicImpl(NodeImplTest.TESTPREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTID, NodeImplTest.TOPICNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS, NodeImplTest.TESTPROOF);
		TestCase.assertEquals(0, a.getListsize());
		TestCase.assertEquals(0, a.getList().size());
		a.add(t);
		TestCase.assertEquals(1, a.getListsize());
		TestCase.assertEquals(1, a.getList().size());
		TestCase.assertEquals(t.getId(), a.getList().get(0).getId());
	}

	@Test
	public void testAdd2() throws Exception {
		TopicImpl t = new TopicImpl(NodeImplTest.TESTPREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTID, NodeImplTest.TOPICNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS, NodeImplTest.TESTPROOF);
		a.add(t);
		a.archive(false, a, NodeImplTest.PREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTPROOF);
		try {
			a.add(t);
			TestCase.fail();
		} catch (IllegalStateException e) {
		}
	}

}
