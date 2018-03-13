import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;

/**
 * Class to display multiple windows and handle movement
 *
 * @author Joseph Brooksbank
 */
class WindowDisplay {
    /**
     * List of all windows to be displayed on the screen
     */
    private ArrayList<Window> windows;
    /**
     * Height and width of StdDraw Canvas
     */
    private int width, height;

    /**
     * Default constructor, creates canvas and arrayList of windows
     *
     * @param width  The width of the canvas
     * @param height THe height of the canvas
     */
    WindowDisplay(int width, int height) {
        // initializing values of height, width, and arrayList object
        //NOTE: I personally would probably just pass width and height directly to StdDraw, but the assignment calls for
        // private variables to store this data in the class.
        this.width = width;
        this.height = height;
        this.windows = new ArrayList<>();

        // Setting up canvas
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.enableDoubleBuffering();
    }

    /**
     * A method to add a window to the current arraylist of windows
     *
     * @param w The window to add
     */
    void addWindow(Window w) {
        windows.add(w);
    }

    /**
     * A method to run the simulation, drawing and animating all of the windows in the correct stack order
     */
    void run() {

        for (; ; ) {

            // drawing all of the windows in correct order to display "top" window last
            StdDraw.clear();
            for (int i = windows.size() - 1; i >= 0; i--) {
                windows.get(i).draw();
            }

            // mouse pressed actions
            if (StdDraw.mousePressed()) {
                // setting "temp" variables for movement loop
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                int indexOfWindow = whichWindow();

                // If the "index" of the window the mouse clicked on is -1, the window does not exist (white space)
                if (indexOfWindow >= 0) {
                    // move selected window to the top of the window stack
                    moveToTop(indexOfWindow);

                    Window currentlySelected = windows.get(whichWindow());
                    // while loop to handle movement
                    while (StdDraw.mousePressed()) {
                        if (x != StdDraw.mouseX()) {
                            currentlySelected.move(StdDraw.mouseX() - x, 0);
                            x = StdDraw.mouseX();
                        }
                        if (y != StdDraw.mouseY()) {
                            currentlySelected.move(0, StdDraw.mouseY() - y);
                            y = StdDraw.mouseY();
                        }

                        // Second draw method to draw during the while loop
                        // there is a better way to do this that doesn't involve the same code twice,
                        // but I can't think of a non-complicated way because of the nested nature of the while loop
                        StdDraw.clear();
                        for (int i = windows.size() - 1; i >= 0; i--) {
                            windows.get(i).draw();
                        }
                        StdDraw.show();
                    }
                }

            }
            StdDraw.show();
        }
    }

    /**
     * A method which determines which window was clicked on
     *
     * @return The index of the window that was clicked on
     */
    private int whichWindow() {
        for (Window w : windows) {
            if (w.contains(StdDraw.mouseX(), StdDraw.mouseY())) {
                return windows.indexOf(w);
            }
        }
        // Returns -1 if the mouse was clicked on a location that isn't a window
        return -1;
    }

    /**
     * A method which moves a window to the top of the window stack
     *
     * @param index The index of the window to move
     */
    private void moveToTop(int index) {
        Window temp = windows.get(index);
        windows.remove(index);
        windows.add(0, temp);
    }

}
