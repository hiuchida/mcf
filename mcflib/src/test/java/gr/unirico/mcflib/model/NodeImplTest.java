package gr.unirico.mcflib.model;

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
	public static final String PREVID = "previd";
	public static final String TESTPREVID = "000001";
	public static final String TESTID = "000002";
	public static final String TESTTIMESTAMP = "2018-01-01";
	private NodeImpl n;

	@Before
	public void setUp() throws Exception {
		n = new CommentImpl(NodeImplTest.TESTPREVID, NodeImplTest.TESTID, "nodeTest", NodeImplTest.TESTTIMESTAMP);
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
		TestCase.assertEquals("", node.getTimestamp());
	}

	@Test
	public void testNewListBuilder() throws Exception {
		ListBuilder lb = n.newListBuilder(n.getClass());
		List<String> l = lb.toList();
		TestCase.assertEquals(5, l.size());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.CommentImpl", l.get(0));
		TestCase.assertEquals("previd:000001", l.get(1));
		TestCase.assertEquals("id:000002", l.get(2));
		TestCase.assertEquals("name:nodeTest", l.get(3));
		TestCase.assertEquals("timestamp:2018-01-01", l.get(4));
	}

	@Test
	public void testNewDigestBuilder() throws Exception {
		DigestBuilder db = n.newDigestBuilder(CommentImpl.class);
		String hash = "VLzsWMqVGs0yWLtn4rKvifztiStX4qmEOT1yq+5ITXYCT3QmJerDT5Tv0eZA/EY9D2NUgUiROL3UpwnHER1kxA==";
		TestCase.assertEquals(hash, db.toString());
	}

	@Test
	public void testSetArchived() throws Exception {
		n.checkArchived();
		n.setArchived(n);
		try {
			n.checkArchived();
			TestCase.fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testValidate() throws Exception {
		String hash = "z02OkxodCD/BMGQ68K+dQyG9xDncCVMxt8gcR/WcNrXeGBcPlmNX+vg5un0VqTqCSQG6Vhh9+xmmVOx0lQ32Yg==";
		n.validate(hash);
	}

}
