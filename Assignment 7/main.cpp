#include <iostream>
#include <ctime>
#include <chrono>
#include <random>
#include <fstream>
#include <cstdlib>
#include "merge_sort.cpp"
#include "quick_sort.cpp"
using namespace std;

const int MINIMUM = -10000;
const int MAXIMUM = 10000;
//const int N= 10e5+7;
int arr_merge[N], arr_quick[N];

void random_num(int n)
{
	ofstream random("random.txt");
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> distr(MINIMUM, MAXIMUM);
    int temp;
    for (int i = 0; i < n; i++)
    {
        temp = distr(gen);
        arr_merge[i] = temp;
        arr_quick[i] = temp;
        random << arr_merge[i] << "		" <<  arr_quick[i] << endl;
    }
}

void ascending_num(int n)
{
    ofstream ascending("ascending.txt");
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> distr(MINIMUM, MAXIMUM);
    int temp;
    temp = distr(gen);
    arr_merge[0] = temp;
    arr_quick[0] = temp;
    srand(time(NULL));
    for (int i = 1; i < n; i++)
    {
        temp = rand() % 10;
        temp = temp+arr_merge[i-1];
        arr_merge[i] = temp;
        arr_quick[i] = temp;
        ascending << arr_merge[i] << "		" <<  arr_quick[i] << endl;
    }
}

void descending_num(int n)
{
    ofstream descending("descending.txt");
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> distr(MINIMUM, MAXIMUM);
    int temp;
    temp = distr(gen);
    arr_merge[0] = temp;
    arr_quick[0] = temp;
    srand(time(NULL));
    for (int i = 1; i < n; i++)
    {
        temp = rand() % 10;
        temp = arr_merge[i-1]-temp;
        arr_merge[i] = temp;
        arr_quick[i] = temp;
        descending << arr_merge[i] << "		" <<  arr_quick[i] << endl;
    }
}

void printArray(int arr1[], int arr2[], int size, ofstream& out)
{
    out << "Sorted Arrays:" << endl;
    out << "Merge Sort             Quick Sort" << endl;
    for (int i = 0; i < size; i++)
    {
        out << arr1[i] << "                 " << arr2[i] << endl;
    }
    out << endl;
}


int main()
{
    ofstream out("output.txt");
    int n, order;
    while(true)
    {
        cout << "How many numbers do you want to sort?(Enter 0 to stop) ";
        cin >> n;
        if(n == 0)
        {
            break;
        }
        cout << "Choose the order of the numbers:\n1. Ascending\n2. Descending\n3. Random\n";
        cin >> order;
        switch(order){
            case 1:
                ascending_num(n);
                break;
            case 2:
                descending_num(n);
                break;
            case 3:
                random_num(n);
                break;
            default:
                cout << "Invalid input!" << endl;
        }


        cout << "Value of n: " << n << " in order " << order << endl;

        auto merge_start = chrono::high_resolution_clock::now();
        merge_sort(arr_merge, 0, n-1);
        auto merge_end = chrono::high_resolution_clock::now();
        long double time_merge = chrono::duration_cast<chrono::microseconds>(merge_end- merge_start).count();


        auto quick_start = chrono::high_resolution_clock::now();
        quick_sort(arr_quick, 0, n-1);
        auto quick_end = chrono::high_resolution_clock::now();
        long double time_quick = chrono::duration_cast<chrono::microseconds>(quick_end- quick_start).count();

        printArray(arr_merge, arr_quick, n, out);

        cout << "Time Required for each sort:" << endl;
        cout << "Merge sort         Quick Sort" << endl;
        cout << (float)time_merge/CLOCKS_PER_SEC << "           		"<< (float)time_quick/CLOCKS_PER_SEC << endl;  
    }
    cout << "Thank you!" << endl;
}
