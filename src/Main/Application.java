package Main;
import java.util.Date;
import java.util.Arrays;

import java.util.Random;
import Main.InputDevice;
public class Application {
    private InputDevice id;
    private OutputDevice od;

    private Player pl1;
    private Player pl2;

    public Application(InputDevice id, OutputDevice od) {
        this.id = id;
        this.od = od;
    }
    public Player[] getPlayers(int numberOfPlayers){

        Player players[] = new Player[numberOfPlayers];
        for(int i = 0; i < numberOfPlayers; i++){
            players[i] = new Player();
        }
        return players;
    }
    public void decideWinner(Board board, int numberOfPlayers, Player players[]){
        int cashValues[] = new int[numberOfPlayers];
        int max = cashValues[0];
        int maxPos = 0;
        int drawPos = 0;
        boolean draw = false;
        Square currentpos;

        for(int i = 0; i < numberOfPlayers;i++){
            cashValues[i] = players[i].getCash();
        }

        for(int i = 0; i < 40; i++){
            currentpos = board.getLayout(i);
            if(currentpos instanceof Property)
                if(((Property) currentpos).getOwner()!=null)
                    ((Property) currentpos).getOwner().addCash(((Property) currentpos).getPrice());
        }

        for (int i = 0; i < cashValues.length; i++) {

            if(cashValues[i] > max)
                maxPos = i;
            else if(cashValues[i]==max){
                int x = players[i].getBoardLocation();
                int z = players[maxPos].getBoardLocation();

                if(x>z)
                    maxPos = i;
                else {
                    draw = true;
                    drawPos = i;
                }
            }
        }
        if(!draw)
            od.writeMessage(players[maxPos].getName());
        else
            od.writeMessage("Draw");
    }
    public void playGame(Board board, int numberOfRounds, int numberOfPlayers, Player players[]){
        Square currentpos;
        for(int i = 0; i < numberOfRounds;i++){
            for(int j = 0;j < numberOfPlayers;j++){
                int roll = players[j].rollDice();
                players[j].movePlayer(roll);
                currentpos = board.getLayout(players[j].getBoardLocation());
                if(currentpos instanceof Property)
                {
                    if(((Property) currentpos).getOwner()==null)
                        ((Property) currentpos).buyProperty(players[j]);
                    else
                        ((Property) currentpos).taxPlayer(players[j]);
                }

            }
        }


    }
}