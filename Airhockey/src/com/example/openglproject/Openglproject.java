package com.example.openglproject;

import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.view.Menu;
import android.widget.Toast;

public class Openglproject extends Activity {
	private GLSurfaceView glSurfaceView;
	private boolean rendererSet = false;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("opengl project");
		glSurfaceView = new GLSurfaceView(this);
		//setContentView(R.layout.activity_openglproject);
		 final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		    final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
		    final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
				System.out.println("opengl project111111111111"+supportsEs2);
				if (supportsEs2) {
					// Request an OpenGL ES 2.0 compatible context.
					glSurfaceView.setEGLContextClientVersion(2);
					// Assign our renderer.
					glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
					glSurfaceView.setRenderer(new AirHockeyRenderer(this));
					rendererSet = true;
					} else {
						
						Toast.makeText(this, "This device does not support OpenGL ES 2.0.",
						Toast.LENGTH_LONG).show();
						return;
						}
					setContentView(glSurfaceView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.openglproject, menu);
		return true;
	}
	@Override
	protected void onPause() {
	super.onPause();
	if (rendererSet) {
	glSurfaceView.onPause();
	}
	}
	@Override
	protected void onResume() {
	super.onResume();
	if (rendererSet) {
	glSurfaceView.onResume();
	}
	}
}
