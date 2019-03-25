import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class display extends JFrame {
    //members
    private JFrame frame;
    private JButton[][] boxes;
    private JButton clear;
    private JTextArea turn;
    private JTextArea roll;
    String pl1,pl2;
    public display(String pl1) {
        //declaration of frame and buttons
        this.frame = new JFrame("Tic Tac Toe Game");
        this.boxes = new JButton[3][3];
        this.clear = new JButton("Restart");
        this.turn = new JTextArea();
        this.roll = new JTextArea("Roll number:L164387");
        this.pl1=pl1;
        // call the initialize method to set up the layout and initialize buttons
        setup();
    }
    public void setPlayers(String p1,String p2){
        this.pl1=p1;
        this.pl2=p2;


    }
    public void setup()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 500));
        frame.setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel blocks = new JPanel(new GridLayout(3,3));

        blocks.setForeground(Color.white);
        blocks.setBackground(Color.BLACK);

        mainPanel.setBackground(Color.black);

        mainPanel.add(blocks, BorderLayout.CENTER);

        JPanel restart = new JPanel(new FlowLayout());
        restart.setForeground(Color.white);
        restart.setBackground(Color.BLACK);
        JPanel rollnum = new JPanel(new FlowLayout());
        rollnum.setForeground(Color.white);
        rollnum.setBackground(Color.BLACK);

        rollnum.add(roll);
        restart.add(clear);

        JPanel playerturn = new JPanel(new FlowLayout());

        playerturn.setBackground(Color.black);
        playerturn.setForeground(Color.WHITE);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(restart, BorderLayout.NORTH);
        frame.add(rollnum,BorderLayout.EAST);
        frame.add(playerturn, BorderLayout.SOUTH);

        playerturn.add(turn);

        turn.setText(this.pl1+"  to play with 'X'");

        for(int row = 0; row<3 ;row++) {
            for(int column = 0; column<3 ;column++) {
                boxes[row][column] = new JButton();
                boxes[row][column].setPreferredSize(new Dimension(70,70));
                boxes[row][column].setText("");
                boxes[row][column].setBackground(Color.darkGray);
                boxes[row][column].setForeground(Color.darkGray);
                blocks.add(boxes[row][column]);

            }
        }

        // make the gui visible as the final step
        frame.setVisible(true);



    }
    // function to clear the view and reset it for a new game
    public void resetGame() {
        for(int row = 0;row<3;row++) {
            for(int column = 0;column<3;column++) {
                boxes[row][column].setText("");
                boxes[row][column].setEnabled(true);
            }
        }
        turn.setText(pl1+"  to play 'X'");
    }
    // function to freeze the view if there is a winner or game is tied
    //donot enable any box to be marked
    public void isWinner(int row, int column, char symbol, String message) {
        boxes[row][column].setText(Character.toString(symbol));
        boxes[row][column].setEnabled(false);
        for(int i = 0; i<3 ;i++) {
            for(int j = 0; j<3 ;j++) {
                boxes[i][j].setEnabled(false);
            }
        }
        turn.setText(message);

    }
    // function to update the view with the correct mark and message
    public void update(int row, int column, char symbol, String message) {
        boxes[row][column].setText(Character.toString(symbol));
        boxes[row][column].setEnabled(false);
        turn.setText(message);

    }
    // function to find the x,y-coordinates of the pressed button
    public ArrayList<Integer> getPosition(ActionEvent e) {
        ArrayList<Integer> position = new ArrayList<Integer>();
        for(int row = 0; row<3 ;row++) {
            for(int column = 0; column<3 ;column++) {
                if(e.getSource() == boxes[row][column]) {
                    position.add(row);
                    position.add(column);
                }
            }
        }
        return position;
    }
    // function to check if the action event was generated because of reset key
    public boolean isReset(ActionEvent e) {
        if(e.getSource() == clear)
            return true;
        return false;
    }
    // function to add action listeners to buttons
    public void setActionListener(controller c) {


        for(int row = 0; row<3 ;row++) {
            for(int column = 0; column<3 ;column++) {
                boxes[row][column].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(display.this.isReset(e))
                            c.setRequest();
                        else {
                            ArrayList<Integer> position = display.this.getPosition(e);
                            c.setRequest(position);
                        }
                    }
                });
            }
        }
        clear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(display.this.isReset(e))
                    c.setRequest();
                else {
                    ArrayList<Integer> position = display.this.getPosition(e);
                    c.setRequest(position);
                }
            }
        });
    }









}
