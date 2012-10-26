package ch.countableset.android.xbmcremote;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
//	private static String TAG = "MainActivity";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final ImageButton btn_left = (ImageButton) findViewById(R.id.btn_left);
        final ImageButton btn_right = (ImageButton) findViewById(R.id.btn_right);
        final ImageButton btn_up = (ImageButton) findViewById(R.id.btn_up);
        final ImageButton btn_down = (ImageButton) findViewById(R.id.btn_down);
        final ImageButton btn_select = (ImageButton) findViewById(R.id.btn_select);
        
        final ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        final ImageButton btn_home = (ImageButton) findViewById(R.id.btn_home);
        
        final ImageButton btn_rewind = (ImageButton) findViewById(R.id.btn_rewind);
        final ImageButton btn_play = (ImageButton) findViewById(R.id.btn_play);
        final ImageButton btn_stop = (ImageButton) findViewById(R.id.btn_stop);
        final ImageButton btn_fastforward = (ImageButton) findViewById(R.id.btn_fastforward);
        
        btn_left.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				NetworkObject object = new NetworkObject(getApplicationContext(), "Input.Left");
				new NetworkTask().execute(object);
			}
		});
        btn_right.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				NetworkObject object = new NetworkObject(getApplicationContext(), "Input.Right");
				new NetworkTask().execute(object);
			}
		});
        btn_up.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				NetworkObject object = new NetworkObject(getApplicationContext(), "Input.Up");
				new NetworkTask().execute(object);
			}
		});
        btn_down.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				NetworkObject object = new NetworkObject(getApplicationContext(), "Input.Down");
				new NetworkTask().execute(object);
			}
		});
        btn_select.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				NetworkObject object = new NetworkObject(getApplicationContext(), "Input.Select");
				new NetworkTask().execute(object);
			}
		});
        btn_back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				NetworkObject object = new NetworkObject(getApplicationContext(), "Input.Back");
				new NetworkTask().execute(object);
			}
		});
        btn_home.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				NetworkObject object = new NetworkObject(getApplicationContext(), "Input.Home");
				new NetworkTask().execute(object);
			}
		});
        btn_rewind.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				NetworkObject object = new NetworkObject(getApplicationContext(), "Player.SeekRewind");
				new NetworkTask().execute(object);
			}
		});
        btn_play.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				NetworkObject object = new NetworkObject(getApplicationContext(), "Player.PlayPause");
				new NetworkTask().execute(object);
			}
		});
        btn_stop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				NetworkObject object = new NetworkObject(getApplicationContext(), "Player.Stop");
				new NetworkTask().execute(object);
			}
		});
        btn_fastforward.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				NetworkObject object = new NetworkObject(getApplicationContext(), "Player.SeekForward");
				new NetworkTask().execute(object);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
