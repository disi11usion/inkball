package inkball;

import inkball.config.GameConfig;
import inkball.layout.Layout;
import org.junit.jupiter.api.Test;

public class SampleTest {

    //    check json read
    @Test
    public void JsonReadTest() {
        GameConfig config = new GameConfig("config.json");
        System.out.println(config);
    }

    //    check GameConfig can read layout or not
    @Test
    public void LayoutReadTest() {
        GameConfig config = new GameConfig("config.json");
        Layout layout = new Layout(config.getLevels().get(1));
        System.out.println(layout);
    }

}

// gradle run						Run the program
// gradle test						Run the testcases

// Please ensure you leave comments in your testcases explaining what the testcase is testing.
// Your mark will be based off the average of branches and instructions code coverage.
// To run the testcases and generate the jacoco code coverage report: 
// gradle test jacocoTestReport
