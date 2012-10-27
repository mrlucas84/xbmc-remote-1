package ch.countableset.android.xbmcremote;

import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;
import ch.countableset.android.library.JSONRPCClient;
import ch.countableset.android.library.JSONRPCException;
import ch.countableset.android.library.JSONRPCParams;

public class NetworkTask extends AsyncTask<NetworkObject, Context, Void> {

	// private static String TAG = "NetworkTask";

	protected Void doInBackground(NetworkObject... arg0) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(arg0[0].getContext());
		String ipAddress = sharedPref.getString(SettingsActivity.IP_ADDRESS, "");
		String port = sharedPref.getString(SettingsActivity.PORT_NUMBER, "");
		
		JSONRPCClient client = JSONRPCClient.create("http://" + ipAddress + ":" + port + "/jsonrpc", JSONRPCParams.Versions.VERSION_2);
		client.setConnectionTimeout(2000);
		client.setSoTimeout(2000);
		JSONObject jsonRequest = new JSONObject();
		JSONObject params = new JSONObject();
		try {
			params.put("playerid", 1);
			jsonRequest.put("id", UUID.randomUUID().hashCode());
			jsonRequest.put("jsonrpc", "2.0");
			if (arg0[0].getCommand().equals("Player.SeekForward")) {
				jsonRequest.put("method", "Player.Seek");
				params.put("value", "smallforward");
				jsonRequest.put("params", params);
			} else if (arg0[0].getCommand().equals("Player.SeekRewind")) {
				jsonRequest.put("method", "Player.Seek");
				params.put("value", "smallbackward");
				jsonRequest.put("params", params);
			} else {
				jsonRequest.put("method", arg0[0].getCommand());
			}
			if (arg0[0].getCommand().equals("Player.PlayPause")
					|| arg0[0].getCommand().equals("Player.Stop")) {
				jsonRequest.put("params", params);
			}
			client.call(jsonRequest);
		} catch (JSONRPCException e) {
			e.printStackTrace();
			publishProgress(arg0[0].getContext());
		} catch (JSONException e) {
			e.printStackTrace();
			publishProgress(arg0[0].getContext());
		}
		return null;
	}

	protected void onProgressUpdate(Context... context) {
		Toast.makeText(context[0], "Could Not Connect!", Toast.LENGTH_SHORT).show();
	}

}
