# Pong

##Instructions

To play the game just select Singleplayer or 2 Players moving with ARROW KEYS and selecting
with ENTER.

If you are in single player you are the left one and you move with ARROW KEYS.

If you are in multiplayer the left one will move with W and S KEYS, the right one with ARROW KEYS

If one of the player got an advantage of 6 or more points the game will go faster.
The game ends when a player get 10 goals.

For pandoras the points are calculated in this way:

	* +100 points for scoring a goal
	* -100 points for enemy goal
	* +50	points for every bonus you get

Only left player points are communicated to Pandoras if you are playing in multiplayer.

## Building
To build an executbale *.jar file go into the root folder of the repository and open a terminal and type

```
./gradlew desktop:dist
```
Your *.jar will be in
 
```
/desktop/build/libs/
```
