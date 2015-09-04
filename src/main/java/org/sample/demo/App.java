package org.sample.demo;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.sample.demo.entity.user.User;
import org.sample.demo.entity.user.UserStatistic;
import org.sample.demo.entity.user.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * IGeLU 2015 Developer Day Demo
 * 
 * @author nishen.naidoo
 *
 */
public class App
{
	private static final Logger log = LoggerFactory.getLogger(App.class);

	// Specify application/xml as the content type.
	private static final String MEDIA_TYPE = MediaType.APPLICATION_XML;

	public static void main(String[] args)
	{
		// place holder for stage 2
		User selectedUser = null;

		// create a request
		WebTarget target = AlmaClient.target("users").queryParam("limit", "10").queryParam("offset", "51510");

		// execute the request - use string as result
		String result = target.request(MEDIA_TYPE).get(String.class);

		// display the result
		log.info("result:\n{}\n", result);

		// execute the same request again - this time use Users as the result
		// JAXB converts returned data into Java Objects.
		// no need to play with XML
		Users users = target.request(MEDIA_TYPE).get(Users.class);

		// go through the list of users displaying some info.
		// this call only gets limited user information
		for (User u : users.getUser())
		{
			log.info("primaryid: {}", u.getPrimaryId());
			log.info("name: {} {}", u.getFirstName(), u.getLastName());
			log.info("status: {}\n", u.getStatus().getValue());

			// if we find a user with this primary id, 'select' it.
			if ("10023449".equals(u.getPrimaryId()))
				selectedUser = u;
		}

		// short circuit if we don't have a user to proceed with
		if (selectedUser == null)
		{
			log.info("no user picked.");
			return;
		}

		// we have selected a user at this point

		// create a new request - get a specific user
		// this call gets complete user information
		target = AlmaClient.target("users").path(selectedUser.getPrimaryId());

		// execute the request - use a String for a result.
		result = target.request(MEDIA_TYPE).get(String.class);

		// display the result
		log.info("result:\n{}\n", result);

		// execute the request again - use User as the result.
		User user = target.request(MEDIA_TYPE).get(User.class);

		// display select information from the User class.
		log.info("name:        {}", user.getFullName());
		log.info("expiry date: {}", user.getExpiryDate());
		for (UserStatistic s : user.getUserStatistics().getUserStatistic())
			log.info("statistic:   {}", s.getStatisticCategory().getValue());
	}
}
