package by.zemich.userservice.aop;

import org.aspectj.lang.annotation.Pointcut;

public class WebUserServicePointCuts {
    @Pointcut("execution(* by.zemich.userservice.service.impl.UserWebServiceImpl.*(..))")
    public void allMethod(){}
}
