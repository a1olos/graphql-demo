package com.aiolos.graphqldemo.service;


import com.aiolos.graphqldemo.dao.BookMapper;
import com.aiolos.graphqldemo.entity.Book;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务简化类
 * </p>
 *
 * @author system
 * @since 2022-11-23
 */
@Service
public class EsBookService {

    @Autowired
    private RestHighLevelClient client;


    public Map<String,Object> getBookById(String id){
        GetRequest getRequest=new GetRequest("city","_doc",id);
        Map map=new HashMap();
        GetResponse response=null;
        try{
            response= client.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response.isExists()){
            // 本初为了方便演示，将id返回
            map.put("id", response.getId());

            // 默认不返回id信息，若不需要id信息直接返回getSource结果即可。
            map.putAll(response.getSource());
            return map;
        }else{
            throw new RuntimeException("Is not exists.");
        }
    }


    public String delCityById(String id) {
        try {
            DeleteRequest request = new DeleteRequest("city", "_doc", id);
            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            return response.status().name();
        } catch (IOException e) {
            throw new RuntimeException("删除失败.");
        }
    }

    public String addCityByInfo(String id, String name, Integer level, String address, String createTime){
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", name);
        jsonMap.put("level", level);
        jsonMap.put("address", address);
        jsonMap.put("createTime", createTime);

        // 若不需要创建指定id的数据，则不需要再IndexRequest的构造函数中传入id
        // IndexRequest indexRequest = new IndexRequest("city", "_doc");
        IndexRequest indexRequest = new IndexRequest("city", "_doc", id);
        indexRequest.source(jsonMap);
        try {
            IndexResponse rp = client.index(indexRequest,RequestOptions.DEFAULT);
            return rp.status().name();
        } catch (IOException e) {
            throw new RuntimeException("添加失败.");
        }
    }

    public String updateCityByInfo(String id, String name){
        UpdateRequest request=new UpdateRequest("city","_doc", id);
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", name);
        request.doc(jsonMap);
        try {
            return client.update(request,RequestOptions.DEFAULT).status().name();
        } catch (IOException e) {
            throw new RuntimeException("修改失败.");
        }
    }

    public List<Map<String, Object>> query(String name, Integer level, Integer index, Integer size){
        SearchRequest request = new SearchRequest("city");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("name", name));
        boolQueryBuilder.must(QueryBuilders.matchQuery("level", level));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //排序
        searchSourceBuilder.sort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC));
        //分页
        searchSourceBuilder.from(index).size(size).query(boolQueryBuilder);
        request.searchType(SearchType.DEFAULT).source(searchSourceBuilder);
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            SearchResponse rp = client.search(request, RequestOptions.DEFAULT);
            for (SearchHit item : rp.getHits().getHits()) {
                list.add(item.getSourceAsMap());
            }
        } catch (IOException e) {
            throw new RuntimeException("查询失败.");
        }

        return list;
    }
}
