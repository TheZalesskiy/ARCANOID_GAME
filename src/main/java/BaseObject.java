/**
 * The base class for all objects in the game 
 */
public abstract class BaseObject
{
    //coordinates
    protected double x;
    protected double y;
    //object range
    protected double radius;

    protected BaseObject(double x, double y, double radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    /**
     * The method draws your object on the "canvas" 
     */
    public abstract void draw(Canvas canvas);

    /**
     * Move yourself in one turn 
     */
    public abstract void move();

    /**
     * Check - Do not go (x, y) abroad .
     */
    public void checkBorders(double minx, double maxx, double miny, double maxy)
    {
        if (x < minx) x = minx;
        if (x > maxx) x = maxx;
        if (y < miny) y = miny;
        if (y > maxy) y = maxy;
    }

    /**
     * Check - whether the transmitted cross (o) and our (this) objects 
     */
    public boolean isIntersec(BaseObject o)
    {
        double dx = x - o.x;
        double dy = y - o.y;
        double destination = Math.sqrt(dx * dx + dy * dy);
        double destination2 = Math.max(radius, o.radius);
        return destination <= destination2;
    }
}
