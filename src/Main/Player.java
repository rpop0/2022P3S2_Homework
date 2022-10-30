package Main;

import java.util.Random;
import java.util.Scanner;

public class Player {

    private String _name;
    private String _color;
    private int _locationOnBoard;
    private int _cash;
    private int _propertyVal;
    private int _totalwealth;

    public Player()
    {
        OutputDevice od = new OutputDevice();
        Scanner scan = new Scanner(System.in);
        od.writeMessage("Name: ");
        String name = scan.nextLine();
        od.writeMessage("Color: ");
        String color = scan.nextLine();
        od.writeMessage("\n\n");

        this._name = name;
        this._color = color;
        this._cash = 0;
        this._locationOnBoard = 0;
    }

    public int rollDice()
    {
        Random dice = new Random();
        Random dice2 = new Random();

        int val1 = dice.nextInt(7);
        int val2 = dice2.nextInt(7);
        

        while (val1 < 1 || val1 > 6)
        {
            val1 = dice.nextInt(7);
        }

        while (val2 < 1 || val2 > 6)
        {
            val2 = dice.nextInt(7);
        }

        return val1 + val2;

    }

    public String getColor()
    {
        return this._color;
    }

    public int getLocation()
    {
        return this._locationOnBoard;
    }

    public int getCash()
    {
        return this._cash;
    }

    public String getName()
    {
        return this._name;
    }

    public void movePlayer(int diceRoll)
    {
        this._locationOnBoard += diceRoll;
        if (this._locationOnBoard > 39)
        {
            this._locationOnBoard = this._locationOnBoard - 40;
            this._cash += 200;
        }
        System.out.println(this._name + " new position is: " + this._locationOnBoard + "\n");
    }

    public void pay(int money)
    {
        this._cash -= money;
        this._propertyVal += money;
    }

    public void receiveMoney(int money)
    {
        this._cash += money;
    }
    
    public void payToPlayer(int money, Player player)
    {
        this._cash -= money;
        player.receiveMoney(money);
    }

    public int getTotalWealth()
    {
        this._totalwealth = this._cash + this._propertyVal;
        return this._totalwealth;
    }

    public int getPropertyVal()
    {
        return this._propertyVal;
    }
}
