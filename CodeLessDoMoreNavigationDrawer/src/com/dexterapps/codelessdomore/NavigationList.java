/*
 * Copyright (C) 2014 Pranay Airan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
