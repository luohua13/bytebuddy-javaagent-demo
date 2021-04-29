package cn.alauda.agent;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.Assert;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class AgentMain {
    public static void premain(String agentArgs, Instrumentation inst) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("this is an perform monitor agent.");

        Class<?> dynamicType  = new ByteBuddy()
                .subclass(Object.class)
                .name("cn.alauda.app2.metrics.ServiceName")
                .defineMethod("hello", String.class, Modifier.PUBLIC)
                //.intercept(FixedValue.value("Hello World ByteBuddy!"))
                .intercept(MethodDelegation.to(AddMethod.class))
                .make()
                .load(AgentMain.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        Object instance = dynamicType.newInstance();
        Method method = instance.getClass().getMethod("hello");

        System.out.println(method.invoke(instance));

    }
}
