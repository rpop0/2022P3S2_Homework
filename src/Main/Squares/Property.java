package Main.Squares;

import Main.Actions.Action;
import Main.Actions.PaymentAction;
import Main.Player;

import java.util.Random;

public class Property extends Square {
    private String _owner = "None";
    private Player _owner_obj;

    private int value;

    private int tax;
    
    public Property(String name)
    {
        super(name);
        this._owner_obj = null;
        this.value = 250;
        this.tax = 100;
    }

    public boolean checkIfOwned()
    {
        if (_owner.equals("None"))
        {
            return false;
        }
        return true;
    }

    public void buyProperty(Player player)
    {
        if(player.getCash() > value)
        {
            Action act;
            this._owner = player.getName();
            this._owner_obj = player;
            player.add_propertyVal(value);
            act = new PaymentAction(player,null,value);
            System.out.println(player.getName() + " has bought " + super.getName() + " with " + value + ".\n");
        }


    }

    public int payTax()
    {
        return tax;
    }

    public String getOwnerName(){return this._owner;}

    public Player getOwner()
    {
        return this._owner_obj;
    }

    public void set_owner(String _owner) {
        this._owner = _owner;
    }

    public void set_owner_obj(Player _owner_obj) {
        this._owner_obj = _owner_obj;
    }

    public int getValue() {
        return value;
    }
}
