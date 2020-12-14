package fri.uni_lj.si.statisticsService.interceptors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger(MyInterceptor.class);

    @Value("${spring.application.name}")
    String appName;

    @Value("${env.type}")
    String envType;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info(appName + " :: " + envType + " :: ENTRY :: " + request.getMethod() + " :: " + request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info(appName + " :: " + envType + " :: EXIT :: " + request.getMethod() + " :: " + request.getRequestURI());
    }
}
