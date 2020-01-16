package com.rpsls.service.game.reporter;

import com.rpsls.dto.GameResult;
import com.rpsls.dto.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class ResultReporter {

  protected GameResult gameResult;

  public ResultReporter(GameResult gameResult) {
    this.gameResult = gameResult;
  }

  public void report(Player winner) {
    this.gameResult.setTotalGames(this.gameResult.getTotalGames() + 1);
    gameResult.setLastWinner(winner);
    if (winner == null) {
      String draw = "Round " + this.gameResult.getTotalGames() + " was a draw.";
      gameResult.getChangeLog().add(draw);
    } else {
      gameResult
          .getChangeLog()
          .add(
              "Round "
                  + this.gameResult.getTotalGames()
                  + " was won by player "
                  + winner.getName());
      if (gameResult.getMapOfPlayerVictories().containsKey(winner.getName())) {
        gameResult
            .getMapOfPlayerVictories()
            .put(winner.getName(), gameResult.getMapOfPlayerVictories().get(winner.getName()) + 1);
      } else {
        gameResult.getMapOfPlayerVictories().put(winner.getName(), 1L);
      }
    }
  }

  public void summarizeGame() {
    List<String> playerSummary = new ArrayList<>();
    for (Entry<String, Long> e : gameResult.getMapOfPlayerVictories().entrySet()) {
      playerSummary.add("Player " + e.getKey() + " won " + e.getValue() + " times");
    }
    gameResult.setSummary(playerSummary);
  }

  public void reportChoices(List<Player> players) {
    for (Player p : players) {
      gameResult.getChangeLog().add("Player " + p.getName() + " chose " + p.draw().name());
    }
  }
}
