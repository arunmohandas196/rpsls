package com.rpsls.service;

import com.rpsls.dto.GameResult;
import com.rpsls.dto.player.CommandLineHumanPlayer;
import com.rpsls.dto.player.ComputerPlayer;
import com.rpsls.dto.player.Player;
import com.rpsls.exception.game.GameException;
import com.rpsls.service.game.TwoPlayerGame;
import com.rpsls.service.game.reporter.ConsoleResultReporter;
import com.rpsls.service.game.reporter.ResultReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(value = "rps.console.enabled", havingValue = "true", matchIfMissing = true)
public class GameConsoleService implements CommandLineRunner {
  int MAX_PLAYERS = 2;
  @Autowired TwoPlayerGame twoPlayerGame;

  private static Player getPlayer(int playerNumber) throws IOException {
    System.out.println("Please choose your type for player " + playerNumber + ":");
    System.out.println("1: Computer, 2: Human, 0: exit");
    Player player = null;
    while (player == null) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      String input = reader.readLine();
      int val = -1;
      try {
        val = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.err.println("Not a valid choice, try again.");
        continue;
      }

      if (val == 0) {
        System.exit(-1);
      }

      if (val == 1) {
        player = new ComputerPlayer("Player" + playerNumber);
      } else if (val == 2) {
        player = new CommandLineHumanPlayer();
      } else {
        System.err.println("Not a valid choice, try again.");
        continue;
      }
    }

    return player;
  }

  private static int getRounds() throws IOException {
    System.out.println("Please choose the number of rounds to play.");

    int rounds = -1;
    while (rounds == -1) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      String input = reader.readLine();
      try {
        rounds = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.err.println("Not a valid choice, try again.");
        continue;
      }
    }

    return rounds;
  }

  @Override
  public void run(String... args) throws Exception {

    System.out.println("Welcome to the Rock, Paper, Scissors, Lizard, Spock game");
    System.out.println("You can either human vs computer or computer v computer.");

    Player player1 = getPlayer(1);
    Player player2 = null;
    if (player1 instanceof CommandLineHumanPlayer) {
      System.out.println("Player one is a human, assigning Player 2 as computer");
      player2 = new ComputerPlayer("Player2");
    } else {
      player2 = getPlayer(2);
    }
    List<Player> players = new ArrayList<>();
    addPlayer(players, player1);
    addPlayer(players, player2);

    System.out.println("Choose how many rounds to play");
    int rounds = getRounds();
    ResultReporter reporter = new ConsoleResultReporter(new GameResult(players));
    twoPlayerGame.play(players, rounds, reporter);
  }

  private void addPlayer(List<Player> players, Player player) throws GameException {
    if (player == null) {
      throw new IllegalArgumentException();
    }
    if (players.size() == MAX_PLAYERS) {
      throw new GameException("Game is full. No new players allowed.");
    }

    if (!players.contains(player)) {
      players.add(player);
    }
  }
}
