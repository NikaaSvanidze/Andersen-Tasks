package Configs;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThisIsMyFirstConditionalBean {
    @Bean
    @ConditionalOnProperty(name = "my.custom.bean.enabled", havingValue = "true", matchIfMissing = false)
    public ThisIsMyFirstConditionalBean thisIsMyFirstConditionalBean() {
        return new ThisIsMyFirstConditionalBean();
    }
}
