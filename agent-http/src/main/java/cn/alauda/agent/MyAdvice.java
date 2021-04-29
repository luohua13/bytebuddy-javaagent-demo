package cn.alauda.agent;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletResponse;

public class MyAdvice {

//    @Advice.OnMethodEnter
//    public static void enter(@Advice.Argument(
//            value = 3,
//            typing = Assigner.Typing.DYNAMIC) HttpServletResponse response) {
//        System.out.println("!!!!!!!!!!!");
//        response.setHeader("X-My-Super-Header", "header value");
//    }
    // 方法执行前
    @Advice.OnMethodEnter
    public static void enter(@Advice.This Object obj,
                             @Advice.AllArguments Object[] allArguments,
                             @Advice.Origin("#t") String className,
                             @Advice.Origin("#m") String methodName) {
        System.out.println(className + "." + methodName);
    }

    @Advice.OnMethodExit
    public static void exit(@Advice.This Object obj,
                            @Advice.AllArguments Object[] allArguments,
                            @Advice.Origin("#t") String className,
                            @Advice.Origin("#m") String methodName){
                          //  @Advice.Return(readOnly = false, typing = Assigner.Typing.DYNAMIC) HttpServletResponse returned) {
        System.out.println(methodName);
        /*This is use for get class of parameters to get
         *an idea what we can do using this advice
         */
        System.out.println("Class names of parameters");

        //response.addHeader("id", "232321232323");

    }
}
