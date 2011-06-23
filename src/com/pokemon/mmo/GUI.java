package com.pokemon.mmo;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import javax.imageio.*;
import java.io.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Hashtable;

public class GUI {

	float x = 400, y = 300;		// position of quad
	float rotation = 0;		// angle of quad rotation
	long lastFrame;			// time at last frame
	int fps;			// frames per second
	long lastFPS;			// last fps time
	boolean finished = false;

	BufferedImage letter_text_pack = null;
	int num_letters = 0;
	BufferedImage[] letters = null;
	int textID;
	int letterno = 0;
	int count = 0;
	Font font;
	Sprite s;

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL();		// init OpenGL
		try {
			font = new Font("assets/text.png", "assets/text.txt", 6, 13);
			font.buildFont(2);	// build the textures for text
			s = new Sprite("assets/pokemon_sprites/front/643.png");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		getDelta();		// call once before loop to initialise lastFrame
		lastFPS = getTime();	// call before loop to initialise fps timer
		
		while (!Display.isCloseRequested() && !finished) {
			int delta = getDelta();
			
			update(delta);
			renderGL();

			Display.update();
			Display.sync(60); // cap fps to 60fps

		}
		
		Display.destroy();
	}

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 600, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	


	public void update(int delta) {
		// rotate quad
		rotation += 0.15f * delta;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) x -= 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) x += 0.35f * delta;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) y -= 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) y += 0.35f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) finished = true;
		
		// keep quad on the screen
		if (x < 0) x = 0;
		if (x > 800) x = 800;
		if (y < 0) y = 0;
		if (y > 600) y = 600;
		
		updateFPS(); // update FPS Counter
	}
	
	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
	 
		return delta;
	}
	
	// Get the accurate system time
	public long getTime() { return (Sys.getTime() * 1000) / Sys.getTimerResolution(); }
	
	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	


	public void renderGL() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	// Clear The Screen And The Depth Buffer

		GL11.glColor3f(0.5f,0.5f,1.0f);						// set the color of the quad (R,G,B,A)
		GL11.glBegin(GL11.GL_QUADS);						// draw quad
			GL11.glVertex2f(100,100);
			GL11.glVertex2f(100+200,100);
			GL11.glVertex2f(100+200,100+200);
			GL11.glVertex2f(100,100+200);
		GL11.glEnd();

		font.draw_str("HELLO", 150, 150);
		
		s.draw(100, 400, 2f);
		s.draw(300, 400, 2.23f);

		GL11.glColor3f(0.5f, 0.5f, 1.0f);					// R,G,B,A Set The Color To Blue One Time Only
		GL11.glPushMatrix();							// draw quad
			GL11.glTranslatef(x, y, 0);
			GL11.glRotatef(rotation, 0f, 0f, 1f);
			GL11.glTranslatef(-x, -y, 0);
			
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f(x - 50, y - 50);
				GL11.glVertex2f(x + 50, y - 50);
				GL11.glVertex2f(x + 50, y + 50);
				GL11.glVertex2f(x - 50, y + 50);
			GL11.glEnd();
		GL11.glPopMatrix();
	}
}

