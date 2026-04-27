package com.padtovector.etl.controller;

import com.padtovector.etl.service.DataLoaderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
class DataLoaderController {

    private final DataLoaderService promptService;

    public DataLoaderController(DataLoaderService promptService) {
        this.promptService = promptService;
    }


}

