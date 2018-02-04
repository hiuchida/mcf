package gr.unirico.mcflib.model;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class ArchiveImplTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNew() throws Exception {
		ArchiveImpl a = new ArchiveImpl("test");
		TestCase.assertEquals(0, a.getList().size());
	}

	@Test
	public void testToList() throws Exception {
		ArchiveImpl a = new ArchiveImpl("first-id", "id", "test", "timestamp");
		List<String> l = a.toList();
		TestCase.assertEquals(6, l.size());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.ArchiveImpl", l.get(0));
		TestCase.assertEquals("previd:first-id", l.get(1));
		TestCase.assertEquals("id:id", l.get(2));
		TestCase.assertEquals("name:test", l.get(3));
		TestCase.assertEquals("timestamp:timestamp", l.get(4));
		TestCase.assertEquals("hash:2qKlartvQcTryaAvBihe+fvKJCqkAPO4sfJ2fTnDkVvV51DpOtofwdBYRy4IatLspgVgwA6SRghCOahirKkh0A==", l.get(5));
	}

	@Test
	public void testToList2() throws Exception {
		TopicImpl t = new TopicImpl("first-id", "id", "test", "timestamp", "running");
		t.setUrl("url");
		ArchiveImpl a = new ArchiveImpl("first-id", "id", "test", "timestamp");
		a.addValidate(t);
		List<String> l = a.toList();
		TestCase.assertEquals(14, l.size());
		TestCase.assertEquals("class:gr.unirico.mcflib.model.ArchiveImpl", l.get(0));
		TestCase.assertEquals("previd:first-id", l.get(1));
		TestCase.assertEquals("id:id", l.get(2));
		TestCase.assertEquals("name:test", l.get(3));
		TestCase.assertEquals("timestamp:timestamp", l.get(4));
		TestCase.assertEquals("\tclass:gr.unirico.mcflib.model.TopicImpl", l.get(5));
		TestCase.assertEquals("\tprevid:first-id", l.get(6));
		TestCase.assertEquals("\tid:id", l.get(7));
		TestCase.assertEquals("\tname:test", l.get(8));
		TestCase.assertEquals("\ttimestamp:timestamp", l.get(9));
		TestCase.assertEquals("\tstatus:complete", l.get(10));
		TestCase.assertEquals("\turl:url", l.get(11));
		TestCase.assertEquals("\thash:myY7jC+o+4Hu3LLtmJWnkAMPhHMp4+d9z9Hg7FQMc0adtLpJXVQ47VE1VILeFdbcBHTwfgFED7DBmaYhaHuIrw==", l.get(12));
		TestCase.assertEquals("hash:xxSGC9/S8ilkNAu06QAshBOEu//PM15sCS00BFhdIwe83oMhJnw8zj2/p/mbVRaFG6E13xhTsIxJcT54DnXxgg==", l.get(13));
	}

	@Test
	public void testToDigestString() throws Exception {
		ArchiveImpl a = new ArchiveImpl("first-id", "id", "test", "timestamp");
		TestCase.assertEquals("2qKlartvQcTryaAvBihe+fvKJCqkAPO4sfJ2fTnDkVvV51DpOtofwdBYRy4IatLspgVgwA6SRghCOahirKkh0A==", a.toDigestString());
	}

	@Test
	public void testAdd() throws Exception {
		TopicImpl t = new TopicImpl("first-id", "id", "test", "timestamp", "running");
		ArchiveImpl a = new ArchiveImpl("first-id", "id", "test", "timestamp");
		TestCase.assertEquals(0, a.getList().size());
		a.add(t);
		TestCase.assertEquals(1, a.getList().size());
	}

	@Test
	public void testArchive() throws Exception {
		ArchiveImpl a = new ArchiveImpl("first-id", "id", "test", "timestamp");
		a.archive(false, a, "previd");
		TestCase.assertEquals("previd", a.getPrevid());
	}

	@Test
	public void testArchive2() throws Exception {
		ArchiveImpl a = new ArchiveImpl("previd", "id", "test", "timestamp");
		a.archive(true, a, "previd");
		TestCase.assertEquals("previd", a.getPrevid());
	}

}
