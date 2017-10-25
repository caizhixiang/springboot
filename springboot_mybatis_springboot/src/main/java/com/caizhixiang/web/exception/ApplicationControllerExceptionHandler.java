package com.caizhixiang.web.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.extern.slf4j.Slf4j;
@EnableWebMvc
@ControllerAdvice
@Slf4j
public class ApplicationControllerExceptionHandler {
	 	@ExceptionHandler(value = Exception.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    @ResponseBody
	    public Map<String, Object> handlerError(HttpServletRequest req, Exception e) {
	 		Map<String,Object> map=new HashMap<>();
	        map.put("tip", "此错误说明调用接口失败，失败原因见msg，如果msg为空，联系后台");
	        map.put("msg", e.getMessage());
	        map.put("path", req.getRequestURI());
	        map.put("params", req.getParameterMap());
	        map.put("status", "0");
	        return map;
	    }
	 	@ExceptionHandler(value = { NoHandlerFoundException.class })
	 	@ResponseStatus(HttpStatus.NOT_FOUND)
	 	@ResponseBody
	 	public Map<String, Object> handlerError1(HttpServletRequest req, Exception e) {
	 		Map<String,Object> map=new HashMap<>();
	 		map.put("tip", "此错误说明调用接口失败，失败原因见msg，如果msg为空，联系后台");
	 		map.put("msg", e.getMessage());
	 		map.put("path", req.getRequestURI());
	 		map.put("params", req.getParameterMap());
	 		map.put("status", "0");
	 		return map;
	 	}
}
