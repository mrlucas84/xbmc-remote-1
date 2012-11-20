package ch.countableset.android.xbmcremote;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

public class ShutdownDialog extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(R.string.dialog_shutdown_text).setPositiveButton(R.string.dialog_shutdown_ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Log.d("ShutdownDialog", "shutdown");
				// Command = System.Shutdown
			}
		}).setNegativeButton(R.string.dialog_shutdown_cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Log.d("ShutdownDialog", "cancel");
			}
		});
		return builder.create();
	}
}
