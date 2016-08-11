package com.javarush.test.level24.lesson14.big01;

/**
 * Class - canvas for drawing.
 */
public class Canvas
{
    //widht & height
    private int width;
    private int height;
    //matrix where the paint . symbol - the color
    private char[][] matrix;

    public Canvas(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.matrix = new char[height + 2][width + 2];
    }

    /**
     * clear canvas
     */
    public void clear()
    {
        this.matrix = new char[height + 2][width + 2];
    }

    /**
     * Prints the transmitted figure in these coordinates uvetom c.
     * If the transmitted array contains one, then on the canvas they will meet characters - with .
     */
    public void drawMatrix(double x, double y, int[][] matrix, char c)
    {
        int height = matrix.length;
        int width = matrix[0].length;

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (matrix[i][j] == 1)
                    setPoint(x + j, y + i, c);
            }
        }
    }

    /**
     * We put a point on the canvas with the coordinates (x, y) and color - c.
     */
    public void setPoint(double x, double y, char c)
    {
        int x0 = (int) Math.round(x);
        int y0 = (int) Math.round(y);
        if (y0 < 0 || y0 >= matrix.length) return;
        if (x0 < 0 || x0 >= matrix[y0].length) return;

        matrix[y0][x0] = c;
    }

    /**
     *We print the contents of the canvas to the screen 
     */
    public void print()
    {
        System.out.println();

        for (int i = 0; i < height + 2; i++)
        {
            for (int j = 0; j < width + 2; j++)
            {
                System.out.print(" ");
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }

            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public char[][] getMatrix()
    {
        return matrix;
    }
}
