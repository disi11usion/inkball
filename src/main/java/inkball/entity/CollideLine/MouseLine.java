package inkball.entity.CollideLine;


import java.util.ArrayList;
import java.util.List;

public class MouseLine {
    public List<CurrentLine> allMouseLines=new ArrayList<>();
    public void drawLines(){
        for (CurrentLine currentLine : allMouseLines) {
        currentLine.drawCurrentLine();
        }
    }

}
