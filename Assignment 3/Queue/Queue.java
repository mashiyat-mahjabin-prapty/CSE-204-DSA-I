import java.util.Scanner;

public class Queue <E> {
    private DLL<E> list;
    public Queue ()
    {
        list = new DLL<>();
        System.out.println("Creating a Queue!");
    }
    public int getsize ()
    {
        return list.getSize();
    }
    public void enqueue (E element)
    {
        list.addLast (element);
    }
    void dequeue ()
    {
        list.removeFront();
    }
    boolean isEmpty ()
    {
        return list.isEmpty();
    }
    E front ()
    {
        return list.first().getElement();
    }

    public static void main(String[] args) {
        String String_old;
        char[] String_new = new char[20000];
        int count[] = new int[26];
        for (int i = 0; i < 26; i++) count[i] = 0;
        Queue<Character> q = new Queue<>();
        Scanner scanner = new Scanner(System.in);
        String_old = scanner.next();

        for (int i = 0, j = 0; i < String_old.length(); i++) {
            char c = String_old.charAt(i);
            count[c - 'a']++;

            if (q.isEmpty()) {
                if (count[c - 'a'] == 1) {
                    q.enqueue(c);
                    String_new[j++] = q.front();
                } else {
                    String_new[j++] = '#';
                }
            } else {
                if (q.front() == c) {
                    q.dequeue();
                    if (count[c - 'a'] == 1) {
                        q.enqueue(c);
                    }
                    if (q.isEmpty()) {
                        String_new[j++] = '#';
                    } else {
                        while (!q.isEmpty()) {
                            if (count[q.front() - 'a'] >= 2) {
                                q.dequeue();
                            }
                            if (count[q.front()-'a'] < 2) break;
                        }
                        if (!q.isEmpty()) {
                            String_new[j++] = q.front();
                        } else {
                            String_new[j++] = '#';
                        }
                    }
                } else {
                    if (count[c - 'a'] == 1) {
                        q.enqueue(c);
                    }
                    String_new[j++] = q.front();
                }
            }
        }
        System.out.println(String_new);
    }
}




