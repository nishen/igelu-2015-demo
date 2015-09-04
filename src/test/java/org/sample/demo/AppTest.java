package org.sample.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppTest
{
	private static final Logger log = LoggerFactory.getLogger(AppTest.class);

	private static List<String> items;

	@BeforeClass
	public static void setup()
	{
		items = new ArrayList<String>();
		items.add("fake");
		items.add("test");
	}

	@Test
	public void TestApp()
	{
		log.debug("running test: {}", "TestApp");

		Assert.assertTrue("This is a unpossible!", items.contains("fake"));
	}
}
