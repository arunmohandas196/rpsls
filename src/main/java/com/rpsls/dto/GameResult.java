package com.rpsls.dto;

import com.rpsls.dto.player.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class GameResult {
  private Long totalGames = 0L;
  private List<String> summary = new ArrayList<>();
  private List<String> changeLog = new ArrayList<>();

  @JsonIgnore private Map<String, Long> mapOfPlayerVictories = new HashMap<>();
  @JsonIgnore private Player lastWinner;
  @JsonIgnore private List<Player> players;

  public GameResult(List<Player> players) {
    this.players = players;
  }
}
