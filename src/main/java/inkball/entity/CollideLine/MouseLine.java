package inkball.entity.CollideLine;


import java.util.ArrayList;
import java.util.List;

public class MouseLine {
    public List<Line> allMouseLines=new ArrayList<>();
    public void drawLines(){
        for (Line line : allMouseLines) {
        line.drawLine();
        }
    }

}
