
package block.breaker;

import javax.swing.JFrame;

public class BlockBreaker {

    public static void main(String[] args) {
        JFrame frame = new JFrame ("Block Breaker");
                        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setVisible(true);
        frame.setSize(700,600);
        
        frame.setResizable(false);
        
        Gameplay gamePlay= new Gameplay();
        frame.add(gamePlay);
    }
    
}
