package mcflib.parser;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import mcflib.model.History;
import mcflib.model.HistoryBody;
import mcflib.model.HistoryChain;
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
		List<String> hllist;
		List<String> hclist;
		{
			HistoryList hl = new HistoryList();
			HistoryBody hb = new HistoryBody();
			History h = new History(hl.getLastId(), hb);
			hl.add(h);
			
			hllist = hl.toList();
			TestCase.assertEquals(15, hllist.size());
		}
		{
			HistoryList hl = (HistoryList)ListParser.parse(hllist);
			TestCase.assertEquals(1, hl.getList().size());
			
			HistoryBody hb = new HistoryBody();
			History h = new History(hl.getLastId(), hb);
			hl.add(h);
			hl.complete(null);
			
			hllist = hl.toList();
			TestCase.assertEquals(26, hllist.size());
		}
		String lastId;
		{
			HistoryList hl = (HistoryList)ListParser.parse(hllist);
			TestCase.assertEquals(2, hl.getList().size());
			
			HistoryChain hc = new HistoryChain();
			hc.add(hl);
			lastId = hc.getLastId();
			
			hclist = hc.toList();
			TestCase.assertEquals(29, hclist.size());
		}
		{
			HistoryList hl = new HistoryList();
			HistoryBody hb = new HistoryBody();
			History h = new History(hl.getLastId(), hb);
			hl.add(h);
			hl.complete(lastId);
			
			hllist = hl.toList();
			TestCase.assertEquals(15, hllist.size());
		}
		{
			HistoryChain hc = (HistoryChain)ListParser.parse(hclist);
			TestCase.assertEquals(1, hc.getList().size());
			
			HistoryList hl = (HistoryList)ListParser.parse(hllist);
			TestCase.assertEquals(1, hl.getList().size());
			
			hc.add(hl);
			hclist = hc.toList();
			TestCase.assertEquals(44, hclist.size());
		}
	}

}
