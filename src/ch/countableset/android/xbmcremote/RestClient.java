package ch.countableset.android.xbmcremote;

import org.apache.http.HttpEntity;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class RestClient {
	
	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void post(Context context, String url, HttpEntity entity, String contentType, AsyncHttpResponseHandler responseHandler) {
		client.post(context, url, entity, contentType, responseHandler);
	}
}
