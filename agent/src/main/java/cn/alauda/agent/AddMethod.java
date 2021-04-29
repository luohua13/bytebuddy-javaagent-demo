package cn.alauda.agent;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


public class AddMethod {
//    @RequestMapping("/hello")
//    @ResponseBody
    public static String hello() {
        return "hello world yy!";
    }

}
