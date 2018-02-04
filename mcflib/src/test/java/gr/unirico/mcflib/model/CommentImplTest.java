package gr.unirico.mcflib.model;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class CommentImplTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNew() throws Exception {
		CommentImpl c = new CommentImpl("test");
		TestCase.assertEquals("", c.getUserid());
		TestCase.assertEquals("", c.getComment());
	}

	@Test
	public void testToList() throws Exception {
		CommentImpl c = new CommentImpl("first-id", "id", "test", "timestamp");
		c.setUserid("userid");
		c.setComment("comment");
		List<String> l = c.toList();
		TestCase.assertEquals(8, l.size());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.CommentImpl", l.get(0));
		TestCase.assertEquals("previd:first-id", l.get(1));
		TestCase.assertEquals("id:id", l.get(2));
		TestCase.assertEquals("name:test", l.get(3));
		TestCase.assertEquals("timestamp:timestamp", l.get(4));
		TestCase.assertEquals("userid:userid", l.get(5));
		TestCase.assertEquals("comment:comment", l.get(6));
		TestCase.assertEquals("hash:3+kzFyiFo9hScB4I2tSx60U8IAh6TIJkQNnij9FVyOte4X7Sa26dsK1oI/JDzZiKCp3klenmiESFpSP3fsLxlQ==", l.get(7));
	}

	@Test
	public void testToDigestString() throws Exception {
		CommentImpl c = new CommentImpl("first-id", "id", "test", "timestamp");
		c.setUserid("userid");
		c.setComment("comment");
		TestCase.assertEquals("3+kzFyiFo9hScB4I2tSx60U8IAh6TIJkQNnij9FVyOte4X7Sa26dsK1oI/JDzZiKCp3klenmiESFpSP3fsLxlQ==", c.toDigestString());
	}

	@Test
	public void testArchive() throws Exception {
		CommentImpl c = new CommentImpl("first-id", "id", "test", "timestamp");
		c.setUserid("userid");
		c.setComment("comment");
		c.archive(false, c, "previd");
		TestCase.assertEquals("previd", c.getPrevid());
	}

	@Test
	public void testArchive2() throws Exception {
		CommentImpl c = new CommentImpl("previd", "id", "test", "timestamp");
		c.setUserid("userid");
		c.setComment("comment");
		c.archive(true, c, "previd");
	}

	@Test
	public void testSetUserid() throws Exception {
		CommentImpl c = new CommentImpl("first-id", "id", "test", "timestamp");
		c.setUserid("userid");
		c.archive(false, c, "previd");
		try {
			c.setUserid("userid");
			TestCase.fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testSetComment() throws Exception {
		CommentImpl c = new CommentImpl("first-id", "id", "test", "timestamp");
		c.setComment("comment");
		c.archive(false, c, "previd");
		try {
			c.setComment("comment");
			TestCase.fail();
		} catch (Exception e) {
		}
	}

}
