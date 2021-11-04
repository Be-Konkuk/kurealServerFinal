package com.konkuk.kureal.service;

import com.konkuk.kureal.dto.Postings;

import java.util.List;


public interface PostingService {
    public int insertArticleWithPostingData(Postings posting_data);
    public Postings selectOneArticleWithPk(int pk);
    public List<Postings> selectArticleWithBuilding(int building);
}

