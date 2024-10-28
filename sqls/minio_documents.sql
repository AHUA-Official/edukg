CREATE TABLE minio_documents (
    document_id VARCHAR(36) PRIMARY KEY,
    document_name VARCHAR(255) NOT NULL,
    minio_url VARCHAR(255) NOT NULL,
    symbol VARCHAR(255) NOT NULL, -- 用于文档搜索的标志
    preview TEXT, -- 预览信息，可以存储文档的预览文本或预览图片的URL
    need_parse BOOLEAN DEFAULT TRUE -- 表示是否需要解析
);