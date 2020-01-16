package com.rpsls.dto.hand;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Hand {
  PAPER,
  ROCK,
  SCISSORS,
  LIZARD,
  SPOCK;

  static {
    ROCK.beatableHands = new HashSet<>(Arrays.asList(SCISSORS, LIZARD));
    PAPER.beatableHands = new HashSet<>(Arrays.asList(SCISSORS, SPOCK));
    SCISSORS.beatableHands = new HashSet<>(Arrays.asList(ROCK, LIZARD));
    LIZARD.beatableHands = new HashSet<>(Arrays.asList(SPOCK, PAPER));
    SPOCK.beatableHands = new HashSet<>(Arrays.asList(ROCK, SCISSORS));
  }

  private String name;
  private Set<Hand> beatableHands;

  public Set<Hand> getBeatableHands() {
    return beatableHands;
  }
}
