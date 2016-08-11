package com.javarush.test.level24.lesson14.big01;

/**
 * The class for "brick" of the object .
 */
public class Brick extends BaseObject
{
    //to draw a picture
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };

    public Brick(double x, double y)
    {
        super(x,y,3);
    }

    /**
     * Draw yourself on the canvas
     */
    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'H');
    }

    /**
     * Do nothing - brick stationary
     */
    @Override
    public void move()
    {
        //do nothing
    }
}
