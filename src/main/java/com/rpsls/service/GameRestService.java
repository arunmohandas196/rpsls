package com.rpsls.service;

import com.rpsls.dto.GameRequest;
import com.rpsls.dto.GameResult;
import com.rpsls.dto.GameWebResult;
import com.rpsls.dto.player.ComputerPlayer;
import com.rpsls.dto.player.Player;
import com.rpsls.dto.player.UniversalHumanPlayer;
import com.rpsls.service.game.TwoPlayerGame;
import com.rpsls.service.game.reporter.ResultReporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class GameRestService {
  TwoPlayerGame twoPlayerGame;
  private int rounds;
  private UniversalHumanPlayer player1;
  private Player computerPlayer;
  private GameResult gameResult;
  private ResultReporter reporter;

  @Autowired
  public GameRestService(TwoPlayerGame twoPlayerGame) {
    this.twoPlayerGame = twoPlayerGame;
    /* TODO Currently all request are assigned to a single player and a single gameResult. To be made more generic*/
    this.player1 = new UniversalHumanPlayer("HumanPlayer");
    this.computerPlayer = new ComputerPlayer("ComputerHand");
    this.gameResult = new GameResult(Arrays.asList(player1, computerPlayer));
    this.reporter = new ResultReporter(gameResult);
    this.rounds = 1;
  }

  public GameWebResult playAgainstComputer(GameRequest request) {
    GameWebResult webResult;
    try {
      player1.setChoice(request.getHand());
      twoPlayerGame.play(player1, computerPlayer, rounds, reporter);
      webResult = new GameWebResult(gameResult);
    } catch (Exception ex) { // TODO: Need to improve this and make it more generic
      webResult = new GameWebResult();
      webResult.setLastResult("UNKNOWN", null);
    }

    return webResult;
  }
}
