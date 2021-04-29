package cn.alauda.agent;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Modifier;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

public class AgentMain {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("this is an perform monitor agent.");

        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
                return builder
                        .method(ElementMatchers.named("index"))
                        //.method(ElementMatchers.<MethodDescription>any()) // 拦截任意方法
                        .intercept(Advice.to(AddMethod.class)); // 委托
                        //.intercept(MethodDelegation.to(AddMethod.class))
                        //.method(ElementMatchers.nameContains("index"))
                        //.intercept(SuperMethodCall.INSTANCE
                          //      .andThen(MethodCall.invoke(ElementMatchers.nameContains("method3"))));
            }
        };

        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {

            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {

            }

            @Override
            public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }
        };

        new AgentBuilder
                .Default()
                .type(ElementMatchers.nameStartsWith("cn.alauda.app2")) // 指定需要拦截的类
                .transform(transformer)
                .with(listener)
                .installOn(inst);
    }

}
