public class Array {
    private int size;
    private String[] arr;
    private int num=0;

    public Array()
    {
        this.size = 50;
        this.arr = new String[50];
    }

    public Array(int n)
    {
        this.size = n;
        this.arr = new String[n];
    }

    public Array(String[] A)
    {
        this.size = A.length;
        this.arr = new String[size];
        //System.out.println("size: " + size);
        for (int i = 0; i < size; i++)
        {
            arr[i] = A[i];
        }
        num = size;
    }

    public Array getArray()
    {
        return this;
    }

    public String[] array()
    {
        return arr;
    }

    public String getAnElement(int i)
    {
        if (i < 0 || i >= num) return "Invalid index!\n";
        return arr[i];
    }

    public void add(String element)
    {
        //System.out.println(size + " " + num);
        if (size == num)
        {
            this.size = size+1;
            String[] temp = arr;
            arr = new String[size];
            for (int i = 0; i < size-1; i++)
            {
                arr[i] = temp[i];
                //System.out.println(arr[i]);
            }
            arr[size-1] = element;
            //System.out.println(arr[size-1]);
            num = size;
        }
        else
        {
            arr[num] = element;
            num++;
        }
    }

    public void add(int i, String element)
    {
        if (i < 0 || i > num)
        {
            System.out.println("Invalid index");
            return;
        }
        //System.out.println(size + " " + num);
        if (size == num)
        {
            int j;
            this.size = size+1;
            String[] temp = arr;
            arr = new String[size];
            for (j = 0; j < i; j++)
            {
                arr[j] = temp[j];
                System.out.println(arr[j]);
            }
            arr[j] = element;
            System.out.println(arr[j]);
            for (int k = i+1; k < size; k++)
            {
                arr[k] = temp[k-1];
                System.out.println(arr[k]);
            }
            num = size;
        }
        else
        {
            int j;
            String temp = arr[i];
            arr[i] = element;
            for (j = num+1; j > i+1; j--)
            {
                arr[j] = arr[j-1];
            }
            arr[j] = temp;
            num++;
        }
    }

    public void remove(String element)
    {
        for (int i = 0; i < num; i++)
        {
            if (arr[i].equalsIgnoreCase(element))
            {
                for (int j = i; j < num-1; j++)
                {
                    arr[j] = arr[j+1];
                }
                num--;
            }
        }
    }

    public int[] findIndex (String element)
    {
        int[] temp = new int[100];   //vector is better
        for (int i = 0; i < 100; i++) temp[i] = -1;
        for (int i = 0, j = 0; i < num; i++)
        {
            if (arr[i].equalsIgnoreCase(element))
            {
                temp[j] = i;
                j++;
            }
        }
        return temp;
    }

    public Array subArray (int start, int end)
    {
        int len = end-start+1;
        Array temp = new Array(len);
        if (end < start || end >= num)
        {
            temp.array()[0] = "Index out of bound!";
            return temp;
        }
        for (int i = 0; i < len; i++)
        {
            temp.arr[i] = arr[start+i];
            //System.out.println(temp.arr[i]);
        }
        temp.num = len;
        return temp;
    }

    public int length ()
    {
        return num;
    }

    public boolean isEmpty ()
    {
        return arr[0].isEmpty();
    }

    public void merge (Array a, Array b)
    {
        int i=0, j=0, k=0;
        while (j < a.length() && k < b.length())
        {
            if (a.array()[j].compareTo(b.array()[k]) <= 0)
            {
                arr[i++] = a.array()[j++];
                num++;
            }
            else if (a.array()[j].compareTo(b.array()[k]) > 0)
            {
                arr[i++] = b.array()[k++];
                num++;
            }
        }
        if (j < a.length())
        {
            arr[i++] = a.array()[j++];
            num++;
        }
        if (k < b.length())
        {
            arr[i++] = b.array()[k++];
            num++;
        }
    }

    public void print()
    {
        for (int i = 0; i < num; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        String[] st = {"Data", "Structures", "And"};
        Array arr;
        Array a = new Array(st);
        a.print();
        arr = a.getArray();
        arr.print();
        a.add("One");
        a.add(3, "Algorithms");
        a.print();
        Array b = new Array();
        b.add("p");
        b.print();
        b.add("h");
        b.print();
        b.add(1, "s");
        b.print();
        int[] abb = arr.findIndex("Structures");
        for (int i = 0; i < abb.length && abb[i] != -1; i++) System.out.println(abb[i]);
        a = arr.subArray(1, 3);
        a.print();
        arr.remove("And");
        arr.print();
        System.out.println(arr.isEmpty());
        String[] s1 = {"a", "b", "d"};
        String[] s2 = {"b", "c", "g"};
        Array a1 = new Array(s1);
        Array a2 = new Array(s2);
        Array merged = new Array(a1.length()+a2.length());
        merged.merge(a1, a2);
        merged.print();
        System.out.println(merged.getAnElement(2));
        merged.add(10, "v");
    }
}
