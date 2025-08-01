package com.example.ecommerce.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
<<<<<<< HEAD
        config.setAllowCredentials(true); // allows cookies
        config.setAllowedOrigins(Arrays.asList("https://wonderful-paletas-fde7bc.netlify.app"));
        config.setAllowedHeaders(Arrays.asList("*"));
=======
        config.setAllowCredentials(true); 
        config.setAllowedOrigins(Arrays.asList("*"));  // ❗ For testing only

        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept", "Origin", "X-Requested-With"));
        config.setExposedHeaders(Arrays.asList("Authorization"));
>>>>>>> branch 'master' of https://github.com/Swaroop-sys/Sikho-App.git
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
