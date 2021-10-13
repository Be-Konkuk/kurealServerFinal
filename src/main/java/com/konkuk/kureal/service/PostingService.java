package com.konkuk.kureal.service;

import com.konkuk.kureal.dto.Postings;

public interface PostingService {
    public int insertArticleWithPostingData(Postings posting_data);
    public Postings selectOneArticleWithPk(int pk);
}

