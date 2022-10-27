package Monopoly;

import java.util.Random;

public class Property extends Square {
    private String _owner = "None";
    
    public Property(String name)
    {
        super(name);
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
        this._owner = player.getName();
        Random n = new Random();
        int money = n.nextInt(301);
        player.pay(money);

        System.out.println(player.getName() + " has bought " + super.getName() + " with " + money + ".\n");
    }

    public String getOwner()
    {
        return this._owner;
    }
}
