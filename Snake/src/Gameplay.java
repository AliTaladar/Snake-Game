import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Gameplay extends JPanel implements Runnable, KeyListener {

    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 500, HEIGHT = 500;
    private Thread thread;
    private boolean running;
    private boolean right = true, left = false, up = false, down = false;
    private BodyPart b;
    private Apple apple;
    private final ArrayList<Apple> apples;
    private final Random r;
    private final ArrayList<BodyPart> snake;
    private int xCoor = 10, yCoor = 10, size = 5;
    private int ticks = 0;
    private int speed = 1000000;
    private int counter = 0;
    private final int level;

    /**
     * Constructor
     * @param i the integer representing the level (hard = 0, easy = 1)
     */
    public Gameplay(int i) {
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        snake = new ArrayList<>();
        apples = new ArrayList<>();
        level = i;
        r = new Random();
        start();
    }

    /**
     * Starts the game
     */
    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Stops the game
     */
    public void stop() {
        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {}

        System.exit(0);
    }

    /**
     * Handles each frame
     */
    public void tick() {
        if (snake.size() == 0) {
            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);
        }
        ticks++;
        if (ticks > speed) {
            if (right) {
                xCoor++;
            }
            if (left) {
                xCoor--;
            }
            if (up) {
                yCoor--;
            }
            if (down) {
                yCoor++;
            }
            ticks = 0;

            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);

            if (snake.size() > size) {
                snake.remove(0);
            }
            if (apples.size() == 0) {
                int xCoor = r.nextInt(49);
                int yCoor = r.nextInt(49);

                apple = new Apple(xCoor, yCoor, 10);
                apples.add(apple);
            }

            for (int i = 0; i < apples.size(); i++) {
                if (xCoor == apple.getxCoor() && yCoor == apple.getyCoor()) {
                    size++;
                    counter++;
                    snake.get(0).changeColor();
                    if (counter < 33 && level == 1) {
                        speed *= 0.96;
                    }
                    apples.remove(0);
                    i++;
                }
            }

            if ((xCoor > 49 || xCoor < 0 || yCoor > 49 || yCoor < 0) && level == 0) {
                stop();
            }
            if (xCoor > 49) {
                xCoor = 0;
            }
            if (xCoor < 0) {
                xCoor = WIDTH / 10;
            }
            if (yCoor > 49) {
                yCoor = 0;
            }
            if (yCoor < 0) {
                yCoor = HEIGHT / 10;
            }

            for (int i = 0; i < snake.size(); i++) {
                if (xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
                    if (i != snake.size() - 1) {

                        stop();

                    }
                }
            }

        }
    }

    /**
     * Paints everything on the screen
     * @param g Graphics class
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < WIDTH / 10; i++) {
            g.drawLine(i * 10, 0, i * 10, HEIGHT);
        }
        for (int i = 0; i < HEIGHT / 10; i++) {
            g.drawLine(0, i * 10, WIDTH, i * 10);
        }
        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).draw(g);
        }
        for (int i = 0; i < apples.size(); i++) {
            apples.get(i).draw(g);
        }

    }

    /**
     * Thread run method
     * Calls methods tick() and run() consistently
     */
    @Override
    public void run() {
        while (running) {
            tick();
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int key = e.getKeyCode();
        keyListener(key);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        keyListener(key);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        keyListener(key);
    }

    public void keyListener(int key) {
        if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && !left) {
            right = true;
            up = false;
            down = false;
        }
        if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && !right) {
            left = true;
            up = false;
            down = false;
        }
        if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && !down) {
            left = false;
            up = true;
            right = false;
        }
        if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && !up) {
            left = false;
            down = true;
            right = false;
        }
    }
}
