package mcf.conf;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mcf.util.FileUtil;

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
		System.setProperty("mcfapp.log.home", logdir);
		Logger logger = LogManager.getLogger(ConfigServlet.class);
		logger.info("mcfapp起動");
	}

	@Override
	public void destroy() {
		Logger logger = LogManager.getLogger(ConfigServlet.class);
		logger.info("mcfappシャットダウン");
		LogManager.shutdown();
		
		super.destroy();
	}

}
