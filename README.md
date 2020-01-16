# Rock, Paper, Scissors, Lizard, Spock
Implementation of Rock, Paper, Scissors, Lizard, Spock
* Scissors cuts Paper
* Paper covers Rock
* Rock crushes Lizard
* Lizard poisons Spock
* Spock smashes Scissors
* Scissors decapitates Lizard
* Lizard eats Paper
* Paper disproves Spock
* Spock vaporizes Rock
* (and as it always has) Rock crushes Scissors`


### How to run 

```
        gradle clean build
        java -jar build/libs/rpsls-0.0.1-SNAPSHOT.jar

```

### Game Modes:

* Rest API:
  1. Played against computer and a UniversalHumanPlayer. 
  2. Games stats are stored till server is up
  3. All requests are mapped against a single player.
  4. Stats are stored in memory needs to be improved
  5. How to run 
     ```
       curl  --request POST 'http://localhost:4567/game' \
       --header 'Content-Type: application/json' \
       --data '{"hand": "PAPER"}'
     ```  
  
* Console mode
  1. Enabled by default (Can be disabled by setting rps.console.enabled to false)
  2. Game played from console
  3. Can be played as Player vs Computer or Computer vs Computer
  4. Currently supports only one round of games
  5. One round consist of n number of games.
  
  
### Scopes to improve
  1. Unique player identification
  2. Persistence of entities
  3. Game engine to be message driven using a message platform like kafka.
  
