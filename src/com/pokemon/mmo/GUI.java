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

	public static void main(String[] argv) {
		GUI example = new GUI();
		example.start();
	}

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		font = new Font("assets/text.png");
		initGL();		// init OpenGL
		font.buildFont(10f);	// build the textures for text
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

	public void buildFont() {
		int src_pixel_format = 0;

		IntBuffer tmpbuf = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
		GL11.glGenTextures(tmpbuf);
		textID = tmpbuf.get(0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textID);


		
		num_letters = letter_text_pack.getWidth() / 6;
		letters = new BufferedImage[num_letters];
		for(int i = 0; i < num_letters; i++) {
			letters[i] = letter_text_pack.getSubimage(6*i, 0, 6, 11);
		}
		// 0xFFFFFFFF == -1
		// 0xFF000000 == -16777216
		// 0xFF808080 == -8355712

		if (letter_text_pack.getColorModel().hasAlpha()) {
			src_pixel_format = GL11.GL_RGBA;
		} else {
			System.out.println("test");
			src_pixel_format = GL11.GL_RGB;
		}

		ByteBuffer imageBuffer = null; 
//		WritableRaster raster;
//		BufferedImage texImage;
//		ColorModel glAlphaColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] {8,8,8,8}, true, false, ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);
//		ColorModel glColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] {8,8,8,0}, false, false, ComponentColorModel.OPAQUE, DataBuffer.TYPE_BYTE);
//		
//		// create a raster that can be used by OpenGL as a source for a texture
//		if (letter_text_pack.getColorModel().hasAlpha()) {
//			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, letter_text_pack.getWidth(), letter_text_pack.getHeight(),4,null);
//			texImage = new BufferedImage(glAlphaColorModel,raster,false,new Hashtable());
//		} else {
//			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, letter_text_pack.getWidth(), letter_text_pack.getHeight(),3,null);
//			texImage = new BufferedImage(glColorModel,raster,false,new Hashtable());
//		}
//			
//		// copy the source image into the produced image
//		Graphics g = texImage.getGraphics();
//		g.setColor(new Color(0f,0f,0f,0f));
//		g.fillRect(0,0, letter_text_pack.getWidth(), letter_text_pack.getHeight());
//		g.drawImage(letter_text_pack,0,0,null);
//		
//		// build a byte buffer from the temporary image 
//		// that be used by OpenGL to produce a texture.
//		byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer()).getData(); 

		ByteBuffer bb = ByteBuffer.allocateDirect(4 * letter_text_pack.getWidth() * letter_text_pack.getHeight());
//		for(int j = 0; j < letters[0].getHeight(); j++) {
//			for(int i = 0; i < letters[0].getWidth(); i++) {
//				System.out.println(letters[0].getRGB(i, j));
//			}
//		}
		byte data[] = (byte[]) letter_text_pack.getRaster().getDataElements(0, 0, letter_text_pack.getWidth(), letter_text_pack.getHeight(), null);
		bb.clear();
		bb.put(data);
		bb.rewind();

		imageBuffer = ByteBuffer.allocateDirect(data.length); 
		imageBuffer.order(ByteOrder.nativeOrder()); 
		imageBuffer.put(data, 0, data.length); 
		imageBuffer.flip();

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, letter_text_pack.getWidth(), letter_text_pack.getHeight(), 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, imageBuffer);







//		

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
	
	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
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

//		GL11.glRasterPos2i(40, 40);
//		GL11.glBitmap(letters[0].getWidth(), letters[0].getHeight(), 1.0f, 1.0f, 6.0f, 0.0f, bb);

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		//GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_DECAL);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textID);
		GL11.glTranslatef(150, 150, 0);
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		int diswidth = 6;
		int disheight = 11;
		int multi = 10;

		int imwidth = 216;
		int imheight = 11;
		int letterwidth = 6;
		int letterheight = 11;
		
		float texwidth = (float) letterwidth / imwidth;
		float texheight = (float) letterheight / imheight;
		if (count++ == 10) { 
			letterno++;
			count = 0;
		}
		float xpos = texwidth * letterno;
		float ypos = 0;
		GL11.glBegin(GL11.GL_QUADS);
//			GL11.glTexCoord2f(0.0f, 0.0f);
//			GL11.glVertex2i(100, 100);
//			GL11.glTexCoord2f((float) letters[0].getWidth(), 0.0f);
//			GL11.glVertex2i(150, 100);
//			GL11.glTexCoord2f((float) letters[0].getWidth(), (float) letters[0].getHeight());
//			GL11.glVertex2i(150, 150);
//			GL11.glTexCoord2f(0.0f, (float) letters[0].getHeight());
//			GL11.glVertex2i(100, 150);
			GL11.glTexCoord2f(xpos, ypos);
			GL11.glVertex2i(0, 0);
			GL11.glTexCoord2f(xpos, ypos + texheight);
			GL11.glVertex2i(0, multi*disheight);
			GL11.glTexCoord2f(xpos + texwidth, ypos + texheight);
			GL11.glVertex2i(multi*diswidth, multi*disheight);
			GL11.glTexCoord2f(xpos + texwidth, ypos);
			GL11.glVertex2i(multi*diswidth, 0);
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();


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

