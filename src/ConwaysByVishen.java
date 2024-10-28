import java.awt.*;
import java.util.Random;

public class ConwaysByVishen implements ConwaysGameOfLife{

    private int rows;
    private int cols;
    private boolean [][] grid;

    //Constructor being used to initialize grid dimensions.

    public ConwaysByVishen(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        this.grid = new boolean[rows][cols];

        //Initial grid with random live and dead cells for visualization

        Random rand = new Random();
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                grid[i][j] = rand.nextBoolean();
            }
        }
    }

    //Utility function to count live neighbours

    private int countLiveNeighbours(Point point)
    {
        int liveNeighbours = 0;
        int x = point.x;
        int y = point.y;

        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++)
            {
                if (i == 0 && j == 0) continue;
                int nx = x + i;
                int ny = y + j;

                // Check if within grid bounds

                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && grid[nx][ny])
                {
                    liveNeighbours++;
                }
            }
        }

        return liveNeighbours;
    }

    //Implementing rules of the game

    @Override
    public boolean liveCellWithFewerThanTwoLiveNeighboursDies(Point point) {
        int neighbours = countLiveNeighbours(point);
        return neighbours < 2;
    }

    @Override
    public boolean liveCellWithTwoOrThreeLiveNeighboursLives(Point point) {
        int neighbours = countLiveNeighbours(point);
        return neighbours == 2 || neighbours == 3;
    }

    @Override
    public boolean liveCellWithMoreThanThreeLiveNeighboursDies(Point point) {
        int neighbours = countLiveNeighbours(point);
        return neighbours > 3;
    }

    @Override
    public boolean deadCellWithExactlyThreeLiveNeighboursBecomesAlive(Point point) {
        int neighbours = countLiveNeighbours(point);
        return neighbours == 3;
    }



    //Evolve the grid for the next generation

    public void nextGeneration()
    {
        boolean [][] newGrid = new boolean[rows][cols];

        for (int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                Point point = new Point (i,j);

                if(grid[i][j])
                {
                    // Apply rules for live cells
                    if(liveCellWithFewerThanTwoLiveNeighboursDies(point))
                    {
                        newGrid[i][j] = false;
                    }
                    if(liveCellWithTwoOrThreeLiveNeighboursLives(point))
                    {
                        newGrid[i][j] = true;
                    }
                    else
                    {
                        newGrid[i][j] = false;
                    }
                }
                else
                {
                    if(deadCellWithExactlyThreeLiveNeighboursBecomesAlive(point))
                    {
                        newGrid[i][j] = true;
                    }
                }
            }
        }

        //Update the grid to next generation
        grid = newGrid;
    }

    public boolean[][] getGrid()
    {
        return grid;
    }



}
