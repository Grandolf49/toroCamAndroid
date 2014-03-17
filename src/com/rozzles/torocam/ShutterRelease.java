/*
 * Rory Crispin --rorycrispin.co.uk -- rozzles.com
 * 
 * Distributed under the Creative Commons 
 * Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0)
 * License, full conditions can be found here: 
 * http://creativecommons.org/licenses/by-sa/3.0/
 *   
 *   This is free software, and you are welcome to redistribute it
 *   under certain conditions;
 *   
 *   Go crazy,
 *   Rozz xx 
 *
 */
package com.rozzles.torocam;

import com.rozzles.torocam.R;
import com.rozzles.torocam.BlueComms.LocalBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ShutterRelease extends toroCamTrigger {


	public int prog = 0;
	public int bulbProg = 0;
	public int spin = 1;
	CheckBox ShutterBmodeCheck;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shutter_release);
		super.onCreate(savedInstanceState);
		
		
		SeekBar seekBar = (SeekBar) findViewById(R.id.TimeDelaySeek);
		final SeekBar ShutterBmodeSeek = (SeekBar) findViewById(R.id.shutterBmodeSeek);
		final TextView seekBarValue = (TextView) findViewById(R.id.delayIntView);
		final TextView ShutterBmodeText = (TextView) findViewById(R.id.shutterBmodeText);
		ShutterBmodeCheck = (CheckBox) findViewById(R.id.shutterBmodeCheck);
		final Spinner spinner = (Spinner) findViewById(R.id.TimeSpinner);

		ShutterBmodeSeek.setEnabled(false);
		spinner.setEnabled(false);
		ShutterBmodeCheck.setOnCheckedChangeListener(new OnCheckedChangeListener()	{

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if (arg1){

					ShutterBmodeSeek.setEnabled(true);
					spinner.setEnabled(true);
					bulbMode = true;
				} else {
					ShutterBmodeSeek.setEnabled(false);
					spinner.setEnabled(false);
					bulbMode = false;
				}
			}
		});
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this,

				R.array.timeArray, android.R.layout.simple_spinner_item);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				java.lang.System.out.println(String.valueOf(arg3));
				if (arg3 == 0) {
					spin = 1;
				} else if (arg3 == 1) {
					spin = 60;
				} else if (arg3 == 2) {
					spin = 3600;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});

		ShutterBmodeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				ShutterBmodeText.setText(String.valueOf(progress));
				bulbProg = progress;
			};

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});


		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				seekBarValue.setText(String.valueOf(progress + " seconds"));
				prog = progress;
			};

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
	}

	public String delayParse(){
		System.out.println(bulbMode);
		if(bulbMode){
			return Integer.toString(spin*bulbProg*1000);
		} else {
			return "0";
		}
	}

	public void sendCapture() {
		mServer.sendData("1," + prog + "," + delayParse() + "," + (bulbMode? 1 : 0) + "!");
	}
}