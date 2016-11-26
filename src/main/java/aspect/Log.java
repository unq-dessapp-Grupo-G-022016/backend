package aspect;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class Log {

    private static Logger log = Logger.getLogger(Log.class.getName());

    public Log() {

        PropertyConfigurator.configure("src/main/java/log4j.properties");
    }

    public Object time(ProceedingJoinPoint point) throws Throwable {

        MethodSignature signature = (MethodSignature) point.getSignature();
        String nameMethod = signature.getMethod().getName();
        log.info("timeInitial: " + nameMethod);
        this.printParameters(point.getArgs());
        Long timeInitial = System.currentTimeMillis();
        Object response = point.proceed();
        Long timeEnd = System.currentTimeMillis();
        log.info("timeEnd: " + nameMethod);
        Long total = timeEnd - timeInitial;
        log.info("total: " + total);
        return response;
    }

    public void printParameters(Object[] objects) {
        log.info("Parameters");

        for (Object o : objects) {
            log.info(o.toString());
        }

        log.info("Parameters");

    }

}
