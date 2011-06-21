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

public class Font {

	private BufferedImage texture_pack;

	public Font(String texture_file) {
		try {
			texture_pack = ImageIO.read(new File(texture_file));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void buildFont(float size) {
	}
}
