package block.breaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;


public class Gameplay extends JPanel implements ActionListener, KeyListener{
    private boolean play= false;
    private int score=0;
    private int totalBrick= 21;
    private Timer timer;
    private int delay=8;
    private int ballposX=120;
    private int ballposY=350;
    private int ballXdir= -1;
    private int ballYdir= -2;
    private int playerX= 200;
    private MapGenerator map;
    
    
   public Gameplay(){
       addKeyListener(this);
       setFocusable(true);
       setFocusTraversalKeysEnabled(true);
 
       
       timer = new Timer(delay,this);
       timer.start();
       
       
       map= new MapGenerator(3,7);
   }
   
   public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        
        //paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 450, 100, 8);
        
        //ball
        g.setColor(Color.red);
        g.fillOval(ballposX, ballposY, 20, 20);
        
        //bricks
        map.draw((Graphics2D) g);
        
        //score
        g.setColor(Color.red);
        g.drawString("Score:"+score, 550, 30);
        
        //game over
        if(ballposY>=570){
            play=false;
            ballXdir=0;
            ballYdir=0;
            
            g.setColor(Color.green);
            g.drawString("GAME OVER , SCORE:"+score, 200, 350);
            g.drawString("Press Enter To Play Again", 230, 400);
        }
        
        //player win
        if(totalBrick<=0){
                play=false;
            ballXdir=0;
            ballYdir=0;
            
            g.setColor(Color.green);
            g.drawString("YOU WON , SCORE:"+score, 200, 350);
            g.drawString("Press Enter To Play Again", 230, 400);
   
        }

   }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(play){
            
            if(ballposX<=0){
                ballXdir=-ballXdir;}
            
            if(ballposY<=0){
                ballYdir=-ballYdir;}
            
            if(ballposX>=670){
                ballXdir=-ballXdir;}
            
            Rectangle ballRect = new Rectangle(ballposX, ballposY,20,20);
            Rectangle paddleRect = new Rectangle(playerX,550,100,8);
            
            
            
            A:for(int i=0; i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++)
                {
                    if(map.map[i][j]>0){
                        
                        int width=map.brickWidth;
                        int height=map.brickHeight;
                        int brickXpos=80+j*width;
                        int brickYpos=50+i*height;
                        
                        Rectangle brickRect = new Rectangle(brickXpos, brickYpos, width, height);
                        
                        if(ballRect.intersects(brickRect)){
                            map.setBrick(0, i, j);
                            totalBrick--;
                            score+=5;
                        
                            if(ballposX+90<=brickXpos || ballposX+1>=brickXpos+width)
                                ballXdir=-ballXdir;
                        }
                        else{
                            ballYdir=-ballYdir;
                        }
                        
                        break A;
                    }
                }
            }
                  
            if(ballRect.intersects(paddleRect)){
                ballYdir=-ballYdir;}
            
            ballposX+=ballXdir;
            ballposY+=ballYdir;
        }
        repaint();
    }
    
    private void moveLeft(){
        play=true;
        playerX=-20;
    }
    
    private void moveRight(){
        play=true;
        playerX=+20;
    }

   
    @Override
    public void keyPressed(KeyEvent e) {
       if(e.getKeyCode()==KeyEvent.VK_LEFT){
           if(playerX<=0){
               playerX=0;
           }else{
               moveLeft();
           }
       }
       
       if(e.getKeyCode()==KeyEvent.VK_RIGHT){
              if(playerX>=600){
                  playerX=600;
              }else{
                  moveRight();
              }
       }
       
       if(e.getKeyCode()==KeyEvent.VK_ENTER){
              if(!play){
               score=0;
              totalBrick=21;
              ballposX=120;
              ballposY=350;
              ballXdir= -1;
              ballYdir= -2;
              playerX= 200;
              
              map=new MapGenerator(3,7);
       }
       }
       repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
       }

    @Override
    public void keyReleased(KeyEvent e) {
        }

  
}
