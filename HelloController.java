package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		String content = """
	            <!doctype html>
	            <html lang="th">
	              <head>
	                <meta charset="utf-8">
	                <title>หน้าแรก</title>
	              </head>
	              <body>
	                <h1>สวัสดีจาก Spring Boot!</h1>
	                <p>นี่คือหน้า HTML ที่ส่งกลับจาก Controller</p>
	              </body>
	            </html>
	            """;
	        return content;
	}

}