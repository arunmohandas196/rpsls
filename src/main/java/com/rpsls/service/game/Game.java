/** */
package com.rpsls.service.game;

import com.rpsls.dto.player.Player;
import com.rpsls.exception.game.GameException;
import com.rpsls.service.game.reporter.ResultReporter;

import java.util.List;

public interface Game {

  void play(List<Player> players, int numberOfRounds, ResultReporter resultReporter)
      throws GameException;
}
