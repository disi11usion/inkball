package inkball.entity.CollideLine;


import java.util.ArrayList;
import java.util.List;

public class MouseLine {
    public List<Line> mouseLines =new ArrayList<>();
    public void drawLines(){
        for (Line line : mouseLines) {
        line.drawLine();
        }
    }

}
