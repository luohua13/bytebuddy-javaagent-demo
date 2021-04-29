package cn.alauda.agent;

import net.bytebuddy.asm.Advice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

public class AddMethod {

    /**
     * This method3 will add to the Method class
     */
//    @Advice.OnMethodEnter
//    public static void index(@Advice.AllArguments Object[] args) throws Exception {
//
//        System.out.println("This is new method : method 3");
//        ((HttpServletResponse) args[3]).setHeader("X-My-Super-Header", "header value");
//    }
    @Advice.OnMethodEnter
    public static void index() throws Exception {

        System.out.println("happy!!!");
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        response.addHeader("id", "1234567890");
    }


    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("name", "luohua");
    }

}