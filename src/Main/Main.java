package Main;

import Main.Exceptions.MonopolyException;
import Main.Exceptions.WrongLoadException;
import Main.IO.InputDevice;
import Main.IO.OutputDevice;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String []args)
    {
        InputDevice id = new InputDevice();
        OutputDevice od = new OutputDevice();

        Application game = new Application(id, od);

        int noOfPlayers = Integer.parseInt(args[0]);
        int noOfRounds = Integer.parseInt(args[1]);

        boolean wait = false;
        String loader = "N";

        while(!wait)
        {
            try {
                Scanner scanner = new Scanner(System.in);
                loader = scanner.nextLine();
                if(!Objects.equals(loader, "Y") &!Objects.equals(loader, "N"))
                    throw new WrongLoadException();
                else
                    wait = true;
            }catch(WrongLoadException e)
            {
                System.out.println("Invalid option, valid load options are \"Y\" for yes and \"N\" for no.\n");
            }
        }


        System.out.println(loader);
        Player []players;
        if(loader.equals("N"))
            players = game.createPlayers(noOfPlayers);
        else
            players = game.Load(noOfPlayers);
        System.out.println(players[1].getName());
        game.playGame(noOfRounds, noOfPlayers, players);
        game.decideWinner(noOfPlayers, players);


    }
}
