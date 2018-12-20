package com.zhao.conf;

import com.zhao.util.CreateValidateCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aotu
 * @date 2018/12/20 15:24
 */
@Configuration
public class CodeConf {

    @Bean
    public CreateValidateCode createValidateCode() {
        CreateValidateCode vCode = new CreateValidateCode(100, 25, 4, 10);
        return vCode;
    }
}
