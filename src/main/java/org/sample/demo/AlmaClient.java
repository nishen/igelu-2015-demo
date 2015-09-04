package org.sample.demo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class AlmaClient
{
	// use site closest to you
	private static final String baseurl = "https://api-eu.hosted.exlibrisgroup.com/almaws/v1";

	// enter your apikey
	private static final String apikey = "";

	private static Client client = ClientBuilder.newClient();

	public static WebTarget target(String path)
	{
		return client.target(baseurl).path(path).queryParam("apikey", apikey);
	}
}
