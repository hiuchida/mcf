package gr.unirico.mcflib.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import gr.unirico.mcflib.model.History;
import gr.unirico.mcflib.model.HistoryChain;
import gr.unirico.mcflib.model.HistoryList;
import gr.unirico.mcflib.parser.ListParser;
import gr.unirico.mcflib.util.FileUtil;

public class McfApi {
	private static McfApi singleton;
	
	public static McfApi getInstance() {
		if (singleton == null) {
			singleton = new McfApi("data");
		}
		return singleton;
	}
	
	public static void init(String dataDir) {
		if (singleton == null) {
			singleton = new McfApi(dataDir);
		}
	}
	
	private String dataDir;
	private HistoryChain archive;
	
	private McfApi(String dataDir) {
		this.dataDir = dataDir;
		FileUtil.mkdir(dataDir);
		archive = readArchive();
	}
	
	public String getDataDir() {
		return dataDir;
	}

	public History newHistory() {
		return new History();
	}

	public HistoryList newHistoryList() {
		return new HistoryList();
	}

	public void write(HistoryList hl) throws IOException {
		String id = hl.getId();
		try (PrintWriter pw = FileUtil.newWriter(dataDir + "/" + id + ".txt")) {
			FileUtil.write(pw, hl.toList());
		} finally {
		}
	}

	public HistoryList read(String id) throws IOException {
		try (BufferedReader br = FileUtil.newReader(dataDir + "/" + id + ".txt")) {
			List<String> list = FileUtil.read(br);
			Object obj = ListParser.parse(list);
			return (HistoryList)obj;
		} finally {
		}
	}
	
	public void archive(HistoryList hl) throws IOException {
		archive.add(hl);
		writeArchive(archive);
	}

	private void writeArchive(HistoryChain hc) throws IOException {
		try (PrintWriter pw = FileUtil.newWriter(dataDir + "/" + "archive.txt")) {
			FileUtil.write(pw, hc.toList());
		} finally {
		}
	}

	private HistoryChain readArchive() {
		File file = new File(dataDir + "/" + "archive.txt");
		if (file.exists()) {
			try (BufferedReader br = FileUtil.newReader(file.getPath())) {
				List<String> list = FileUtil.read(br);
				Object obj = ListParser.parse(list);
				return (HistoryChain)obj;
			} catch (IOException e) {
			} finally {
			}
		}
		return new HistoryChain();
	}

}
