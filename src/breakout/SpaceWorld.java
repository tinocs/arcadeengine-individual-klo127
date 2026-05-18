package breakout;

import engine.Actor;
import engine.Sound;
import engine.World;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SpaceWorld extends World {
    private Spaceship spaceship;
    private Ball ball;
    private Score score;
    private Lives lives;
    private int level;
    private Image brick2;
    private Sound won;
    private Text message;



    private Image backgroundImage;
    private ImageView background;

    public Image getBImage() {
        return backgroundImage;
    }

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

    public Spaceship getPaddle() {
        return spaceship;
    }

    private boolean isPaused;
    public SpaceWorld() {
        setPrefSize(700,500);
        level = 1;
        isPaused = true;
        brick2 = new Image(getClass().getResource("/breakoutresources/brick2.png").toString());
        won = new Sound("ballbounceresources/game_won.wav");
        isOver=false;

    }

    @Override
    public void act(long now) {
//        if(getObjects(Brick.class).isEmpty()){
//            isPaused = true;
//            if(level ==1){
//                createBrickRect(3,4,getWidth()/4,getHeight()/6);
//                createBrickRect(3,4,getWidth()*3/4,getHeight()/6);
//                createBrickRect(2,10,getWidth()/2,getHeight()/3);
//                level++;
//            }else if(level==2){
//                createBrickRect(4,4,getWidth()/4,getHeight()/6);
//                createBrickRect(4,4,getWidth()*3/4,getHeight()/6);
//                createBrickRect(4,4,getWidth()/4,getHeight()/3);
//                createBrickRect(4,4,getWidth()*3/4,getHeight()/3);
//                level++;
//            }else if(level ==3){
//                isOver= true;
//                isPaused = true;
//                won.play();
//                level++;
//                showMessage("You Win! Press Space to Try Again!");
//            }
//        }
        if (isOver && isKeyPressed(KeyCode.SPACE)) {
            stop();
            SpaceInvaders.getS().setScene(SpaceInvaders.getMainMenu());
        }

    }
    public void showMessage(String text) {
        message.setText(text);
        message.setX(getWidth()/2 - message.getLayoutBounds().getWidth()/2);
        message.setY(getHeight()/2);
        message.setVisible(true);
    }
    public ImageView getB(){
        return background;
    }
    @Override
    public void onDimensionsInitialized() {

        backgroundImage = new Image(getClass().getResource("/breakoutresources/background.png").toString(),1000,500,true,true);
        background = new ImageView(backgroundImage);
        background.setX(-(backgroundImage.getWidth() - getWidth()) / 2);
        score = new Score();
        score.setX(20);
        score.setY(35);
        lives = new Lives();
        lives.setX(20);
        lives.setY(65);
        getChildren().addAll(background, score, lives);
        ball = new Ball();
        ball.setX(getWidth() / 2);
        ball.setY(getHeight() / 2);
        message = new Text("");
        message.setFont(new Font(30));
        message.setVisible(false);
        getChildren().addAll(message);


        spaceship = new Spaceship();
        add(spaceship);
        spaceship.setX(getWidth() / 2 - spaceship.getWidth() / 2);
        spaceship.setY(getHeight() * 9 / 10 - spaceship.getHeight() / 2);
        this.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                spaceship.setX(event.getX() - spaceship.getWidth() / 2);
            }
        });
    }
    public Score getScore(){
        return score;
    }
    public void scroll(double dx) {
        for (Actor a : getObjects(Actor.class)) {
            a.setX(a.getX()-dx);
        }

        background.setX(background.getX() - dx);
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


