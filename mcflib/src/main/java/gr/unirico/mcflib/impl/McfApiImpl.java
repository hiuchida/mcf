package gr.unirico.mcflib.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import gr.unirico.mcflib.api.History;
import gr.unirico.mcflib.api.HistoryList;
import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.model.HistoryChainImpl;
import gr.unirico.mcflib.model.HistoryImpl;
import gr.unirico.mcflib.model.HistoryListImpl;
import gr.unirico.mcflib.parser.ListParser;
import gr.unirico.mcflib.util.FileUtil;

public class McfApiImpl implements McfApi {
	private String dataDir;
	private HistoryChainImpl archive;
	
	public McfApiImpl(String dataDir) {
		this.dataDir = dataDir;
		FileUtil.mkdir(dataDir);
		archive = readArchive();
	}
	
	public String getDataDir() {
		return dataDir;
	}

	public History newHistory(String name) {
		return new HistoryImpl(name);
	}

	public HistoryList newHistoryList(String name) {
		return new HistoryListImpl(name);
	}

	public void write(HistoryList hl) throws IOException {
		String id = hl.getId();
		try (PrintWriter pw = FileUtil.newWriter(dataDir + "/" + id + ".txt")) {
			FileUtil.write(pw, ((HistoryListImpl)hl).toList());
		} finally {
		}
	}

	public HistoryList read(String id) throws IOException {
		try (BufferedReader br = FileUtil.newReader(dataDir + "/" + id + ".txt")) {
			List<String> list = FileUtil.read(br);
			Object obj = ListParser.parse(list);
			return (HistoryListImpl)obj;
		} finally {
		}
	}
	
	public void delete(HistoryList hl) {
		String id = hl.getId();
		FileUtil.delete(dataDir + "/" + id + ".txt");
	}
	
	public void archive(HistoryList hl) throws IOException {
		archive.add((HistoryListImpl)hl);
		writeArchive(archive);
		delete(hl);
	}

	private void writeArchive(HistoryChainImpl hc) throws IOException {
		try (PrintWriter pw = FileUtil.newWriter(dataDir + "/" + "archive.txt")) {
			FileUtil.write(pw, hc.toList());
		} finally {
		}
	}

	private HistoryChainImpl readArchive() {
		File file = new File(dataDir + "/" + "archive.txt");
		if (file.exists()) {
			try (BufferedReader br = FileUtil.newReader(file.getPath())) {
				List<String> list = FileUtil.read(br);
				Object obj = ListParser.parse(list);
				return (HistoryChainImpl)obj;
			} catch (IOException e) {
			} finally {
			}
		}
		return new HistoryChainImpl("Archive Master");
	}

}
