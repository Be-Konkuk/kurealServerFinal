package com.konkuk.kureal.service.impl;

import com.konkuk.kureal.dao.PostingDao;
import com.konkuk.kureal.dto.*;
import com.konkuk.kureal.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostingServiceImpl implements PostingService {
    private PostingDao postingDao;

    @Autowired
    public PostingServiceImpl(PostingDao postingDao) {
        this.postingDao = postingDao;
    }

    @Override
    public int insertArticleWithPostingData(Postings posting_data) {
        int resultInsert = postingDao.insertArticleWithPostingData(posting_data);
        return resultInsert;
    }

    @Override
    public Postings selectOneArticleWithPk(int pk) {
        Postings posting = postingDao.selectOneArticleWithPk(pk);
        return posting;
    }
}
