package com.pokemon.mmo;

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
import java.io.IOException;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

public class Texture2D {
	// The colour model including alpha for the GL image
	private static ColorModel glAlphaColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] {8,8,8,8}, true, false, ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);	
	// The colour model for the GL image
	private static ColorModel glColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] {8,8,8,0}, false, false, ComponentColorModel.OPAQUE, DataBuffer.TYPE_BYTE);

	private int textureID = -1;
	private int width = 0;
	private int height = 0;

	public Texture2D() {}

	public Texture2D(String fname) throws IOException {
		textureID = createTextureID();				// Register a new texture with OpenGL
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);	// Bind the new texture
		
		BufferedImage image = ImageIO.read(new File(fname));	// Read in new image
		int srcPixelFormat = 0;					// Check format
		if (image.getColorModel().hasAlpha()) {
			srcPixelFormat = GL11.GL_RGBA;
		} else {
			srcPixelFormat = GL11.GL_RGB;
		}
		
		width = image.getWidth();
		height = image.getHeight();

		ByteBuffer textureBuffer = null; 
		WritableRaster raster = null;
		BufferedImage texImage = null;
		
		// create a raster that can be used by OpenGL as a source for a texture
		if (image.getColorModel().hasAlpha()) {
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, image.getWidth(), image.getHeight(), 4, null);
			texImage = new BufferedImage(glAlphaColorModel, raster, false, new Hashtable());
		} else {
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, image.getWidth(), image.getHeight(), 3, null);
			texImage = new BufferedImage(glColorModel, raster, false, new Hashtable());
		}
			
		// copy the source image into the produced image
		Graphics g = texImage.getGraphics();
		g.setColor(new Color(0f, 0f, 0f, 0f));
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.drawImage(image,0,0,null);
		
		// build a byte buffer from the temporary image 
		// that be used by OpenGL to produce a texture.
		byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer()).getData(); 

		textureBuffer = ByteBuffer.allocateDirect(data.length); 
		textureBuffer.order(ByteOrder.nativeOrder()); 
		textureBuffer.put(data, 0, data.length); 
		textureBuffer.flip();
		
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST); 									// set filters
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, image.getWidth(), image.getHeight(), 0, srcPixelFormat, GL11.GL_UNSIGNED_BYTE, textureBuffer);	// produce a texture
	}

	// Create a new texture ID 
	private int createTextureID() { 
		IntBuffer tmp = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
		GL11.glGenTextures(tmp); 
		return tmp.get(0);
	} 

	public void bind() { GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID); }	// Bind the specified GL context to a texture
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
}
