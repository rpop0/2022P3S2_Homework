package Main.Actions;

import Main.Application;
import Main.Player;

import java.util.*;

public class DrawCardAction implements Action{
    private Player player;
    private String type;
    private ArrayList<String> cards;

    public DrawCardAction(Player player, String type)
    {
        this.player = player;
        this.type = type;
        this.cards = new ArrayList<String>();

        //Add Chance cards here:
        if(Objects.equals(type, "Chance"))
        {
            this.cards.add("Advance to GO!");
            this.cards.add("Bank pays you a dividend of $50!");
            this.cards.add("Go to Jail.");
        }

        //Add Community cards here
        if(Objects.equals(type, "Community Chest"))
        {
            this.cards.add("Doctor's fee! Pay $50.");
            this.cards.add("It is your birthday! Collect $10 from every player.");
            this.cards.add("Life insurance matures! Collect $100.");
        }
    }

    @Override
    public Action apply()
    {
        Random random = new Random();
        int choice = random.nextInt(3); //there's 16 of each card in monopoly
        System.out.println(this.type + ": " + this.cards.get(choice));

        if(Objects.equals(type, "Chance"))
            switch (choice)
            {
                case (0) -> {
                    return new MoveAction(player, -2);
                }
                case (1) -> {
                    return new PaymentAction(null, player, 50);
                }
                case (2) -> {
                    return new MoveAction(player,-1);
                }
                default -> {
                    return null;
                }
            }

        if(Objects.equals(type,"Community Chest"))
            switch (choice)
            {
                case (0) -> {
                    return new PaymentAction(player, null, 50);
                }
                case (1) -> {
                    return new CollectAction(Arrays.stream(Application.getPlayers()).toList(),player,10);
                }
                case (2) -> {
                    return new PaymentAction(null, player, 100);
                }
                default -> {
                    return null;
                }
            }
        return null;
    }
}
