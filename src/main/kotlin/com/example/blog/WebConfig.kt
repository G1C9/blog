package com.example.blog

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.HiddenHttpMethodFilter

@Configuration
class WebConfig {

    @Bean
    fun hiddenHttpMethodFilter(): FilterRegistrationBean<HiddenHttpMethodFilter> {
        val filterRegBean = FilterRegistrationBean<HiddenHttpMethodFilter>(HiddenHttpMethodFilter())
        filterRegBean.addUrlPatterns("/*")
        return filterRegBean
    }
}