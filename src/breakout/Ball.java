package breakout;

import engine.Actor;
import engine.Sound;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Ball extends Actor {
    private int dx;
    private int dy;
    Image image;
    Sound bounce;
    Sound hit;
    Sound loseLife;
    Sound lost;


    public Ball(){
        image = new Image(getClass().getResource("/breakoutresources/ball.png").toString());
        dx = 4;
        dy = -4;
        setImage(image);
        bounce = new Sound("ballbounceresources/ball_bounce.wav");
        hit = new Sound("ballbounceresources/brick_hit.wav");
        loseLife = new Sound("ballbounceresources/lose_life.wav");
        lost = new Sound("ballbounceresources/game_lost.wav");
    };
    @Override
    public void act(long now) {
        SpaceWorld w = (SpaceWorld) getWorld();
        if(w.isPaused()) {
            if (w.isKeyPressed(KeyCode.SPACE)&&!w.isOver()) {
                dy=-5;
                w.setPaused(false);
            }
        }else if(!w.isOver()){
            move(dx,dy);
            if(getX()<=0){
                bounce.play();
                setX(0);
                dx =Math.abs(dx);
            }
            if((getX()+getWidth())>getWorld().getWidth()){
                setX(getWorld().getWidth()-getWidth());
                bounce.play();
                dx =-Math.abs(dx);
            }
            if(getY()<=0){
                bounce.play();
                dy =-dy;
            }
            if((getY()+getHeight())>getWorld().getHeight()){
                loseLife.play();
                dy =-dy;
                SpaceWorld world = (SpaceWorld) getWorld();
                world.getLives().setValue(world.getLives().getValue()-1);
                ((SpaceWorld) getWorld()).setPaused(true);
                if(world.getLives().getValue()==0){
                    lost.play();
                    world.setOver(true);
                    world.setPaused(true);
                    world.showMessage("You Ran Out of Lives. Press Space to Try Again.");
                }
            }
            if(getOneIntersectingObject(Spaceship.class)!=null){
                bounce.play();
                dy = -dy;
            }
            Brick brick = getOneIntersectingObject(Brick.class);
            if(brick!=null&&brick.getOpacity()>=1.0){
                hit.play();
                if(getX()>=brick.getX()&&getX()<=(brick.getX()+brick.getWidth())){
                    dy= -dy;
                }else if(getY()>=brick.getY()&&getY()<=(brick.getY()+brick.getHeight())){
                    dx= -dx;
                }else{
                    dy= -dy;
                    dx = -dx;
                }
                FadeTransition fade = new FadeTransition(Duration.millis(150), brick);
                fade.setToValue(0.0);
                fade.setOnFinished(e -> {
                    getWorld().remove(brick);
                });
                fade.play();
                SpaceWorld world =(SpaceWorld)getWorld();
                world.getScore().setValue(world.getScore().getValue()+100);
            }
        }

    }
}
