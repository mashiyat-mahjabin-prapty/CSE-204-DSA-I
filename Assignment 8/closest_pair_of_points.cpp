#include<iostream>
#include<cmath>
#include<limits>
#include "point.cpp"
using namespace std;

void merge(Point arr[], int start, int mid, int last, char coordinate)
{
    int a = mid-start+1;
    int b = last-mid;
    Point ll[a], rr[b];
    for (int i = 0; i < a; i++)
    {
        ll[i] = arr[start+i];
    }

    for (int i = 0; i < b; i++)
    {
        rr[i] = arr[mid+i+1];
    }

    int p = 0, q = 0, k = start;
    while(p < a && q < b)
    {
        if(coordinate == 'X'){
            if(ll[p].getX() <= rr[q].getX())
            {
                arr[k] = ll[p];
                p++;
            }
            else
            {
                arr[k] = rr[q];
                q++;
            }
            k++;
        }
        else {
            if(ll[p].getY() <= rr[q].getY())
            {
                arr[k] = ll[p];
                p++;
            }
            else
            {
                arr[k] = rr[q];
                q++;
            }
            k++;
        }
    }

    while(p < a)
    {
        arr[k] = ll[p];
        p++;
        k++;
    }

    while(q < b)
    {
        arr[k] = rr[q];
        q++;
        k++;
    }
}

void merge_sort(Point arr[], int start, int last, char coordinate)
{
    //cout << "In merge sort" << endl;
    if(start >= last) return;
    int mid = start + (last-start)/2;

    merge_sort(arr, start, mid, coordinate);
    merge_sort(arr, mid+1, last, coordinate);
    merge(arr, start, mid, last, coordinate);
}


float distance(Point a, Point b)
{
    return sqrt((a.getX()-b.getX())*(a.getX()-b.getX())+(a.getY()-b.getY())*(a.getY()-b.getY()));
}

pair_points minimum(pair_points a, pair_points b)
{
    float d1 = distance(a.p1, a.p2);
    float d2 = distance(b.p1, b.p2);

    return (d1>d2)?b:a;
}

pair_points baseCase(Point p[], int n)
{
    float min_dist = std::numeric_limits<float>::max();
    pair_points points;
    for(int i = 0; i < n; i++)
    {
        for(int j = i+1; j < n; j++)
        {
            if(distance(p[i], p[j]) < min_dist)
            {
                min_dist = distance(p[i], p[j]);
                points.p1 = p[i];
                points.p2 = p[j];
            }
        }
    }
    return points;
}

pair_points stripClosest(Point strip[], int n, pair_points d)
{
    float m = distance(d.p1, d.p2);
    pair_points q = d;

    for(int i = 0; i < n; i++)
    {
        for(int j = i+1; j < n && (strip[j].getY()-strip[i].getY()) < m; j++)
        {
            if(distance(strip[i], strip[j]) < m)
            {
                m = distance(strip[i], strip[j]);
                q.p1 = strip[i];
                q.p2 = strip[j];
            }
        }
    }
    return q;
}

pair_points closest(Point px[], Point py[], int n)
{
    if(n <= 3)
    {
        return baseCase(px, n);
    }

    int mid = n/2;
    Point mid_point = px[mid];

    Point py_left[mid];
    Point py_right[n-mid];
    int l = 0, r = 0;
    for(int i = 0; i < n; i++)
    {
        if(py[i].getX() <= mid_point.getX() && l < mid) py_left[l++] = py[i];
        else py_right[r++] = py[i];
    }

    pair_points left = closest(px, py_left, mid);
    pair_points right = closest(px+mid, py_right, n-mid);

    pair_points d = minimum(left, right);

    Point strip[n];
    int j = 0;

    for(int k = 0; k < n; k++)
    {
        if(abs(py[k].getX()-mid_point.getX()) < distance(d.p1, d.p2))
            strip[j++] = py[k];
    }

    pair_points strip_min = stripClosest(strip, j, d);

    return minimum(d, strip_min);
}

pair_points closestPoint(Point p[], int n)
{
    Point px[n], py[n];
    for(int i = 0; i < n; i++)
    {
        px[i] = p[i];
        py[i] = p[i];
    }
    merge_sort(px, 0, n-1, 'X');
    merge_sort(py, 0, n-1, 'Y');

    return closest(px, py, n);
}
