import javax.swing.*;

public class Main {

    public Main() {
        int level = JOptionPane.showConfirmDialog(null,
                "Do you want to play the game on hard mode?",
                "Difficulty level",
                JOptionPane.YES_NO_OPTION);
        JFrame frame = new JFrame();
        Gameplay g = new Gameplay(level);
        frame.add(g);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(level == 0) {
            frame.setTitle("SNAKE (Hard Mode)");
        }else if(level == 1){
            frame.setTitle("SNAKE (Easy Mode)");
        }
        frame.setLocationRelativeTo(null);


    }

    public static void main(String[] args) {
        new Main();
    }


}


