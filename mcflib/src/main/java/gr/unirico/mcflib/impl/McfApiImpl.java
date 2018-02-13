package gr.unirico.mcflib.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.unirico.mcflib.api.Archive;
import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.api.Topic;
import gr.unirico.mcflib.model.ArchiveImpl;
import gr.unirico.mcflib.model.CommentImpl;
import gr.unirico.mcflib.model.TopicImpl;
import gr.unirico.mcflib.parser.ListParser;
import gr.unirico.mcflib.util.FileUtil;

public class McfApiImpl implements McfApi {
	private static Logger logger = LoggerFactory.getLogger(McfApiImpl.class);
	private String dataDir;
	
	public McfApiImpl(String dataDir) {
		this.dataDir = dataDir;
		FileUtil.mkdir(dataDir);
		logger.info("McfApiImpl: {}", dataDir);
	}
	
	public void setDataDir(String dataDir) {
		this.dataDir = dataDir;
	}

	public String getDataDir() {
		return dataDir;
	}

	public Comment newComment(String userid, String comment) {
		logger.info("newComment: {}, {}", userid, comment);
		Comment c = new CommentImpl("comment name");
		c.setUserid(userid);
		c.setComment(comment);
		return c;
	}

	public Topic newTopic(String sitename, String siteurl) {
		logger.info("newTopic: {}, {}", sitename, siteurl);
		Topic t = new TopicImpl(sitename);
		t.setUrl(siteurl);
		return t;
	}

	public synchronized List<Topic> getTopicList() {
		List<Topic> list = new ArrayList<>();
		List<File> flist = FileUtil.filelist(dataDir);
		for (File f : flist) {
			String fname = f.getName();
			if (fname.equals("archive.txt")) {
				continue;
			}
			try {
				String id = fname.substring(0, fname.indexOf("."));
				Topic t = readTopic(id);
				list.add(t);
			} catch (Exception e) {
				logger.error("Error in getTopicList", e);
				FileUtil.delete(f.getPath());
			}
		}
		Collections.sort(list, new TopicComparator(false));
		logger.info("getTopicList: {}", list.size());
		return list;
	}

	public synchronized List<Topic> getArchivedTopicList() {
		Archive archive = readArchive();
		List<Topic> list = archive.getList();
		logger.info("getArchivedTopicList: {}", list.size());
		return list;
	}

	public synchronized void writeTopic(Topic t) throws IOException {
		String id = t.getId();
		logger.info("writeTopic: {}", id);
		try (PrintWriter pw = FileUtil.newWriter(dataDir + "/" + id + ".txt")) {
			FileUtil.write(pw, ((TopicImpl)t).toList());
		} finally {
		}
	}

	public synchronized Topic readTopic(String id) throws IOException {
		logger.info("readTopic: {}", id);
		try (BufferedReader br = FileUtil.newReader(dataDir + "/" + id + ".txt")) {
			List<String> list = FileUtil.read(br);
			Object obj = ListParser.parse(list);
			return (TopicImpl)obj;
		} finally {
		}
	}
	
	private void deleteTopic(Topic t) {
		String id = t.getId();
		FileUtil.delete(dataDir + "/" + id + ".txt");
	}
	
	public synchronized void archiveTopic(Topic t) throws IOException {
		logger.info("archiveTopic: {}", t.getId());
		Archive archive = readArchive();
		archive.add(t);
		writeArchive(archive);
		deleteTopic(t);
	}

	private void writeArchive(Archive a) throws IOException {
		try (PrintWriter pw = FileUtil.newWriter(dataDir + "/" + "archive.txt")) {
			FileUtil.write(pw, ((ArchiveImpl)a).toList());
		} finally {
		}
	}

	private Archive readArchive() {
		File file = new File(dataDir + "/" + "archive.txt");
		if (file.exists()) {
			try (BufferedReader br = FileUtil.newReader(file.getPath())) {
				List<String> list = FileUtil.read(br);
				Object obj = ListParser.parse(list);
				return (ArchiveImpl)obj;
			} catch (Exception e) {
				logger.error("Error in readArchive", e);
				FileUtil.delete(file.getPath());
			} finally {
			}
		}
		return new ArchiveImpl("Archive Master");
	}

}
