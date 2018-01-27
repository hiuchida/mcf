package mcf;

import java.io.IOException;
import java.io.PrintWriter;

import mcf.conf.DataConfig;
import mcflib.model.History;
import mcflib.model.HistoryBody;
import mcflib.model.HistoryList;
import mcflib.util.FileUtil;

public class Debug {
	public static void test() throws IOException {
		HistoryList hl = new HistoryList();
		HistoryBody hb = new HistoryBody();
		History h = new History(null, hb);
		hl.add(h);
		
		HistoryBody hb2 = new HistoryBody();
		History h2 = new History(h.getId(), hb2);
		hl.add(h2);
		
		hl.complete(null);

		String dataDir = DataConfig.getInstance().getPath();
		try (PrintWriter pw = FileUtil.newWriter(dataDir + "/" + "historyList.txt")) {
			FileUtil.write(pw, hl.toList());
		} finally {
		}
	}

}
