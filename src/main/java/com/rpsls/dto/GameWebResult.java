package com.rpsls.dto;

import com.rpsls.dto.hand.HandCompareResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameWebResult {
  private LastResult lastResult;
  private GameResult result;

  public GameWebResult(GameResult result) {
    this.result = result;
    setLastResult(result);
  }

  public void setLastResult(String lastResult, String computerHand) {
    this.lastResult = new LastResult(lastResult, computerHand);
  }

  private void setLastResult(GameResult result) {
    this.lastResult = new LastResult();
    if (result != null) {
      this.result = result;
      if (result.getLastWinner() != null
          && result.getPlayers().get(0).getName().equals(result.getLastWinner().getName())) {
        lastResult.setResult(HandCompareResult.WIN.name());
      } else if (result.getLastWinner() == null) {
        lastResult.setResult(HandCompareResult.DRAW.name());
      } else {
        lastResult.setResult(HandCompareResult.LOSE.name());
      }
      lastResult.setComputerHand(result.getPlayers().get(1).draw().name());
    } else {
      lastResult.setResult("UNKNOWN");
    }
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private class LastResult {
    private String result;
    private String computerHand;
  }
}
