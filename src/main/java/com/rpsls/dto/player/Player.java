package com.rpsls.dto.player;

import com.rpsls.dto.hand.Hand;

public interface Player {

  void initialise();

  void choose() throws PlayerException;

  Hand draw();

  String getName();
}
