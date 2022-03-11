package api.config;


import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class EnvConfiguration {


    private final Map<String, String> environment;

    public EnvConfiguration() {
        Map<String, String> env = new HashMap<>();
        env.put("SECRET_KEY", "12345joipuiu98uoiupoiuhpuhhoiuy9uuhi98hliugfkjgliulkjlkjhliuhlkhlkj");
        this.environment = env;
    }

    public String getProperty(String key) {
        return environment.get(key);
    }

}
