package com.pokemon.mmo;

import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class Sprite extends Texture2D {

	public Sprite(String fname) throws IOException { super(fname); }

	public void draw(int x, int y, float size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		bind();
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0f, 0f);	GL11.glVertex2i(0, 0);
			GL11.glTexCoord2f(0f, 1f);	GL11.glVertex2i(0, (int) (size*getHeight()));
			GL11.glTexCoord2f(1f, 1f);	GL11.glVertex2i((int) (size*getWidth()), (int) (size*getHeight()));
			GL11.glTexCoord2f(1f, 0f);	GL11.glVertex2i((int) (size*getWidth()), 0);
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}
}
