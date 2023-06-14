#include <iostream>
using namespace std;

int main() {
	
	int T;
	cin >> T;

	int N, currShell, rightGuesses, score;
	while (T--)
	{
		cin >> N;
	
		int a[100], b[100], g[100];
		for (int i = 0; i < N; i++)
		{
			cin >> a[i] >> b[i] >> g[i];
		}
		
		score = 0;
		
		for (int i = 1; i < 4; i++)
		{
			currShell = i, rightGuesses = 0;
			for (int j = 0; j < N; j++)
			{
				if (a[j] == currShell) currShell = b[j];
				else if (b[j] == currShell) currShell = a[j];

				if (g[j] == currShell) rightGuesses++;
			}
			score = max(score, rightGuesses);
		}
		cout << score << endl;
	}
	return 0;
}