package ch.countableset.android.xbmcremote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

//	 private static String TAG = "MainActivity";
	private Vibrator haptic = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		haptic = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.menu_settings:
				Intent intent = new Intent(this, SettingsActivity.class);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	public void onClick(View v) {
		String command = null;
		switch(v.getId()) {
			case R.id.btn_left:
				command = "Input.Left";
				break;
			case R.id.btn_right:
				command = "Input.Right";
				break;
			case R.id.btn_up:
				command = "Input.Up";
				break;
			case R.id.btn_down:
				command = "Input.Down";
				break;
			case R.id.btn_select:
				command = "Input.Select";
				break;
			case R.id.btn_back:
				command = "Input.Back";
				break;
			case R.id.btn_home:
				command =  "Input.Home";
				break;
			case R.id.btn_play:
				command = "Player.PlayPause";
				break;
			case R.id.btn_stop:
				command = "Player.Stop";
				break;
			case R.id.btn_fastforward:
				command = "Player.SeekForward";
				break;
			case R.id.btn_rewind:
				command = "Player.SeekRewind";
				break;
		}
		if(command != null) {
			haptic.vibrate(50);
			NetworkObject object = new NetworkObject(getApplicationContext(), command);
			new NetworkTask().execute(object);
		}
	}
}
