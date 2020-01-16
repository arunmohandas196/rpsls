/** */
package com.rpsls.service.game;

import com.rpsls.dto.hand.Hand;
import com.rpsls.dto.hand.HandCompareResult;
import com.rpsls.dto.player.Player;
import com.rpsls.service.hand.HandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameEngine {

  private static final int NUMBER_OF_PLAYERS = 2;

  HandService handService;

  @Autowired
  public GameEngine(HandService handService) {
    this.handService = handService;
  }

  public Player determineWinner(List<Player> players) {
    if (players == null || players.size() != NUMBER_OF_PLAYERS) {
      throw new IllegalArgumentException("You need to have two players to use this engine.");
    }

    Hand player1Hand = players.get(0).draw();
    Hand player2Hand = players.get(1).draw();

    HandCompareResult result = handService.beats(player1Hand, player2Hand);

    switch (result) {
      case WIN:
        return players.get(0);
      case LOSE:
        return players.get(1);
      default:
        return null;
    }
  }
}
