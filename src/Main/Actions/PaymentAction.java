package Main.Actions;

import Main.Exceptions.InsufficientFundsException;
import Main.Player;

public class PaymentAction implements Action{
    private Player source;
    private Player destination;
    private int amount;

    public PaymentAction(Player source, Player destination, int amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }

    @Override
    public Action apply() {
        Action act;

        if(source!=null)
            try
            {
                if(source.getCash() >= amount)
                    act = new PaymentAction(source,null,amount);
                else throw new InsufficientFundsException();
            }
            catch(InsufficientFundsException e)
            {
                if (e.resolve(source,amount))
                    act = new PaymentAction(source,null,amount);
                else {
                    System.out.println(source.getName() + " has fallen into Bankruptcy!\n");
                    source.setBankruptcy();
                }

            }

        if(this.destination != null)
            this.destination.set_cash(this.destination.getCash()+amount);
        return null;
    }
}
