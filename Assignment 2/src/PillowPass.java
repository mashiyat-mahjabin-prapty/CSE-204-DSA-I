import java.util.Scanner;

public class PillowPass
{
    private static DLL<Integer> list = new DLL<>();
    private static DLL.Node<Integer> current = list.getHeader();
    private static boolean reverse = false;
    private static boolean won = false;
    private static int count = 0;
    private static DLL.Node<Integer> winner;
    private static int totalTime = 0;

    private static void updateTime (int t) //function to update the current player
    {
        if (!won)
        {
            while (totalTime+current.getElement() < t)
            {
                totalTime += current.getElement();
                if (!reverse)
                {
                    current = current.getNext();
                    if (current == list.getTrailer())
                    {
                        current = list.getHeader().getNext();
                    }

                }
                else
                {
                    current = current.getPrev();
                    if (current == list.getHeader())
                    {
                        current = list.getTrailer().getPrev();
                    }
                }
                //System.out.println("Current is " + current.getSerial());
            }
        }
    }

    public static void main(String[] args)
    {
        System.out.println("Welcome to the Pillow-passing game!");
        System.out.print("How many players? ");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        int N=0;
        while (N <= 0)
        {
            N = scanner.nextInt();
            if (N < 1)
            {
                System.out.println("Invalid number of players! Try again");
            }
        }
        for (int i = 0; i < N; i++)
        {
            count++;
            list.addBetween(scanner.nextInt(), count, current, current.getNext());
            current = current.getNext();
            //System.out.println(current.getElement());
        }
        current = list.first();
        //System.out.println(current.getElement());
        while (true)
        {
            int time;
            String command;
            time = scanner.nextInt();
            command = scanner.next();
            //System.out.println(time + " " + command);
            if (command.equalsIgnoreCase("F"))
            {
                //System.out.println("Inside F");
                if (won)
                {
                    System.out.println("Game over: Player " + winner.getSerial() + " wins");
                }
                else
                {
                    updateTime(time);
                    System.out.println("Game Over: Player " + current.getSerial() + " holding the pillow at t = " + time);
                    System.out.print("Pillow passing sequence: ");
                    DLL.Node<Integer> temp = current;
                    if (!reverse)
                    {
                        while (current != list.getTrailer())
                        {
                            System.out.print(current.getSerial() + " ");
                            current = current.getNext();
                        }
                        current = list.getHeader().getNext();
                        while (current != temp)
                        {
                            System.out.print(current.getSerial() + " ");
                            current = current.getNext();
                        }
                    }
                    else
                    {
                        while (current != list.getHeader())
                        {
                            //System.out.print("In first while: ");
                            System.out.print(current.getSerial() + " ");
                            current = current.getPrev();
                        }
                        current = list.getTrailer().getPrev();
                        while (current != temp)
                        {
                            //System.out.print("In second while: ");
                            System.out.print(current.getSerial() + " ");
                            current = current.getPrev();
                        }
                    }
                }
                break;
            }
            else if (command.equalsIgnoreCase("M"))
            {
                updateTime(time);
                totalTime = time;
                //System.out.println("Inside M");
                if (!won)
                {
                    DLL.Node<Integer> temp = null;
                    if (!reverse)
                    {
                        temp = current.getNext();
                        if (temp == list.getTrailer())
                        {
                            temp = list.getHeader().getNext();
                        }
                    }
                    else
                    {
                        temp = current.getPrev();
                        if (temp == list.getHeader())
                        {
                            temp = list.getTrailer().getPrev();
                        }
                    }

                    list.remove(current);
                    System.out.println("Player " + current.getSerial() + " has been eliminated at t = " + time);
                    current = temp;
                    if (current.getNext() == list.getTrailer() && current.getPrev() == list.getHeader())
                    {
                        won = true;
                        winner = current;
                    }
                    //endAt = time;
                }

            }
            else if (command.equalsIgnoreCase("R"))
            {
                updateTime(time);
                //System.out.println("Inside R");
                if (reverse) reverse = false;
                else reverse = true;
            }
            else if (command.equalsIgnoreCase("P"))
            {
                updateTime(time);
                //System.out.println("Inside P");
                if (!won)
                {
                    System.out.println("Player " + current.getSerial() + " is holding the pillow at t = " + time);
                }

            }
            else if (command.equalsIgnoreCase("I"))
            {
                updateTime(time);
                //System.out.println("Inside I");
                int t = scanner.nextInt();
                if (!won)
                {
                    if (reverse)
                    {
                        count++;
                        if (current.getNext() == list.getTrailer())
                        {
                            System.out.println("Previous " + list.getHeader().getElement() + " Next " + list.getHeader().getNext().getElement());
                            list.addBetween(t, count, list.getHeader(), list.getHeader().getNext());
                        }
                        else{
                            System.out.println("Previous " + current.getElement() + " Next " + current.getNext().getElement());
                            list.addBetween(t, count, current, current.getNext());
                        }
                    }
                    else
                    {
                        count++;
                        if (current.getPrev() == list.getHeader())
                        {
                            System.out.println("Previous " + list.getTrailer().getPrev().getElement() + " Next " + list.getTrailer().getElement());
                            list.addBetween(t, count, list.getTrailer().getPrev(), list.getTrailer());
                        }
                        else
                        {
                            System.out.println("Previous " + current.getPrev().getElement() + " Next " + current.getElement());
                            list.addBetween(t, count,  current.getPrev(), current);
                        }
                    }
                }
            }
        }
    }
}
