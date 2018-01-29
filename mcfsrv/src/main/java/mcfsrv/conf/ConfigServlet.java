package mcfsrv.conf;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mcfsrv.util.FileUtil;

/**
 * Servlet implementation class ConfigServlet
 */
public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfigServlet() {
        super();
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ServletContext context = config.getServletContext();
		String logDir = context.getRealPath("/WEB-INF/logs");
		FileUtil.mkdir(logDir);
		System.setProperty("mcfsrv.log.home", logDir);
		Logger logger = LogManager.getLogger(ConfigServlet.class);
		logger.info("mcfsrv起動");
		String dataDir = context.getRealPath("/WEB-INF/data");
		DataConfig.getInstance().init(dataDir);
	}

	@Override
	public void destroy() {
		Logger logger = LogManager.getLogger(ConfigServlet.class);
		logger.info("mcfsrvシャットダウン");
		LogManager.shutdown();
		
		super.destroy();
	}

}
