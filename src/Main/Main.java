package Monopoly;

public class Main {

    public static void main(String []args)
    {
        InputDevice id = new InputDevice();
        OutputDevice od = new OutputDevice();

        Application game = new Application(id, od);

        int noOfPlayers = Integer.parseInt(args[0]);
        int noOfRounds = Integer.parseInt(args[1]);

        Player []players = game.getPlayers(noOfPlayers);
        game.playGame(noOfRounds, noOfPlayers, players);
        game.decideWinner(noOfPlayers, players);

    }
}
