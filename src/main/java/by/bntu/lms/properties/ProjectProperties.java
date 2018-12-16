package by.bntu.lms.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {
    private static Properties properties;

    public static Properties getInstance() throws IOException {
        if (properties == null) {
            properties = new Properties();
            try (FileInputStream in = new FileInputStream("./configurations/project.properties")) {
                properties.load(in);
            }
            return properties;
        }
        return properties;
    }
}
