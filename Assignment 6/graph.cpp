#include <iostream>
#include <queue>
#include <fstream>
using namespace std;

class Graph
{
private:
    int **arr;
    int *visitedbfs;
    int *visiteddfs;
    int roads, cities, locations, friends, bfsTotal, dfsTotal, totalPieces;
    int *bfsPieces, *dfsPieces, *Friends, *bfsResult, *dfsResult;
    ofstream out;

    

public:
    Graph()
    {
        cout << "Creating a graph" << endl;
        totalPieces = 0;
        out.open("output.txt");
    }

    void input(int C, int R, int L, int F)
    {
        roads = R;
        cities = C;
        locations = L;
        friends = F;

        arr = new int*[cities];
        for (int i = 0; i < cities; i++)
        {
            arr[i] = new int[cities];
        }
        
        int i, j;
        for (i = 0; i < roads; i++)
        {
            cin >> i >> j;

            arr[i][j] = 1;
            arr[j][i] = 1;
        }

        cout << "All edges" << endl;
        bfsPieces = new int[cities];
        dfsPieces = new int[cities];
        Friends = new int[friends];

        for(int i = 0; i < cities; i++)
        {
            bfsPieces[i] = 0;
            dfsPieces[i] = 0;
        }

        int tempCity, tempPieces, tempFriend;
        for (int k = 0; k < locations; k++)
        {
            //cout << "In here" << endl;
            cin >> tempCity >> tempPieces;
            bfsPieces[tempCity] = tempPieces;
            dfsPieces[tempCity] = tempPieces;
            totalPieces += tempPieces;
        }

        cout << "All locations" <<endl;
        for (int k = 0; k < friends; k++)
        {
            cin >> tempCity >> tempFriend;
            Friends[tempFriend] = tempCity;
        }
        cout << "All friends" << endl;

        visitedbfs = new int[cities];
        visiteddfs = new int[cities];
        for (int i = 0; i < cities; i++)
        {
        	visitedbfs[i] = 0;
        	visiteddfs[i] = 0;
		}
    }

    int BFS(int root, int pieces[], int cityNo)
    {
        int collected = 0;
        queue<int> q;
        q.push(root);
        while (!q.empty())
        {
            root = q.front();
            q.pop();
            for (int i = 0; i < cityNo; i++)
            {
                if (arr[root][i] == 1)
                {
                    if (visitedbfs[i] == 0)
                    {
                        q.push(i);
                        visitedbfs[i] = 1;
                        if (pieces[i] != 0)
                        {
                            collected += pieces[i];
                            //cout << collected << endl;
                            pieces[i] = 0;
                        }
                    }
                }
            }
        }

        return collected;
    }

    int DFS(int root, int pieces[], int cityNo)
    {
        //cout << "In DFS" << endl;

        int collected = pieces[root];
        pieces[root] = 0;

        visiteddfs[root] = 1;

        for (int i = 0; i < cityNo; i++)
        {
            if (arr[root][i] == 1 && visiteddfs[i] == 0)
            {
                collected += DFS(i, pieces, cityNo);
            }
        }
        return collected;
    }

    void bfsOperation()
    {
        bfsTotal = 0;
        bfsResult = new int[friends];
        out << "Result of BFS" << endl;
        for (int i = 0; i < friends; i++)
        {
            int temp = BFS(Friends[i], bfsPieces, cities);
            bfsResult[i] = temp;
            bfsTotal += temp;
        }
        if (bfsTotal == totalPieces)
        {
            out << "Mission Accomplished" << endl;
        }
        else
        {
            out << "Mission Impossible" << endl;
        }
        out << bfsTotal << " out of " << totalPieces << " are collected" << endl;
        for (int i = 0; i < friends; i++)
        {
            out << i << " collected " << bfsResult[i] << " pieces" << endl;
        }
        out << endl;
    }

    void dfsOperation()
    {
        dfsTotal = 0;
        dfsResult = new int[friends];
        out << "Result of DFS" << endl;
        for (int i = 0; i < friends; i++)
        {
            int temp = DFS(Friends[i], dfsPieces, cities);
            dfsResult[i] = temp;
            dfsTotal += temp;
        }
        if (dfsTotal == totalPieces)
        {
            out << "Mission Accomplished" << endl;
        }
        else
        {
            out << "Mission Impossible" << endl;
        }
        out << dfsTotal << " out of " << totalPieces << " are collected" << endl;
        for (int i = 0; i < friends; i++)
        {
            out << i << " collected " << dfsResult[i] << " pieces" << endl;
        }
        out << endl;
    }
    
    ~Graph()
    {
    	delete bfsPieces;
		delete dfsPieces;
		delete Friends;
		delete bfsResult;
		delete dfsResult;
        for (int i = 0; i < cities; i++)
        {
            delete [] arr[i];
        }
        delete arr;
        delete visiteddfs;
        delete visitedbfs;
	}
};

int main ()
{
    Graph g1;
    int C, R, L, F;
    cin >> C >> R >> L >> F;
    g1.input(C, R, L, F);

    g1.bfsOperation();
    g1.dfsOperation();
}
