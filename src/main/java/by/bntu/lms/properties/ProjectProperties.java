package by.bntu.lms.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {
    public Properties loadProjectProperties() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream("./configurations/project.properties")) {
            properties.load(in);
        }
        return properties;
    }
}
