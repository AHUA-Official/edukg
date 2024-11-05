package com.example.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AAFurinaminioprasejson {


        /** ID */
        private String  id;
        /** 文档id外键 */
        private String documentId;
        /** json文档名 */
        private String jsondocName;
        /** minio地址 */
        private String jsonMinioUrl;
        /** chain */
        private Integer chainId;
        /** version */
        private Integer versionNum;
        /** 标识 */
        private String symbol;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDocumentId() {
            return documentId;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public String getJsondocName() {
            return jsondocName;
        }

        public void setJsondocName(String jsondocName) {
            this.jsondocName = jsondocName;
        }

        public String getJsonMinioUrl() {
            return jsonMinioUrl;
        }

        public void setJsonMinioUrl(String jsonMinioUrl) {
            this.jsonMinioUrl = jsonMinioUrl;
        }

        public Integer getChainId() {
            return chainId;
        }

        public void setChainId(Integer chainId) {
            this.chainId = chainId;
        }

        public Integer getVersionNum() {
            return versionNum;
        }

        public void setVersionNum(Integer versionNum) {
            this.versionNum = versionNum;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }


        private static final Set<String> generatedjsonUUIDs = Collections.synchronizedSet(new HashSet<>());

        public  String generatejsonUniqueUUID() {
            String uuid;
            do {
                uuid = UUID.randomUUID().toString();
            } while (generatedjsonUUIDs.contains(uuid));

            generatedjsonUUIDs.add(uuid);
            return uuid;
        }
        public AAFurinaminioprasejson(String documentId, String jsondocName, String jsonMinioUrl, Integer chainId, Integer versionNum, String symbol) {
            this.id = generatejsonUniqueUUID();
            this.documentId = documentId;
            this.jsondocName = jsondocName;
            this.jsonMinioUrl = jsonMinioUrl;
            this.chainId = chainId;
            this.versionNum = versionNum;
            this.symbol = symbol;
        }

    }

