package gr.unirico.mcflib.model;

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
		c = new CommentImpl(NodeImplTest.TESTPREVID, NodeImplTest.TESTID, "commentTest", NodeImplTest.TESTTIMESTAMP);
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
		TestCase.assertEquals(8, l.size());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.CommentImpl", l.get(0));
		TestCase.assertEquals("previd:000001", l.get(1));
		TestCase.assertEquals("id:000002", l.get(2));
		TestCase.assertEquals("name:commentTest", l.get(3));
		TestCase.assertEquals("timestamp:2018-01-01", l.get(4));
		TestCase.assertEquals("userid:testuser", l.get(5));
		TestCase.assertEquals("comment:testComment", l.get(6));
		TestCase.assertTrue(l.get(7).startsWith("hash:"));
	}

	@Test
	public void testToDigestString() throws Exception {
		c.setUserid(CommentImplTest.TESTUSER);
		c.setComment(CommentImplTest.TESTCOMMENT);
		String hash = "x9tKpk6Fegvx4kZRpYzusoZvAIpTX1s0Ksr4ow+6ggTqPM3EHzf/SeLiGEdXxP75K13DOAd4JcR2iYEFE7TFiw==";
		TestCase.assertEquals(hash, c.toDigestString());
	}

	@Test
	public void testArchive() throws Exception {
		c.setUserid(CommentImplTest.TESTUSER);
		c.setComment(CommentImplTest.TESTCOMMENT);
		c.archive(false, c, NodeImplTest.PREVID);
		TestCase.assertEquals(NodeImplTest.PREVID, c.getPrevid());
	}

	@Test
	public void testArchive2() throws Exception {
		c.setUserid(CommentImplTest.TESTUSER);
		c.setComment(CommentImplTest.TESTCOMMENT);
		c.archive(true, c, NodeImplTest.TESTPREVID);
	}

	@Test
	public void testSetUserid() throws Exception {
		c.setUserid(CommentImplTest.TESTUSER);
		c.archive(false, c, NodeImplTest.PREVID);
		try {
			c.setUserid(CommentImplTest.TESTUSER);
			TestCase.fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testSetComment() throws Exception {
		c.setComment(CommentImplTest.TESTCOMMENT);
		c.archive(false, c, NodeImplTest.PREVID);
		try {
			c.setComment(CommentImplTest.TESTCOMMENT);
			TestCase.fail();
		} catch (Exception e) {
		}
	}

}
