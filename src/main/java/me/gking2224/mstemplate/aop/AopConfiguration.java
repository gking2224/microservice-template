package me.gking2224.mstemplate.aop;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import me.gking2224.common.aop.CommonAopConfiguration;

@EnableAspectJAutoProxy(proxyTargetClass=true)
@Import(CommonAopConfiguration.class)
public class AopConfiguration {
    
}
