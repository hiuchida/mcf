package gr.unirico.mcflib.model;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;
import junit.framework.TestCase;

public class NodeImplTest {
	private static Logger logger = LoggerFactory.getLogger(NodeImplTest.class);
	public static final String FIRSTID = "first-id";
	public static final String FIRSTHASH = "0";
	public static final String PREVID = "previd";
	public static final String TESTPREVID = "000001";
	public static final String TESTID = "000002";
	public static final String COMMENTNAME = "commentTest";
	public static final String TOPICNAME = "topicTest";
	public static final String ARCHIVENAME = "archiveTest";
	public static final String TESTTIMESTAMP = "2018-01-01";
	public static final String TESTSTATUS = "editing";
	private NodeImpl n;

	@Before
	public void setUp() throws Exception {
		n = new CommentImpl(NodeImplTest.TESTPREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTID, NodeImplTest.COMMENTNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS);
		logger.debug("setUp:");
	}

	@After
	public void tearDown() throws Exception {
		logger.debug("tearDown:");
	}

	@Test
	public void testNew() throws Exception {
		NodeImpl node = new CommentImpl("nodeTest");
		TestCase.assertEquals("first-id", node.getPrevid());
		TestCase.assertEquals("nodeTest", node.getName());
		TestCase.assertNotNull(node.getTimestamp());
		TestCase.assertEquals("editing", node.getStatus());
	}

	@Test
	public void testNewListBuilder() throws Exception {
		ListBuilder lb = n.newListBuilder(n.getClass());
		List<String> l = lb.toList();
		TestCase.assertEquals(7, l.size());
		Iterator<String> i = l.iterator();
		TestCase.assertEquals("class:gr.unirico.mcflib.model.CommentImpl", i.next());
		TestCase.assertEquals("previd:000001", i.next());
		TestCase.assertEquals("prevhash:0", i.next());
		TestCase.assertEquals("id:000002", i.next());
		TestCase.assertEquals("name:commentTest", i.next());
		TestCase.assertEquals("timestamp:2018-01-01", i.next());
		TestCase.assertEquals("status:editing", i.next());
	}

	@Test
	public void testNewDigestBuilder() throws Exception {
		DigestBuilder db = n.newDigestBuilder(CommentImpl.class);
		String hash = "BV/4fSZJaiOeDWn7zlZELnHT4D5+pbM3g2X6/DxI+tHBg/OCNpyHkgQZdGApddLBe6neVTstmCWMzbDkSqf5CA==";
		TestCase.assertEquals(hash, db.toString());
	}

	@Test
	public void testSetArchived() throws Exception {
		n.checkArchived();
		n.setArchived(n);
		TestCase.assertEquals("fixed", n.getStatus());
		try {
			n.checkArchived();
			TestCase.fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testValidate() throws Exception {
		String hash = "P5hOEFVEMVRIhGrva1ThO7wKvkli9cEBOFCRsX3oL6hgGkRSpCCvT95FMCAasQW+tHcyvZCz06tyZaP1a5jx3w==";
		n.validate(hash);
	}

}
