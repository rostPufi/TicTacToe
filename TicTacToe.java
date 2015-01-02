package tictactoe;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

public class TicTacToe extends JApplet{
  private JLabel lbMessage = new JLabel("not important text");
  private JButton[] btArray = null;
  private JPanel pnGame = null;
  
  private void menu() {
    JMenuBar mB = new JMenuBar();
    setJMenuBar(mB);
    //Game
    JMenu mGame = new JMenu(Constants.GAME);
    mB.add(mGame);
    JMenuItem miNewGame=new JMenuItem(Constants.NEWGAME);
    mGame.add(miNewGame);
    miNewGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        resetGame();
      }
    });
    //Table
    JMenu mTable=new JMenu(Constants.TABLE);
    mB.add(mTable);
    JRadioButtonMenuItem[] rbItems=new JRadioButtonMenuItem[Constants.TABLESIZES.length];
    ButtonGroup rg = new ButtonGroup();
    for (int i = 0; i < rbItems.length; i++) {
      JRadioButtonMenuItem rbmi=new JRadioButtonMenuItem(Constants.TABLESIZES[i]+" x "+Constants.TABLESIZES[i]);
      rbmi.setActionCommand(""+Constants.TABLESIZES[i]);
      rbmi.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Model.setTableSize(Integer.parseInt(e.getActionCommand()));
          resetGame();
        }
      });
      rbItems[i]=rbmi;
      rg.add(rbmi);
      mTable.add(rbmi);
    }    
    rbItems[0].setSelected(true);
    JMenu mHelp = new JMenu(Constants.HELP);
    mB.add(mHelp);
    JMenuItem miAbout=new JMenuItem(Constants.ABOUT);
    mHelp.add(miAbout);
    //About
    final JApplet applet=this;
    miAbout.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(applet, Constants.MADEBY, Constants.ABOUT, JOptionPane.INFORMATION_MESSAGE);
      }
    });
  }
  
  private void statusBar() {
    setSize(Constants.APPLETSIZE);
    setLayout(new BorderLayout());
    JPanel pnToolbar = new JPanel();
    lbMessage.setFont(Constants.SMALLFONT);
    pnToolbar.add(lbMessage);
    add(pnToolbar, BorderLayout.PAGE_END);
  }
  
  private JPanel gamePanel() {
    JPanel pn=new JPanel();
    pn.setLayout(new GridLayout(Model.getTableSize(), Model.getTableSize()));
    final Font FONT = new Font(Constants.LARGEFONT.getFontName(), Constants.LARGEFONT.getStyle(),
       (int)(Math.min(Constants.APPLETSIZE.width, Constants.APPLETSIZE.height) / Model.getTableSize() * 0.65));
    btArray=new JButton[(int)Math.pow(Model.getTableSize(), 2)+1];
    for (int i=1; i<=Math.pow(Model.getTableSize(), 2); i++) {
      JButton bt = new JButton();
      bt.setFont(FONT);
      btArray[i] = bt;
      bt.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          nextStep((JButton)e.getSource());
        }
      });
      pn.add(bt);
    }
    return pn;
  }
  
  @Override
  public void init() {
    Model.setTableSize(3);
    menu();
    statusBar();
    resetGame();
  }
  
  private void resetGame() {
    Model.resetGame();
    Model.nextStep();
    lbMessage.setText(Constants.STEP+" " + Model.getStepCount() + ": " + Model.getGamerName());
    if(pnGame!=null) {
      remove(pnGame);
      pnGame=null;
    }
    pnGame=gamePanel();
    add(pnGame, BorderLayout.CENTER);
    revalidate();
  }
  
  private void nextStep(JButton btCurrent) {
    btCurrent.setText(Model.getGamerName());
    btCurrent.setEnabled(false);
    Model.nextStep();
    lbMessage.setText(Constants.STEP+" " + Model.getStepCount() + ": " + Model.getGamerName());
    String winner = getWinner();
    if (!winner.equals("")) {
      lbMessage.setText(Constants.RESULT+": "+Constants.THEWINNERIS+" " + winner+"!");
      for (int i=1; i<=Math.pow(Model.getTableSize(), 2); i++)
        btArray[i].setEnabled(false);
    }
    else if (Model.getStepCount() > Math.pow(Model.getTableSize(),2))
      lbMessage.setText(Constants.RESULT+": "+Constants.MATCHDRAWN+"!");
  }
 
  private String getWinner() {
    if(Model.getStepCount()<=Model.getTableSize()*2-1) // too few steps, impossible to win
      return "";
    String data;
    // checking for the rows
    for (int i=1; i<=Model.getTableSize(); i++) {
      data = "";
      for (int j=1; j<=Model.getTableSize(); j++)
        data += btArray[(i-1)*Model.getTableSize()+j].getText();
      if (data.equals(Model.getWinnerXseries()) || data.equals(Model.getWinnerOseries()))
        return String.valueOf(data.charAt(0));
    }
    // checking for the columns
    for (int i=1; i<=Model.getTableSize(); i++) {
      data = "";
      for (int j=1; j<=Model.getTableSize(); j++)
        data += btArray[(j-1)*Model.getTableSize()+i].getText();
      if (data.equals(Model.getWinnerXseries()) || data.equals(Model.getWinnerOseries()))
        return String.valueOf(data.charAt(0));
    }
    // checking for the diagonals
    data = "";
    for (int i=1; i<=Model.getTableSize(); i++)
      data += btArray[(i-1)*(Model.getTableSize()+1)+1].getText();
    if (data.equals(Model.getWinnerXseries()) || data.equals(Model.getWinnerOseries()))
        return String.valueOf(data.charAt(0));
    data = "";
    for (int i=1; i<=Model.getTableSize(); i++)
      data += btArray[i*(Model.getTableSize()-1)+1].getText();
    if (data.equals(Model.getWinnerXseries()) || data.equals(Model.getWinnerOseries()))
        return ""+data.charAt(0);
    // no winner --> match drawn
    return "";
  }
}
