package gr.unirico.mcflib.parser;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gr.unirico.mcflib.model.HistoryImpl;
import gr.unirico.mcflib.model.HistoryChainImpl;
import gr.unirico.mcflib.model.HistoryListImpl;
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
			HistoryListImpl hl = new HistoryListImpl("Thread-1");
			HistoryImpl h = new HistoryImpl("Comment-1");
			hl.add(h);
			
			hllist = hl.toList();
			TestCase.assertEquals(17, hllist.size());
		}
		{
			HistoryListImpl hl = (HistoryListImpl) gr.unirico.mcflib.parser.ListParser.parse(hllist);
			TestCase.assertEquals(1, hl.getList().size());
			
			HistoryImpl h = new HistoryImpl("Comment-2");
			hl.add(h);
			
			hllist = hl.toList();
			TestCase.assertEquals(28, hllist.size());
		}
		{
			HistoryListImpl hl = (HistoryListImpl) gr.unirico.mcflib.parser.ListParser.parse(hllist);
			TestCase.assertEquals(2, hl.getList().size());
			
			HistoryChainImpl hc = new HistoryChainImpl("Archive Master");
			hc.add(hl);
			
			hclist = hc.toList();
			TestCase.assertEquals(33, hclist.size());
		}
		{
			HistoryListImpl hl = new HistoryListImpl("Thread-2");
			HistoryImpl h = new HistoryImpl("Comment-1");
			hl.add(h);
			
			hllist = hl.toList();
			TestCase.assertEquals(17, hllist.size());
		}
		{
			HistoryChainImpl hc = (HistoryChainImpl) gr.unirico.mcflib.parser.ListParser.parse(hclist);
			TestCase.assertEquals(1, hc.getList().size());
			
			HistoryListImpl hl = (HistoryListImpl) gr.unirico.mcflib.parser.ListParser.parse(hllist);
			TestCase.assertEquals(1, hl.getList().size());
			
			hc.add(hl);
			hclist = hc.toList();
			TestCase.assertEquals(50, hclist.size());
		}
	}

}
