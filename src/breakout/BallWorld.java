package breakout;

import engine.World;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class BallWorld extends World {
    private Paddle paddle;
    private Score score;
    private Lives lives;
    int level;
    public BallWorld() {
        setPrefSize(700,500);
        level = 1;

    }

    @Override
    public void act(long now) {
        if(getObjects(Brick.class).isEmpty()){
            if(level ==1){
                createBrickRect(3,3);
                level++;
            }else if(level==2){
                createBrickRect(6,7);
                level++;
            }
        }
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
        Ball ball = new Ball();

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

    public void createBrickRect(int rows, int cols){

        Brick b = new Brick();
        double x=(getWidth()-cols*b.getWidth())/2;
        double y=getHeight()/8;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){

                Brick brick=new Brick();

                brick.setX(j*b.getWidth()+x);
                brick.setY(i*b.getHeight()+y);
                if(i==0||i==rows-1||j==0||j==cols-1){
                    brick.setImage(new Image("/breakoutresources/brick2.png"));
                }
                add(brick);
            }
        }
    }
}


