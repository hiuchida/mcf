package gr.unirico.mcfapp.config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.util.FileUtil;

/**
 * Servlet implementation class ConfigServlet
 */
public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConfigServlet() {
        super();
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ServletContext context = config.getServletContext();
		String logdir = context.getRealPath("/WEB-INF/logs");
		FileUtil.mkdir(logdir);
		System.setProperty("gr.unirico.mcfapp.log.home", logdir);
		Logger logger = LogManager.getLogger(ConfigServlet.class);
		logger.info("mcfapp起動");
		String dataDir = context.getRealPath("/WEB-INF/data");
		McfApi.init(dataDir);
	}

	@Override
	public void destroy() {
		Logger logger = LogManager.getLogger(ConfigServlet.class);
		logger.info("mcfappシャットダウン");
		LogManager.shutdown();
		
		super.destroy();
	}

}
