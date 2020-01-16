package com.rpsls.service.game;

import com.rpsls.dto.GameResult;
import com.rpsls.dto.hand.Hand;
import com.rpsls.dto.player.Player;
import com.rpsls.dto.player.UniversalHumanPlayer;
import com.rpsls.exception.game.GameException;
import com.rpsls.service.game.reporter.ResultReporter;
import com.rpsls.service.hand.HandService;
import com.rpsls.service.hand.HandServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TwoPlayerGameTest {
  UniversalHumanPlayer player1;
  UniversalHumanPlayer player2;
  GameResult gameResult;
  ResultReporter reporter;
  TwoPlayerGame twoPlayerGame;
  GameEngine gameEngine;
  HandService handService;
  List<Player> players;

  @BeforeEach
  public void init() {
    player1 = new UniversalHumanPlayer("Player1");
    player2 = new UniversalHumanPlayer("Player1");
    players = Arrays.asList(player1, player2);
    gameResult = new GameResult(Arrays.asList(player1, player2));
    reporter = new ResultReporter(gameResult);
    handService = new HandServiceImpl();
    gameEngine = new GameEngine(handService);
    twoPlayerGame = new TwoPlayerGame(gameEngine);
  }

  @Test
  public void shouldRunValidRockPaperScissorGame() throws GameException {
    player1.setChoice(Hand.ROCK.name());
    player2.setChoice(Hand.PAPER.name());
    twoPlayerGame.play(players, 1,reporter);
    Assertions.assertEquals(player1.getName(), gameResult.getLastWinner().getName());

    player1.setChoice(Hand.ROCK.name());
    player2.setChoice(Hand.ROCK.name());
    twoPlayerGame.play(players, 1,reporter);
    Assertions.assertNull(gameResult.getLastWinner());
  }
}
