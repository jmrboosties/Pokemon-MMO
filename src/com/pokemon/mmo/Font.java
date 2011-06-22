package com.pokemon.mmo;

import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.lang.Character;

public class Font extends Texture2D {
	
	private HashMap<Character, Integer> hm = new HashMap<Character, Integer>();;

	private int num_letters = 0;
	private int letter_width = 0;
	private int letter_height = 0;
	private float tex_letter_width = 0;
	private float tex_letter_height = 0;
	
	private int list_base = -1;

	public Font(String fname, int lwidth, int lheight) throws IOException {
		super(fname);
		letter_width = lwidth;
		letter_height = lheight;
		
		num_letters = (getWidth() * getHeight()) / (letter_width * letter_height);
		
		tex_letter_width = (float) letter_width / getWidth();
		tex_letter_height = (float) letter_height / getHeight();
		
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		for(int i = 0; i < letters.length(); i++) {
			hm.put(letters.charAt(i), i);
		}
	}

	public void buildFont(int size) {
		if (list_base >= 0) { GL11.glDeleteLists(list_base, num_letters); }
		list_base = GL11.glGenLists(num_letters);

		for(int i = 0; i < num_letters; i++) {
			float u = i * tex_letter_width;
			float v = 0;
			GL11.glNewList(list_base + i, GL11.GL_COMPILE);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			bind();
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(u, v);					GL11.glVertex2i(0, 0);
				GL11.glTexCoord2f(u, v + tex_letter_height);			GL11.glVertex2i(0, size*letter_height);
				GL11.glTexCoord2f(u + tex_letter_width, v + tex_letter_height);	GL11.glVertex2i(size*letter_width, size*letter_height);
				GL11.glTexCoord2f(u + tex_letter_width, v);			GL11.glVertex2i(size*letter_width, 0);
			GL11.glEnd();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glTranslatef(size*letter_width, 0, 0);
			GL11.glEndList();
		}
	}

	public void draw_char(char letter, int x, int y) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		GL11.glCallList(list_base + hm.get(letter));
		GL11.glPopMatrix();
	}

	public void draw_str(String str, int x, int y) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		for(int i = 0; i < str.length(); i++) {
			GL11.glCallList(list_base + hm.get(str.charAt(i)));
		}
		GL11.glPopMatrix();
	}
}
