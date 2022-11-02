package figure.shapeImpl;

import figure.Shape;

public record Cylinder(double radius, double height) implements Shape {

	@Override
	public double calculateVolume() {
		return Math.PI * Math.sqrt(radius) * height;
	}
}
