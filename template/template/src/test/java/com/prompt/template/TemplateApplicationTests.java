package com.prompt.template;

import com.prompt.template.service.TemplateApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TemplateApplicationTests {

	@Test
	void contextLoads() {
	}

    @Autowired
    private TemplateApplicationService chatService;
    @Test
    void testTemplateRenderer() {
        System.out.println("Template Renderer Test");

        var output=this.chatService.chatTemp();
        var out=this.chatService.chatTemplate();
        var op=this.chatService.chatApi();
        System.out.println("Template Renderer Test 1");
        System.out.println(output);
        System.out.println("Template Renderer Test 2");
        System.out.println(out);
        System.out.println("Template Renderer Test 3");
        System.out.println(op);
    }

}
