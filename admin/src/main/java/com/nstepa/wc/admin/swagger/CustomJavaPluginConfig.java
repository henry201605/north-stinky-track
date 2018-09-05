package com.nstepa.wc.admin.swagger;

import com.google.common.collect.Lists;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.models.dto.ResponseMessage;
import com.mangofactory.swagger.ordering.ResourceListingPositionalOrdering;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableSwagger
public class CustomJavaPluginConfig {
    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean //Don't forget the @Bean annotation
    public SwaggerSpringMvcPlugin customImplementation() {
        SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig);

        swaggerSpringMvcPlugin.apiInfo(apiInfo())
                .globalResponseMessage(RequestMethod.GET, Lists.newArrayList(new ResponseMessage(200, "成功", null), new ResponseMessage(400, "请求异常", null), new ResponseMessage(500, "服务器异常", null), new ResponseMessage(600, "Not Login", null)))
                .globalResponseMessage(RequestMethod.POST, Lists.newArrayList(new ResponseMessage(200, "成功", null), new ResponseMessage(400, "请求异常", null), new ResponseMessage(500, "服务器异常", null), new ResponseMessage(600, "Not Login", null)))
                .apiListingReferenceOrdering(new ResourceListingPositionalOrdering())
        ;

        return swaggerSpringMvcPlugin;
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "NorthStinkyTrack API",
                "NorthStinkyTrack 后台文档",
                "http://www.xxx.com",
                "xxx@gmail.com",
                "Apache License",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );
        return apiInfo;
    }

}