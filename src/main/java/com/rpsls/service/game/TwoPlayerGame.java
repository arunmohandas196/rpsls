/** */
package com.rpsls.service.game;

import com.rpsls.dto.player.Player;
import com.rpsls.dto.player.PlayerException;
import com.rpsls.exception.game.GameException;
import com.rpsls.service.game.reporter.ResultReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TwoPlayerGame implements Game {

  private static final int MAX_PLAYERS = 2;

  protected GameEngine gameEngine;

  @Autowired
  public TwoPlayerGame(GameEngine gameEngine) {
    this.gameEngine = gameEngine;
  }

  public void play(
      Player player1, Player player2, int numberOfRounds, ResultReporter resultReporter)
      throws GameException {
    play(Arrays.asList(player1, player2), numberOfRounds, resultReporter);
  }

  public void play(List<Player> players, int numberOfRounds, ResultReporter resultReporter)
      throws GameException {
    if (players.size() < MAX_PLAYERS) {
      throw new GameException("Not enough players to play");
    }

    for (Player p : players) {
      p.initialise();
    }

    for (int i = 1; i <= numberOfRounds; i++) {

      for (int j = 0, n = players.size(); j < n; j++) {
        try {
          players.get(j).choose();
        } catch (PlayerException e) {
          throw new GameException("Problem occurred running game", e);
        }
      }

      resultReporter.reportChoices(players);

      Player winner = gameEngine.determineWinner(players);
      resultReporter.report(winner);
    }
    resultReporter.summarizeGame();
  }
}
