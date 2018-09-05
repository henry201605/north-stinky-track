package com.nstepa.wc.springboot.support.annotations;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SpringbootAppSettings {
}
