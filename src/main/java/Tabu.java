import java.awt.event.*;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
public class Tabu extends JFrame {
    protected Game game;
    private JPanel panel;
    private JButton pas;
    private JButton correct;
    private JButton tabu;
    private JButton finish;
    private JButton ret;
    private JTextArea bannedWords;
    private JTextField wordField;
    private FlowLayout flayout=new FlowLayout();
    private BorderLayout blayout=new BorderLayout();
    private int point;
    private int Pas;
    public Tabu(String word, String b1, String b2, String b3, String b4, String b5, Game game, int point, int Pas){

        super("TABU");

        this.game=game;

        bannedWords=new JTextArea();
        bannedWords.setText("Banned words:\n"+b1+"\n"+b2+"\n"+b3+"\n"+b4+"\n"+b5);
        bannedWords.setEditable(false);
        bannedWords.setBackground(Color.BLACK);
        bannedWords.setForeground(Color.RED);
        wordField=new JTextField("Word: "+word);
        wordField.setEditable(false);
        wordField.setBackground(Color.BLACK);
        wordField.setForeground(Color.GREEN);
        panel=new JPanel();
        pas=new JButton("PAS");
        correct=new JButton("CORRECT");
        tabu=new JButton("TABU");
        finish=new JButton("Time ended");
        ret=new JButton("Return");


        panel.setLayout(flayout);
        panel.add(pas);
        panel.add(tabu);
        panel.add(correct);
        panel.add(finish);
        panel.add(ret);


        Handler handler=new Handler();
        pas.addActionListener(handler);
        tabu.addActionListener(handler);
        correct.addActionListener(handler);
        finish.addActionListener(handler);
        ret.addActionListener(handler);

        setLayout(blayout);
        add(panel, BorderLayout.SOUTH);
        add(bannedWords, BorderLayout.CENTER);
        add(wordField,BorderLayout.NORTH);

        this.point=point;
        this.Pas=Pas;
    }
    private class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("PAS")){
                //PAS
                if(Pas==0)
                    JOptionPane.showMessageDialog(null, "All passes are used");
                else {
                    game.newWord(point, Pas - 1);
                    Tabu.this.setVisible(false);
                }
            }
            else if(e.getActionCommand().equals("TABU")){
                //TABU
                game.newWord(point-1, Pas);
                Tabu.this.setVisible(false);
            }
            else if(e.getActionCommand().equals("CORRECT")){
                //DOĞRU
                game.newWord(point+1, Pas);
                Tabu.this.setVisible(false);
            }
            else if(e.getActionCommand().equals("Time ended")){
                //Süre bitti
                JOptionPane.showMessageDialog(null,"point: "+point);
                Tabu.this.setVisible(false);
                Game game=new Game();
                game.set();
                game.start();
            }
            else if(e.getActionCommand().equals("Geri")){
                GameMenu panel=new GameMenu("Prizrak");
                panel.setSize(250,200);
                panel.setDefaultCloseOperation(EXIT_ON_CLOSE);
                panel.setVisible(true);
            }
        }
    }
}