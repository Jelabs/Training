package com.airhockey.android;

/*
import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_LINES;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.GLES20.glViewport;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

import com.airhockey.android.util.LoggerConfig;
import com.airhockey.android.util.ShaderHelper;
import com.airhockey.android.util.TextResourceReader;
import com.example.openglapplication.R;

public class AirHockeyRenderer implements Renderer {
	private static final int POSITION_COMPONENT_COUNT = 2;
	private static final int BYTES_PER_FLOAT = 4;
	private final FloatBuffer vertexData;
	private final Context context;
	private int program;
	private static final String U_COLOR = "u_Color";
	private int uColorLocation;
	private static final String A_POSITION = "a_Position";
	private int aPositionLocation;
	
	public AirHockeyRenderer(Context context) {
        this.context = context;
        
		//float[] tableVertices = {0f, 0f,0f, 14f,9f, 14f,9f, 0f};//it is only for rectangle
        float[] tableVerticesWithTriangles = {
        		// Triangle 1
        		-0.5f, -0.5f,
        		0.5f, 0.5f,
        		-0.5f, 0.5f,
        		// Triangle 2
        		-0.5f, -0.5f,
        		0.5f, -0.5f,
        		0.5f, 0.5f,
        		// Line 1
        		-0.5f, 0f,
        		0.5f, 0f,
        		// Mallets
        		0f, -0.25f,
        		0f, 0.25f
        		};
		vertexData = ByteBuffer
				.allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
				vertexData.put(tableVerticesWithTriangles);
				//java heep memory to Native heep memory
		
	}
	
	@Override
	public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
	glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	
	String vertexShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_vertex_shader);
	String fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_fragment_shader);
	
	int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
	int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);
	
	program = ShaderHelper.linkProgram(vertexShader, fragmentShader);
	
	if (LoggerConfig.ON) {
		ShaderHelper.validateProgram(program);
		}
	
	glUseProgram(program);
	uColorLocation = glGetUniformLocation(program, U_COLOR);
	
	aPositionLocation = glGetAttribLocation(program, A_POSITION);
	
	vertexData.position(0);
	glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GL_FLOAT,false, 0, vertexData);
	glEnableVertexAttribArray(aPositionLocation);
	}

	@Override
	public void onDrawFrame(GL10 arg0) {
		// TODO Auto-generated method stub
	glClear(GL_COLOR_BUFFER_BIT);
	glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f);
	glDrawArrays(GL_TRIANGLES, 0, 6);
	
	glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
	glDrawArrays(GL_LINES, 6, 2);
	
	// Draw the first mallet blue.
	glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
	glDrawArrays(GL_POINTS, 8, 1);
	// Draw the second mallet red.
	glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
	glDrawArrays(GL_POINTS, 9, 1);
	
		
	}

	@Override
	public void onSurfaceChanged(GL10 arg0, int width, int height) {
		// TODO Auto-generated method stub
		glViewport(0, 0, width, height);
	}

	
}
*/
/***
 * Excerpted from "OpenGL ES for Android",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/kbogla for more book information.
***/

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_LINES;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.GLES20.*;
import static android.opengl.Matrix.orthoM;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

import com.airhockey.android.util.LoggerConfig;
import com.airhockey.android.util.MatrixHelper;
import com.airhockey.android.util.ShaderHelper;
import com.airhockey.android.util.TextResourceReader;
import com.example.openglapplication.R;
import static android.opengl.Matrix.*;
public class AirHockeyRenderer implements Renderer {

    private static final String A_POSITION = "a_Position";
    private static final String A_COLOR = "a_Color";    
    private static final int POSITION_COMPONENT_COUNT = 4;
    private static final int COLOR_COMPONENT_COUNT = 3;    
    private static final int BYTES_PER_FLOAT = 4;
    private static final int STRIDE = 
        (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * BYTES_PER_FLOAT;
    
    private final FloatBuffer vertexData;
    private final Context context;

    private int program;
    private int aPositionLocation;
    private int aColorLocation;
    private static final String U_MATRIX = "u_Matrix";
    private final float[] projectionMatrix = new float[16];
    private int uMatrixLocation;
    
    
    private final float[] modelMatrix = new float[16];
    public AirHockeyRenderer() {
        // This constructor shouldn't be called -- only kept for showing
        // evolution of the code in the chapter.
        context = null;
        vertexData = null;
    }

    public AirHockeyRenderer(Context context) {
        this.context = context;
        
        /*
		float[] tableVertices = { 
			0f,  0f, 
			0f, 14f, 
			9f, 14f, 
			9f,  0f 
		};
         */
        /*
		float[] tableVerticesWithTriangles = {
			// Triangle 1
			0f,  0f, 
			9f, 14f,
			0f, 14f,

			// Triangle 2
			0f,  0f, 
			9f,  0f,							
			9f, 14f			
			// Next block for formatting purposes
			9f, 14f,
			, // Comma here for formatting purposes			

			// Line 1
			0f,  7f, 
			9f,  7f,

			// Mallets
			4.5f,  2f, 
			4.5f, 12f
		};
         */

      /*  float[] tableVerticesWithTriangles = {   
            // Order of coordinates: X, Y, R, G, B
            
            // Triangle Fan
               0f,    0f,   1f,   1f,   1f,         
            -0.5f, -0.5f, 0.7f, 0.7f, 0.7f,            
             0.5f, -0.5f, 0.7f, 0.7f, 0.7f,
             0.5f,  0.5f, 0.7f, 0.7f, 0.7f,
            -0.5f,  0.5f, 0.7f, 0.7f, 0.7f,
            -0.5f, -0.5f, 0.7f, 0.7f, 0.7f,

            // Line 1
            -0.5f, 0f, 1f, 0f, 0f,
             0.5f, 0f, 1f, 0f, 0f,

            // Mallets
            0f, -0.25f, 0f, 0f, 1f,
            0f,  0.25f, 1f, 0f, 0f
        };

        float[] tableVerticesWithTriangles = { //2d
        		// Order of coordinates: X, Y, R, G, B
        		// Triangle Fan
        		0f, 0f, 1f, 1f, 1f,
        		-0.5f, -0.8f, 0.7f, 0.7f, 0.7f,
        		0.5f, -0.8f, 0.7f, 0.7f, 0.7f,
        		0.5f, 0.8f, 0.7f, 0.7f, 0.7f,
        		-0.5f, 0.8f, 0.7f, 0.7f, 0.7f,
        		-0.5f, -0.8f, 0.7f, 0.7f, 0.7f,
        		// Line 1
        		-0.5f, 0f, 1f, 0f, 0f,
        		0.5f, 0f, 1f, 0f, 0f,
        		// Mallets
        		0f, -0.4f, 0f, 0f, 1f,
        		0f, 0.4f, 1f, 0f, 0f
        		};
      */  
        float[] tableVerticesWithTriangles = { //3d for adding z,w component 
        		// Order of coordinates: X, Y, Z, W, R, G, B
        		// Triangle Fan
        		0f, 0f, 0f, 1.5f, 1f, 1f, 1f,
        		-0.5f, -0.8f, 0f, 1f, 0.7f, 0.7f, 0.7f,
        		0.5f, -0.8f, 0f, 1f, 0.7f, 0.7f, 0.7f,
        		0.5f, 0.8f, 0f, 2f, 0.7f, 0.7f, 0.7f,
        		-0.5f, 0.8f, 0f, 2f, 0.7f, 0.7f, 0.7f,
        		-0.5f, -0.8f, 0f, 1f, 0.7f, 0.7f, 0.7f,
        		// Line 1
        		-0.5f, 0f, 0f, 1.5f, 1f, 0f, 0f,
        		0.5f, 0f, 0f, 1.5f, 1f, 0f, 0f,
        		// Mallets
        		0f, -0.4f, 0f, 1.25f, 0f, 0f, 1f,
        		0f, 0.4f, 0f, 1.75f, 1f, 0f, 0f
        		};
        vertexData = ByteBuffer
            .allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)
            .order(ByteOrder.nativeOrder()).asFloatBuffer();

        vertexData.put(tableVerticesWithTriangles);
    
    }


  
    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
        /*
		// Set the background clear color to red. The first component is red,
		// the second is green, the third is blue, and the last component is
		// alpha, which we don't use in this lesson.
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
         */

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        String vertexShaderSource = TextResourceReader
            .readTextFileFromResource(context, R.raw.simple_vertex_shader);
        String fragmentShaderSource = TextResourceReader
            .readTextFileFromResource(context, R.raw.simple_fragment_shader);

        int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
        int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);

        program = ShaderHelper.linkProgram(vertexShader, fragmentShader);

        if (LoggerConfig.ON) {
            ShaderHelper.validateProgram(program);
        }

        glUseProgram(program);

      //  uColorLocation = glGetUniformLocation(program, U_COLOR); old color
        
        aPositionLocation = glGetAttribLocation(program, A_POSITION);
        aColorLocation = glGetAttribLocation(program, A_COLOR); //new color

        // Bind our data, specified by the variable vertexData, to the vertex
        // attribute at location A_POSITION_LOCATION.
        vertexData.position(0);
        glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GL_FLOAT, 
            false, STRIDE, vertexData);
        glEnableVertexAttribArray(aPositionLocation);
       
        
        vertexData.position(POSITION_COMPONENT_COUNT);
        glVertexAttribPointer(aColorLocation, COLOR_COMPONENT_COUNT, GL_FLOAT,
        false, STRIDE, vertexData);
        glEnableVertexAttribArray(aColorLocation);
        uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
    }

    /**
     * onSurfaceChanged is called whenever the surface has changed. This is
     * called at least once when the surface is initialized. Keep in mind that
     * Android normally restarts an Activity on rotation, and in that case, the
     * renderer will be destroyed and a new one created.
     * 
     * @param width
     *            The new width, in pixels.
     * @param height
     *            The new height, in pixels.
     */
    @Override
    public void onSurfaceChanged(GL10 glUnused, int width, int height) {
        // Set the OpenGL viewport to fill the entire surface.
        glViewport(0, 0, width, height);	
      /*  final float aspectRatio = width > height ?
        		(float) width / (float) height :
        		(float) height / (float) width;
        		if (width > height) {
        		// Landscape
        		orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, -1f, 1f);
        		} else {
        		// Portrait or square
        		orthoM(projectionMatrix, 0, -1f, 1f, -aspectRatio, aspectRatio, -1f, 1f);
        		}
        		*/
       MatrixHelper.perspectiveM(projectionMatrix, 45, (float) width/ (float) height, 1f, 10f);
       setIdentityM(modelMatrix, 0);
      // translateM(modelMatrix, 0, 0f, 0f, -2f);
       translateM(modelMatrix, 0, 0f, 0f, -2.5f);
       rotateM(modelMatrix, 0, -60f, 1f, 0f, 0f);
       final float[] temp = new float[16];
       multiplyMM(temp, 0, projectionMatrix, 0, modelMatrix, 0);
       System.arraycopy(temp, 0, projectionMatrix, 0, temp.length);
    }

    /**
     * OnDrawFrame is called whenever a new frame needs to be drawn. Normally,
     * this is done at the refresh rate of the screen.
     */
    @Override
    public void onDrawFrame(GL10 glUnused) {
        // Clear the rendering surface.
        glClear(GL_COLOR_BUFFER_BIT);
       
        // Assign the matrix
        glUniformMatrix4fv(uMatrixLocation, 1, false, projectionMatrix, 0);
        // Draw the table.
       // glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f);		
        glDrawArrays(GL_TRIANGLE_FAN, 0, 6);
        
        // Draw the center dividing line.
       // glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);		
        glDrawArrays(GL_LINES, 6, 2); 
        
        // Draw the first mallet blue.        
      //  glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);		
        glDrawArrays(GL_POINTS, 8, 1);

        // Draw the second mallet red.
       // glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);		
        glDrawArrays(GL_POINTS, 9, 1);
    }
}
