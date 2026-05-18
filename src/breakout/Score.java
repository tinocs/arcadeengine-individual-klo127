package breakout;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
public class Score extends Text {
    private int val;
    public Score(){
        val = 0;
        setFont(new Font(20));
        setFill(Color.WHITE);
        updateDisplay();
    }
    public void setValue(int value){
        val = value;
        updateDisplay();
    }
    public int getValue(){
        return val;
    }
    public void updateDisplay(){
        setText("Score: "+val);

    }

}

