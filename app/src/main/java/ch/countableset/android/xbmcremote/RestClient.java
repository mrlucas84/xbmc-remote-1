package ch.countableset.android.xbmcremote;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class RestClient {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(Context context, String url, HttpEntity entity, String contentType, AsyncHttpResponseHandler responseHandler) {
        client.post(context, url, entity, contentType, responseHandler);
    }

    public static HttpEntity createEntity(JSONObject data) throws UnsupportedEncodingException {
        return new StringEntity(data.toString());
    }

    public static JSONObject createJSONParams(String command) {
        JSONObject jsonRequest = new JSONObject();
        JSONObject params = new JSONObject();
        try {
            params.put("playerid", 1);
            jsonRequest.put("id", UUID.randomUUID().hashCode());
            jsonRequest.put("jsonrpc", "2.0");
            if(command.equals("Player.SeekForward")) {
                jsonRequest.put("method", "Player.Seek");
                params.put("value", "smallforward");
                jsonRequest.put("params", params);
            } else if(command.equals("Player.SeekRewind")) {
                jsonRequest.put("method", "Player.Seek");
                params.put("value", "smallbackward");
                jsonRequest.put("params", params);
            } else {
                jsonRequest.put("method", command);
            }
            if(command.equals("Player.PlayPause")
                    || command.equals("Player.Stop")) {
                jsonRequest.put("params", params);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonRequest;
    }

    public static String createUrl(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String ipAddress = sharedPref.getString(SettingsActivity.IP_ADDRESS, "");
        String port = sharedPref.getString(SettingsActivity.PORT_NUMBER, "");

        return "http://" + ipAddress + ":" + port + "/jsonrpc";
    }
}
