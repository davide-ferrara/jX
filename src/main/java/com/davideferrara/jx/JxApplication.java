package com.davideferrara.jx;

import com.davideferrara.jx.classes.XmlConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;
import java.util.logging.*;

@SpringBootApplication
@EnableAsync
public class JxApplication {
	private static final Logger logger = Logger.getLogger(JxApplication.class.getName());
	private static final XmlConfig configXml = new XmlConfig("config.xml");
	private static final String logFileName = configXml.getValueFromElement("logging", "logFile");
	private static final String appName = configXml.getValueFromElement("appInfo", "name");
	private static final String version = configXml.getValueFromElement("appInfo", "version");
	private static final String description = configXml.getValueFromElement("appInfo", "description");
	private static final String author = configXml.getValueFromElement("appInfo", "author");
	private static final String contact = configXml.getValueFromElement("appInfo", "contact");

	public static void main(String[] args) {

		// Logging config
		try {
			FileHandler fileHandler = new FileHandler(logFileName);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
			logger.setLevel(Level.ALL);
		}
		catch (SecurityException e){
			logger.log(Level.SEVERE, "Cannot create log file, Security Exception!", e);
			System.exit(1);
		}

		catch (IOException e){
			logger.log(Level.SEVERE, "Cannot create log file, IO Exception!", e);
			System.exit(2);
		}

		logger.log(Level.INFO, "Starting application " + appName + "\nVersion: "  + version + "\nMadeBy: " + author + "\nDescription: " + description + "\nContact: " + contact);
		SpringApplication.run(JxApplication.class, args);
	}

	public static Logger getLogger(){
		return logger;
	}

	public static XmlConfig getXmlConfig(){
		return configXml;
	}

}
