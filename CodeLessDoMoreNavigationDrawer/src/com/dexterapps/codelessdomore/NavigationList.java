package com.dexterapps.codelessdomore;

import java.util.ArrayList;
import java.util.HashMap;

public class NavigationList {
	
	
	public static HashMap<String, Class<?>> navigationOptions;
	
	public static ArrayList<String> navigationOptionsArray;
	
	static 
	{
		navigationOptions = new HashMap<String, Class<?>>();
		
		navigationOptions.put("Box Office", MainActivity.class);
		navigationOptions.put("Opening Movies", OpeningMovies.class);
		
		navigationOptionsArray = new ArrayList<String>();
		
		navigationOptionsArray.add("Box Office");
		navigationOptionsArray.add("Opening Movies");

	}

	
}
