package inkball.utils;

import processing.data.JSONObject;

import java.io.File;

import static processing.core.PApplet.loadJSONObject;

public class JsonConfigReader {
      public static JSONObject CreateJsonConfig(String filePath) {
        return loadJSONObject(new File(filePath));
    }
}
