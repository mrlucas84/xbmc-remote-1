package ch.countableset.android.xbmcremote;

import android.content.Context;

public class NetworkObject {

	private Context context;
	private String command;

	public NetworkObject(Context context, String command) {
		this.context = context;
		this.command = command;
	}

	public Context getContext() {
		return this.context;
	}

	public String getCommand() {
		return this.command;
	}
}
