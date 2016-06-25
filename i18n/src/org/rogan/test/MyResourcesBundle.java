package org.rogan.test;

import java.util.Locale;
import java.util.ResourceBundle;

public class MyResourcesBundle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Locale locale = Locale.US;
		ResourceBundle myResource = ResourceBundle.getBundle("MyResources",locale);
		String key = myResource.getString("key");
		String value = myResource.getString("value");
		System.out.println("key:"+key);
		System.out.println("value:"+value);
				
	}

}
