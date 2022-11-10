package Main.Squares;

import Main.Actions.Action;
import Main.Actions.DrawCardAction;
import Main.Actions.MoveAction;
import Main.Actions.PaymentAction;
import Main.Player;

import java.io.Serial;
import java.io.Serializable;

public class Square implements Serializable {
    
    private String _name;
    
    public Square(String name)
    {
        this._name = name;
    }

    public String getName()
    {
        return this._name;
    }

    public Action getAction(Player source)
    {
        Action act;
        if(this instanceof Property) {
            if(((Property) this).checkIfOwned())
                act = new PaymentAction(source,((Property) this).getOwner(),((Property) this).payTax());
            else {((Property) this).buyProperty(source); act=null;}
        }
        else if(this instanceof Jail)
        {
            act = new MoveAction(source,-1);
        }
        else if(this instanceof ChanceOrCommunity)
        {
            act = new DrawCardAction(source, this.getName());
        }
        else
            act = null;

        return act;
    }


}
