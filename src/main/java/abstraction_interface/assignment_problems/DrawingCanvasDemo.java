
package abstraction_interface.assignment_problems;

abstract class Shape {
    private static int count = 1000;
    private final String shapeId;

    Shape() {
        count++;
        shapeId = "SH-" + count;
    }

    public String getShapeId() {
        return shapeId;
    }

    public abstract double calculateArea();

    public void scale(double factor) {
        scale(factor, factor);
    }

    public void scale(double xFactor, double yFactor) {
        // Overloaded scaling method
    }
}

class CircleShape extends Shape {
    private double radius;

    CircleShape(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        radius *= Math.sqrt(xFactor * yFactor);
    }
}

class SquareShape extends Shape {
    private double side;

    SquareShape(double side) {
        this.side = side;
    }

    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        side *= Math.sqrt(xFactor * yFactor);
    }
}

public class DrawingCanvasDemo {
    static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }

    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        SquareShape sq = new SquareShape(4.0);

        System.out.printf("%.2f%n", c.calculateArea());
        System.out.println(sq.calculateArea());

        sq.scale(2.0);
        System.out.println(sq.calculateArea());

        printArea(c);
        System.out.println(c.getShapeId());
    }
}
