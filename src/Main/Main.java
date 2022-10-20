package Main;


public class Main {
    public static void main(String[] args) {
        String[] layout = {"Start",".","Community Chest",".","Income tax",".",".","Chance",".",".","Prison",".",".",".",".",".",".","Community Chest",".",".",".",".","Chance",".",".",".",".",".",".",".","Go to prison",".",".","Community Chest",".",".","Chance",".","Super tax","."};
        InputDevice id = new InputDevice();
        OutputDevice od = new OutputDevice();
        Application monopoly = new Application(id,od);
        Board board = new Board(layout);

        int numberOfPlayers = Integer.parseInt(args[0]);
        int numberOfRounds = Integer.parseInt(args[1]);

        Player players[] = monopoly.getPlayers(numberOfPlayers);
        monopoly.playGame(numberOfRounds,numberOfPlayers,players);
        monopoly.decideWinner(numberOfPlayers,players);







    }
}