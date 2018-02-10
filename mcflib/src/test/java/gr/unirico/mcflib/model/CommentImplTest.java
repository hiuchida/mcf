package gr.unirico.mcflib.model;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class CommentImplTest {
	private static Logger logger = LoggerFactory.getLogger(CommentImplTest.class);
	public static final String TESTUSER = "testuser";
	public static final String TESTCOMMENT = "testComment";
	private CommentImpl c;

	@Before
	public void setUp() throws Exception {
		c = new CommentImpl(NodeImplTest.TESTPREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTID, NodeImplTest.COMMENTNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS);
		logger.debug("setUp:");
	}

	@After
	public void tearDown() throws Exception {
		logger.debug("tearDown:");
	}

	@Test
	public void testNew() throws Exception {
		CommentImpl comment = new CommentImpl("commentTest");
		TestCase.assertEquals("", comment.getUserid());
		TestCase.assertEquals("", comment.getComment());
	}

	@Test
	public void testToList() throws Exception {
		c.setUserid(CommentImplTest.TESTUSER);
		c.setComment(CommentImplTest.TESTCOMMENT);
		List<String> l = c.toList();
		TestCase.assertEquals(11, l.size());
		Iterator<String> i = l.iterator();
		TestCase.assertEquals("---", i.next());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.CommentImpl", i.next());
		TestCase.assertEquals("previd:000001", i.next());
		TestCase.assertEquals("prevhash:0", i.next());
		TestCase.assertEquals("id:000002", i.next());
		TestCase.assertEquals("name:commentTest", i.next());
		TestCase.assertEquals("timestamp:2018-01-01", i.next());
		TestCase.assertEquals("status:editing", i.next());
		TestCase.assertEquals("userid:testuser", i.next());
		TestCase.assertEquals("comment:testComment", i.next());
		TestCase.assertTrue(i.next().startsWith("hash:"));
	}

	@Test
	public void testToDigestString() throws Exception {
		c.setUserid(CommentImplTest.TESTUSER);
		c.setComment(CommentImplTest.TESTCOMMENT);
		String hash = "zWUGZjrlAKZkRzdUhhT1S2NmD6Q/GHZXk77awHWD0hhLduOXktdyjqpMvQkaWcXJvgZw1HquHiwHoNPCo0sQBA==";
		TestCase.assertEquals(hash, c.toDigestString());
	}

	@Test
	public void testArchive() throws Exception {
		c.setUserid(CommentImplTest.TESTUSER);
		c.setComment(CommentImplTest.TESTCOMMENT);
		c.archive(false, c, NodeImplTest.PREVID, NodeImplTest.FIRSTHASH);
		TestCase.assertEquals(NodeImplTest.PREVID, c.getPrevid());
		TestCase.assertEquals(NodeImplTest.FIRSTHASH, c.getPrevhash());
	}

	@Test
	public void testArchive2() throws Exception {
		c.setUserid(CommentImplTest.TESTUSER);
		c.setComment(CommentImplTest.TESTCOMMENT);
		c.archive(true, c, NodeImplTest.TESTPREVID, NodeImplTest.FIRSTHASH);
	}

	@Test
	public void testSetUserid() throws Exception {
		c.setUserid(CommentImplTest.TESTUSER);
		c.archive(false, c, NodeImplTest.PREVID, NodeImplTest.FIRSTHASH);
		try {
			c.setUserid(CommentImplTest.TESTUSER);
			TestCase.fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testSetComment() throws Exception {
		c.setComment(CommentImplTest.TESTCOMMENT);
		c.archive(false, c, NodeImplTest.PREVID, NodeImplTest.FIRSTHASH);
		try {
			c.setComment(CommentImplTest.TESTCOMMENT);
			TestCase.fail();
		} catch (Exception e) {
		}
	}

}
