package com.nstepa.wc.admin.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAop {

    protected Logger logger = LoggerFactory.getLogger(ControllerAop.class);

    /**
     * Pointcut定义切点函数
     */
    @Pointcut("within(com.nstepa.wc.admin.controller..*)")
    private void controllerPointcut() {
    }


    @Before("controllerPointcut()")
    public void before(JoinPoint joinPoint) {
        info(joinPoint);
    }

    private void info(JoinPoint joinPoint) {
        logger.info("controller aop start --------------------------------------------------");
        logger.info("King:\t" + joinPoint.getKind());
        logger.info("Target:\t" + joinPoint.getTarget().toString());
        Object[] os = joinPoint.getArgs();
        logger.info("Args length:" + os.length);
        logger.info("Args:");
        for (int i = 0; i < os.length; i++) {
            logger.info("\t==>参数[" + i + "]:\t" + os[i]);
        }
        try {
//            logger.info("userId: " + UserRequest.getCurrentUser().getUid());
//            logger.info("schoolId: " + UserRequest.getCurrentUser().getSchoolId());
        } catch (Exception e) {
        }
        logger.info("Signature:\t" + joinPoint.getSignature());
        logger.info("SourceLocation:\t" + joinPoint.getSourceLocation());
        logger.info("StaticPart:\t" + joinPoint.getStaticPart());
        logger.info("controller aop stop --------------------------------------------------");
    }
}
