#include<iostream>
#include "heap.h"
using namespace std;

int main()
{
    Heap h(5);
    vector <int> v;
    v.push_back(12);
    v.push_back(5);
    v.push_back(7);
    v.push_back(34);
    v.push_back(56);
    v.push_back(2);
    h.insert(12);
    h.insert(5);
    h.insert(7);
    h.insert(34);
    h.insert(9);
    cout << "All element inserted" << endl;

    //h.printHeap();
    heapsort(v);

    cout<<v[0]<< " " << v[v.size()-1] << endl;
}