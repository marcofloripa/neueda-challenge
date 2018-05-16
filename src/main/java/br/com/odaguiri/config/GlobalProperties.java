package br.com.odaguiri.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("global")
public class GlobalProperties {

	private String scheme;
	private String serverName;
	private Integer serverPort;
	
	public String getScheme() {
		return scheme;
	}
	
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}
	
}
