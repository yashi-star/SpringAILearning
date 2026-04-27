package com.rag.advancerag.service;

import java.util.List;

public interface AdvanceragService {
    String getResponse(String userQuery);
     void saveData(List<String> list);
}
