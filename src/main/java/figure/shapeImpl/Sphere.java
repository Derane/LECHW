package figure.shapeImpl;

import figure.Shape;

public record Sphere(double radius) implements Shape {

	@Override
	public double calculateVolume() {
		return 4d / 3d * Math.PI * (radius * radius * radius);
	}
}
