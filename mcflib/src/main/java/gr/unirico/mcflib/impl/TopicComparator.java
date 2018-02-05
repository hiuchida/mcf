package gr.unirico.mcflib.impl;

import java.util.Comparator;

import gr.unirico.mcflib.api.Topic;

public class TopicComparator implements Comparator<Topic> {
	private boolean bAsc;
	
	public TopicComparator(boolean bAsc) {
		this.bAsc = bAsc;
	}
	
	@Override
	public int compare(Topic t1, Topic t2) {
		int cmp = t1.getTimestamp().compareTo(t2.getTimestamp());
		return bAsc ? cmp : -cmp;
	}

}
