package mcflib.api;

import mcflib.util.FileUtil;

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
	
	private McfApi(String dataDir) {
		this.dataDir = dataDir;
		FileUtil.mkdir(dataDir);
	}
	
	public String getDataDir() {
		return dataDir;
	}

}
