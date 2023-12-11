package com.dwarakarun.engine;

public class Transform {
	private float x, y;
	private int z;
	public Transform(float x, float y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public int getZ() {
		return z;
	}
	public void setX(float v) {
		x = v;
	}
	public void setY(float v) {
		y = v;
	}
	public void setZ(int v) {
		z = v;
	}
}
