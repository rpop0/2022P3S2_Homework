package Main.Exceptions;

import Main.Actions.Action;
import Main.Actions.PaymentAction;
import Main.Board;
import Main.Player;
import Main.Squares.Property;
import Main.Squares.Square;

import java.util.Objects;

public class InsufficientFundsException extends MonopolyException
{
    public boolean resolve(Player player, int amount) {
        Action act;
        for (Square i : Board.getBoard())
            if (i instanceof Property)
                if (Objects.equals(((Property) i).getOwnerName(), player.getName())) {
                    ((Property) i).set_owner_obj(null);
                    ((Property) i).set_owner("None");
                    player.add_propertyVal(-((Property) i).getValue());
                    act = new PaymentAction(null, player, ((Property) i).getValue());
                    if (player.getCash() >= amount)
                        break;
                }
        return player.getCash() >= amount;
    }
}
