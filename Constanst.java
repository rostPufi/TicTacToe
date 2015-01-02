package tictactoe;

import java.awt.Dimension;
import java.awt.Font;

public interface Constants {
  //captions
  String GAME="Game";
  String NEWGAME="New game";
  String TABLE="Table";
  int[] TABLESIZES= {3, 4, 5};
  String HELP="Help";
  String ABOUT="About";
  String STEP="Step";
  String RESULT="Result";
  String THEWINNERIS="the winner is";
  String MATCHDRAWN="match drawn";
  String MADEBY="Made by\nSándor Kaczur, Attila Friedel\nDennis Gabor College\nand TRADIGME IP 2014 (Virrat, Finland) students:\nHanna, Nikita, Nevena, Rostislav, Sebastian";
  //GUI, Logic, player        
  Dimension APPLETSIZE = new Dimension(350, 400);
  Font SMALLFONT = new Font("Comic Sans MS", Font.PLAIN, 16);
  Font LARGEFONT = new Font("Comic Sans MS", Font.PLAIN, 40);
  String[] GAMER = {"O", "X"};
}
