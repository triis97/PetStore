package com.zinkworks.gradpetstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ParsedApi {
    private List<Paths> paths;
    private List<Schemas> schemas;

    @Data
    @AllArgsConstructor
    public static class Schemas {
        private String name;
        private String schema;
    }

    @Data
    @AllArgsConstructor
    public static class Paths {
        private String path;
        private String method;
        private String scenario;
        private String summary;
    }
}
