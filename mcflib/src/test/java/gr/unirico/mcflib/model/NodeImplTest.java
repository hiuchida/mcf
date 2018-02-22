package gr.unirico.mcflib.model;

import java.lang.invoke.MethodHandles;
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
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
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
	public static final String TESTSTATUS2 = "fixed";
	public static final int TESTPROOF = 0;
	public static final int TESTPROOF2 = 39359;
	private NodeImpl n;

	@Before
	public void setUp() throws Exception {
		n = new CommentImpl(NodeImplTest.TESTPREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTID, NodeImplTest.COMMENTNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS, NodeImplTest.TESTPROOF);
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
		TestCase.assertEquals(9, l.size());
		Iterator<String> i = l.iterator();
		TestCase.assertEquals("---", i.next());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.CommentImpl", i.next());
		TestCase.assertEquals("previd:000001", i.next());
		TestCase.assertEquals("prevhash:0", i.next());
		TestCase.assertEquals("id:000002", i.next());
		TestCase.assertEquals("name:commentTest", i.next());
		TestCase.assertEquals("timestamp:2018-01-01", i.next());
		TestCase.assertEquals("status:editing", i.next());
		TestCase.assertEquals("proof:0", i.next());
	}

	@Test
	public void testNewDigestBuilder() throws Exception {
		DigestBuilder db = n.newDigestBuilder(CommentImpl.class);
		String hash = "fDOthAfGERzhD9z3rOH185946pZMKmat0pU02aQugU8=";
		TestCase.assertEquals(hash, db.toString());
	}

	@Test
	public void testArchive() throws Exception {
		n.archive(false, n, NodeImplTest.PREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTPROOF);
		TestCase.assertEquals(NodeImplTest.PREVID, n.getPrevid());
		TestCase.assertEquals(NodeImplTest.FIRSTHASH, n.getPrevhash());
		TestCase.assertEquals(NodeImplTest.TESTSTATUS2, n.getStatus());
		TestCase.assertEquals(NodeImplTest.TESTPROOF2, n.getProof());
	}

	@Test
	public void testArchive2() throws Exception {
		n = new CommentImpl(NodeImplTest.TESTPREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTID, NodeImplTest.COMMENTNAME, NodeImplTest.TESTTIMESTAMP, NodeImplTest.TESTSTATUS2, NodeImplTest.TESTPROOF2);
		n.archive(true, n, NodeImplTest.TESTPREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTPROOF);
	}

	@Test
	public void testCheckArchived() throws Exception {
		n.checkArchived();
		n.archive(false, n, NodeImplTest.PREVID, NodeImplTest.FIRSTHASH, NodeImplTest.TESTPROOF);
		try {
			n.checkArchived();
			TestCase.fail();
		} catch (IllegalStateException e) {
		}
	}

	@Test
	public void testValidate() throws Exception {
		String hash = "nIkEaKUHSdwH3DKibATBxhl7H6qb+yRmkDiJG6/ueWk=";
		n.validate(hash);
	}

}
