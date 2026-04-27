package breakout;

import engine.Sound;
import engine.World;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BallWorld extends World {
    private Paddle paddle;
    private Ball ball;
    private Score score;
    private Lives lives;
    private int level;
    private Image brick2;
    private Sound won;
    private Text message;

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    private boolean isOver;

    public Ball getBall() {
        return ball;
    }
    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    private boolean isPaused;
    public BallWorld() {
        setPrefSize(700,500);
        level = 1;
        isPaused = true;
        brick2 = new Image(getClass().getResource("/breakoutresources/brick2.png").toString());
        won = new Sound("ballbounceresources/game_won.wav");
        isOver=false;
        message = new Text("");
        message.setFont(new Font(30));
        message.setVisible(false);
        getChildren().addAll(message);
    }

    @Override
    public void act(long now) {
        if(getObjects(Brick.class).isEmpty()){
            isPaused = true;
            if(level ==1){
                createBrickRect(3,4,getWidth()/4,getHeight()/6);
                createBrickRect(3,4,getWidth()*3/4,getHeight()/6);
                createBrickRect(2,10,getWidth()/2,getHeight()/3);
                level++;
            }else if(level==2){
                createBrickRect(4,4,getWidth()/4,getHeight()/6);
                createBrickRect(4,4,getWidth()*3/4,getHeight()/6);
                createBrickRect(4,4,getWidth()/4,getHeight()/3);
                createBrickRect(4,4,getWidth()*3/4,getHeight()/3);
                level++;
            }else if(level ==3){
                isOver= true;
                isPaused = true;
                won.play();
                level++;
                showMessage("You Win! Press Space to Try Again!");
            }
        }
        if (isOver && isKeyPressed(KeyCode.SPACE)) {
            Breakout.getS().setScene(Breakout.getMainMenu());
        }

    }
    public void showMessage(String text) {
        message.setText(text);
        message.setX(getWidth()/2 - message.getLayoutBounds().getWidth()/2);
        message.setY(getHeight()/2);
        message.setVisible(true);
    }

    @Override
    public void onDimensionsInitialized() {
        score = new Score();
        score.setX(20);
        score.setY(35);
        lives = new Lives();
        lives.setX(20);
        lives.setY(65);
        getChildren().addAll(score,lives);
        ball = new Ball();

        add(ball);
        ball.setX(getWidth()/2);
        ball.setY(getHeight()/2);

        paddle = new Paddle();
        add(paddle);
        paddle.setX(getWidth()/2-paddle.getWidth()/2);
        paddle.setY(getHeight()*9/10-paddle.getHeight()/2);
        this.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                paddle.setX(event.getX()-paddle.getWidth()/2);
            }
        });

    }
    public Score getScore(){
        return score;
    }

    public Lives getLives() {
        return lives;
    }

    public void createBrickRect(int rows, int cols, double x, double y){
        Brick b = new Brick();
        double sx = x-cols*b.getWidth()/2;
        double sy = y-rows*b.getHeight()/2;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                Brick brick=new Brick();
                brick.setX(j*b.getWidth()+sx);
                brick.setY(i*b.getHeight()+sy);
                if(i==0||i==rows-1||j==0||j==cols-1){
                    brick.setImage(brick2);
                }
                add(brick);
            }
        }
    }
}


