import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
   * new class that implements the JFrame and creates and adds all components of the game
   */
public class gameFrame extends JFrame{ //extends JFrame so this class can just be treated as the JFrame
    ImageIcon logo = new ImageIcon(new ImageIcon("logo.png").getImage().getScaledInstance(350, 100, Image.SCALE_SMOOTH)); // scale the image to be the right size
    ImageIcon background = new ImageIcon("Background.jpg");
    ImageIcon gridImage = new ImageIcon(new ImageIcon("grid.png").getImage().getScaledInstance(555, 558, Image.SCALE_SMOOTH)); // scale the image to be the right size
    JButton userBoard[][] = new JButton[11][11]; //size of the game
    JButton computerBoard[][] = new JButton[userBoard.length][userBoard[0].length];
    JLabel logoLabel = new JLabel(logo);
    JLabel equationLabel = new JLabel("y = — x + ");
    JTextField riseTextField = new JTextField();
    JTextField runTextField = new JTextField();
    JTextField yIntTextField = new JTextField();
    JLabel errorLabel = new JLabel("Error, please try again");
    String[] ships = {"Battleship", "Destroyer", "Submarine", "Battleship", "Destroyer", "Submarine"}; //names of all the ships that can be placed
    JLabel shipType = new JLabel("Ship: " + ships[0]); // displays the ship type
    JLabel grid = new JLabel(gridImage);
    JLabel turnJLabel = new JLabel("Your Turn");
    JLabel rotateLabel = new JLabel("Press 'r' to rotate");
    JLabel coordinateLabel = new JLabel("Coordinate:");
    JTextField coordinateField = new JTextField();
    JButton resetButton = new JButton("Play Again");
    JLabel difficultyLabel = new JLabel("Select a difficulty");
    JButton level1Button = new JButton("1");
    JButton level2Button = new JButton("2");
    int rotate = 0;
    int rise, run, yInt;
    int shipCount;
    int playerTurn = 0;
    int difficulty = 0;
    int mouseX, mouseY;
    Color player = Color.GREEN;
    java.util.Timer timer = new java.util.Timer();
    java.util.Timer timer2 = new java.util.Timer();
    boolean shipCheck1 = true, shipCheck2 = true, shipCheck3 = true;
    boolean p2ShipCheck1 = true, p2ShipCheck2 = true, p2ShipCheck3 = true;
    int counter1 = 0, counter2 = 0, counter3 = 0; // these count the number of ships on the board after every turn
    int p2Counter1 = 0, p2Counter2 = 0, p2Counter3 = 0;
    
    /**
     * parameter initializes everything in the game needed
     */
    gameFrame() { // initialize everything that is going to be on the GUI
        setVisible(true); 
        setSize(1000, 750); // set the bounds of the game
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Battleship");
        logoLabel.setBounds(325, 0, 350, 100);
        setContentPane(new JLabel(background)); // set the background of the game
        add(logoLabel);        

        resetButton.setFont(new Font("Verdana", Font.BOLD, 25)); // button to userBoard again after someone has won the game
        resetButton.setBounds(655, 570, 200, 75);
        resetButton.setForeground(Color.BLACK);
        resetButton.setVisible(false);
        resetButton.addActionListener(e -> playAgain()); // if reset button is pressed
        add(resetButton);
        

        grid.setBounds(28, 98, 555, 558); // the grid image in from of the buttons
        grid.setVisible(true);
        add(grid);

        errorLabel.setFont(new Font("Verdana", Font.BOLD, 30)); // the label if there is an error with something the user inputted (out of range or not peoper coordinate notation)
        errorLabel.setBounds(575, 130, 400, 150);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        add(errorLabel);

        rotateLabel.setFont(new Font("Verdana", Font.BOLD, 30)); // instructions displaying you can rotate the ships with 'r'
        rotateLabel.setBounds(680, 25, 400, 40);
        rotateLabel.setForeground(Color.BLACK);
        rotateLabel.setVisible(false);
        add(rotateLabel);

        difficultyLabel.setFont(new Font("Verdana", Font.BOLD, 30)); // label asking the user what difficulty they want
        difficultyLabel.setBounds(630, 250, 300, 150);
        difficultyLabel.setForeground(Color.WHITE);
        add(difficultyLabel);

        level1Button.setFont(new Font("Verdana", Font.BOLD, 30)); // level 1 button
        level1Button.setBounds(655, 360, 75, 75);
        level1Button.setForeground(Color.BLACK);
        level1Button.setBackground(Color.WHITE);
        level1Button.setFocusable(false);
        level1Button.addActionListener(e -> startGame(1)); // if level 1 is pressed, the difficuly is set to 1 (the coordinate version)
        add(level1Button);

        level2Button.setFont(new Font("Verdana", Font.BOLD, 30)); //level 2 button
        level2Button.setBounds(805, 360, 75, 75);
        level2Button.setForeground(Color.BLACK);
        level2Button.setBackground(Color.WHITE);
        level2Button.setFocusable(false);
        level2Button.addActionListener(f -> startGame(2)); // if level 1 is pressed, the difficuly is set to 2 (the slope version)
        add(level2Button);

        turnJLabel.setFont(new Font("Verdana", Font.BOLD, 40)); // label telling you whos turn it is
        turnJLabel.setBounds(640, 450, 300, 150);
        turnJLabel.setForeground(Color.WHITE);
        turnJLabel.setVisible(false);
        add(turnJLabel);

        shipType.setBounds(60, 650, 300, 50); // tells you what kind of ship you are placing depending on size
        shipType.setFont(new Font("Verdana", Font.BOLD, 30));
        shipType.setForeground(Color.WHITE);
        add(shipType);

        coordinateField.setBounds(650, 270, 200, 75); // Text field for the coordinate to be inputted
        coordinateField.setFont(new Font("Verdana", Font.BOLD, 40));
        coordinateField.setFocusable(false);
        coordinateField.setBackground(Color.lightGray);
        coordinateField.setVisible(false);
        coordinateField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){ // add key listener, so if enter is pressed, go to the confirm equation method
                    confirmEquation();
                }
            }
        });
        add(coordinateField);

        coordinateLabel.setFont(new Font("Verdana", Font.BOLD, 30)); // label that said Coordinate: above the input field
        coordinateLabel.setBounds(650, 230, 400, 40);
        coordinateLabel.setForeground(Color.BLACK);
        coordinateLabel.setVisible(false);
        add(coordinateLabel);

        equationLabel.setBounds(600, 250, 300, 150); //label that says "y = — x + b"
        equationLabel.setFont(new Font("Verdana", Font.BOLD, 50));
        equationLabel.setForeground(Color.WHITE);
        equationLabel.setVisible(false);
        add(equationLabel);

        riseTextField.setBounds(710, 270, 50, 50); //text field for the rise value of the equation
        riseTextField.setFocusable(false);
        riseTextField.setVisible(false);
        riseTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){ //add key listener, when enter is pressed, go to the confirm equation method
                    confirmEquation();
                }
            }
        });
        add(riseTextField);

        runTextField.setBounds(710, 340, 50, 50); //text field for the run value of the equation
        runTextField.setFocusable(false);
        runTextField.setVisible(false);
        runTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){ //add key listener, when enter is pressed, go to the confirm equation method
                    confirmEquation();
                }
            }
        });
        add(runTextField);

        yIntTextField.setBounds(880, 305, 50, 50); //text field for the y-intercept value of the equation
        yIntTextField.setFocusable(false);
        yIntTextField.setVisible(false);
        yIntTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){ //add key listener, when enter is pressed, go to the confirm equation method
                    confirmEquation();
                }
            }
        });
        add(yIntTextField);

        riseTextField.setBackground(Color.lightGray);
        runTextField.setBackground(Color.lightGray);
        yIntTextField.setBackground(Color.lightGray);
        riseTextField.setFont(new Font("Verdana", Font.BOLD, 40)); //set font and colours of the text fields
        runTextField.setFont(new Font("Verdana", Font.BOLD, 40));
        yIntTextField.setFont(new Font("Verdana", Font.BOLD, 40));

        
        for (int i = 0; i < userBoard.length; i ++) { //initializes both set of buttons (boards for the game)
            for (int j = 0; j < userBoard[0].length; j++) {
                userBoard[i][j] = new JButton(); // userBoard if the array of buttons the user will be playing on
                userBoard[i][j].setBounds(i * 46 + 50, j * 46 + 125, 46, 46); // set the location and size of the buttons
                userBoard[i][j].setFocusable(false);
                userBoard[i][j].setVisible(true);
                userBoard[i][j].setFont(new Font("Verdana", Font.BOLD, 0));      
                userBoard[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 0)); //remove the outlines of the buttons           
                userBoard[i][j].setBackground(Color.lightGray);// initialize what happens when the button is pressed
                userBoard[i][j].setText("");
                add(userBoard[i][j]);  //add the user's buttons to the game 


                computerBoard[i][j] = new JButton(); //computerBoard is the array of buttons the comupter will be playing on
                computerBoard[i][j].setBounds(i * 46 + 50, j * 46 + 125, 46, 46); // set the location and size of the buttons
                computerBoard[i][j].setFocusable(false);
                computerBoard[i][j].setVisible(true);
                computerBoard[i][j].setFont(new Font("Verdana", Font.BOLD, 0));               
                computerBoard[i][j].setBackground(Color.lightGray);
                computerBoard[i][j].setText("");
                computerBoard[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 0)); //remove the outlines of the buttons
                computerBoard[i][j].setVisible(false); // the compiuter's game should not be visible to the user
                add(computerBoard[i][j]);  //add the computer's buttons to the game 
            }
        }
        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                repaint(); // refresh the JFrame every millisecond, this removes lag from the user input
            }
          };
        timer.scheduleAtFixedRate(task, 0, 1);
    }


    /**
     * initailize the functions of the game and display the needed buttons/textfeild depending on the difficulty
     * @return void
     */
    public void startGame(int level) {
        difficulty = level;
        difficultyLabel.setVisible(false);
        level1Button.setVisible(false);
        level2Button.setVisible(false);
        turnJLabel.setVisible(true);
        rotateLabel.setVisible(true);
        addKeyListener(new KeyAdapter() { // add a key listener
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_R){
                    rotate++;
                    for (int i = 0; i < userBoard.length; i ++) {
                        for (int j = 0; j < userBoard[0].length; j++) {
                            if(userBoard[i][j].getText().equals("")) userBoard[i][j].setBackground(Color.lightGray); // reset the colour of the board to the ship before it was rotate is no longer visible
                            else if(userBoard[i][j].getText().equals("0") || userBoard[i][j].getText().equals("1") || userBoard[i][j].getText().equals("2") && playerTurn % 2 == 0) userBoard[i][j].setBackground(Color.GREEN); //reset the colours, but if a ship is present make the colour green
                        }
                    }
                    if (shipCount == 0) ShipLength.shipLength4(userBoard, rotate, player, mouseX, mouseY); //call the shipLength methods after the ship is rotated, so the ship is still visible after it is rotated
                    if (shipCount == 1) ShipLength.shipLength3(userBoard, rotate, player, mouseX, mouseY);
                    if (shipCount == 2) ShipLength.shipLength2(userBoard, rotate, player, mouseX, mouseY);
                    repaint();
                }
            }
        });
        for (int i = 0; i < userBoard.length; i++) {
            for (int j = 0; j < userBoard.length; j++) {
                buttonPressed(i, j); // initialize what happens to the buttons over hover and when pressed
                buttonHover(i, j);
            }
        }

        if (difficulty == 1) { // if difficulty is 1 (coordinate version)
            coordinateField.setVisible(true); //set the coordiate stuff visible
            coordinateLabel.setVisible(true);
        }

        else if (difficulty == 2) { // if difficulty is 2 (slope version)
            equationLabel.setVisible(true);// set the slope stuff visible
            yIntTextField.setVisible(true);
            riseTextField.setVisible(true);
            runTextField.setVisible(true);
        }


        
    }

    /**
     * if the userBoard again button is pressed, create a new game
     * @return void
     */
    public void playAgain() { // if userBoard again is pressed, destroy the current JFrame and create a new one.
        dispose(); // destroy the current JFrame
        new gameFrame(); // create a new JFrame
    }


    /**
     * set what happens to the buttons when the mouse hovers over them, and exits
     * @param int xCoord, the x value of the button
     * @param int yCoord, the y value of the button
     * @return void
     */
    public void buttonHover(int xCoord, int yCoord) {
        
        userBoard[xCoord][yCoord].addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * if the mouse hovers over a button
             * @param MouseEvent evt
             * @return void
             */
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (shipCount != 3) { // while the ship count is not equal to 3 (it is the user's turn), if not do nothing on hover afterwards
                    if (shipCount == 0) ShipLength.shipLength4(userBoard, rotate, player, xCoord, yCoord); // when on hover, depending on the length of the ship being placed, change the colour of the JButtons to represent a ship
                    if (shipCount == 1) ShipLength.shipLength3(userBoard, rotate, player, xCoord, yCoord);
                    if (shipCount == 2) ShipLength.shipLength2(userBoard, rotate, player, xCoord, yCoord);
                    mouseX = xCoord; // set the location of the mouse to the x and y values of the buttons
                    mouseY = yCoord;

                }
            }
        
            /**
             * if the mouse is no longer hovering over the JButton, reset its colour, this will depend on the length of the ship, what is the orientation of the ship, and the location of the ship
             * @param MouseEvent evt
             * @return void
             */
            public void mouseExited(java.awt.event.MouseEvent evt) {
                try {
                    if (shipCount == 0) { //if the shipCount is 0, the length of the ship is 4
                        if (rotate % 2 == 0) { // if the ship is placed vertically
                            if (yCoord < userBoard.length - 3) {//if the ship is within the range of the game
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord + 1].getText().equals("")) userBoard[xCoord][yCoord + 1].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord + 2].getText().equals("")) userBoard[xCoord][yCoord + 2].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord + 3].getText().equals("")) userBoard[xCoord][yCoord + 3].setBackground(Color.lightGray);
                            }
                            if (yCoord == userBoard.length - 3) { // if the user is trying to place a ship outside of the game, adjust it so it moves inside (applies to if statements below)
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord + 1].getText().equals("")) userBoard[xCoord][yCoord + 1].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord - 1].getText().equals("")) userBoard[xCoord][yCoord - 1].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord + 2].getText().equals("")) userBoard[xCoord][yCoord + 2].setBackground(Color.lightGray);
                            }
                            if (yCoord == userBoard.length - 2) {
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord + 1].getText().equals("")) userBoard[xCoord][yCoord + 1].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord - 1].getText().equals("")) userBoard[xCoord][yCoord - 1].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord - 2].getText().equals("")) userBoard[xCoord][yCoord - 2].setBackground(Color.lightGray);
                            }
                            if (yCoord == userBoard.length - 1) {
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord - 1].getText().equals("")) userBoard[xCoord][yCoord-  1].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord - 2].getText().equals("")) userBoard[xCoord][yCoord - 2].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord - 3].getText().equals("")) userBoard[xCoord][yCoord - 3].setBackground(Color.lightGray);
                            }
                        }
                        if (rotate % 2 == 1) { // if the ship is places horizontally
                            if (xCoord < userBoard.length - 3) {// previous commets for this method apply below depending on the size of the ship being placed
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord + 1][yCoord].getText().equals("")) userBoard[xCoord + 1][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord + 2][yCoord].getText().equals("")) userBoard[xCoord + 2][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord + 3][yCoord].getText().equals("")) userBoard[xCoord + 3][yCoord].setBackground(Color.lightGray);
                            }
                            if (xCoord == userBoard.length - 3) {
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord + 1][yCoord].getText().equals("")) userBoard[xCoord + 1][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord - 1][yCoord].getText().equals("")) userBoard[xCoord - 1][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord + 2][yCoord].getText().equals("")) userBoard[xCoord + 2][yCoord].setBackground(Color.lightGray);
                            }
                            if (xCoord == userBoard.length - 2) {
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord + 1][yCoord].getText().equals("")) userBoard[xCoord + 1][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord - 1][yCoord].getText().equals("")) userBoard[xCoord - 1][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord - 2][yCoord].getText().equals("")) userBoard[xCoord - 2][yCoord].setBackground(Color.lightGray);
                            }
                            if (xCoord == userBoard.length - 1) {
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord - 1][yCoord].getText().equals("")) userBoard[xCoord - 1][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord - 2][yCoord].getText().equals("")) userBoard[xCoord - 2][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord - 3][yCoord].getText().equals("")) userBoard[xCoord - 3][yCoord].setBackground(Color.lightGray);
                            }
                        }
                    }


                    if (shipCount == 1) { // if the shipCount is 1, the length of the ship if 3
                        if (rotate % 2 == 0) {
                            if (yCoord < userBoard.length - 2) {
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord + 1].getText().equals("")) userBoard[xCoord][yCoord + 1].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord + 2].getText().equals("")) userBoard[xCoord][yCoord + 2].setBackground(Color.lightGray);
                            }
                            if (yCoord == userBoard.length - 2) {
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord + 1].getText().equals("")) userBoard[xCoord][yCoord + 1].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord - 1].getText().equals("")) userBoard[xCoord][yCoord - 1].setBackground(Color.lightGray);
                            }
                            if (yCoord == userBoard.length - 1) {
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord - 1].getText().equals("")) userBoard[xCoord][yCoord-  1].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord - 2].getText().equals("")) userBoard[xCoord][yCoord - 2].setBackground(Color.lightGray);
                            }
                        }
                        if (rotate % 2 == 1) { // if the ship is places horizontally
                            if (xCoord < userBoard.length - 2) {
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord + 1][yCoord].getText().equals("")) userBoard[xCoord + 1][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord + 2][yCoord].getText().equals("")) userBoard[xCoord + 2][yCoord].setBackground(Color.lightGray);
                            }
                            if (xCoord == userBoard.length - 2) {
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord + 1][yCoord].getText().equals("")) userBoard[xCoord + 1][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord - 1][yCoord].getText().equals("")) userBoard[xCoord - 1][yCoord].setBackground(Color.lightGray);
                            }
                            if (xCoord == userBoard.length - 1) {
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord - 1][yCoord].getText().equals("")) userBoard[xCoord - 1][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord - 2][yCoord].getText().equals("")) userBoard[xCoord - 2][yCoord].setBackground(Color.lightGray);
                            }
                        }
                    }


                    if (shipCount == 2) { //  if the shipCount is 2, the length of the ship is 2
                        if (rotate % 2 == 0) { // if the ship is places vertically
                            if (yCoord < userBoard.length - 1) {
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord + 1].getText().equals("")) userBoard[xCoord][yCoord + 1].setBackground(Color.lightGray);
                            }
                            if (yCoord == userBoard.length - 1) {
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord][yCoord - 1].getText().equals("")) userBoard[xCoord][yCoord - 1].setBackground(Color.lightGray);
                            }
                        }
                        if (rotate % 2 == 1) { // if the ship is places horizontally
                            if (xCoord < userBoard.length - 1) {
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord + 1][yCoord].getText().equals("")) userBoard[xCoord + 1][yCoord].setBackground(Color.lightGray);
                            }
                            if (xCoord == userBoard.length - 1) {
                                if (userBoard[xCoord][yCoord].getText().equals("")) userBoard[xCoord][yCoord].setBackground(Color.lightGray);
                                if (userBoard[xCoord - 1][yCoord].getText().equals("")) userBoard[xCoord - 1][yCoord].setBackground(Color.lightGray);
                            }
                        }
                    }



                    if (shipCount != 3) { // when the user has placed 3 ships, do nothing over hover, and whatever the button has been assigned set their fixed colour that can no longer change
                        for (int xCoord = 0; xCoord < userBoard.length; xCoord ++) {
                            for (int yCoord = 0; yCoord < userBoard[0].length; yCoord++) {
                                if(userBoard[xCoord][yCoord].getText().equals("0") || userBoard[xCoord][yCoord].getText().equals("1") || userBoard[xCoord][yCoord].getText().equals("2")) {
                                    userBoard[xCoord][yCoord].setBackground(Color.GREEN);
                                } 
                            }
                        }
                    }
                } 
                catch (ArrayIndexOutOfBoundsException e){} // if it tries to place a ship outside the game board area
                catch (NullPointerException e){}
            }
        });
    }

    /**
     * when the computer selects a location for thier ship
     * @param int xCoord, the x value of the button
     * @param int yCoord, the y value of the button
     * @return void
     */
    public void computerButtonPressed(int xCoord, int yCoord) { // if the robot is confirming its ships
                    
        if (shipCount == 3) {
            if (ShipLength.shipLength4(computerBoard, rotate, player, xCoord, yCoord)) {
                ShipLength.confirmShipLength4(computerBoard, rotate, player, xCoord, yCoord, shipCount);
                shipCount++; // increase shipCount if the ship is placed without any errors
            }
        }
        else if (shipCount == 4) {
            if (ShipLength.shipLength3(computerBoard, rotate, player, xCoord, yCoord)) {
                ShipLength.confirmShipLength3(computerBoard, rotate, player, xCoord, yCoord, shipCount);
                shipCount++;
            }
        }
        else if (shipCount == 5) {
            if (ShipLength.shipLength2(computerBoard, rotate, player, xCoord, yCoord)) {
                ShipLength.confirmShipLength2(computerBoard, rotate, player, xCoord, yCoord, shipCount);
                shipCount++;
            }
        }
        
        for (int a = 0; a < computerBoard.length; a++) { // after every placement make sure there are only 3 ships and no errors with the placement
            for (int b = 0; b < computerBoard.length; b++) {
                if (computerBoard[a][b].getText().equals("")) {
                    computerBoard[a][b].setBackground(Color.lightGray); // this avoids there being multiple of the same ship if there is an error with its placement
                }
                else {
                    computerBoard[a][b].setBackground(Color.BLUE);
                }
            }
        }

        try {
        shipType.setText("Ship: " + ships[shipCount]); // display the type of ship being placed based on length
        } catch (ArrayIndexOutOfBoundsException e) {
            if (difficulty == 1) { //when difficulty is 1, set the coordinate stuff focusable
                coordinateField.setBackground(Color.WHITE);
                coordinateField.setFocusable(true);
            }
            if (difficulty == 2) { //when difficulty is 2, set the slope stuff focusable
            riseTextField.setBackground(Color.WHITE);
            riseTextField.setFocusable(true);
            runTextField.setBackground(Color.WHITE);
            runTextField.setFocusable(true);
            yIntTextField.setBackground(Color.WHITE);
            yIntTextField.setFocusable(true);
            }
            shipType.setVisible(false);
            for (int q = 0; q < computerBoard.length; q++) {
                for (int w = 0; w < computerBoard[0].length; w++) {
                    computerBoard[q][w].setBackground(Color.lightGray); // when all of the computer's shipd have been placed, reset their colour so the user can't see them and change boards with the user
                    userBoard[q][w].setVisible(false);
                    computerBoard[q][w].setVisible(true);
                }
            }
            repaint();
            turnJLabel.setText("Your Turn"); // indicate its the user's turn
        }
        
    }



    /**
     * if the user is selecting the location of thier ships (button is pressed)
     * @param int xCoord, the x value of the button
     * @param int yCoord, the y value of the button
     * @return void
     */
    public void buttonPressed(int xCoord, int yCoord) { // if the user if confirming a ship
        userBoard[xCoord][yCoord].addActionListener(
        new ActionListener() {
            /**
             * action listener if a button is pressed
             * @return void
             */
            public void actionPerformed(ActionEvent e) {

                if (shipCount == 0 && ShipLength.shipLength4(userBoard, rotate, player, xCoord, yCoord)) {
                    ShipLength.confirmShipLength4(userBoard, rotate, player, xCoord, yCoord, shipCount); // place a ship of the user's board and if successful, increase the shipcount, so the next length of ship will be placed
                    shipCount++;
                }
                else if (shipCount == 1 && ShipLength.shipLength3(userBoard, rotate, player, xCoord, yCoord)) {
                    ShipLength.confirmShipLength3(userBoard, rotate, player, xCoord, yCoord, shipCount);
                    shipCount++;
                }

                else if (shipCount == 2 && ShipLength.shipLength2(userBoard, rotate, player, xCoord, yCoord)) {
                    ShipLength.confirmShipLength2(userBoard, rotate, player, xCoord, yCoord, shipCount);
                    shipCount++;
                }

                if (shipCount <= 3) shipType.setText("Ship: " + ships[shipCount]); // display the type of ship being placed based on length
                if (shipCount == 3) {
                    player = Color.BLUE; // when shipCount is equal to 3 (the user has placed all thier ships), set player to Colour Blue
                    repaint();
                    playerTurn++;
                    turnJLabel.setText("<html>Computer's<br/>Turn</html>"); // once the user has placed all of their ships, indicate its the coputer's turn and allow the bot to userBoard its ship
                    rotateLabel.setVisible(false);
                    botPlaceShips(); // tell the computer to place its ships
                }
            }
        });
    }

    /**
     * when it is the robot's turn to place ships, it will select random coordinates
     * @return void
     */
    public void botPlaceShips() {

        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                rotate += (int)Math.floor(Math.random()*(10));
                int xCoord = (int)Math.floor(Math.random()*(computerBoard.length));
                int yCoord = (int)Math.floor(Math.random()*(computerBoard.length)); // select a random location for the bot's ships
                computerButtonPressed(xCoord, yCoord);
                if (shipCount == 6) timer2.cancel();
            }
        };
        timer2.scheduleAtFixedRate(task2, 3000, 3000); // set a timer, so the placement seems realitic as if a person is placing the ships
        for (int i = 0; i < computerBoard.length; i++) {
            for (int j = 0; j < computerBoard[0].length; j++) {
                computerBoard[i][j].setVisible(false); // set the computers board to no visible
                add(computerBoard[i][j]);
            }
        }
        playerTurn++; // increase player turn 
    }

    /**
     * find the greatest common denominator between 2 numbers to simplify a fraction
     * @param int numberator, the numerator of the fraction
     * @param int demoninator, the denominator of the fraction
     * @return int, the gcd between the 2 numbers
     */
    public int greatestCommonDenominator(int numerator, int denominator) {
        if (denominator == 0) return numerator;
        return greatestCommonDenominator(denominator, numerator % denominator); //recurssion to keep finding the gcd
         
    }

    /**
     * when the user is inputting a coordinate, obtain the value the user inputted and apply it to the game 
     * @return void
     */
    public void confirmEquation() { // when enter is pressed on the coordinate field

        if (difficulty == 1) { // if the difficulty is the coordinate version
            try {
                errorLabel.setVisible(false);
                errorLabel.setText("Error, please try again");
                String parts[] = String.valueOf(coordinateField.getText()).split(","); // split the contents of the coordinate and 
                int xCoord = Integer.valueOf(parts[0].trim().substring(1).trim());
                int yCoord = Integer.valueOf(parts[1].trim().substring(0, parts[1].trim().length() - 1).trim()); // set the variables to the x and y values inputted
                if (xCoord > userBoard.length/2 || xCoord < -userBoard.length/2 || yCoord > userBoard.length/2 || yCoord < -userBoard.length/2) { // if the coordinates are out of the range
                    coordinateField.setText("");
                    errorLabel.setVisible(true);
                    errorLabel.setFont(new Font("Verdana", Font.BOLD, 30));
                    errorLabel.setText("Error, please try again"); //show an error and do not apply to the game
                }
                else {
                    coordinateField.setText("");
                    int coordinatePlaced = guessSpot(xCoord, yCoord);
                    if (coordinatePlaced == 1) { // if the coordinate is valid 
                        coordinateField.setBackground(Color.lightGray);
                        coordinateField.setFocusable(false);
                        playerTurn++;
                        botGuessShip();
                    }
                    else if(coordinatePlaced == 2){ // if the coordinate is valid and you win the game
                        coordinateField.setBackground(Color.lightGray);
                        coordinateField.setFocusable(false);
                    }
                    else { // if you have already guessed that previously
                        errorLabel.setVisible(true);
                        errorLabel.setFont(new Font("Verdana", Font.BOLD, 25));
                        errorLabel.setText("You already guessed that");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) { // if there is any of the following errors (not proper notation, not numerical values, out of bounds of the game)
                coordinateField.setText("");
                errorLabel.setVisible(true);
                errorLabel.setFont(new Font("Verdana", Font.BOLD, 30));
                errorLabel.setText("Error, please try again");
            }
            catch (StringIndexOutOfBoundsException e) {
                coordinateField.setText("");
                errorLabel.setVisible(true);
                errorLabel.setFont(new Font("Verdana", Font.BOLD, 30));
                errorLabel.setText("Error, please try again");
            }
            catch (NumberFormatException e) {
                coordinateField.setText("");
                errorLabel.setVisible(true);
                errorLabel.setFont(new Font("Verdana", Font.BOLD, 30));
                errorLabel.setText("Error, please try again");
            }
        }

        if (difficulty == 2) { // if the difficulty is the slope version
            try {
                errorLabel.setVisible(false);
                boolean winner = false; // winner checks if there has been a winner after an input
                if (yIntTextField.getText().equals("")) yIntTextField.setText("0"); // if a text field is empty, set the value to 0
                if (riseTextField.getText().equals("")) riseTextField.setText("0");
                if (runTextField.getText().equals("")) runTextField.setText("0");
                rise = Integer.valueOf(riseTextField.getText()); //set the integers to the values in the text fields
                run = Integer.valueOf(runTextField.getText());
                yInt = Integer.valueOf(yIntTextField.getText());
                riseTextField.setText("");
                runTextField.setText("");
                yIntTextField.setText("");
                
                if (rise == 0 || run == 0) { // make sure the values are not 0, so it isnt an error or a stright line
                    errorLabel.setVisible(true);
                    errorLabel.setFont(new Font("Verdana", Font.BOLD, 30));
                    errorLabel.setText("Error, please try again");
                } else {

                    int simplifyFraction = greatestCommonDenominator(rise, run); // find the gcd from the rise and run values
            
                    rise /= simplifyFraction; //divide the integers with the gcd found
                    run /= simplifyFraction;
                    int xCoord = 0;
                    int yCoord = yInt; // set the begining x and y coordinates
                    int count = 0, count2 = 0;
                    for (int i = 0; i < userBoard.length; i++) {
                        for (int j = 0; j < userBoard.length; j++) {
                            if (computerBoard[i][j].getText().equals("X")) count++; // counter the nuumber of X's on the board before the slope is added
                        }
                    }
                        xCoord = 0;
                        yCoord = yInt; // set the begining x and y coordinates
                        while (xCoord <= userBoard.length/2 && xCoord >= -userBoard.length/2) { // continue the line until it reaches the end of the graph
                            if (yCoord <= userBoard.length/2 && yCoord >= -userBoard.length/2) {
                                int coordinatePlaced = guessSpot(xCoord, yCoord); //set a variable temporarily to check if it successfully places the coordinate and if there is a win
                                if (coordinatePlaced == 1) {
                                    riseTextField.setBackground(Color.lightGray);
                                    riseTextField.setFocusable(false);
                                    runTextField.setBackground(Color.lightGray);
                                    runTextField.setFocusable(false);
                                    yIntTextField.setBackground(Color.lightGray);
                                    yIntTextField.setFocusable(false);
                                    
                                }
                                else if(coordinatePlaced == 2){ // if there is a winner set the boolean winner to true
                                    winner = true;
                                }
                            }
                            xCoord += run;
                            yCoord += rise; // increment the x and y values with the rise and run values, increasing
                        }
                        xCoord = 0;
                        yCoord = yInt; // set the begining x and y coordinates
                        while (xCoord <= userBoard.length/2 && xCoord >= -userBoard.length/2) {
                            if (yCoord <= userBoard.length/2 && yCoord >= -userBoard.length/2) {
                                int coordinatePlaced = guessSpot(xCoord, yCoord); //set a variable temporarily to check if it successfully places the coordinate and if there is a win
                                if (coordinatePlaced == 1) {
                                    riseTextField.setBackground(Color.lightGray);
                                    riseTextField.setFocusable(false);
                                    runTextField.setBackground(Color.lightGray);
                                    runTextField.setFocusable(false);
                                    yIntTextField.setBackground(Color.lightGray);
                                    yIntTextField.setFocusable(false);
                                    
                                }
                                else if(coordinatePlaced == 2){  // if there is a winner set the boolean winner to true
                                    winner = true;
                                }
                            }
                            xCoord -= run;
                            yCoord -= rise; // increment the x and y values with the rise and run values, decreasing
                        }
                        for (int i = 0; i < userBoard.length; i++) {
                            for (int j = 0; j < userBoard.length; j++) {
                                if (computerBoard[i][j].getText().equals("X")) count2++; // counter the nuumber of X's on the board after the slope is added
                            }
                        }
                        if (winner) { // if there is a winner
                            riseTextField.setBackground(Color.lightGray);
                            riseTextField.setFocusable(false);
                            runTextField.setBackground(Color.lightGray);
                            runTextField.setFocusable(false);
                            yIntTextField.setBackground(Color.lightGray);
                            yIntTextField.setFocusable(false);
                        }
                        else if (count != count2){ // if the number of X's before the slope doesn't equal the number of X's after, this means they haven't guessed that slope
                            playerTurn++; //increment player turn
                            botGuessShip(); // tell the computer to guess a slope
                        }
                        else { // if the slope has already been guessed
                            errorLabel.setVisible(true);
                            errorLabel.setFont(new Font("Verdana", Font.BOLD, 25));
                            errorLabel.setText("You already guessed that");
                        }
                }
            }
            catch (ArithmeticException e) { // if there is any of the following errors (not proper notation, not numerical values, out of bounds of the game)
                riseTextField.setText("");
                runTextField.setText("");
                yIntTextField.setText("");
                errorLabel.setVisible(true);
                errorLabel.setFont(new Font("Verdana", Font.BOLD, 30));
                errorLabel.setText("Error, please try again");
            }
            catch(NumberFormatException e) {
                riseTextField.setText("");
                runTextField.setText("");
                yIntTextField.setText("");
                errorLabel.setVisible(true);
                errorLabel.setFont(new Font("Verdana", Font.BOLD, 30));
                errorLabel.setText("Error, please try again");
            }
        }
    }

    /**
     * when either player is trying to guess a spot on the game
     * @param int xCoord, the x coordinate passed
     * @param int yCoord, the y coordinate passed
     * @return void
     */
    public int guessSpot(int xCoord, int yCoord) {
        java.util.Timer timer4 = new java.util.Timer();
        int middle = userBoard.length/2; // middle of the game (0, 0)
        yCoord = -yCoord; // y value will be inverted because of how the JButtons are arranged
        if (playerTurn % 2 == 0) { // if it is the user's turn
            if (!computerBoard[middle + xCoord][middle + yCoord].getText().equals("X")) { // if the guessed coordinate isnt already guessed
                if (!computerBoard[middle + xCoord][middle + yCoord].getText().equals(""))  { // if there is a ship, mark a red X
                    computerBoard[middle + xCoord][middle + yCoord].setForeground(Color.RED);
                    computerBoard[middle + xCoord][middle + yCoord].setText("X");
                    computerBoard[middle + xCoord][middle + yCoord].setFont(new Font("Verdana", Font.BOLD, 50));
                }
                else { // if there is no ship, mark a black X
                    computerBoard[middle + xCoord][middle + yCoord].setForeground(Color.BLACK);
                    computerBoard[middle + xCoord][middle + yCoord].setText("X");
                    computerBoard[middle + xCoord][middle + yCoord].setFont(new Font("Verdana", Font.BOLD, 50));
                }
                //Counting the number of ships after every turn to determine if there is a sink and if you win
                if (shipCheck1) counter3 = 0; // only reset the value if the ship has not already sunk
                if (shipCheck2) counter2 = 0;
                if (shipCheck3) counter1 = 0;
                for (int i = 0 ; i < computerBoard.length; i++) {
                    for (int j = 0; j < computerBoard.length; j++) {
                        if (shipCheck1 && computerBoard[i][j].getText().equals("3")) counter3++; // count the number of each boat for the user
                        if (shipCheck2 && computerBoard[i][j].getText().equals("4")) counter2++;
                        if (shipCheck3 && computerBoard[i][j].getText().equals("5")) counter1++;
                    }
                }
                if (counter1 == 0 || counter2 == 0 || counter3 == 0) {
                    shipType.setVisible(true);
                    shipType.setText("SINK!");
                    if (counter1 == 0) { // if any boats sink, set them to 1 and don't reset their value to avoid saying sink after every turn
                        shipCheck3 = false;
                        counter1 = 1;
                    }
                    if (counter2 == 0) {
                        shipCheck2 = false;
                        counter2 = 1;
                    }
                    if (counter3 == 0) {
                        shipCheck1 = false;
                        counter3 = 1;
                    }
                    TimerTask task4 = new TimerTask() { // timer to show sink text and remove after 2 seconds
                        @Override
                        public void run() {
                            shipType.setVisible(false);
                            timer4.cancel();
                        }
                    };
                    timer4.scheduleAtFixedRate(task4, 2000, 1000); // create a timer with a delay of 2 seconds that only runs once
                }
                if (!shipCheck1 && !shipCheck2 && !shipCheck3) { // if all ships are sunk, return 2 indicating a win
                    turnJLabel.setText("You Win!");
                    turnJLabel.setVisible(true);
                    resetButton.setVisible(true);
                    return 2;
                }
                return 1; // if everything is valid and is not a win, return 1
            }
            else {
                return 0; // if there is an error, if it has already been guessed (only for difficulty == 1), return 0
            }
        }
        else { // if it is the computer's turn
            if (!userBoard[middle + xCoord][middle + yCoord].getText().equals("X")) { // if the guessed coordinate isnt already guessed
                if (!userBoard[middle + xCoord][middle + yCoord].getText().equals(""))  { // if there is a ship, mark a red X
                    userBoard[middle + xCoord][middle + yCoord].setForeground(Color.RED);
                    userBoard[middle + xCoord][middle + yCoord].setText("X");
                    userBoard[middle + xCoord][middle + yCoord].setFont(new Font("Verdana", Font.BOLD, 50));
                }
                else { // if there is no ship, mark a black X
                    userBoard[middle + xCoord][middle + yCoord].setForeground(Color.BLACK);
                    userBoard[middle + xCoord][middle + yCoord].setText("X");
                    userBoard[middle + xCoord][middle + yCoord].setFont(new Font("Verdana", Font.BOLD, 50));
                }
                //Counting the number of ships after every turn to determine if there is a sink and if you win
                if (p2ShipCheck1) p2Counter3 = 0; // only reset the value if the ship has not already sunk
                if (p2ShipCheck2) p2Counter2 = 0;
                if (p2ShipCheck3) p2Counter1 = 0;
                for (int i = 0 ; i < userBoard.length; i++) {
                    for (int j = 0; j < userBoard.length; j++) {
                        if (p2ShipCheck1 && userBoard[i][j].getText().equals("0")) p2Counter3++; // count the number of each boat for the computer
                        if (p2ShipCheck2 && userBoard[i][j].getText().equals("2")) p2Counter2++;
                        if (p2ShipCheck3 && userBoard[i][j].getText().equals("1")) p2Counter1++;
                    }
                }
                if (p2Counter1 == 0 || p2Counter2 == 0 || p2Counter3 == 0) {
                    shipType.setVisible(true);
                    shipType.setText("SINK!");
                    if (p2Counter1 == 0) { // if any  of the computer's boats sink, set them to 1 and don't reset their value to avoid saying sink after every turn
                        p2ShipCheck3 = false;
                        p2Counter1 = 1;
                    }
                    if (p2Counter2 == 0) {
                        p2ShipCheck2 = false;
                        p2Counter2 = 1;
                    }
                    if (p2Counter3 == 0) {
                        p2ShipCheck1 = false;
                        p2Counter3 = 1;
                    }
                    TimerTask task4 = new TimerTask() { // timer to show sink text and remove after 2 seconds
                        @Override
                        public void run() {
                            shipType.setVisible(false);
                            timer4.cancel();
                        }
                    };
                    timer4.scheduleAtFixedRate(task4, 2000, 1000); // create a timer with a delay of 2 seconds that only runs once
                }
                if (!p2ShipCheck1 && !p2ShipCheck2 && !p2ShipCheck3) {
                    turnJLabel.setText("Computer Wins!");
                    turnJLabel.setVisible(true);
                    resetButton.setVisible(true);
                    return 2; // if all ships are sunk, return 2 indicating a win for the computer
                }
                return 1; // if everything is valid and is not a win, return 1
            }
            else {
                return 0; // if there is an error, if it has already been guessed (only for difficulty == 1), return 0
            }
        }
        
        
    }

    /**
     * the computer selects random spots to guess on the user's ships
     * @return void
     */
    public void botGuessShip() { // the computer's turn
        
    java.util.Timer timer3 = new java.util.Timer();
        TimerTask task3 = new TimerTask() { // starts a timer to immatate the time a person would take 
            int count = 0;
            @Override
            public void run() {
                if (count == 0) { // first iteration of the timer
                    for (int i = 0; i < userBoard.length; i++) {
                        for (int j = 0; j < userBoard[0].length; j++) {
                            userBoard[i][j].setVisible(true);
                            computerBoard[i][j].setVisible(false);  // set the board to the player's board for the computer to guess
                        }
                    }
                    turnJLabel.setText("<html>Computer's<br/>Turn</html>"); // indicate its the computer's turn
                    count++; // increment counter
                }
                else if (count == 1) {// second interation
                    if (difficulty == 1) { // if difficulty is coordinate version
                        int xCoord = (int)Math.floor(Math.random()*((userBoard.length/2)-(-userBoard.length/2)+1)+(-userBoard.length/2));
                        int yCoord = (int)Math.floor(Math.random()*((userBoard.length/2)-(-userBoard.length/2)+1)+(-userBoard.length/2));
                        coordinateField.setText("(" + xCoord + ", " + yCoord + ")");
                        int coordinatePlaced = guessSpot(xCoord, yCoord);
                        if (coordinatePlaced == 1) count++; // if the coordiate is valid increment the counter
                        else if(coordinatePlaced == 2){  // if the computer wins the game
                            coordinateField.setBackground(Color.lightGray); // dont allow any more inputs
                            coordinateField.setFocusable(false);
                            timer3.cancel(); // stop the timer
                        }
                    }

                    if (difficulty == 2) { // if difficulty is slope version
                        boolean winner = false;
                        rise = 0;
                        run = 0;
                        yInt = (int)Math.floor(Math.random()*((userBoard.length)-(-userBoard.length)+1)+(-userBoard.length)); // randomize nubers within the range of the board
                        while (rise == 0 || run == 0) { //make sure neither of the values are equal to 0
                            rise = (int)Math.floor(Math.random()*((userBoard.length/2)-(-userBoard.length/2)+1)+(-userBoard.length/2));
                            run = (int)Math.floor(Math.random()*((userBoard.length/2)-(-userBoard.length/2)+1)+(-userBoard.length/2));
                        }
                        int simplifyFraction = greatestCommonDenominator(rise, run); //find the greatest common denominator between the 2 numbers
                        rise /= simplifyFraction; // diving the rise and run by the gcd found
                        run /= simplifyFraction;
                        int xCoord = 0;
                        int yCoord = yInt; //set the beginning values of the x and y coordinates

                        int count1 = 0, count2 = 0;
                        for (int i = 0; i < userBoard.length; i++) {
                            for (int j = 0; j < userBoard.length; j++) {
                                if (userBoard[i][j].getText().equals("X")) count1++; // counter the number of X's before the slope is added
                            }
                        }
                            riseTextField.setText(String.valueOf(rise)); // display the values that the computer is guessing on the text feilds, so the user knows what the computer guessed
                            runTextField.setText(String.valueOf(run));
                            yIntTextField.setText(String.valueOf(yInt));
                            xCoord = 0;
                            yCoord = yInt; //set the beginning values of the x and y coordinates
                            while (xCoord <= userBoard.length/2  && xCoord >= -userBoard.length/2) {
                                if (yCoord <= userBoard.length/2 && yCoord >= -userBoard.length/2) {
                                    int coordinatePlaced = guessSpot(xCoord, yCoord); //set a variable temporarily to check if it successfully places the coordinate and if there is a win
                                    if(coordinatePlaced == 2){ // if there is a winner, set winner to true
                                        winner = true;
                                    }
                                }
                                xCoord += run;
                                yCoord += rise;
                            }
                            xCoord = 0;
                            yCoord = yInt; //set the beginning values of the x and y coordinates
                            while (xCoord <= userBoard.length/2 && xCoord >= -userBoard.length/2) {
                                if (yCoord <= userBoard.length/2 && yCoord >= -userBoard.length/2) {
                                    int coordinatePlaced = guessSpot(xCoord, yCoord); //set a variable temporarily to check if it successfully places the coordinate and if there is a win
                                    if(coordinatePlaced == 2){ // if there is a winner, set winner to true
                                        winner = true;
                                    }
                                }
                                xCoord -= run;
                                yCoord -= rise;
                            }
                            if (winner) timer3.cancel(); //if there is a winner stop the timer prevent the game from continuing
                            for (int i = 0; i < userBoard.length; i++) {
                                for (int j = 0; j < userBoard.length; j++) {
                                    if (computerBoard[i][j].getText().equals("X")) count2++; //count the number of X's after the slope is added
                                }
                            }
                            if(count1 != count2) count++; // if counter does not equal count2 (the number of X's before and after the slope is different), increment counter
                    }
                }
                else { // last iteration
                    for (int i = 0; i < userBoard.length; i++) {
                        for (int j = 0; j < userBoard[0].length; j++) {
                            userBoard[i][j].setVisible(false);
                            computerBoard[i][j].setVisible(true); // set the board to the computer's board for the user to guess on
                        }
                    }
                    playerTurn++; // increment player turn
                    turnJLabel.setText("Your Turn");
                    repaint();
                    if (difficulty == 1) { // if the difficulty is the coordinate version
                        coordinateField.setText(""); // set the text fields need to focusable
                        coordinateField.setBackground(Color.WHITE);
                        coordinateField.setFocusable(true);
                    }
                    if (difficulty == 2) { // if the difficulty is the slope version
                        riseTextField.setText(""); // set the text fields need to focusable
                        runTextField.setText("");
                        yIntTextField.setText("");
                        riseTextField.setBackground(Color.WHITE);
                        riseTextField.setFocusable(true);
                        runTextField.setBackground(Color.WHITE);
                        runTextField.setFocusable(true);
                        yIntTextField.setBackground(Color.WHITE);
                        yIntTextField.setFocusable(true);
                    }
                    timer3.cancel();
                }
                
            }
        };
        timer3.scheduleAtFixedRate(task3, 4000, 3000); // create a timer with a delay of 4 seconds, and will run every 3 seconds
    }
}
