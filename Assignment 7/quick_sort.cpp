#include<iostream>
#include<cstdlib>
#include<ctime>
using namespace std;

int partition(int arr[], int start, int last)
{
    int pivot = arr[last];
    int i = start-1;

    for(int j = start; j < last; j++)
    {
        if(arr[j] <= pivot)
        {
            i++;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    int p = arr[i+1];
    arr[i+1] = arr[last];
    arr[last] = p;
    return i+1;
}

/*int randomized_partition(int arr[], int start, int end)
{
    srand(time(NULL));

    int random_position = start + rand() % (end-start);
    int temp = arr[end];
    arr[end] = arr[random_position];
    arr[random_position] = temp;

    return partition(arr, start, end);
}*/

void quick_sort(int arr[], int start, int last)
{
    //cout << "In quick sort" << endl;
    if(start < last)
    {
        int pivot = partition(arr, start, last);
        quick_sort(arr, start, pivot-1);
        quick_sort(arr, pivot+1, last);
    }
}
