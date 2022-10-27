package Monopoly;

public class Board {
    
    private Square []_places = new Square[40];
    public Board()
    {
        for (int i = 0; i < 40; i++)
        {
            if 
            (i == 1 || i == 3 || i == 5 || i == 6 || i == 8 || i == 9 || i == 11
                || i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 18 || i == 19
                || i == 20 || i == 21 || i == 23 || i == 24 || i == 25 || i == 26 || i == 27 
                || i == 28 || i == 29 || i == 31 || i == 32 || i == 34 || i == 35 || i == 37 
                || i == 39)
            {
                this._places[i] = new Property("Property");
            }
            else
            {
                this._places[i] = new Square("Normal");
            }
        }
    }

    public Square []getBoard()
    {
        return this._places;
    }

}
