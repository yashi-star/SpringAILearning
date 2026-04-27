package com.rag.vectordb;

import com.rag.vectordb.helper.Helper;
import com.rag.vectordb.service.VectordbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VectordbApplicationTests {
@Autowired
private VectordbService vectordbService;
	@Test
	void saveDataToVectorDb() {
        System.out.println("saving data to database");
        this.vectordbService.saveData(Helper.getData());
        System.out.println("saved data to database");
	}

}
