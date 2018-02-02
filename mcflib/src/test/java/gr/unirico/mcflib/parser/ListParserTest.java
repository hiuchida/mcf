package gr.unirico.mcflib.parser;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gr.unirico.mcflib.model.ArchiveImpl;
import gr.unirico.mcflib.model.CommentImpl;
import gr.unirico.mcflib.model.TopicImpl;
import junit.framework.TestCase;

public class ListParserTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParse() throws Exception {
		List<String> tlist;
		List<String> alist;
		{
			TopicImpl t = new TopicImpl("Thread-1");
			CommentImpl c = new CommentImpl("Comment-1");
			t.add(c);
			
			tlist = t.toList();
			TestCase.assertEquals(16, tlist.size());
		}
		{
			TopicImpl t = (TopicImpl) ListParser.parse(tlist);
			TestCase.assertEquals(1, t.getList().size());
			
			CommentImpl c = new CommentImpl("Comment-2");
			t.add(c);
			
			tlist = t.toList();
			TestCase.assertEquals(24, tlist.size());
		}
		{
			TopicImpl t = (TopicImpl) ListParser.parse(tlist);
			TestCase.assertEquals(2, t.getList().size());
			
			ArchiveImpl a = new ArchiveImpl("Archive Master");
			a.add(t);
			
			alist = a.toList();
			TestCase.assertEquals(29, alist.size());
		}
		{
			TopicImpl t = new TopicImpl("Thread-2");
			CommentImpl c = new CommentImpl("Comment-1");
			t.add(c);
			
			tlist = t.toList();
			TestCase.assertEquals(16, tlist.size());
		}
		{
			ArchiveImpl a = (ArchiveImpl) ListParser.parse(alist);
			TestCase.assertEquals(1, a.getList().size());
			
			TopicImpl t = (TopicImpl) ListParser.parse(tlist);
			TestCase.assertEquals(1, t.getList().size());
			
			a.add(t);
			alist = a.toList();
			TestCase.assertEquals(45, alist.size());
		}
	}

}
