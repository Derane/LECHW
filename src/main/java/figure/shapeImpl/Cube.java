package figure.shapeImpl;

import figure.Shape;

public record Cube(double lengthOfSide) implements Shape {

	@Override
	public double calculateVolume() {
		return Math.pow(lengthOfSide, 3);
	}
}
