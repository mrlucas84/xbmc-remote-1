package ch.countableset.android.xbmcremote;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class ShutdownDialog extends DialogFragment {
    private Context context;
    private static String TAG = "ShutDownDialog";

    public ShutdownDialog(Context context) {
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_shutdown_text).setPositiveButton(R.string.dialog_shutdown_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.d("ShutdownDialog", "shutdown");
                sendCommand("System.Shutdown");
            }
        }).setNegativeButton(R.string.dialog_shutdown_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.d("ShutdownDialog", "shutdown canceled");
            }
        });
        return builder.create();
    }

    private void sendCommand(String command) {
        String url = RestClient.createUrl(context);
        HttpEntity entity = null;
        try {
            entity = RestClient.createEntity(RestClient.createJSONParams(command));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RestClient.post(context, url, entity, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject response) {
                try {
                    Log.d(TAG, response.toString());
                    if(!response.getString("result").equals("OK")) {
                        Toast.makeText(context, "Counld not connect!", Toast.LENGTH_SHORT).show();
                    }
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
