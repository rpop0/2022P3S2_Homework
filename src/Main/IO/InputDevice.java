package Main.IO;

import java.util.Random;

public class InputDevice {

    public InputDevice()
    {

    }

    public int[] getNumbers(int N)
    {
        int arr[] = new int[N];
        Random temp = new Random();

        
        for (int i = 0; i < N; i++)
        {
            int t = temp.nextInt(101);
            
            while (t == 0)
            {
                t = temp.nextInt(101);
            }

            arr[i] = t;
        }
        return arr;
    }

    public String getType()
    {
        return "random";
    }

    public int nextInt()
    {
        Random n = new Random();
        int t = n.nextInt(101);

        while (t == 0)
        {
            t = n.nextInt(101);
        }

        return t;

    }

    public String getLine()
    {
        return "The quick brown fox jumps over the lazy dog";
    }

}
