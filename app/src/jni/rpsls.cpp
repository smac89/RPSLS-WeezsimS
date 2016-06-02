//
// Created by casablanca on 02/06/16.
//

#include <iostream>
#include "rpsls.h"
using namespace std;

int main() {
    // Declaring integers
    string p1;
    string p2;

    // Declaring Moves
    while (true) {
        cout << "Enter moves for player 1?" << endl;
        cin >> p1;

        cout << "Enter move for Player 2?" << endl;
        cin >> p2;

        cout << rps(p1, p2);
    }

  return 0;
}

inline string rps(string p1, string p2) {
    if (p1 == "scissors" && (p2 == "paper" || p2 == "lizard")) {
        return "Player 1 wins the game";
    } else if (p1 == "paper" && (p2 == "rock" || p2 == "spock")) {
        return "Player 1 wins the game";
    } else if (p1 == "rock" && (p2 == "lizard" || p2 == "scissors")) {
        return "Player 1 wins the game";
    } else if (p1 == "lizard" && (p2 == "spock" || p2 == "paper")) {
        return "Player 1 wins the game";
    } else if (p1 == "spock" && (p2 == "scissors" || p2 == "rock")) {
        return "Player 1 wins the game";
    } else if (p2 == "scissors" && (p1 == "paper" || "lizard")) {
        return "Player 2 wins the game";
    } else if (p2 == "paper" && (p1 == "rock" || p1 == "spock")) {
        return "Player 2 wins the game";
    } else if (p2 == "rock" && (p1 == "lizard" || p1 == "scissors")) {
        return "Player 2 wins the game";
    } else if (p2 == "lizard" && (p1 == "spock" || p1 == "paper")) {
        return "Player 2 wins the game";
    } else if (p2 == "spock" && (p1 == "scissors" || p1 == "rock")) {
        return "Player 2 wins the game";
    } else if (p2 == p1) {
        return "it's a tie";
    }
}
