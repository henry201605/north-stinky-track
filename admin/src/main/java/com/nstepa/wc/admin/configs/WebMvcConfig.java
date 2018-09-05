package com.nstepa.wc.admin.configs;

import com.nstepa.wc.beans.User;
import com.nstepa.wc.commons.pojos.Response;
import com.nstepa.wc.commons.utils.CookieUtil;
import com.nstepa.wc.commons.utils.GsonUtils;
import com.nstepa.wc.springboot.support.Cookies;
import com.nstepa.wc.springboot.support.ResponseCode;
import com.nstepa.wc.springboot.support.annotations.LoginRequired;
import com.nstepa.wc.springboot.support.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter implements EnvironmentAware {

    protected Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
    private static final String DISPLAY_ERROR = "服务器开了个小差";

    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");
//        //Swagger ui Mapping
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/")
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        addCheckLoginInterceptor(registry);
    }

    /**
     * 添加
     *
     * @param registry
     */
    private void addCheckLoginInterceptor(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                if (handler instanceof HandlerMethod) {
                    HandlerMethod mHandler = (HandlerMethod) handler;
                    String token = CookieUtil.getCookieValue(request, Cookies.USER_COOKIE);
                    //如果有用户cookie，尝试获取用户信息
                    User user =  new User();

//                    ItBaseUser user = tryToLoadAccount(mHandler, request, token);

                    //如果要求做登录校验，检查信息是否正确
                    if (mHandler.hasMethodAnnotation(LoginRequired.class) && user == null) {
                        if (logger.isDebugEnabled())
                            logger.debug("[addCheckLoginInterceptor] Login required function without login message, token -> {}, url -> {}",
                                    token, request.getRequestURI());
                        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                        Response resp = BaseController.statusResponse(ResponseCode.NOT_LOGIN);
                        response.getOutputStream().write(GsonUtils.format(resp).getBytes());
                        return false;
                    }
                }

                return true;    //always true
            }
        });

    }

//    private ItBaseUser tryToLoadAccount(HandlerMethod mHandler, HttpServletRequest request, String token) throws IllegalAccessException, UnsupportedEncodingException {
//
//        if (logger.isDebugEnabled())
//            logger.debug("[tryToLoadAccount([mHandler, request, token])] Validate token -> {}", token);
//        //首先尝试用token加载
//        ItBaseUser user = null;
//        if (StringUtils.isNotEmpty(token)) {
////            String uid= new String(Base64.getDecoder().decode(token),"UTF-8");
//            user = itBaseUserMapper.selectById(Base64Util.decoder(token));
//        }
//        // 只在个人profile下添加模拟用户，与test服务器隔离开
//        if (user == null && NepaValidates.containsAny(env.getActiveProfiles(), Profiles.MABB, Profiles.ZHANGW)) {
//            // 这里通过header或其他形式，模拟一个token
////            user = new ItBaseUser();
////            user.setSchoolId(1);
//        }
//        request.setAttribute(Requests.CURRENT_USER, user);
//        return user;
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH").allowCredentials(true).maxAge(3600);
    }

}