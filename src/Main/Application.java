package Main;

import Main.Actions.Action;
import Main.Actions.MoveAction;
import Main.IO.InputDevice;
import Main.IO.OutputDevice;
import Main.Squares.Square;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.exit;

public class Application {
    InputDevice id;
    OutputDevice od;
    Square[]board = new Board().getBoard();
    static Player[] players;

    public void Save()
    {
        try(ObjectOutputStream fos = new ObjectOutputStream(new FileOutputStream("SaveFile.txt")))
        {
            for(Player current : players)
            {
                fos.writeObject(current);
            }

            for (Square i : Board.getBoard())
                fos.writeObject(i);

        }catch(FileNotFoundException e){System.out.println("Error creating savefile."); exit(-2);}
        catch(IOException e) {System.out.println("Error writing to savefile."); exit(-2);}
    }

    public Player[] Load(int noOfPlayers)
    {
        try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream("SaveFile.txt")))
        {
            players = new Player[noOfPlayers];
            for (int i = 0; i < noOfPlayers; i++)
            {
                players[i] = (Player)ios.readObject();
            }
            for (int i = 0; i < 40; i++)
                board[i] = (Square)ios.readObject();
        }catch(FileNotFoundException e){System.out.println("Savefile not found, please start a new game."); exit(-1);}
        catch(IOException e){System.out.println("Savefile may be corrupted or access isn't authorized, please start a new game."); exit(-1);}
        catch(ClassNotFoundException e){e.printStackTrace();}


        return players;
    }

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

    public Player[] createPlayers(int noOfPlayers)
    {
        players = new Player[noOfPlayers];
        for (int i = 0; i < noOfPlayers; i++)
        {
            players[i] = new Player();
        }
        return players;
    }

    public static Player[] getPlayers(){return players;}

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
            if (PlayersCash[i] > max && !players[i].isBankrupt())
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
        Save();
    }

    public void playGame(int noOfRounds, int noOfPlayers, Player []players)
    {
        for (int i = 0; i < noOfRounds; i++)
        {
            for (int j = 0; j < noOfPlayers; j++)
            {
                if(players[j].getTimeOut()!=0 && !players[j].isBankrupt())
                {
                    players[j].setTimeOut(players[j].getTimeOut()-1);
                    if(players[j].getTimeOut()>0)
                        od.writeMessage("Player " + players[j].getName() + " is in Jail for " + players[j].getTimeOut() + " more turns!\n");
                    else
                        od.writeMessage("Player " + players[j].getName() + " has left Jail!\n");
                }
                else
                {
                    int roll = players[j].rollDice();
                    Action act = new MoveAction(players[j], roll);
                    while (act != null) {
                        act = act.apply();
                    }
                }
            }
        }
    }

}
