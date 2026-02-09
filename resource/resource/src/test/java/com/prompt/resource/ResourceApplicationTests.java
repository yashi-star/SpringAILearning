package com.prompt.resource;

import com.prompt.resource.service.ResourceApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ResourceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
    private ResourceApplicationService chatService;
    @Test
    void testTemplateRenderer() {
        System.out.println("Template Renderer Test");

        var output=this.chatService.chatTemplate();
    
        System.out.println(output);
  
    }

}
