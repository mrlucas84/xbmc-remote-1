package ch.countableset.android.xbmcremote;

import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class MainActivity extends Activity {

	private static String TAG = "MainActivity";
	private Vibrator haptic = null;
	private Context mContext = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mContext = getApplicationContext();
		
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
			case R.id.menu_shutdown:
				DialogFragment dialogShutdown = new ShutdownDialog(getApplicationContext());
				dialogShutdown.show(getFragmentManager(), "shutdown");
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
			Log.d(TAG, "sending command: " + command);
			sendCommand(command, v);
		}
	}
	
	private void sendCommand(final String command, final View v) {
		String url = RestClient.createUrl(this);
		HttpEntity entity = null;
		try {
			entity = RestClient.createEntity(RestClient.createJSONParams(command));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		RestClient.post(this, url, entity, "application/json", new JsonHttpResponseHandler() {
		    @Override
		    public void onSuccess(JSONObject response) {
	    		Log.d(TAG, response.toString());
	    		if(response.optJSONObject("error") != null) {
	    			// An error has occurred, print the error message that was received
	    			if(!response.optJSONObject("error").optString("message").equals("")) {
	    				Toast.makeText(mContext, response.optJSONObject("error").optString("message"), Toast.LENGTH_SHORT).show();
	    			}
	    		} else if(response.optString("result").equals("OK")) {
	    			// The response was OK, and if the command was stop, I was to switch the selected state
	    			// of the play/pause button
	    			if(command.equals("Player.Stop")) {
	    				View play = findViewById(R.id.btn_play);
	    				play.setSelected(false);
	    			}
	    		} else if(!response.optString("result").equals("OK")) {
	    			// play/pause/rewind/fast-forward returns "results":{"speed":0} instead of OK, thus
	    			// check if play pause was pressed and change the state
	    			if(command.equals("Player.PlayPause") && v.isSelected())
	    				v.setSelected(false);
	    			else if(command.equals("Player.PlayPause") && !v.isSelected())
	    				v.setSelected(true);
	    		}
		    }
		});
	}
	
}
