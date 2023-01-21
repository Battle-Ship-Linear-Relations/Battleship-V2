# Battleship-V2

<ins>Instructions</ins>

- You will be asked to choose a difficulty
   - 1 -> coordinate version
   - 2 -> slope version
- First, you must place your ships on your own board, ships can be rotated by pressing ‘r’
- Then, the computer will place its ships on its board, you will not be able to see the computer’s ships
- You will then input a value depending on the difficulty
- Guesses on the board will be shown as black X’s and hits will be shown as red X’s
- If a ship has sunk on either side, it will indicate at the bottom of the screen
- Once a player has sunk all the other player’s ships, they win the game!

<ins>Difficulty 1</ins>

- This is the coordinates version of the game and is the easiest
- Once you place all your ships and the computer places all of its ships, you will be asked to input a coordinate to guess the other player's ship
- The program will not allow you to play repeated coordinates 

<ins>Difficulty 2</ins>

- This is the slope version of the game, and is intermediate
- You will input an equation for the slope in the form y = mx + b
- the program will not allow you to input slopes that are flat lines or undefined slopes
- The program will not allow you play repeated slopes
- Lines must directly intersect a ship to be considered a hit


------------------------------------------------------------------------------------------------------------------------------------

Possible Ideas:
- add a method to add inequalities
- add a help button to know what the different difficulties are
- Add an instructions screen before the game begins
- Make both boards visible (the user's and computer's)
- Make an area that keeps track of everything inputted



Known Bugs:
- the botand user may be able to input slopes that never occur within the range of the game board
