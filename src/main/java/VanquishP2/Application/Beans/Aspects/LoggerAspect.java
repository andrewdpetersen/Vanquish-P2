/*
package VanquishP2.Application.Beans.Aspects;

import VanquishP2.Application.Beans.Models.Logger;
import VanquishP2.Application.Beans.Service.JWTUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

*/
/**
 * LoggerAspect
 * Logs all method calls and exceptions for all classes except filters.
 *
 * @author Kollier Martin
 *//*


@Aspect
@Component
public class LoggerAspect {
    private ApplicationContext context;
    private final Logger logger = container.getBean(JWTUtil.class);

    @PostConstruct
    private void init(ApplicationContext context){
        this.context = context;
        context.getBean(Logger.class);
    }

    @Pointcut("within(VanquishP2.*) && !within(VanquishP2.Application.Beans.Filters.*)")
    public void logAll() {}

    @Before("logAll()")
    public void logMethodStart(JoinPoint jp) {
        String methodSig = extractMethodSignature(jp);
        String argStr = Arrays.toString(jp.getArgs());
        logger.info("{} invoked at {}", methodSig, System.currentTimeMillis());
        logger.info("Input arguments: {}", argStr);
    }

    @AfterReturning(pointcut = "logAll()", returning = "returnedObj")
    public void logMethodReturn(JoinPoint jp, Object returnedObj) {
        String methodSig = extractMethodSignature(jp);
        logger.info("{} successfully returned at {}", methodSig, System.currentTimeMillis());
        logger.info("Object returned: {}", returnedObj);
    }

    @AfterThrowing(pointcut = "logAll()", throwing = "e")
    public void logMethodException(JoinPoint jp, Throwable e) {
        String methodSig = extractMethodSignature(jp);
        logger.warn("{} was thrown in method {} at {} with message: {}", e.getClass().getSimpleName(), methodSig, System.currentTimeMillis(), e.getMessage());
    }

    private String extractMethodSignature(JoinPoint jp) {
        return jp.getTarget().getClass().toString() + "." + jp.getSignature().getName();
    }
}
*/
