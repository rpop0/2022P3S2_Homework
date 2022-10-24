package Main;

import java.util.Random;

public class Board {
    private Square[] layout;

    public Board(){

        this.layout = new Square[40];
        for(int i = 0; i < 40; i++) {
            if (i % 2 == 0)
                this.layout[i] = new Property("DummyName", 50, 10);
            else
                this.layout[i] = new Nothing();
        }
    }

    public Square getLayout(int pos)
    {
        return this.layout[pos];
    }

}

