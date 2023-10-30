package com.dwarakarun.engine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;
import java.nio.file.*;
import java.util.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.stb.STBImage.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


public class WindowSystem extends GameSystem {
	private long handle;
	private int tex;

	private void handleErrors() {
		int err = glGetError();
		while (err != GL_NO_ERROR) {
			System.out.format("ERROR: %d\n", err);
			err = glGetError();
		}
	}

	public WindowSystem(Engine eng) {
		super(eng);
		deps = new String[] {};
	}

	@Override
	public void init() {
		if (!glfwInit()) {
			throw new IllegalStateException("Unable to init GLFW");
		}
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

		handle = glfwCreateWindow(800, 600, "Dwaraka Run", NULL, NULL);
		if (handle == NULL) {
			System.out.println("NULL window");
		}

		glfwMakeContextCurrent(handle);
		glfwSwapInterval(1);
		glfwShowWindow(handle);

		GL.createCapabilities();

		glClearColor(0.0f, 0.0f, 1.0f, 1.0f);
		System.out.println("Init windowsystem");
		glLoadIdentity();
		handleErrors();

		tex = glGenTextures();
		glEnable(GL_TEXTURE_2D);
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, tex);

		handleErrors();

		String spriteStr = "/home/n3rdy/dev/java/DwarakaRun/sprite.png";

//		BufferedImage bufimg = null;
//		try {
//			bufimg = ImageIO.read(new File(spriteStr));
//		} catch(IOException e) {
//			System.out.println(e);
//		} finally {
//		}
//		int[] pixels = bufimg.getRGB(0, 0, bufimg.getWidth(), bufimg.getHeight(), null, 0, bufimg.getWidth());
//		ByteBuffer buffer = ByteBuffer.allocateDirect(pixels.length * 4);
//		for (int pixel : pixels) {
//			buffer.put((byte) ((pixel >> 16) & 0xFF));
//			buffer.put((byte) ((pixel >> 8) & 0xFF));
//			buffer.put((byte) (pixel & 0xFF));
//			buffer.put((byte) ((pixel >> 24) & 0xFF));
//		}
//		buffer.flip();


		ByteBuffer image;
        int width, height, channels;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            /* Prepare image buffers */
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            /* Load image */
            stbi_set_flip_vertically_on_load(true);
            image = stbi_load(spriteStr, w, h, comp, 4);
            if (image == null) {
                throw new RuntimeException("Failed to load a texture file!"
                                           + System.lineSeparator() + stbi_failure_reason());
            }

            /* Get width and height of image */
            width = w.get();
            height = h.get();
			channels = comp.get();
        }

		System.out.println("Loaded img");
		System.out.format("Width: %d\tHeight: %d\tChannels: %d\t\n", width, height, channels);

		if (image.limit() != width*height*4) {
			System.out.println("WEIRD SIZE");
		} else {
			System.out.println("size sane");
		}

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		handleErrors();

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		handleErrors();

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		handleErrors();

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		handleErrors();

		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
		handleErrors();

//		glGenerateMipmap(GL_TEXTURE_2D);
		handleErrors();
}

	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void swapBuffers() {
		glfwSwapBuffers(handle);
	}

	public void pollEvents() {
		glfwPollEvents();
	}

	@Override
	public void end() {
		glfwFreeCallbacks(handle);
		glfwDestroyWindow(handle);
		glfwTerminate();
	}

	@Override
	public void update() {
		if (glfwWindowShouldClose(handle)) {
			eng.end();
		}
		glBindTexture(GL_TEXTURE_2D, tex);
		glPushMatrix();
		glTranslatef(-0.25f, -0.25f, 0);
		glBegin(GL_QUADS);

		glTexCoord2f(0.0f, 0.0f);
		glVertex2f(0, 0);

		glTexCoord2f(0, 1f);
		glVertex2f(0, 0.5f);

		glTexCoord2f(1f, 1f);
		glVertex2f(0.5f, 0.5f);

		glTexCoord2f(1f, 0);
		glVertex2f(0.5f, 0);

		glEnd();
		glPopMatrix();
	}
}
