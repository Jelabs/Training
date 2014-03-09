package com.example.openglproject;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

public class OpenGlProjectRender implements Renderer {
	private static final int POSITION_COMPONENT_COUNT = 2;
	OpenGlProjectRender(){
		float[] tableVertices = {
				0f, 0f,
				0f, 14f,
				9f, 14f,
				9f, 0f
				};
		float[] tableVerticesWithTriangles = {
				// Triangle 1
				0f, 0f,
				9f, 14f,
				0f, 14f,
				// Triangle 2
				0f, 0f,
				9f, 0f,
				9f, 14f
				};
		float[] line = {
				0f, 7f,
				9f, 7f,
				// Mallets
				4.5f, 2f,
				4.5f, 12f
		};
	}
	@Override
	public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
	glUnused.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void onDrawFrame(GL10 arg0) {
		// TODO Auto-generated method stub
	arg0.glClear(GL_COLOR_BUFFER_BIT);
		
	}

	@Override
	public void onSurfaceChanged(GL10 arg0, int width, int height) {
		// TODO Auto-generated method stub
		arg0.glViewport(0, 0, width, height);
	}

	
}
