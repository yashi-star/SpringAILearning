package com.padtovector.etl;

import com.padtovector.etl.service.DataLoaderService;
import com.padtovector.etl.service.DataTransformerService;
import org.junit.jupiter.api.Test;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EtlApplicationTests {

    @Autowired
    private DataLoaderService dataLoaderService;

    @Autowired
    private DataTransformerService dataTransformerService;

    @Autowired
    private VectorStore vectorStore;
	@Test
	void testJsonDataLoader() {
        var documents = dataLoaderService.loadDocumentsFromJson();
        System.out.println("document size  JSON: " + documents.size());
        documents.forEach(doc -> {System.out.println(doc);});
	}

    @Test
    void testPdfDataLoader() {
        var documents = dataLoaderService.loadDocumentsFromPdf();
        System.out.println("document size PDF : " + documents.size());
        documents.forEach(doc -> {System.out.println(doc);});

        System.out.println("Read ... Now tranforming data");
        var transformDocument=dataTransformerService.transform(documents);
        System.out.println(transformDocument.size());


        // going to save data in database
        this.vectorStore.add(transformDocument);
        System.out.println("Saving data to db");
    }





}
