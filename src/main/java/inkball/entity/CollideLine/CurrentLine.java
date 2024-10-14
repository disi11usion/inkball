package inkball.entity.CollideLine;


import inkball.App;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class CurrentLine {
    private List<PVector> currentLine = new ArrayList<>();
    private static App app;

    public List<PVector> getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(List<PVector> currentLine) {
        this.currentLine = currentLine;
    }

    public void drawCurrentLine() {
        for (int i = 0; i < currentLine.size() - 1; i++) {
            PVector mousePoint1 = currentLine.get(i);
            PVector mousePoint2 = currentLine.get(i + 1);
            app.line(mousePoint1.x, mousePoint1.y, mousePoint2.x,
                    mousePoint2.y);
            app.strokeWeight(10);
        }
    }

    public static void setApp(App app) {
        CurrentLine.app = app;
    }
}
