package com.konkuk.kureal.dao;

import com.konkuk.kureal.dto.Postings;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostingDao {
    private static NamedParameterJdbcTemplate jdbc;
    private static RowMapper<Postings> postingRowMapper = BeanPropertyRowMapper.newInstance(Postings.class);

    public PostingDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public int insertArticleWithPostingData(Postings posting_data) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(posting_data);
        jdbc.update(PostingDaoSqls.INSERT_ARTICLE_WITH_POSTING_DATA,sqlParameterSource,keyHolder);
        return keyHolder.getKey().intValue();
    }
    public static Postings selectOneArticleWithPk(int pk) {
        Map<String, Integer> params = new HashMap<>();
        params.put("pk",pk);
        return jdbc.queryForObject(PostingDaoSqls.SELECT_ONE_ARTICLE_WITH_PK,params,postingRowMapper);
    }

    public List<Postings> selectArticleWithBuilding(int building) {
        Map<String, Integer> params = new HashMap<>();
        params.put("building",building);
        return jdbc.query(PostingDaoSqls.SELECT_ARTICLES_WITH_BUILDING, params, postingRowMapper);
    }
}
