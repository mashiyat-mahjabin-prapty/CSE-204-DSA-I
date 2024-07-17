#include<iostream>
#include<fstream>
#include<cstdio>
//#include "second_closest_pair.cpp"
#include "closest_pair_of_points.cpp"
using namespace std;

int main()
{
    string myText;
    ifstream MyReadFile("input.txt");
    int n, tempX, tempY;
    
    MyReadFile >> n;
    Point array[n];
    for (int i = 0; i < n; i++)
    {
        MyReadFile >> tempX >> tempY;
        array[i].setVal(i, tempX, tempY);
    }
    MyReadFile.close();
    
    pair_points closest = closestPoint(array, n);

    for (int i = 0; i < n; i++)
    {
        Point temp;
        if (array[i].getX() == closest.p1.getX() && array[i].getY() == closest.p1.getY())
        {
            temp = array[i];
            array[i] = array[n-1];
            array[n-1] = temp;
            break;
        }    
    }
    
    pair_points aa = closestPoint(array, n-1);
    for (int i = 0; i < n; i++)
    {
        Point temp;
        if (array[i].getX() == closest.p2.getX() && array[i].getY() == closest.p2.getY())
        {
            temp = array[i];
            array[i] = array[n-1];
            array[n-1] = temp;
            break;
        }    
    }
    pair_points bb = closestPoint(array, n-1);

    

    pair_points answer = minimum(aa, bb);
    cout << min(answer.p1.getIndex(), answer.p2.getIndex()) << " " << max(answer.p1.getIndex(), answer.p2.getIndex()) << endl;
    cout << distance(answer.p1, answer.p2) << endl;
}
