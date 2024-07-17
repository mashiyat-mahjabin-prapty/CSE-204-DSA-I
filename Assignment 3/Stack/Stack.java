import java.util.Scanner;

public class Stack <E> {
    private DLL<E> list;
    private int size = 0;
    private static boolean unary = false;
    private static boolean validity = true;
    public Stack ()
    {
        System.out.println("Creating a stack!");
        list = new DLL<>();
    }
    public int getSize ()
    {
        return list.getSize();
    }
    public void push (E element)
    {
        list.addLast(element);
    }
    public E top ()
    {
        return list.last().getElement();
    }
    public void pop ()
    {
        list.removeBack();
    }
    public boolean isEmpty ()
    {
        return list.isEmpty();
    }

    private int opPrecedence (char c)
    {
        if (c == '*' || c == '/')
            return  1;
        else
            return  0;
    }

    private static float operation (float a, float b, char c)
    {
        if (c == '+') return a+b;
        else if (c == '-') return a-b;
        else if (c == '*') return a*b;
        else
        {
            if (b != 0)
            {
                return a/b;
            }
            else
            {
                return Float.MAX_VALUE;
            }
        }
        //return a;
    }

    public static void start (String expression)
    {
        Stack<Character> ops = new Stack<>();
        Stack<Float> value = new Stack<>();
        for (int i = 0; i < expression.length(); i++)
        {
            if (expression.charAt(i) == '(')
            {
                ops.push(expression.charAt(i));
            }
            else if (expression.charAt(i) == '+' || expression.charAt(i) == '/' || expression.charAt(i) == '*' || expression.charAt(i) == '-')
            {
                if (expression.charAt(i) == '-')
                {
                    if (expression.charAt(i-1) == '(')
                    {
                        unary = true;
                        ops.push(expression.charAt(i));
                        continue;
                    }
                }
                char c = expression.charAt(i);
                while (!ops.isEmpty() && ops.top() != '(' && ops.opPrecedence(ops.top()) >= ops.opPrecedence(c))
                {
                    //System.out.println("Entered here");
                    char temp = ops.top();
                    float op1 = value.top();
                    value.pop();
                    float op2 = value.top();
                    value.pop();
                    float val = operation(op2, op1, temp);
                    ops.pop();
                    value.push(val);
                }
                ops.push(c);
            }
            else if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9')
            {
                int temp = expression.charAt(i) - '0';
                if (i > 0 && expression.charAt(i-1) >= '0' && expression.charAt(i-1) <= '9')
                {
                    if (value.top() < 0.0)
                    {
                        float p = value.top()*10 - temp;
                        value.pop();
                        value.push(p);
                    }
                    else
                    {
                        float p = value.top()*10 + temp;
                        value.pop();
                        value.push(p);
                    }
                }
                else
                {
                    value.push((float)temp);
                }
            }
            else if (expression.charAt(i) == ' ') {
                //System.out.println("Inside space");
                continue;
            }
            else if (expression.charAt(i) == ')')
            {
                //System.out.println("Here");
                if (ops.isEmpty()){
                    validity = false;
                    //System.out.println("Not valid");
                    break;
                }
                if (ops.top() == '(')
                {
                    ops.pop();
                }
                else if (unary)
                {
                    float temp = value.top();
                    value.pop();
                    value.push(-temp);
                    ops.pop();
                    unary = false;
                    if (ops.top() == '(')
                    {
                        ops.pop();
                    }
                }
                else
                {
                    float op1 = value.top();
                    value.pop();
                    float op2 = value.top();
                    value.pop();
                    float val = operation(op2, op1, ops.top());
                    ops.pop();
                    value.push(val);
                    if (ops.top() == '(')
                    {
                        ops.pop();
                    }
                }
            }
            else
            {
                System.out.println("Invalid input");
                validity = false;
                break;
            }
            //if (!ops.isEmpty()) System.out.print(ops.top() + " ");
            //if (!value.isEmpty()) System.out.println(value.top());
        }
        while (!ops.isEmpty())
        {
            //System.out.println("Here");
            if (ops.top() == '(')
            {
                validity = false;
                break;
            }
            float op1 = value.top();
            value.pop();
            float op2 = value.top();
            value.pop();
            float val = operation(op2, op1, ops.top());
            ops.pop();
            value.push(val);

        }
        if (value.isEmpty()) System.out.println("Invalid input");
        else if (!validity) System.out.println("Invalid expression");
        else
        {
            float result = value.top();
            value.pop();
            if (!ops.isEmpty() || !value.isEmpty())
            {
                System.out.println("Not Valid!");
                //System.out.println(ops.top() + " " + value.top());
            }
            else
            {
                System.out.println("Valid");
                System.out.println(result);
            }
        }

    }

    public static void main(String[] args)
    {
        String expression;
        Scanner scanner = new Scanner(System.in);
        expression = scanner.nextLine();

        Stack.start(expression);
    }
}


