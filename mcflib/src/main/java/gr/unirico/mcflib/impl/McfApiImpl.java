package gr.unirico.mcflib.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.api.Topic;
import gr.unirico.mcflib.model.ArchiveImpl;
import gr.unirico.mcflib.model.CommentImpl;
import gr.unirico.mcflib.model.TopicImpl;
import gr.unirico.mcflib.parser.ListParser;
import gr.unirico.mcflib.util.FileUtil;

public class McfApiImpl implements McfApi {
	private String dataDir;
	
	public McfApiImpl(String dataDir) {
		this.dataDir = dataDir;
		FileUtil.mkdir(dataDir);
	}
	
	public String getDataDir() {
		return dataDir;
	}

	public Comment newComment(String name) {
		return new CommentImpl(name);
	}

	public Topic newTopic(String name) {
		return new TopicImpl(name);
	}

	public void writeTopic(Topic hl) throws IOException {
		String id = hl.getId();
		try (PrintWriter pw = FileUtil.newWriter(dataDir + "/" + id + ".txt")) {
			FileUtil.write(pw, ((TopicImpl)hl).toList());
		} finally {
		}
	}

	public Topic readTopic(String id) throws IOException {
		try (BufferedReader br = FileUtil.newReader(dataDir + "/" + id + ".txt")) {
			List<String> list = FileUtil.read(br);
			Object obj = ListParser.parse(list);
			return (TopicImpl)obj;
		} finally {
		}
	}
	
	private void deleteTopic(Topic hl) {
		String id = hl.getId();
		FileUtil.delete(dataDir + "/" + id + ".txt");
	}
	
	public void archiveTopic(Topic hl) throws IOException {
		ArchiveImpl archive = readArchive();
		archive.add((TopicImpl)hl);
		writeArchive(archive);
		deleteTopic(hl);
	}

	private void writeArchive(ArchiveImpl hc) throws IOException {
		try (PrintWriter pw = FileUtil.newWriter(dataDir + "/" + "archive.txt")) {
			FileUtil.write(pw, hc.toList());
		} finally {
		}
	}

	private ArchiveImpl readArchive() {
		File file = new File(dataDir + "/" + "archive.txt");
		if (file.exists()) {
			try (BufferedReader br = FileUtil.newReader(file.getPath())) {
				List<String> list = FileUtil.read(br);
				Object obj = ListParser.parse(list);
				return (ArchiveImpl)obj;
			} catch (IOException e) {
			} finally {
			}
		}
		return new ArchiveImpl("Archive Master");
	}

}
