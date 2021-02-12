package platform.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan
@Configuration
public class SpringWebConfig implements WebMvcConfigurer {
    // I'm leaving this shell because I think we'll use it later
}