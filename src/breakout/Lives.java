package breakout;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
public class Lives extends Text {
    private int val;
    public Lives(){
        val =3;
        setFill(Color.WHITE);
        setFont(new Font(20));
        updateDisplay();

    }
    public int getValue() {
        return val;
    }

    public void setValue(int val) {
        this.val = val;
        updateDisplay();
    }
    public void updateDisplay(){
        setText("Lives: "+val);

    }


}