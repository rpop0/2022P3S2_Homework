package Main.Actions;

import Main.Board;
import Main.Player;

public class MoveAction implements Action{

    private Player player;
    private int amount;

    public MoveAction(Player player, int amount) {
        this.player = player;
        this.amount = amount;
    }

    @Override
    public Action apply() {
        if(amount>=0) {
            int pos = this.player.getLocation() + amount;
            if (pos > 39) {
                pos -= 40;
                this.player.set_cash(this.player.getCash() + 200);
            }

            this.player.set_locationOnBoard(pos);
            System.out.println(this.player.getName() + " new position is: " + this.player.getLocation() + "\n");
        }

        //Go to Jail!
        else if(amount == -1)
        {
            this.player.set_locationOnBoard(20);
            System.out.println(this.player.getName() + " went to jail for 3 turns!" + '\n');
            this.player.setTimeOut(3);
            return null;
        }

        //Advance to GO, collect $200.
        else if(amount == -2)
        {
            this.player.set_locationOnBoard(0);
            System.out.println(this.player.getName() + " advanced to GO, earning $200!" + '\n');
            return new PaymentAction(null,player,200);
        }
        return Board.getSquare(this.player.getLocation()).getAction(this.player);
    }
}
