package com.konkuk.kureal.dao;

public class PostingDaoSqls {
    public static final String INSERT_ARTICLE_WITH_POSTING_DATA = "INSERT INTO article (date,nickname,article,photo,tag,latitude,longitude) VALUES (:date,:nickname,:article,:photo,:tag,:latitude,:longitude)";
    public static final String SELECT_ONE_ARTICLE_WITH_PK = "select * from article where pk = :pk";
    public static final String SELECT_ARTICLES_WITH_BUILDING = "select * from article where building = :building";
}

