/*
* Rory Crispin --rorycrispin.co.uk -- rozzles.com
*
* Distributed under the Creative Commons
* Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0)
* License, full conditions can be found here:
* http://creativecommons.org/licenses/by-sa/3.0/
*
* This is free software, and you are welcome to redistribute it
* under certain conditions;
*
* Go crazy,
* Rozz xx
*
*/
package com.rozzles.torocam;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import com.rozzles.torocam.R;

public class FlatSettings extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flat_settings);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.flat_settings, menu);
		return true;
	}

}
