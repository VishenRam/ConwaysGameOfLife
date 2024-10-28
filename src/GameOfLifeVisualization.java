import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameOfLifeVisualization extends JPanel{

    private static final int CELL_SIZE = 10;
    private ConwaysByVishen game;
    private Timer timer;

    public GameOfLifeVisualization(int rows, int cols)
    {
        game = new ConwaysByVishen(rows, cols);

        //Set up the timer for continuous animation
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.nextGeneration();
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        boolean[][] grid = game.getGrid();

        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                if (grid[i][j])
                {
                    g.setColor(Color.BLACK);
                }
                else
                {
                    g.setColor(Color.WHITE);
                }

                g.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.GRAY);
                g.drawRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    public static void main(String [] args)
    {
        JFrame frame = new JFrame("Conway's Game of Life");
        GameOfLifeVisualization visualizer = new GameOfLifeVisualization(50,50);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(visualizer);
        frame.setVisible(true);
    }



}
