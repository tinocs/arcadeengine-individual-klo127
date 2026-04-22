package breakout;

import engine.World;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class BallWorld extends World {
    private Paddle paddle;
    private Score score;
    public BallWorld() {
        setPrefSize(800,600);

    }

    @Override
    public void act(long now) {

    }

    @Override
    public void onDimensionsInitialized() {
        score = new Score();
        score.setX(20);
        score.setY(40);
        getChildren().addAll(score);
        Ball ball = new Ball();

        add(ball);
        ball.setX(getWidth()/2);
        ball.setY(getHeight()/2);

        paddle = new Paddle();
        add(paddle);
        paddle.setX(getWidth()/2-paddle.getWidth()/2);
        paddle.setY(getHeight()*7/8-paddle.getHeight()/2);
        this.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                paddle.setX(event.getX()-paddle.getWidth()/2);
            }
        });
        int rows=5;
        int cols=10;
        Brick b = new Brick();
        double x=(getWidth()-cols*b.getWidth())/2;
        double y=getHeight()/8;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                Brick brick=new Brick();
                add(brick);
                brick.setX(j*b.getWidth()+x);
                brick.setY(i*b.getHeight()+y);
            }
        }
    }
    public Score getScore(){
        return score;
    }
}

