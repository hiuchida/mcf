package gr.unirico.mcflib.parser;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gr.unirico.mcflib.model.History;
import gr.unirico.mcflib.model.HistoryChain;
import gr.unirico.mcflib.model.HistoryList;
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
		List<String> hllist;
		List<String> hclist;
		{
			HistoryList hl = new HistoryList();
			History h = new History();
			hl.add(h);
			
			hllist = hl.toList();
			TestCase.assertEquals(15, hllist.size());
		}
		{
			HistoryList hl = (HistoryList) gr.unirico.mcflib.parser.ListParser.parse(hllist);
			TestCase.assertEquals(1, hl.getList().size());
			
			History h = new History();
			hl.add(h);
			
			hllist = hl.toList();
			TestCase.assertEquals(25, hllist.size());
		}
		{
			HistoryList hl = (HistoryList) gr.unirico.mcflib.parser.ListParser.parse(hllist);
			TestCase.assertEquals(2, hl.getList().size());
			
			HistoryChain hc = new HistoryChain();
			hc.add(hl);
			
			hclist = hc.toList();
			TestCase.assertEquals(29, hclist.size());
		}
		{
			HistoryList hl = new HistoryList();
			History h = new History();
			hl.add(h);
			
			hllist = hl.toList();
			TestCase.assertEquals(15, hllist.size());
		}
		{
			HistoryChain hc = (HistoryChain) gr.unirico.mcflib.parser.ListParser.parse(hclist);
			TestCase.assertEquals(1, hc.getList().size());
			
			HistoryList hl = (HistoryList) gr.unirico.mcflib.parser.ListParser.parse(hllist);
			TestCase.assertEquals(1, hl.getList().size());
			
			hc.add(hl);
			hclist = hc.toList();
			TestCase.assertEquals(44, hclist.size());
		}
	}

}