package engine;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor extends ImageView {
    public Actor(){

    }
    //This method is called every frame once start has been called on the world.
    public abstract void act(long now);

    //This method is called when an actor is added to the world and should be overridden in subclasses as desired.
    public void	addedToWorld(){

    }

    //Returns The height of the current image of this actor.
    public double getHeight(){
        return getBoundsInParent().getHeight();
    }


    //Returns a list of the actors of a given type intersecting this actor.
    public <A extends Actor> java.util.List<A>	getIntersectingObjects(java.lang.Class<A> cls){
        List<A> result = new ArrayList<A>();
        List<A> actors = getWorld().getObjects(cls);
        for(A a : actors){
            if(a!=this&&a.getBoundsInParent().intersects(getBoundsInParent())){
                result.add(a);
            }
        }
        return result;
    }

    //Returns one actor of the given class that is intersecting this actor.
    public <A extends Actor> A	getOneIntersectingObject(java.lang.Class<A> cls){
        if(!getIntersectingObjects(cls).isEmpty()){
            return getIntersectingObjects(cls).get(0);
        }
        return null;

    }

    //Returns The width of the current image of this actor.
    public double getWidth(){
        return getBoundsInParent().getWidth();
    }

    //returns the world this actor is in, or null if it is not in a world.
    public World getWorld(){
        World world = (World)getParent();
        return world;
    }

    //Moves this actor by the given dx and dy.
    public void	move(double dx, double dy){
        this.setY(getY()+dy);
        this.setX(getX()+dx);
    }

}
