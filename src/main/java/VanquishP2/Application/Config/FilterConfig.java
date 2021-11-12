package VanquishP2.Application.Config;

import VanquishP2.Application.Beans.Filters.AuthenticationFilter;
import VanquishP2.Application.Beans.Filters.CORSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class FilterConfig {

    @Bean
    @SuppressWarnings({"rawtypes, unchecked"})
    public FilterRegistrationBean authRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AuthenticationFilter());
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return filterRegistrationBean;
    }

    @Bean
    @SuppressWarnings({"rawtypes, unchecked"})
    public FilterRegistrationBean corsRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new CORSFilter());
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return filterRegistrationBean;
    }
}
