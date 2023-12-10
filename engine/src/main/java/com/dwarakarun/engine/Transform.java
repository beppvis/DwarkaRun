package com.dwarakarun.engine;

public class Transform {
	private float x, y, z;
	public Transform(float x, float y, float z) {
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
	public float getZ() {
		return z;
	}
	public void setX(float v) {
		x = v;
	}
	public void setY(float v) {
		y = v;
	}
	public void setZ(float v) {
		z = v;
	}
}
