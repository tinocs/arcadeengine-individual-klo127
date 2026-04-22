package breakout;

import engine.World;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BallWorld extends World {
    private Paddle paddle;

    public BallWorld() {
        setPrefSize(800,600);
    }

    @Override
    public void act(long now) {

    }

    @Override
    public void onDimensionsInitialized() {
        Ball ball = new Ball();

        add(ball);
        ball.setX(getWidth()/2);
        ball.setY(getHeight()/2);

        paddle = new Paddle();
        add(paddle);
        paddle.setX(getWidth()/2-paddle.getWidth()/2);
        paddle.setY(getHeight()*5/6-paddle.getHeight()/2);
        this.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                paddle.setX(event.getX()-paddle.getWidth()/2);
            }
        });
    }
}

