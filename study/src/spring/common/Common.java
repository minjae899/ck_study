package spring.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import spring.common.web.CommonController;

public class Common {

	protected Log log = LogFactory.getLog(CommonController.class);
	
	protected int recordCountPerPage = 10;
	protected int pageSize = 10;
	
	protected void log(String message){
		log(message, "");
	}
	
	protected void log(String message, String type){
		if(log.isDebugEnabled()){
			switch (type) {
			case "fatal":
				log.fatal(message);
				break;
				
			case "error":
				log.error(message);
				break;
				
			case "warn":
				log.warn(message);
				break;
				
			case "info":
				log.info(message);
				break;
				
			case "debug":
				log.debug(message);
				break;
				
			case "trace":
				log.trace(message);
				break;

			default:
				log.debug(message);
				break;
			}
		}else{
			System.out.println(message);
		}
	}
	
}
