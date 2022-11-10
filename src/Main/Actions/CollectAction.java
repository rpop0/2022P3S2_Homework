package Main.Actions;

import Main.Exceptions.InsufficientFundsException;
import Main.Player;

import java.util.List;

public class CollectAction implements Action{
    private List<Player> source;
    private Player destination;
    private int amount;

    public CollectAction(List<Player> source, Player destination, int amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }

    @Override
    public Action apply()
    {
        for (Player currentSource : source)
            if(currentSource != destination)
            {
                Action act;
                try
                {
                    if(currentSource.getCash() >= amount)
                        act = new PaymentAction(currentSource,null,amount);
                    else throw new InsufficientFundsException();
                }
                catch(InsufficientFundsException e)
                {
                    if (e.resolve(destination,amount))
                        act = new PaymentAction(currentSource,null,amount);
                    else {
                        System.out.println(currentSource.getName() + " has fallen into Bankruptcy!\n");
                        currentSource.setBankruptcy();
                    }

                }
                this.destination.set_cash(this.destination.getCash()+amount);
            }
        return null;
    }
}
