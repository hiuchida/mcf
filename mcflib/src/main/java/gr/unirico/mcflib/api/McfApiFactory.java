package gr.unirico.mcflib.api;

import gr.unirico.mcflib.impl.McfApiImpl;

public class McfApiFactory {
	private static McfApiImpl singleton;
	
	public synchronized static McfApi getInstance() {
		if (singleton == null) {
			singleton = new McfApiImpl("data");
		}
		return singleton;
	}
	
	public synchronized static void init(String dataDir) {
		if (singleton == null) {
			singleton = new McfApiImpl(dataDir);
		}
	}
	
}
