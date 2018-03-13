import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

/**
 * This is a class for a single window
 *
 * @author Joseph Brooksbank
 */
class Window {
    /**
     * X and Y coordinates for the center of the window
     */
    private double x, y;
    /**
     * Half width and half heights of the window
     */
    private double width, height;
    /**
     * Color of the window
     */
    private Color color;

    /**
     * Default constructor, initializes values
     *
     * @param x      The X coordinate of the window
     * @param y      The Y coordinate of the window
     * @param width  The half width of the window
     * @param height The half height of the window
     * @param color  The color of the window
     */
    Window(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Method which draws the window in the correct color at the correct location
     */
    void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(x, y, width, height);
    }

    /**
     * A method which determines if a point is inside the window or not
     *
     * @param x The x coordinate of the point
     * @param y The y coordinate of the point
     * @return Whether the point is inside the window or not
     */
    boolean contains(double x, double y) {
        return (x >= this.x - width && x <= this.x + width) &&
                (y >= this.y - height && y <= this.y + height);
    }

    /**
     * A method which moves the window by an amount
     *
     * @param x The amount to move in the x direction
     * @param y The amount to move in the y direction
     */
    void move(double x, double y) {
        this.x += x;
        this.y += y;
    }
}
