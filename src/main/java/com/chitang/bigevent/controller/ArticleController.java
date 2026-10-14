package com.chitang.bigevent.controller;

import com.chitang.bigevent.pojo.Result;
import com.chitang.bigevent.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(/*@RequestHeader(name = "Authorization") String token, HttpServletResponse response*/){
    /*    //验证token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            return Result.success("所有的文章数据...");
        } catch (Exception e) {
            //http响应状态码为401
            response.setStatus(401);
            return Result.error("未登录");
        }*/
        return Result.success("所有的文章数据...");
    }
}
