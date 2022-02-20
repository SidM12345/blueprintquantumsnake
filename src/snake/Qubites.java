package snake;
import java.awt.Color;

public class Qubites implements Placeable
{
    private static int decayTime = 6;
    private Tile positive, negative;
    private long sinceLastBind;
    private int qubitesRemaining;
    public Qubites()
    {
        sinceLastBind = System.currentTimeMillis();
        qubitesRemaining = 2;
    }

    public void bindQubitesToTiles(Tile positive, Tile negative)
    {
        qubitesRemaining = 2;
        this.positive = positive;
        this.negative = negative;
        sinceLastBind = System.currentTimeMillis();
    }

    public void update()
    {
        //call this method multiple times per second
        if ((System.currentTimeMillis() - sinceLastBind) / 1000F >= decayTime)
        {
            if (qubitesRemaining == 2) 
            {   
                decay();
            }
            reset();
        }
    }

    private void reset()
    {
        qubitesRemaining = 0;
        positive = null;
        negative = null;
    }

    private void decay()
    {
        //create wormholes
    }

    public int getQubitesRemaining()
    {
        return qubitesRemaining;
    }

    public int hasQubite(Tile tile)
    {
        //call this method everytime the snake moves
        int charge = 0;
        if (tile == positive)
        {
            positive = null;
            charge = 1;
        }
        else if (tile == negative)
        {
            negative = null;
            charge = -1;
        }
        else
        {
            return charge;
        }
        sinceLastBind = System.currentTimeMillis();
        qubitesRemaining--;
        return charge;
    }

    public Color getQubiteColor()
    {
        if (qubitesRemaining == 1)
        {
            if (positive != null)
            {
                return new Color(255, 0, 0);
            }
            else if (negative != null)
            {
                return new Color(0, 0, 255);
            }
        }
        else if (qubitesRemaining == 2)
        {
            return new Color(30, 30, 30);
        }

        return new Color(0, 255, 0);
    }

    public Tile getPositive()
    {
        return positive;
    }
    public Tile getNegative()
    {
        return negative;
    }
}
