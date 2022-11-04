package figure.shapeImpl;

import figure.Shape;

public record Cylinder(double radius, double height) implements Shape {

	@Override
	public double calculateVolume() {
		return Math.PI * Math.pow(radius, 2) * height;
	}
}
