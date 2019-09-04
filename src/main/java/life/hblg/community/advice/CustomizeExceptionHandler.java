package life.hblg.community.advice;


import com.alibaba.fastjson.JSON;
import life.hblg.community.dto.ResultDTO;
import life.hblg.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//自定义处理异常  用于上下文中业务的异常
@ControllerAdvice
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
    @ResponseBody
    @ExceptionHandler(Exception.class)  //该异常处理返回一个 ModelAndView 等价于Controller的返回值  @XXXMapping注解方法的返回值
    ResultDTO handle( Throwable ex, Model model,HttpServletRequest request,
                        HttpServletResponse response) {
        String contentType = request.getContentType(); //拿到contentType
        System.out.println (contentType+"********" );
        if(contentType.contains ( "application/json" )){
            ResultDTO resultDTO=null;
            //返回json  错误信息
            if(ex instanceof  CustomizeException)  //如果ex 是自定义Exception 的实例的话
            {
                resultDTO = ResultDTO.errorOf ((CustomizeException)ex );
            }else {
                ex.printStackTrace ();
            }
            try {
                response.setContentType("application/json");//设置相应头
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write( JSON.toJSONString(resultDTO));
                writer.close();
            }catch (Exception e) {
            }
            return null;
        }else {
            //返回html
            return ResultDTO.errorOf ( 999,"异常" );
/*
            if(ex instanceof  Exception)  //如果ex 是Exception 的实例的话
            {
                ex.printStackTrace ();

                model.addAttribute ( "msg", ex.getMessage () );
            }else {
                model.addAttribute ( "msg", "服务器崩溃了 信息来源于 CustomizeExceptionHandler" );
            }
            return new ModelAndView("error");*/

        }

    }

}

