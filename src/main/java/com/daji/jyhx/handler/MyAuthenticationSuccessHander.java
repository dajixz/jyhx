package com.daji.jyhx.handler;

import com.daji.jyhx.entity.Teacher;
import com.daji.jyhx.vo.ResponseVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 大稽
 * @date2018/10/1116:35
 */
@Component("myAuthenticationSuccessHander")
public class MyAuthenticationSuccessHander implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功！");
        response.setContentType("application/json;charset=UTF-8");
        Teacher principal = (Teacher) authentication.getPrincipal();
        Teacher teacher = new Teacher();
        teacher.setTeacherId(principal.getTeacherId());
        teacher.setTeacherName(principal.getTeacherName());
        teacher.setTeacherGradeId(principal.getTeacherGradeId());
        teacher.setTeacherSchoolId(principal.getTeacherSchoolId());
        ResponseVo vo = new ResponseVo();
        vo.setCode(200);
        vo.setMsg("登录成功！");
        vo.setData(teacher);
        response.getWriter().write(objectMapper.writeValueAsString(vo));
    }
}
