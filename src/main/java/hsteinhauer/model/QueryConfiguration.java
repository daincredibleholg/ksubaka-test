package hsteinhauer.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class QueryConfiguration {

	private final Logger logger = LogManager.getLogger(QueryConfiguration.class);
	private static QueryConfiguration instance;
	private final Properties config = new Properties();

	private QueryConfiguration() {
		initiateConfiguration();
	}

	QueryConfiguration (Properties properties) {
		this.config.putAll(properties);
	}

	private void initiateConfiguration() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties");

		try {
			config.load(in);
		} catch (IOException e) {
			logger.info("No application.properties file found");
		}
	}

	public static QueryConfiguration getInstance() {
		if (instance == null) {
			instance = new QueryConfiguration();
		};

		return instance;
	}

	public String getProperty(String key) {
		return config.getProperty(key);
	}

}
