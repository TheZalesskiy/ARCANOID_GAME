import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * the main class of the game
 */
public class Arcanoid {
    
    private int width;
    private int height;

    //list bricks
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    //ball
    private Ball ball;
    //Stand
    private Stand stand;

    //game over?
    private boolean isGameOver = false;

    public Arcanoid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    /**
     *Drawing on the canvas boundaries and objects .
     */
    public void draw(Canvas canvas) {
        drawBoders(canvas);
        for (Brick current : bricks)
            current.draw(canvas);
        ball.draw(canvas);
        stand.draw(canvas);
        //will draw boundaries
        //will draw bricks
        //will draw Ball
        //will draw Stand
    }

    /**
     * Drawing on the canvas boundaries
     */
    private void drawBoders(Canvas canvas) {
        //draw game
        for (int i = 0; i < width + 2; i++) {
            for (int j = 0; j < height + 2; j++) {
                canvas.setPoint(i, j, '.');
            }
        }

        for (int i = 0; i < width + 2; i++) {
            canvas.setPoint(i, 0, '-');
            canvas.setPoint(i, height + 1, '-');
        }

        for (int i = 0; i < height + 2; i++) {
            canvas.setPoint(0, i, '|');
            canvas.setPoint(width + 1, i, '|');
        }
    }

    /**
     * The main program loop.
     * It's all important actions occur
     */
    public void run() throws Exception {
        //Create a canvas for drawing .
        Canvas canvas = new Canvas(width, height);

        //Create an object " observer of the keyboard " and we start it.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //Execute loop until the game is ending
        while (!isGameOver) {
            //" Observer " contains events keystrokes ?
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();

                //If the " left arrow " - move the figure to the left
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    stand.moveLeft();
                    //If the " right arrow " - move the figure to the right
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    stand.moveRight();
                    //If the "gap" - run the ball
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ball.start();
            }

            //We move objects
            move();

            //check collision
            checkBricksBump();
            checkStandBump();

            //check that the ball could fly through the bottom.
            checkEndGame();

            //We draw the objects
            canvas.clear();
            draw(canvas);
            canvas.print();

            //pause
            Thread.sleep(300);
        }

        //writing message "Game Over"
        System.out.println("Game Over!");
    }

    /**
     * We move the ball and stand.
     */
    public void move() {
        ball.move();
        stand.move();
        
    }

    /**
     * Check collision with bricks .
     * If a collision was - the ball flies in a random direction 0..360 degrees
     */
    public void checkBricksBump() {
        for (Brick currentBrick : bricks) {
            if (ball.isIntersec(currentBrick)) {
                double angel = Math.random() * 360;
                ball.setDirection(angel);
                bricks.remove(currentBrick);
                break;
            }
        }
        //Then check - whether the ball collided with a brick .
        // If it is - a brick to remove and run the ball in a random direction .
    }

    /**
     * Check collision with stand .
     * If a collision was - the ball flies in a random direction up 80..100 degrees
     */
    public void checkStandBump() {
        if (ball.isIntersec(stand)) {
            double angel = 80 + Math.random()*20;
            ball.setDirection(angel);
        }
        //Then check - whether the ball collided with a stand .
        // If yes - run the ball up to 80..100 degrees .
    }

    /**
     * Check - whether the ball flew through the bottom .
     * If yes - the game is over (isGameOver = true)
     */
    public void checkEndGame() {
        if (ball.getY() >= height)
        //If the ball flew over the bottom border - the game is over
        isGameOver = true;          
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static Arcanoid game;

    public static void main(String[] args) throws Exception {
        game = new Arcanoid(20, 30);

        Ball ball = new Ball(10, 29, 2, 95);
        game.setBall(ball);

        Stand stand = new Stand(10, 30);
        game.setStand(stand);

        game.getBricks().add(new Brick(3, 3));
        game.getBricks().add(new Brick(7, 5));
        game.getBricks().add(new Brick(12, 5));
        game.getBricks().add(new Brick(16, 3));

        game.run();
    }
}



















