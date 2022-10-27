package Monopoly;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Application {
    InputDevice id;
    OutputDevice od;
    Square []board = new Board().getBoard();

    public Application(InputDevice _id, OutputDevice _od)
    {
        this.od = _od;
        this.id = _id;
    }

    public void run()
    {

        // od.writeMessage("Application started");
        // int no1 = id.nextInt();
        // int no2 = id.nextInt();

        // od.writeMessage("Today's lucky numbers are: ");
        // od.writeMessage(no1 + ", " + no2);
        randomArraySort();
    }

    public void sortNumbers(int []arr)
    {
        Arrays.sort(arr);
    }

    public void randomArraySort()
    {
        try (Scanner input = new Scanner(System.in)) {
            int N = input.nextInt();
            System.out.println();
            int []arr = id.getNumbers(N);
            sortNumbers(arr);

            for (int i : arr)
            {
                System.out.println(i);
            }
        }
    }

    public int[] wordSizeHistogram(String prop)
    {
        int []arr = new int[100];

        for (String i: prop.split(" "))
        {
            arr[i.length()-1]++; 
        }

        return arr;
    }

    public void exampleHistogram(String his)
    {
        int []arr = wordSizeHistogram(his);
        
        int j = 1;
        for (int i : arr)
        {
            if (i != 0)
                System.out.println(j + ": " + i);
            j++;
        }
    }

    public Player[] getPlayers(int noOfPlayers)
    {
        Player []players = new Player[noOfPlayers];
        for (int i = 0; i < noOfPlayers; i++)
        {
            players[i] = new Player();
        }
        return players;
    }

    public void decideWinner(int noOfPlayers, Player []players)
    {
        int []PlayersCash = new int[noOfPlayers];
        int max = PlayersCash[0];
        int maxPos = 0;
        int drawPos = 0;
        boolean draw = false;
        String drawText = "Draw";

        for (int i = 0; i < noOfPlayers; i++)
        {
            PlayersCash[i] = players[i].getTotalWealth();
        }

        for (int i = 0; i < PlayersCash.length; i++)
        {
            if (PlayersCash[i] > max)
            {
                maxPos = i;
                max = PlayersCash[i];
            }
            else if (PlayersCash[i] == max)
            {
                int x = players[i].getLocation();
                int z = players[maxPos].getLocation();

                if (x > z)
                {
                    maxPos = i;
                }
                else
                {
                    draw = true;
                    drawPos = i;
                }
            }
        }
        if (!draw)
        {
            od.writeMessage(players[maxPos].getName() + " has WON, with: " + players[maxPos].getTotalWealth() + ", of which " + players[maxPos].getCash() + " is cash and " + players[maxPos].getPropertyVal() + " is property value " + ".\n");
            
            for (int i = 0; i < players.length; i++)
            {
                if (i != maxPos)
                {
                    od.writeMessage(players[i].getName() + " has lost, with: " + players[i].getTotalWealth() + ", of which " + players[i].getCash() + " is cash and " + players[i].getPropertyVal() + " is property value " + ".\n");
                }
            }
        }
        else
        {
            od.writeMessage(drawText + " between players: " + players[maxPos].getName() + " and " + players[drawPos].getName() + ", both with: " + players[maxPos].getCash() + "$.");
        }
    }

    public void playGame(int noOfRounds, int noOfPlayers, Player []players)
    {
        for (int i = 0; i < noOfRounds; i++)
        {
            for (int j = 0; j < noOfPlayers; j++)
            {
                int roll = players[j].rollDice();
                players[j].movePlayer(roll);
                if (board[players[j].getLocation()] instanceof Property )
                {
                    if ( ((Property) board[players[j].getLocation()]).checkIfOwned() )
                    {
                        for (int x = 0; x < players.length; x++)
                        {
                            if ( ((Property) board[players[j].getLocation()]).getOwner() == players[x].getName() && players[j].getName() != players[x].getName())
                            {
                                Random n = new Random();
                                int money = n.nextInt(101);
                                players[j].payToPlayer(money, players[x]);
                                System.out.println(players[j].getName() + " has payed " + money + " to " + players[x].getName() + "\n");
                            }
                        }
                    }
                    else
                    {
                        ( (Property) board[players[j].getLocation()]).buyProperty(players[j]);
                    }
                }
            }
        }
    }


}
