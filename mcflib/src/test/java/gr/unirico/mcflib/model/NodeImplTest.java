package gr.unirico.mcflib.model;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;
import junit.framework.TestCase;

public class NodeImplTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNew() throws Exception {
		NodeImpl n = new CommentImpl("test");
		TestCase.assertEquals("first-id", n.getPrevid());
		TestCase.assertEquals("test", n.getName());
		TestCase.assertEquals("", n.getTimestamp());
	}

	@Test
	public void testNewListBuilder() throws Exception {
		NodeImpl n = new CommentImpl("first-id", "id", "test", "timestamp");
		ListBuilder lb = n.newListBuilder(n.getClass());
		List<String> l = lb.toList();
		TestCase.assertEquals(5, l.size());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.CommentImpl", l.get(0));
		TestCase.assertEquals("previd:first-id", l.get(1));
		TestCase.assertEquals("id:id", l.get(2));
		TestCase.assertEquals("name:test", l.get(3));
		TestCase.assertEquals("timestamp:timestamp", l.get(4));
	}

	@Test
	public void testNewDigestBuilder() throws Exception {
		NodeImpl n = new CommentImpl("first-id", "id", "test", "timestamp");
		DigestBuilder db = n.newDigestBuilder(CommentImpl.class);
		TestCase.assertEquals("w6WzrQ3xQai/Y+jPkdXgkbaHO1xnydOPpxdgIyLCN2ld4AFOOsleKcFBO6geiP1gcZT9/+JYxnLPyQql/DKGqg==", db.toString());
	}

	@Test
	public void testSetArchived() throws Exception {
		NodeImpl n = new CommentImpl("first-id", "id", "test", "timestamp");
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
		NodeImpl n = new CommentImpl("first-id", "id", "test", "timestamp");
		String hash = "wz69jS9lqwG3IFuy8eZRGQRafftjMly8ONnfE0EWums+xlJRScJSJCjjIH/LQG1XSy7QyA8B59p6a4hNVpv4tQ==";
		n.validate(hash);
	}

}
