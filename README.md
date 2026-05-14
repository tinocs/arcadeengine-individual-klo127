# README #

## Individual Game Project ##

**Kyle Lo**

**Period:**	1

**Game Title:** Space Invaders

## Game Proposal ##

Write a paragraph here describing the game you want to make.  Describe how it is played and which features
you want to include in your game.  Remember, any simple game can be scaled up with features and any complex
game can be scaled down.

**Game Controls:**

+ Left/Right arrow keys to move spaceship left or right.
+ Space bar to shoot

**Game Elements:**


+ Player(spaceship) that can shoot bullets that can damage enemies
  + Player has limited lives
+ Aliens/Enemy Spaceships you must shoot down
   + Aliens can either move around or be still
   + Aliens fire back bullets which you must avoid
   + Aliens may have variable health depending on their shape/color and change shape/color as they lose health
   + Aliens may drop power ups when hit
+ 3 levels increasing in difficulty with more and more components after each level
+ Has limited lives and loses when all lives are used up


**How to Win:**

+ Shoot down all aliens and spaceships while avoiding getting hit to beat each level!
+ Beat every level to win!

## Link Examples ##

+ [Space Invaders](https://www.youtube.com/watch?v=MU4psw3ccUI)

## Teacher Response ##

Your teacher can add comments and suggestions here

## Class Design and Brainstorm ##

+ **SpaceInvaders** - runs the Space Invaders game.
+ **SpaceWorld** - extends World. Keeps track of the level and sets the Scene to match the level you're on.
+ **Ship** - extends Actor and moves with left/right arrow keys. Keeps track of lives.
+ **Alien** - extends Actor and defines a basic enemy. Keeps track of its lives and image.
+ **Bullet** - takes one life from ships and aliens when they collide with it then disappears
+ **Power Up** - Drops when certain Aliens are killed and disappears to power up ships when touched by ship.

