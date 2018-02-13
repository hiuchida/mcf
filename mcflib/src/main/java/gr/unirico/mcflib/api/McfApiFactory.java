package gr.unirico.mcflib.api;

import gr.unirico.mcflib.impl.McfApiImpl;

public class McfApiFactory {
	private static McfApiImpl singleton = new McfApiImpl("data");
	
	public static McfApi getInstance() {
		return singleton;
	}
	
	public static void init(String dataDir) {
		singleton.setDataDir(dataDir);
	}
	
}
