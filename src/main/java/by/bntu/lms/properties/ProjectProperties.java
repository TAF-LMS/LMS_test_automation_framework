package by.bntu.lms.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {
    private static Properties properties;

    public static Properties getInstance() {
        if (properties == null) {
            properties = new Properties();
            try (FileInputStream in = new FileInputStream("./configurations/project.properties")) {
                properties.load(in);
            } catch (IOException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Can not read property file");
            }
            return properties;
        }
        return properties;
    }
}
