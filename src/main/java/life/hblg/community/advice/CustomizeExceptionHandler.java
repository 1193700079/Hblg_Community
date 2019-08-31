package life.hblg.community.advice;


import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//自定义处理异常  用于上下文中业务的异常
@ControllerAdvice("life.hblg.community.controller")
public class CustomizeExceptionHandler {

    //扫描处理所有异常类
  /*  @ExceptionHandler(Exception.class)  //该异常处理返回一个 ModelAndView 等价于Controller的返回值  @XXXMapping注解方法的返回值
   ModelAndView handle( Throwable ex, Model model) {
        ModelAndView modelAndView = new ModelAndView ("error");
        if(ex instanceof  Exception)  //如果ex 是Exception 的实例的话
        {
            modelAndView.addObject ( "msg", ex.getMessage ());
        }else {
            modelAndView.addObject ( "msg", "服务器崩溃了 信息来源于 CustomizeExceptionHandler");
        }
        return modelAndView;
    }*/
    @ExceptionHandler(Exception.class)  //该异常处理返回一个 ModelAndView 等价于Controller的返回值  @XXXMapping注解方法的返回值
    ModelAndView handle( Throwable ex, Model model) {
        if(ex instanceof  Exception)  //如果ex 是Exception 的实例的话
        {
            model.addAttribute ( "msg", ex.getMessage () );
        }else {
            model.addAttribute ( "msg", "服务器崩溃了 信息来源于 CustomizeExceptionHandler" );
        }
        return new ModelAndView("error");
    }

}

