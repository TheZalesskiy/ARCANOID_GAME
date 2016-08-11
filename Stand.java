package com.javarush.test.level24.lesson14.big01;

/**
 *  Stand by which reflects the ball .
 */
public class Stand extends BaseObject
{
    // Image to draw
    private static int[][] matrix = {
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };

    //speed
    private double speed = 1;
    //direction (-1 left, +1 right)
    private double direction = 0;

    public Stand(double x, double y)
    {
        super(x,y,3);
    }

    /**
     * The method moves the stand in accordance with the current value direction.
     */
    public void move()
    {
        double dx = speed * direction;
        x = x + dx;

        checkBorders(radius, Arcanoid.game.getWidth() - radius + 1, 1, Arcanoid.game.getHeight() + 1);
    }

    /**
     * direction is set to -1
     */
    public void moveLeft()
    {
        direction = -1;
    }

    /**
     * direction is set to +1
     */
    public void moveRight()
    {
        direction = 1;
    }

    public double getSpeed()
    {
        return speed;
    }

    public double getDirection()
    {
        return direction;
    }

    /**
     * Draw yourself on the canvas
     */
    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'M');
    }
}
