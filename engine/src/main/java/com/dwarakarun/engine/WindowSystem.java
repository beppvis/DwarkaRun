package com.dwarakarun.engine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.io.File;
import java.io.IOException;
import java.nio.*;
import java.nio.file.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class WindowSystem extends GameSystem {
	private long handle;
	private int vbo;
	private int vao;
	private int shader;

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

		FloatBuffer buf = memAllocFloat(4 * 2);
		buf.put(0.0f);
		buf.put(0.5f);

		buf.put(0.0f);
		buf.put(0.0f);

		buf.put(0.5f);
		buf.put(0.0f);

		buf.put(0.5f);
		buf.put(0.5f);

		buf.flip();

		glLoadIdentity();

//		String vertShaderSrc = "#version 330\n" +
////			"uniform mat4 mvp;\n" +
//			"layout (location=0) in vec2 pos;\n" + 
//			"out vec4 vertColor;\n" +
//			"void main(void) {\n" +
//			"gl_Position = vec4(pos.x, pos.y, 0.0, 0.0);\n" +
//			"vertColor = vec4(1.0, 1.0, 1.0, 1.0);\n" + 
//			"}";
//

		File f = new File("hello_world.txt");

		String vertShaderSrc="", fragShaderSrc="";
		try {
		Path p1 = Path.of("../../../engine/shaders/vertex.glsl");
		Path p2 = Path.of("../../../engine/shaders/fragment.glsl");
		vertShaderSrc = Files.readString(p1);
		fragShaderSrc = Files.readString(p2);
		} catch(IOException ioe) {
			System.out.println("Except");
		}finally {
		}
//		String fragShaderSrc = "#version 330\n" +
//			"in vec4 vertColor;\n" +
//			"out vec4 fragColor;\n" +
//			"void main(void) {\n" +
//			"fragColor = vertColor;\n" + 
//			"}";

		System.out.println(vertShaderSrc);
		System.out.println(fragShaderSrc);

		int vertShader = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vertShader, vertShaderSrc);
		glCompileShader(vertShader);
		int status = glGetShaderi(vertShader, GL_COMPILE_STATUS);
		if (status != GL_TRUE) {
			System.out.println("Failed compilation!");
			System.out.println(glGetShaderInfoLog(vertShader));
		} else {
			System.out.println("Compiled vert shader!");
		}

		int fragShader = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fragShader, fragShaderSrc);
		glCompileShader(fragShader);
		status = glGetShaderi(fragShader, GL_COMPILE_STATUS);
		if (status != GL_TRUE) {
			System.out.println("Failed compilation of frag!");
			System.out.println(glGetShaderInfoLog(fragShader));
		} else {
			System.out.println("Compiled frag shader");
		}

		shader = glCreateProgram();
		glAttachShader(shader, vertShader);
		glAttachShader(shader, fragShader);
		glLinkProgram(shader);
		status = glGetProgrami(shader, GL_LINK_STATUS);
		if (status != GL_TRUE) {
			System.out.println("FAILED LINKING");
			System.out.println(glGetProgramInfoLog(shader));
		} else {
			System.out.println("linked program");
		}

		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, buf, GL_STATIC_DRAW);
		memFree(buf);

		vao = glGenVertexArrays();
		glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0L);
		glEnableVertexAttribArray(0);
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
		glUseProgram(shader);
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glDrawArrays(GL_QUADS, 0, 4);
	}
}
