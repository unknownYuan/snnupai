package me.snnupai.door.controller;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static me.snnupai.door.util.Utils.page_size;

@RestController
public class SearchController {
    @Autowired
    TransportClient client;

    @PostMapping("/query/ghd_snnupai_trade/trade")
    public ResponseEntity queryTrade(@RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "content", required = false) String content,
                                     @RequestParam(value = "pagenum", required = true) int pagenum){
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if(title != null){
            boolQuery.must(QueryBuilders.matchQuery("title", title));
        }

        if(content != null){
            boolQuery.must(QueryBuilders.matchQuery("content", content));
        }

        int from = (pagenum - 1) * page_size;
        SearchRequestBuilder searchRequestBuilder = this.client.prepareSearch("ghd_snnupai_trade")
                .setTypes("trade")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQuery)
                .setFrom(from)
                .setSize(page_size);
        SearchResponse searchResponse = searchRequestBuilder.get();

        List<Map<String, Object>> result = new ArrayList<>();
        for (SearchHit hit:searchResponse.getHits()){
            result.add(hit.getSource());
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/query/ghd_snnupai_love/love")
    public ResponseEntity queryLove(@RequestParam(value = "content", required = false) String content,
                                     @RequestParam(value = "pagenum", required = true) int pagenum){
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if(content != null){
            boolQuery.must(QueryBuilders.matchQuery("content", content));
        }
        int from = (pagenum - 1) * page_size;
        SearchRequestBuilder searchRequestBuilder = this.client.prepareSearch("ghd_snnupai_love")
                .setTypes("love")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQuery)
                .setFrom(from)
                .setSize(page_size);
        SearchResponse searchResponse = searchRequestBuilder.get();

        List<Map<String, Object>> result = new ArrayList<>();
        for (SearchHit hit:searchResponse.getHits()){
            result.add(hit.getSource());
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/query/ghd_snnupai_user/user")
    public ResponseEntity queryUser(@RequestParam(value = "nickname", required = false) String nickname,
                                     @RequestParam(value = "pagenum", required = true) int pagenum){
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if(nickname != null){
            boolQuery.must(QueryBuilders.matchQuery("nick_name", nickname));
        }
        int from = (pagenum - 1) * page_size;
        SearchRequestBuilder searchRequestBuilder = this.client.prepareSearch("ghd_snnupai_user")
                .setTypes("user")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQuery)
                .setFrom(from)
                .setSize(page_size);
        SearchResponse searchResponse = searchRequestBuilder.get();

        List<Map<String, Object>> result = new ArrayList<>();
        for (SearchHit hit:searchResponse.getHits()){
            result.add(hit.getSource());
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
