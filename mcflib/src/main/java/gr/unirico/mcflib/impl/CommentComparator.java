package gr.unirico.mcflib.impl;

import java.util.Comparator;

import gr.unirico.mcflib.api.Comment;

public class CommentComparator implements Comparator<Comment> {
	private boolean bAsc;
	
	public CommentComparator(boolean bAsc) {
		this.bAsc = bAsc;
	}
	
	@Override
	public int compare(Comment c1, Comment c2) {
		int cmp = c1.getTimestamp().compareTo(c2.getTimestamp());
		return bAsc ? cmp : -cmp;
	}

}
