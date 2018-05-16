package br.com.odaguiri.util;

public final class UrlUtil {

	public static String buildUrl(String scheme, String serverName, Integer serverPort) {
		StringBuilder url = new StringBuilder(scheme);
		url.append("://").append(serverName);
		if (serverPort != null) {
			url.append(":").append(serverPort);
		}
		url.append("/");
		return url.toString();
	}
}
