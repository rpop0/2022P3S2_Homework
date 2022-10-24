package Main;


public class Main {
    public static void main(String[] args) {
        InputDevice id = new InputDevice();
        OutputDevice od = new OutputDevice();
        Application monopoly = new Application(id,od);
        Board board = new Board();

        int numberOfPlayers = Integer.parseInt(args[0]);
        int numberOfRounds = Integer.parseInt(args[1]);

        Player players[] = monopoly.getPlayers(numberOfPlayers);
        monopoly.playGame(board,numberOfRounds,numberOfPlayers,players);
        monopoly.decideWinner(board,numberOfPlayers,players);







    }
}