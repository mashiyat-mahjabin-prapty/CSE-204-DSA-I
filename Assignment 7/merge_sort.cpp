#include <iostream>
using namespace std;

const int N = 10e6+7;
int l[N], r[N];

void merge(int arr[], int start, int mid, int last)
{
    int a = mid-start+1;
    int b = last-mid;

    for (int i = 0; i < a; i++)
    {
        l[i] = arr[start+i];
    }

    for (int i = 0; i < b; i++)
    {
        r[i] = arr[mid+i+1];
    }

    int p = 0, q = 0, k = start;
    while(p < a && q < b)
    {
        if(l[p] <= r[q])
        {
            arr[k] = l[p];
            p++;
        }
        else
        {
            arr[k] = r[q];
            q++;
        }
        k++;
    }

    while(p < a)
    {
        arr[k] = l[p];
        p++;
        k++;
    }

    while(q < b)
    {
        arr[k] = r[q];
        q++;
        k++;
    }
}

void merge_sort(int arr[], int start, int last)
{
    //cout << "In merge sort" << endl;
    if(start >= last) return;
    int mid = start + (last-start)/2;

    merge_sort(arr, start, mid);
    merge_sort(arr, mid+1, last);
    merge(arr, start, mid, last);
}
