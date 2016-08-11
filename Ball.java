package com.javarush.test.level24.lesson14.big01;

/**
 * Class for ball game
 */
public class Ball extends BaseObject
{
    //speed
    private double speed;
    //direction (degrees from 0 to 360)
    private double direction;

    //current motion vector (dx, dy)
    private double dx;
    private double dy;

    //whether the object is frozen or can move
    private boolean isFrozen;

    public Ball(double x, double y, double speed, double direction)
    {
        super(x, y, 1);

        this.direction = direction;
        this.speed = speed;

        this.isFrozen = true;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public double getDirection()
    {
        return direction;
    }

    public double getDx()
    {
        return dx;
    }

    public double getDy()
    {
        return dy;
    }

    /**
     * Install a new direction.
     * Immediately and calculate a new vector .
     * Taco approach is useful when a rebound from the walls.
     */
    public void setDirection(double direction)
    {
        this.direction = direction;

        double angel = Math.toRadians(direction);
        dx = Math.cos(angel) * speed;
        dy = -Math.sin(angel) * speed;
    }

    /**
     * Draw yourself on the "canvas" .
     */
    @Override
    public void draw(Canvas canvas)
    {
        canvas.setPoint(x, y, 'O');
    }

    /**
     *Move yourself in one step.
     */
    public void move()
    {
        if (isFrozen) return;

        x += dx;
        y += dy;

        checkRebound(1, Arcanoid.game.getWidth(), 1, Arcanoid.game.getHeight() + 5);
    }

    /**
     *Check whether the ball over the wall did not fall .
     * If yes - reflect his.
     */
    public void checkRebound(int minx, int maxx, int miny, int maxy)
    {
        if (x < minx)
        {
            x = minx + (minx - x);
            dx = -dx;
        }

        if (x > maxx)
        {
            x = maxx - (x - maxx);
            dx = -dx;
        }

        if (y < miny)
        {
            y = miny + (miny - y);
            dy = -dy;
        }

        if (y > maxy)
        {
            y = maxy - (y - maxy);
            dy = -dy;
        }
    }

    /**
     * Runs the ball
     * isFrozen = false.
     * To recalculate the motion vector (dx, dy).
     */
    public void start()
    {
        this.setDirection(direction);
        this.isFrozen = false;
    }
}
