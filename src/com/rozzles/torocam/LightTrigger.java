/*
 * Rory Crispin --rorycrispin.co.uk -- rozzles.com
 * 
 * Distributed under theAttribution-NonCommercial-ShareAlike 4.0 International
 * License, full conditions can be found here: 
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *   
 *   This is free software, and you are welcome to redistribute it
 *   under certain conditions;
 *   
 *   Go crazy,
 *   Rozz xx 
 * 
 */
package com.rozzles.torocam;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import com.rozzles.torocam.core.toroCamTrigger;

public class LightTrigger extends toroCamTrigger {
	public float delay;
	public float mod;
	public int bulbBinary;
	CheckBox bulb;
	CheckBox persistent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_light_trigger);
		super.onCreate(savedInstanceState);

		bulb = (CheckBox) findViewById(R.id.bulbCheck);
		persistent = (CheckBox) findViewById(R.id.PersistentCheckBoxLightTrigger);
		SeekBar delaySeek = (SeekBar) findViewById(R.id.LightDelay);
		SeekBar modSeek = (SeekBar) findViewById(R.id.multiplierSeek);
		final TextView delayView = (TextView) findViewById(R.id.timeDelayVal);
		final TextView modView = (TextView) findViewById(R.id.multiplierVal);

		delaySeek
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

					@Override
					public void onProgressChanged(SeekBar arg0, int arg1,
							boolean arg2) {
						delay = (float) arg1 / 100;
						delayView.setText(String.valueOf(delay + " seconds"));
					}

					@Override
					public void onStartTrackingTouch(SeekBar arg0) {
					}

					@Override
					public void onStopTrackingTouch(SeekBar arg0) {
					}
				});
		modSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				mod = (float) arg1 / 100;
				modView.setText(String.valueOf(mod + "x"));
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
			}
		});
	}

	@Override
	public void sendCapture() {

		if (bulb.isChecked() == true) {
			bulbBinary = 1;
		} else {
			bulbBinary = 0;
		}
		sendToroCamMessage("3,1000," + Math.round((200 - (mod * 100))) + ","
				+ Math.round(delay * 1000) + "," + ((persistent.isChecked()) ? 1:0)+ "," + bulbBinary + "," + ((internalTriggerMode()) ? 0:1) +"!");
	}

	public void Recal(View v) {
		sendToroCamMessage("9,1!");
	}

}
