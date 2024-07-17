#include<iostream>
using namespace std;

class Point{
private:
    int index;
    int x;
    int y;
public:
    Point()
    {
        x = 0;
        y = 0;
    }
    Point(int index, int x, int y)
    {
        this-> index = index;
        this->x = x;
        this->y = y;
    }
    int getX()
    {
        return x;
    }
    int getY()
    {
        return y;
    }
    int getIndex()
    {
        return index;
    }
    void setVal(int index, int x, int y)
    {
        this->index = index;
        this->x = x;
        this->y = y;
    }
    void print()
    {
		cout << this->x << " " << this->y << endl;
	}
};

struct pair_points{
    Point p1;
    Point p2;
};

struct pair_pair{
    pair_points first;
    pair_points second;
};