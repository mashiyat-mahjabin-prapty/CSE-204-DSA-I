#include<iostream>
#include<cstring>
#include<fstream>
using namespace std;
const long long N = 10e8+7;

long long ways(long long f[], long long num_dice, long long sum)
{
	if(num_dice == 0 && sum == 0) return 1;
	else if(num_dice==0 || sum == 0) return 0;
	else if(sum < 0) return 0;
    long long cumsum[num_dice+1][sum+1];
    memset(cumsum, 0, sizeof(cumsum));
    cumsum[0][0] = 1;

    for(int i = 1; i <= num_dice; i++)
    {
        for (int j = 1; j <= sum; j++)
        {
            cumsum[i][j] = cumsum[i][j-1] + cumsum[i-1][j-1] + N;

            if (j - f[i-1] - 1 >= 0)
            {
                cumsum[i][j] -= cumsum[i-1][j-f[i-1]-1];
            }
            cumsum[i][j] = cumsum[i][j]%N;
        }
    }
    return cumsum[num_dice][sum]; 
}

int main()
{
    ifstream input("input.txt");
    long long n, sum;
    input >> n >> sum;
    long long faces[n];

    for (int i = 0; i < n; i++)
    {
        input >> faces[i];
    }
    cout << ways(faces, n, sum) << endl;
}
