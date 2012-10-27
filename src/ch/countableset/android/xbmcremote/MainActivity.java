package ch.countableset.android.xbmcremote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

//	 private static String TAG = "MainActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
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
		NetworkObject object = null;
		switch(v.getId()) {
			case R.id.btn_left:
				object = new NetworkObject(getApplicationContext(), "Input.Left");
				break;
			case R.id.btn_right:
				object = new NetworkObject(getApplicationContext(), "Input.Right");
				break;
			case R.id.btn_up:
				object = new NetworkObject(getApplicationContext(), "Input.Up");
				break;
			case R.id.btn_down:
				object = new NetworkObject(getApplicationContext(), "Input.Down");
				break;
			case R.id.btn_select:
				object = new NetworkObject(getApplicationContext(), "Input.Select");
				break;
			case R.id.btn_back:
				object = new NetworkObject(getApplicationContext(), "Input.Back");
				break;
			case R.id.btn_home:
				object = new NetworkObject(getApplicationContext(), "Input.Home");
				break;
			case R.id.btn_play:
				object = new NetworkObject(getApplicationContext(), "Player.PlayPause");
				break;
			case R.id.btn_stop:
				object = new NetworkObject(getApplicationContext(), "Player.Stop");
				break;
			case R.id.btn_fastforward:
				object = new NetworkObject(getApplicationContext(), "Player.SeekForward");
				break;
			case R.id.btn_rewind:
				object = new NetworkObject(getApplicationContext(), "Player.SeekRewind");
				break;
		}
		if(object != null) {
			new NetworkTask().execute(object);
		}
	}
}
