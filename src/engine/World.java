package engine;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.*;

public abstract class World extends Pane {
    private AnimationTimer timer;
    private boolean timerRunning;
    private Set<KeyCode> keysPressed;
    private boolean widthSet;
    private boolean heightSet;

    public World(){
        widthSet =false;
        heightSet = false;
        keysPressed = new HashSet<KeyCode>();
        timerRunning = false;
        widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.intValue()>0){
                    widthSet = true;
                }
                if(widthSet&&heightSet){
                    onDimensionsInitialized();
                }
            }
        });
        heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.intValue()>0){
                    heightSet = true;
                }
                if(widthSet&&heightSet){
                    onDimensionsInitialized();
                }
            }
        });
        sceneProperty().addListener(new ChangeListener<Scene>() {
            @Override
            public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
                requestFocus();
            }
        });
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keysPressed.add(event.getCode());
            }
        });
        setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keysPressed.remove(event.getCode());
            }
        });
        timer = new AnimationTimer(){

            @Override
            public void handle(long now) {
                act(now);
                for(Actor n: getObjects(Actor.class)){
                    if(n.getWorld()!=null){
                        n.act(now);
                    }

                }
            }
        };

    }
    //This method is called every frame once start has been called.
    public abstract void act(long now);

    //Adds the given actor to the world and then calls the addedToWorld() method on the actor that was added.
    public void add(Actor actor){
        getChildren().addAll(actor);
        actor.addedToWorld();
    }

    //Returns a list of all the actors in the world of the given class.
    public <A extends Actor> java.util.List<A>	getObjects(java.lang.Class<A> cls){
        List<A> list = new ArrayList<A>();
        for(Node n: getChildren()){
            if(cls.isInstance(n)){
                list.add(cls.cast(n));
            }
        }
        return list;
    }

    //Returns a list of all actors of the given class containing the given x, y
    public <A extends Actor> java.util.List<A>	getObjectsAt(double x, double y, java.lang.Class<A> cls){
        List<A> a = new ArrayList<>();
        for (Node n : getChildren()) {
            if (cls.isInstance(n)) {
                A actor = cls.cast(n);
                if (actor.getBoundsInParent().contains(x, y)) {
                    a.add(actor);
                }
            }
        }
        return a;
    }


    //Returns true if the given key is pressed and false otherwise.
    public boolean	isKeyPressed(javafx.scene.input.KeyCode code){
        return keysPressed.contains(code);
    }

    //Returns whether or not the world's timer is stopped
    public boolean isStopped(){
        return !timerRunning;
    }

    //Subclasses should override this.
    public abstract void onDimensionsInitialized();

    //Removes the given actor from the world.
    public void	remove(Actor actor){
        getChildren().removeAll(actor);
    }

    //STARTS the timer that calls the act method on the world and on each Actor in the world each frame.
    public void	start(){
        timerRunning=true;
        timer.start();

    }

    //STOPS the timer that calls the act method on the world and on each Actor in the world each frame.
    public void	stop(){
        timerRunning=false;
        timer.stop();
    }



}
