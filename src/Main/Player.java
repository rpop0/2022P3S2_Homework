package Main;

import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;
    private String pawnShape;
    private int cash;
    private int boardLocation;

    public Player() {
        OutputDevice od = new OutputDevice();
        Scanner scan = new Scanner(System.in);
        od.writeMessage("Name: ");
        String name = scan.nextLine();
        od.writeMessage("Shape of the pawn: ");
        String pawnShape = scan.nextLine();

        this.name = name;
        this.pawnShape = pawnShape;
        this.cash = 0;
        this.boardLocation = 0 ;
    }
    public int rollDice(){
        Random rand = new Random();
        return rand.nextInt(2,12);
    }

    public int getBoardLocation() {
        return boardLocation;
    }
    public int getCash(){
        return cash;
    }
    public String getName(){
        return name;
    }

    public void movePlayer(int diceRoll){
        this.boardLocation += diceRoll;
        if(this.boardLocation > 39){
            this.boardLocation = this.boardLocation - 40;
            this.cash += 200;}
    }

    public void addCash(int cash) {
        this.cash += cash;
    }

    public void removeCash(int cash) {
        this.cash -= cash;
    }
}
