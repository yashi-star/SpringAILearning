package com.prompt.entities.prompt_entity.service;

import com.prompt.entities.prompt_entity.entity.Tutorial;
import org.springframework.stereotype.Service;

import java.util.List;

//public interface PromptEntityApplicationService {
//    Tutorial chat(String message);
//}

public interface PromptEntityApplicationService {
   List<Tutorial> chat(String message);
}
