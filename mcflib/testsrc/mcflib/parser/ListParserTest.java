package mcflib.parser;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import mcflib.model.History;
import mcflib.model.HistoryBody;
import mcflib.model.HistoryList;

public class ListParserTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParse() throws Exception {
		List<String> list;
		{
			HistoryList hl = new HistoryList();
			HistoryBody hb = new HistoryBody();
			History h = new History(hl.getLastId(), hb);
			hl.add(h);
			
			list = hl.toList();
			TestCase.assertEquals(15, list.size());
		}
		{
			HistoryList hl = (HistoryList)ListParser.parse(list);
			TestCase.assertEquals(1, hl.getList().size());
			
			HistoryBody hb = new HistoryBody();
			History h = new History(hl.getLastId(), hb);
			hl.add(h);
			hl.complete(null);
			
			list = hl.toList();
			TestCase.assertEquals(26, list.size());
		}
		{
			HistoryList hl = (HistoryList)ListParser.parse(list);
			TestCase.assertEquals(2, hl.getList().size());
		}
	}

}
