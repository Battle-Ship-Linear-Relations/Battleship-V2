import javax.swing.*;
import java.awt.*;

public class ShipLength {


    
    /**
   * if the length of the ship being the placed is 4
   *
   * @param JButton buttonArray[][], array of buttons passed based on whos turn it is
   * @param int rotate, the rotation of the ship being placed, vertical or horzintal
   * @param Color player, the colour of the ship that needs to be placed based on whos turn it is
   * @param xCoord, the x location of the button
   * @param yCoord, the y location of the ship
   * @return boolean, if the ship can be placed without any errors, or not
   */
    public static boolean shipLength4(JButton buttonArray[][], int rotate, Color player, int xCoord, int yCoord) {
        if (rotate % 2 == 0) { // the rotation of the ship if vertical
            if (yCoord < buttonArray.length - 3) {
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord][yCoord + 1].getText().equals("") && buttonArray[xCoord][yCoord + 2].getText().equals("") && buttonArray[xCoord][yCoord + 3].getText().equals("")){ // if the location of the ship being placed is not interfeiring with any other ships previously placed
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) { // if the colour the ship trying ot be placed is not red
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord][yCoord + 1].setBackground(player);
                        buttonArray[xCoord][yCoord + 2].setBackground(player);
                        buttonArray[xCoord][yCoord + 3].setBackground(player);
                        return true; // if  the ship can successfully be placed return true;
                    }
                    return false; //if there is an error with the ship being placed, return false (all return comments apply to the next few lines within this method)
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED); // if the ship being placed is interfeiring with another ship, set the colour to red
                    buttonArray[xCoord][yCoord + 1].setBackground(Color.RED);
                    buttonArray[xCoord][yCoord + 2].setBackground(Color.RED);
                    buttonArray[xCoord][yCoord + 3].setBackground(Color.RED);
                    return false;
                }
            }
            if (yCoord == buttonArray.length - 3) { // if the ship if being placed outside the range of the game, adjust it so it is inside the game (apllies to the next 2 if statemnets)
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord][yCoord - 1].getText().equals("") && buttonArray[xCoord][yCoord + 1].getText().equals("") && buttonArray[xCoord][yCoord + 2].getText().equals("")){
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) {
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord][yCoord + 2].setBackground(player);
                        buttonArray[xCoord][yCoord - 1].setBackground(player);
                        buttonArray[xCoord][yCoord + 1].setBackground(player);
                        return true;
                    }
                    return false;
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED); // if the ship being placed is interfeiring with another ship, set the colour to red
                    buttonArray[xCoord][yCoord + 2].setBackground(Color.RED);
                    buttonArray[xCoord][yCoord - 1].setBackground(Color.RED);
                    buttonArray[xCoord][yCoord + 1].setBackground(Color.RED);
                    return false;
                }
            }
            if (yCoord == buttonArray.length - 2) {
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord][yCoord + 1].getText().equals("") && buttonArray[xCoord][yCoord - 1].getText().equals("") && buttonArray[xCoord][yCoord - 2].getText().equals("")){
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) {
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord][yCoord + 1].setBackground(player);
                        buttonArray[xCoord][yCoord - 1].setBackground(player);
                        buttonArray[xCoord][yCoord - 2].setBackground(player);
                        return true;
                    }
                    return false;
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED); // if the ship being placed is interfeiring with another ship, set the colour to red
                    buttonArray[xCoord][yCoord + 1].setBackground(Color.RED);
                    buttonArray[xCoord][yCoord - 1].setBackground(Color.RED);
                    buttonArray[xCoord][yCoord - 2].setBackground(Color.RED);
                    return false;
                }
            }
            if (yCoord == buttonArray.length - 1) {
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord][yCoord -2].getText().equals("") && buttonArray[xCoord][yCoord - 1].getText().equals("") && buttonArray[xCoord][yCoord - 3].getText().equals("")){
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) {
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord][yCoord - 2].setBackground(player);
                        buttonArray[xCoord][yCoord - 1].setBackground(player);
                        buttonArray[xCoord][yCoord - 3].setBackground(player);
                        return true;
                    }
                    return false;
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord][yCoord - 2].setBackground(Color.RED);
                    buttonArray[xCoord][yCoord - 1].setBackground(Color.RED);
                    buttonArray[xCoord][yCoord - 3].setBackground(Color.RED);
                    return false;
                }
            }
        }
        if (rotate % 2 == 1) { // if the rotation of the ship is horizontal
            if (xCoord < buttonArray.length - 3) { // if its within the range of the game (all comments above apply to the next few if statements in this method)
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord + 1][yCoord].getText().equals("") && buttonArray[xCoord + 2][yCoord].getText().equals("") && buttonArray[xCoord + 3][yCoord].getText().equals("")){
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) {
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord + 1][yCoord].setBackground(player);
                        buttonArray[xCoord + 2][yCoord].setBackground(player);
                        buttonArray[xCoord + 3][yCoord].setBackground(player);
                        return true;
                    }
                    return false;
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord + 1][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord + 2][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord + 3][yCoord].setBackground(Color.RED);
                    return false;
                }
            }
            if (xCoord == buttonArray.length - 3) { // if the ship if being placed outside the range of the game, adjust it so it is inside the game (apllies to the next 2 if statemnets)
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord - 1][yCoord].getText().equals("") && buttonArray[xCoord + 1][yCoord].getText().equals("") && buttonArray[xCoord + 2][yCoord].getText().equals("")){
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) {
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord + 1][yCoord].setBackground(player);
                        buttonArray[xCoord - 1][yCoord].setBackground(player);
                        buttonArray[xCoord + 2][yCoord].setBackground(player);
                        return true;
                    }
                    return false;
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord + 1][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord - 1][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord + 2][yCoord].setBackground(Color.RED);
                    return false;
                }
            }
            if (xCoord == buttonArray.length - 2) {
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord + 1][yCoord].getText().equals("") && buttonArray[xCoord - 1][yCoord].getText().equals("") && buttonArray[xCoord - 2][yCoord].getText().equals("")){
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) {
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord + 1][yCoord].setBackground(player);
                        buttonArray[xCoord - 1][yCoord].setBackground(player);
                        buttonArray[xCoord - 2][yCoord].setBackground(player);
                        return true;
                    }
                    return false;
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord + 1][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord - 1][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord - 2][yCoord].setBackground(Color.RED);
                    return false;
                }
            }
            if (xCoord == buttonArray.length - 1) {
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord - 2][yCoord].getText().equals("") && buttonArray[xCoord - 1][yCoord].getText().equals("") && buttonArray[xCoord - 3][yCoord].getText().equals("")){
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) {
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord - 2][yCoord].setBackground(player);
                        buttonArray[xCoord - 1][yCoord].setBackground(player);
                        buttonArray[xCoord - 3][yCoord].setBackground(player);
                        return true;
                    }
                    return false;
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord - 2][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord - 1][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord - 3][yCoord].setBackground(Color.RED);
                    return false;
                }
            }
        }
        return false;
    }








   /**
   * if the length of the ship being the placed is 3
   *
   * @param JButton buttonArray[][], array of buttons passed based on whos turn it is
   * @param int rotate, the rotation of the ship being placed, vertical or horzintal
   * @param Color player, the colour of the ship that needs to be placed based on whos turn it is
   * @param xCoord, the x location of the button
   * @param yCoord, the y location of the ship
   * @return boolean, if the ship can be placed without any errors, or not
   */
    public static boolean shipLength3(JButton buttonArray[][], int rotate, Color player, int xCoord, int yCoord) {
        if (rotate % 2 == 0) { // if the rotation of the ship is vertical
            if (yCoord < buttonArray.length - 2) { // if the ship if within the range of the game
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord][yCoord + 1].getText().equals("") && buttonArray[xCoord][yCoord + 2].getText().equals("")){ // if it does not interfeire with any other ships
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) { //  if the colour of the ship is not red (no error)
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord][yCoord + 1].setBackground(player);
                        buttonArray[xCoord][yCoord + 2].setBackground(player);
                        return true; //if it can be placed without errors, return true
                    }
                    return false; // if there is an error return true
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord][yCoord + 1].setBackground(Color.RED);
                    buttonArray[xCoord][yCoord + 2].setBackground(Color.RED);
                    return false; //if there is an error, return true
                }
            }
            if (yCoord == buttonArray.length - 2) { // if the ship is trying to placed outside of the range
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord][yCoord + 1].getText().equals("") && buttonArray[xCoord][yCoord - 1].getText().equals("")){// if there is no other ships in the way 
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) {
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord][yCoord + 1].setBackground(player);
                        buttonArray[xCoord][yCoord - 1].setBackground(player);
                        return true; // previous comments on return statements apply to every thing in this method
                    }
                    return false;
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord][yCoord + 1].setBackground(Color.RED);
                    buttonArray[xCoord][yCoord - 1].setBackground(Color.RED);
                    return false;
                }
            }
            if (yCoord == buttonArray.length - 1) {
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord][yCoord -2].getText().equals("") && buttonArray[xCoord][yCoord - 1].getText().equals("")){// if there is no other ships in the way 
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) {
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord][yCoord - 2].setBackground(player);
                        buttonArray[xCoord][yCoord - 1].setBackground(player);
                        return true;
                    }
                    return false;
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord][yCoord - 2].setBackground(Color.RED);
                    buttonArray[xCoord][yCoord - 1].setBackground(Color.RED);
                    return false;
                }
            }
        }
        if (rotate % 2 == 1) { // if the rotation of the ship is horizontal
            if (xCoord < buttonArray.length - 2) { // if the ship if within the range
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord + 1][yCoord].getText().equals("") && buttonArray[xCoord + 2][yCoord].getText().equals("")){// if there is no other ships in the way 
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) {
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord + 1][yCoord].setBackground(player);
                        buttonArray[xCoord + 2][yCoord].setBackground(player);
                        return true;
                    }
                    return false;
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord + 1][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord + 2][yCoord].setBackground(Color.RED);
                    return false;
                }
            }
            if (xCoord == buttonArray.length - 2) { // if the ship is trying to be placed outside the range of the game
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord + 1][yCoord].getText().equals("") && buttonArray[xCoord - 1][yCoord].getText().equals("")){// if there is no other ships in the way 
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) {
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord + 1][yCoord].setBackground(player);
                        buttonArray[xCoord - 1][yCoord].setBackground(player);
                        return true;
                    }
                    return false;
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord + 1][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord - 1][yCoord].setBackground(Color.RED);
                    return false;
                }
            }
            if (xCoord == buttonArray.length - 1) {
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord - 2][yCoord].getText().equals("") && buttonArray[xCoord - 1][yCoord].getText().equals("")){// if there is no other ships in the way 
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) {
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord - 2][yCoord].setBackground(player);
                        buttonArray[xCoord - 1][yCoord].setBackground(player);
                        return true;
                    }
                    return false;
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord - 2][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord - 1][yCoord].setBackground(Color.RED);
                    return false;
                }
            }
        }
        return false;
    }












    /**
   * if the length of the ship being the placed is 2
   *
   * @param JButton buttonArray[][], array of buttons passed based on whos turn it is
   * @param int rotate, the rotation of the ship being placed, vertical or horzintal
   * @param Color player, the colour of the ship that needs to be placed based on whos turn it is
   * @param xCoord, the x location of the button
   * @param yCoord, the y location of the ship
   * @return boolean, if the ship can be placed without any errors, or not
   */
    public static boolean shipLength2(JButton buttonArray[][], int rotate, Color player, int xCoord, int yCoord) {
        if (rotate % 2 == 0) { //if the rotation of the ship if vertical
            if (yCoord < buttonArray.length - 1) { //if the ship is within the range of the game
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord][yCoord + 1].getText().equals("")){ // if it does not interfeire with any other ships
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) { // if the colour is not red (no error)
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord][yCoord + 1].setBackground(player);
                        return true; //if it can be placed without errors, return true
                    }
                    return false; //if there is an error, return false
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED); // if there is an error, set the colour of the ship to red
                    buttonArray[xCoord][yCoord + 1].setBackground(Color.RED);
                    return false;// if there is an error, return false (applys to all return statements in this method)
                }
            }
            if (yCoord == buttonArray.length - 1) { //if the ship is trying to placed outside the range of the game
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord][yCoord - 1].getText().equals("")){// if there is no other ships in the way 
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) {
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord][yCoord - 1].setBackground(player);
                        return true;
                    }
                    return false;
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED);// if there is an error, set the colour of the ship to red
                    buttonArray[xCoord][yCoord - 1].setBackground(Color.RED);
                    return false;
                }
            }
        }
        if (rotate % 2 == 1) { // if the rotation of the ship if horizontal
            if (xCoord < buttonArray.length - 1) {
                if (buttonArray[xCoord][yCoord].getText().equals("") && buttonArray[xCoord + 1][yCoord].getText().equals("")){ // if there is no other ships in the way 
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) {
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord + 1][yCoord].setBackground(player);
                        return true; // if there is no problem with the ship, return true
                    }
                    return false; // if there is a problem, return false
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED);// if there is an error, set the colour of the ship to red
                    buttonArray[xCoord + 1][yCoord].setBackground(Color.RED);
                    return false;
                }
            }
            if (xCoord == buttonArray.length - 1) {
                if (buttonArray[xCoord][yCoord].getText().equals("") &&  buttonArray[xCoord - 1][yCoord].getText().equals("")){ // if there is no other ships in the way 
                    if (buttonArray[xCoord][yCoord].getBackground() != Color.RED) {
                        buttonArray[xCoord][yCoord].setBackground(player);
                        buttonArray[xCoord - 1][yCoord].setBackground(player);
                        return true;
                    }
                    return false;
                }
                else {
                    buttonArray[xCoord][yCoord].setBackground(Color.RED);
                    buttonArray[xCoord - 1][yCoord].setBackground(Color.RED);
                    return false;
                }
            }
        }
        return false;
    }






    /**
   * if the ship can be placed successfully, confirm its location, and if the length of the ship being the placed is 4
   *
   * @param JButton buttonArray[][], array of buttons passed based on whos turn it is
   * @param int rotate, the rotation of the ship being placed, vertical or horzintal
   * @param Color player, the colour of the ship that needs to be placed based on whos turn it is
   * @param xCoord, the x location of the button
   * @param yCoord, the y location of the ship
   * @param int shipCount, the number of ships placed
   * @return boolean, if the ship can be placed without any errors, or not
   */
    public static void confirmShipLength4 (JButton buttonArray[][], int rotate, Color player, int xCoord, int yCoord, int shipCount) {
        if (rotate % 2 == 0) { // if the rotation of the ship is vertical 
            if (yCoord < buttonArray.length - 3) { // if the ship is being placed within the range of the game
                buttonArray[xCoord][yCoord].setBackground(player); // set the background colour based on whos turn it is (applies to everything in this method)
                buttonArray[xCoord][yCoord + 1].setBackground(player);
                buttonArray[xCoord][yCoord + 2].setBackground(player);
                buttonArray[xCoord][yCoord + 3].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount)); //assign the text of the ship to the number of ship it is
                buttonArray[xCoord][yCoord + 1].setText(String.valueOf(shipCount));
                buttonArray[xCoord][yCoord + 2].setText(String.valueOf(shipCount));
                buttonArray[xCoord][yCoord + 3].setText(String.valueOf(shipCount));
            }
            if (yCoord == buttonArray.length - 3) { // if the ship is trying to be placed outside of the game
                buttonArray[xCoord][yCoord].setBackground(player);
                buttonArray[xCoord][yCoord + 1].setBackground(player);
                buttonArray[xCoord][yCoord - 1].setBackground(player);
                buttonArray[xCoord][yCoord + 2].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord][yCoord + 1].setText(String.valueOf(shipCount));
                buttonArray[xCoord][yCoord - 1].setText(String.valueOf(shipCount));
                buttonArray[xCoord][yCoord + 2].setText(String.valueOf(shipCount));
            }
            if (yCoord == buttonArray.length - 2) {
                buttonArray[xCoord][yCoord].setBackground(player);
                buttonArray[xCoord][yCoord + 1].setBackground(player);
                buttonArray[xCoord][yCoord - 1].setBackground(player);
                buttonArray[xCoord][yCoord - 2].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord][yCoord + 1].setText(String.valueOf(shipCount));
                buttonArray[xCoord][yCoord - 1].setText(String.valueOf(shipCount));
                buttonArray[xCoord][yCoord - 2].setText(String.valueOf(shipCount));
            }
            if (yCoord == buttonArray.length - 1) {
                buttonArray[xCoord][yCoord].setBackground(player);
                buttonArray[xCoord][yCoord - 2].setBackground(player);
                buttonArray[xCoord][yCoord - 1].setBackground(player);
                buttonArray[xCoord][yCoord - 3].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord][yCoord - 1].setText(String.valueOf(shipCount));
                buttonArray[xCoord][yCoord - 2].setText(String.valueOf(shipCount));
                buttonArray[xCoord][yCoord - 3].setText(String.valueOf(shipCount));
            }
        }
        if (rotate % 2 == 1) { // if the roation of the ship if horizontal 
            if (xCoord < buttonArray.length - 3) { // if the ship is within the range of the game
                buttonArray[xCoord][yCoord].setBackground(player);
                buttonArray[xCoord + 1][yCoord].setBackground(player);
                buttonArray[xCoord + 2][yCoord].setBackground(player);
                buttonArray[xCoord + 3][yCoord].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord + 1][yCoord].setText(String.valueOf(shipCount));
                buttonArray[xCoord + 2][yCoord].setText(String.valueOf(shipCount));
                buttonArray[xCoord + 3][yCoord].setText(String.valueOf(shipCount));
            }
            if (xCoord == buttonArray.length - 3) { // if the ship is trying to be placed outside the range, set the ship 1 back
                buttonArray[xCoord][yCoord].setBackground(player);
                buttonArray[xCoord + 1][yCoord].setBackground(player);
                buttonArray[xCoord - 1][yCoord].setBackground(player);
                buttonArray[xCoord + 2][yCoord].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord + 1][yCoord].setText(String.valueOf(shipCount));
                buttonArray[xCoord - 1][yCoord].setText(String.valueOf(shipCount));
                buttonArray[xCoord + 2][yCoord].setText(String.valueOf(shipCount));
            }
            if (xCoord == buttonArray.length - 2) { // if the ship is trying to be placed outside the range, set the ship 2 back
                buttonArray[xCoord][yCoord].setBackground(player);
                buttonArray[xCoord + 1][yCoord].setBackground(player);
                buttonArray[xCoord - 1][yCoord].setBackground(player);
                buttonArray[xCoord - 2][yCoord].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord + 1][yCoord].setText(String.valueOf(shipCount));
                buttonArray[xCoord - 1][yCoord].setText(String.valueOf(shipCount));
                buttonArray[xCoord - 2][yCoord].setText(String.valueOf(shipCount));
            }
            if (xCoord == buttonArray.length - 1) {  // if the ship is trying to be placed outside the range, set the ship 3 back
                buttonArray[xCoord][yCoord].setBackground(player);
                buttonArray[xCoord - 2][yCoord].setBackground(player);
                buttonArray[xCoord - 1][yCoord].setBackground(player);
                buttonArray[xCoord - 3][yCoord].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord - 2][yCoord].setText(String.valueOf(shipCount));
                buttonArray[xCoord - 1][yCoord].setText(String.valueOf(shipCount));
                buttonArray[xCoord - 3][yCoord].setText(String.valueOf(shipCount));
            }
        }
    }




   /**
   * if the ship can be placed successfully, confirm its location, and if the length of the ship being the placed is 3
   *
   * @param JButton buttonArray[][], array of buttons passed based on whos turn it is
   * @param int rotate, the rotation of the ship being placed, vertical or horzintal
   * @param Color player, the colour of the ship that needs to be placed based on whos turn it is
   * @param xCoord, the x location of the button
   * @param yCoord, the y location of the ship
   * @param int shipCount, the number of ships placed
   * @return boolean, if the ship can be placed without any errors, or not
   */
    public static void confirmShipLength3 (JButton buttonArray[][], int rotate, Color player, int xCoord, int yCoord, int shipCount) {
        if (rotate % 2 == 0) { // if the rotation of the ship is vertiical 
            if (yCoord < buttonArray.length - 2) { // if the ship being placed is within the range of the board (applies to everything in this method)
                buttonArray[xCoord][yCoord].setBackground(player); //set the colour of the ship based on whos turn it is (applies to everything in this method)
                buttonArray[xCoord][yCoord + 1].setBackground(player);
                buttonArray[xCoord][yCoord + 2].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord][yCoord + 1].setText(String.valueOf(shipCount));
                buttonArray[xCoord][yCoord + 2].setText(String.valueOf(shipCount));
            }
            if (yCoord == buttonArray.length - 2) { //if the ship is trying to be placed outside the range of the board b
                buttonArray[xCoord][yCoord].setBackground(player);
                buttonArray[xCoord][yCoord + 1].setBackground(player);
                buttonArray[xCoord][yCoord - 1].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord][yCoord + 1].setText(String.valueOf(shipCount));
                buttonArray[xCoord][yCoord - 1].setText(String.valueOf(shipCount));
            }
            if (yCoord == buttonArray.length - 1) {
                buttonArray[xCoord][yCoord].setBackground(player);
                buttonArray[xCoord][yCoord - 2].setBackground(player);
                buttonArray[xCoord][yCoord - 1].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord][yCoord - 1].setText(String.valueOf(shipCount));
                buttonArray[xCoord][yCoord - 2].setText(String.valueOf(shipCount));
            }
        }
        if (rotate % 2 == 1) { // if the rotation of the shihp is horizontal
            if (xCoord < buttonArray.length - 2) {// if the ship being placed is within the range of the board
                buttonArray[xCoord][yCoord].setBackground(player);
                buttonArray[xCoord + 1][yCoord].setBackground(player);
                buttonArray[xCoord + 2][yCoord].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord + 1][yCoord].setText(String.valueOf(shipCount));
                buttonArray[xCoord + 2][yCoord].setText(String.valueOf(shipCount));
            }
            if (xCoord == buttonArray.length - 2) {
                buttonArray[xCoord][yCoord].setBackground(player);
                buttonArray[xCoord + 1][yCoord].setBackground(player);
                buttonArray[xCoord - 1][yCoord].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord + 1][yCoord].setText(String.valueOf(shipCount));
                buttonArray[xCoord - 1][yCoord].setText(String.valueOf(shipCount));
            }
            if (xCoord == buttonArray.length - 1) {
                buttonArray[xCoord][yCoord].setBackground(player);
                buttonArray[xCoord - 2][yCoord].setBackground(player);
                buttonArray[xCoord - 1][yCoord].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord - 2][yCoord].setText(String.valueOf(shipCount));
                buttonArray[xCoord - 1][yCoord].setText(String.valueOf(shipCount));
            }
        }
    }

    

    /**
   * if the ship can be placed successfully, confirm its location, and if the length of the ship being the placed is 2
   *
   * @param JButton buttonArray[][], array of buttons passed based on whos turn it is
   * @param int rotate, the rotation of the ship being placed, vertical or horzintal
   * @param Color player, the colour of the ship that needs to be placed based on whos turn it is
   * @param xCoord, the x location of the button
   * @param yCoord, the y location of the ship
   * @param int shipCount, the number of ships placed
   * @return boolean, if the ship can be placed without any errors, or not
   */
    public static void confirmShipLength2 (JButton buttonArray[][], int rotate, Color player, int xCoord, int yCoord, int shipCount) {
        if (rotate % 2 == 0) {
            if (yCoord < buttonArray.length - 1) {
                buttonArray[xCoord][yCoord].setBackground(player); //set the colour of the ship based on whos turn it is (applies to everything in this method)
                buttonArray[xCoord][yCoord + 1].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord][yCoord + 1].setText(String.valueOf(shipCount));
            }
            if (yCoord == buttonArray.length - 1) {
                buttonArray[xCoord][yCoord].setBackground(player);
                buttonArray[xCoord][yCoord - 1].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord][yCoord - 1].setText(String.valueOf(shipCount));
            }
        }
        if (rotate % 2 == 1) {
            if (xCoord < buttonArray.length - 1) {
                buttonArray[xCoord][yCoord].setBackground(player);
                buttonArray[xCoord + 1][yCoord].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord + 1][yCoord].setText(String.valueOf(shipCount));
            }
            if (xCoord == buttonArray.length - 1) {
                buttonArray[xCoord][yCoord].setBackground(player);
                buttonArray[xCoord - 1][yCoord].setBackground(player);
                buttonArray[xCoord][yCoord].setText(String.valueOf(shipCount));//assign the text of the ship to the number of ship it is
                buttonArray[xCoord - 1][yCoord].setText(String.valueOf(shipCount));
            }
        }
    }
}
