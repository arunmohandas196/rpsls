package com.rpsls.controller;

import com.rpsls.dto.GameRequest;
import com.rpsls.dto.GameWebResult;
import com.rpsls.exception.game.GameException;
import com.rpsls.service.GameRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

  @Autowired private GameRestService gameRestService;

  @PostMapping(
      value = "",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public GameWebResult gameAgainstComputer(@RequestBody GameRequest request) throws GameException {

    return gameRestService.playAgainstComputer(request);
  }
}
