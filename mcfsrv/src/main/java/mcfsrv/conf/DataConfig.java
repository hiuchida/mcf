package mcfsrv.conf;

import mcfsrv.util.FileUtil;

public class DataConfig {
	private static DataConfig singleton = new DataConfig();
	
	public static DataConfig getInstance() {
		return singleton;
	}
	
	private String dataDir;
	
	private DataConfig() {
	}
	
	public void init(String dir) {
		dataDir = dir;
		FileUtil.mkdir(dataDir);
	}

	public String getPath() {
		return dataDir;
	}

}
