package Main;

import Main.IO.OutputDevice;
import Main.Squares.Property;
import Main.Squares.Square;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Player implements Serializable {

    private String _name;
    private String _color;
    private int _locationOnBoard;
    private int _cash;
    private int _propertyVal;
    private int _totalwealth;
    private int timeOut;
    private boolean bankrupt;

    public String getAllData()
    {
        String data = _name+" "+_color+" "+_locationOnBoard+" "+_cash+" "+_propertyVal+" "+_totalwealth+" "+timeOut+ " " + bankrupt +"\n";
        for (Square i : Board.getBoard())
            if (i instanceof Property)
                if (Objects.equals(((Property) i).getOwnerName(), this._name))
                    data = data.concat(i.getName() + " ");
        return data;
    }

    public Player(String data)
    {
        String[] dataParts = data.split(" ");
        _name = dataParts[0];
        _color = dataParts[1];
        _locationOnBoard = Integer.parseInt(dataParts[2]);
        _cash = Integer.parseInt(dataParts[3]);
        _propertyVal = Integer.parseInt(dataParts[4]);
        _totalwealth = Integer.parseInt(dataParts[5]);
        timeOut = Integer.parseInt(dataParts[6]);
        bankrupt = Boolean.parseBoolean(dataParts[7]);
    }

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
        this.timeOut = 0;
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

    public void receiveMoney(int money)
    {
        this._cash += money;
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

    public void set_locationOnBoard(int _locationOnBoard) {
        this._locationOnBoard = _locationOnBoard;
    }

    public void set_cash(int _cash) {
        this._cash = _cash;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public boolean isBankrupt(){return this.bankrupt;}

    public void setBankruptcy(){this.bankrupt = true;}

    public void add_propertyVal(int _propertyVal) {
        this._propertyVal += _propertyVal;
    }
}
