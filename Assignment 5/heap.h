#include<iostream>
#include<vector>
using namespace std;

class Heap
{
	private:
		int* array;
		long long currentIndex=1;
		long long int heapsize;

	public:	

		Heap (long long sz)
		{
			heapsize = sz+1;
			//cout << "Heap created from constructor with size " << heapsize << endl;
			array = new int[heapsize];	
		}

		Heap (vector<int> &v)
		{
			heapsize = v.size()+1;
			array = new int[heapsize];
			//cout << heapsize << " " << v.size() << endl;
			for (int i = 1; i <= v.size(); i++)
			{
				array[i] = v[i-1];
			}
			//printHeap();	
		}
	
		long long int size ()
		{
			return heapsize;
		}
		void setsize (long long sz)
		{
			heapsize = sz;
		}
		int getParent (long long index)
		{
			return index/2;
		}
		
		int getLeft (long long index)
		{
			return 2*index;
		}
		
		int getRight (long long index)
		{
			return 2*index+1;
		}
		void heapify (int* a, int n, long long index)
		{
			//cout << "In heapify" << endl;
			int left = getLeft(index);
			int right = getRight(index);
			int smaller;
			
			if (left < n && a[left] < a[index])    //compares with left child
			{                                                  
				smaller = left;	
			}
			else
			{
				smaller = index;
			}
					
			if (right < n && a[right] < a[smaller])      //compares the larger with right child 
			{
				smaller = right;
			}
			//cout << left << " " << right << " " << largest << endl;
			
			if (smaller != index)     //if largest=index, then the element is already in the right place
			{
				int temp = array[smaller];
				array[smaller] = array[index];
				array[index] = temp;
				
				heapify (a, n, smaller);
			}
			//cout << a[index] << endl;
		}
		
		void insert (int num)
		{
			array[currentIndex] = num;
			long long i = currentIndex;
			while (i > 1 && array[getParent(i)] > array[i])
			{
				int temp = array[i];
				array[i] = array[getParent(i)];
				array[getParent(i)] = temp;
				i = getParent(i);
			}
			
			currentIndex++;
		}
		
		int getMax ()
		{
			return array[1];
		}
		
		void deleteKey()
		{
			if (size() < 1)
			{
				cout << "Error in size" << endl;
				return;
			}
			cout << "Deleting element: " << array[1] << endl;
			array[1] = array[size()-1];
			setsize(size()-1);
			heapify (array, size(), 1);
		}
		
		void printHeap()
		{
			for (int i = 1; i < size(); i++)
			{
				cout << array[i] << endl;
			}
			
		}

		void buildMinHeap ()
		{
			//printHeap();
			for (int i = (heapsize/2); i > 0; i--)
			{
				heapify(array, size(), i);
			}	
			//printHeap();
		}

		void interchange(int index)
		{
			int temp = array[index];
			array[index] = array[1];
			array[1] = temp;
			heapify(array, index, 1);
			setsize(size()-1);
		}

		void arrayCopy (vector<int> &vec, int index)
		{
			vec[index] = array[index+1];
			
		}

		~Heap()
		{
			delete[] array;
		}		 
};



void heapsort(vector< int > &v)
{
	Heap h(v);
	h.buildMinHeap();
	
	for (int i = v.size()-1; i >= 0; i--)
	{
		v[v.size()-1-i] = h.getMax();
		h.interchange(i+1);
	}
	
	cout << "Heapsort successful!" << endl;
}