/***
 * Excerpted from "OpenGL ES for Android",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/kbogla for more book information.
***/
package com.example.openglproject;

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
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

import com.example.openglproject.util.LoggerConfig;
import com.example.openglproject.util.ShaderHelper;
import com.example.openglproject.util.TextResourceReader;

public class AirHockeyRenderer implements Renderer {
    private static final String U_COLOR = "u_Color";
    private static final String A_COLOR = "a_Color";
    private static final String A_POSITION = "a_Position";    
    private static final int POSITION_COMPONENT_COUNT = 2;
    
    private static final int BYTES_PER_FLOAT = 4;
    private final FloatBuffer vertexData;
    private final Context context;
    private static final int COLOR_COMPONENT_COUNT = 3;
    private static final int STRIDE =
    (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * BYTES_PER_FLOAT;
    private int program;
    private int uColorLocation;
    private int aPositionLocation;
    private int aColorLocation;

    public AirHockeyRenderer() {
        // This constructor shouldn't be called -- only kept for showing
        // evolution of the code in the chapter.
        context = null;
        vertexData = null;
        System.out.println("compileshadre000001");
    }

    public AirHockeyRenderer(Context context) {
        this.context = context;
        System.out.println("compileshadre1");
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
         
        float[] tableVerticesWithTriangles = {
            // Triangle 1
            -0.5f, -0.5f, 
             0.5f,  0.5f,
            -0.5f,  0.5f,

            // Triangle 2
            -0.5f, -0.5f, 
             0.5f, -0.5f, 
             0.5f,  0.5f,

            // Line 1
            -0.5f, 0f, 
             0.5f, 0f,

            // Mallets
            0f, -0.25f, 
            0f,  0.25f
        };
        */
        float[] tableVerticesWithTriangles = {   
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


        vertexData = ByteBuffer
            .allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer();

        vertexData.put(tableVerticesWithTriangles);
    }


    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
        /*
		// Set the background clea
		 * r color to red. The first component is red,
		// the second is green, the third is blue, and the last component is
		// alpha, which we don't us e in this lesson.
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
         */
    	System.out.println("compileshadre0000000011111");
        glUnused.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        String vertexShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_vertex_shader);
        String fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_fragment_shader);

        int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
        int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);

        program = ShaderHelper.linkProgram(vertexShader, fragmentShader);

        if (LoggerConfig.ON) {
            ShaderHelper.validateProgram(program);
        }
       

        glUseProgram(program);
        //uColorLocation = glGetUniformLocation(program, U_COLOR);
        aColorLocation = glGetAttribLocation(program, A_COLOR);
        aPositionLocation = glGetAttribLocation(program, A_POSITION);
        
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
        glUnused.glViewport(0, 0, width, height);		
    }

    /**
     * OnDrawFrame is called whenever a new frame needs to be drawn. Normally,
     * this is done at the refresh rate of the screen.
     */
    
    @Override
    public void onDrawFrame(GL10 glUnused) {
        // Clear the rendering surface.
      
    	 glUnused.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        // Draw the table.
       // glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f);		
      // glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);
        glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 6);

        
        // Draw the center dividing line.
        //glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);		
        glDrawArrays(GLES20.GL_LINES, 6, 2); 
        
        // Draw the first mallet blue.        
        //glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);		
        glDrawArrays(GLES20.GL_POINTS, 8, 1);

        // Draw the second mallet red.
        //glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);		
        glDrawArrays(GLES20.GL_POINTS, 9, 1);
       
    }
}
