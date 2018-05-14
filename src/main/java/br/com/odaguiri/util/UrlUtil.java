package br.com.odaguiri.util;

public final class UrlUtil {

	public static String buildUrl(String scheme, String serverName, int serverPort) {
		StringBuilder url = new StringBuilder(scheme);
		url.append("://")
			.append(serverName)
			.append(":")
			.append(serverPort)
			.append("/");
		return url.toString();
	}
}
