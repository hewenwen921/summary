package com.wen.test;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * es测试
 */
public class ElasticSearchTest {
    Client client;

//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;

//    @Autowired
//    private Wen wen;


    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ElasticsearchTemplate wen = (ElasticsearchTemplate) ctx.getBean("elasticsearchTemplate");

//        Client client = esTest.buildClient();
//        esTest.client = client;
//        esTest.createIndex();
//        Thread.sleep(1000);
//        client.close();
        ElasticSearchTest esTest=new ElasticSearchTest();
        System.out.println(wen.getClient());
//        esTest.elasticsearchTemplate.createIndex("sku");

//        System.out.
    }


    public Client buildClient() throws UnknownHostException {
        //1.7.2写法
//        Settings setting = ImmutableSettings.settingsBuilder()
//                .put("client.transport.sniff", true)
//                .put("cluster.name", "es-wen").build();
//
//        Client client = new TransportClient(setting)
//                        .addTransportAddress(new InetSocketTransportAddress(


        Settings settings = Settings.settingsBuilder()
                .put("client-transport-ignore-cluster-name", true)
                .put("cluster.name", "es")
//                .put("client.transport.nodes_sampler_interval", "10s")
//                .put("client.transport.ping_timeout", "10s")
//                .put("cluster-nodes", "127.0.0.1:9300")
//                .put("shield.elsearch", System.getProperty("shield.elsearch"))
                .build();

        Client client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.0.64"), 9300));

        System.out.println(client);

        return client;
    }

    public void createIndex() {
        try {
            if (client.admin().indices().prepareExists("item").execute()
                    .actionGet().isExists()) {
                DeleteIndexResponse deleteIndexResponse = client.admin().indices()
                        .delete(new DeleteIndexRequest("item")).actionGet();
            }

            client.admin().indices().prepareCreate("item").execute().actionGet();

            XContentBuilder mapping = XContentFactory.jsonBuilder().startObject()
                    .startObject("properties")
                    .startObject("itemLastCountTime").field("type", "date").endObject()
                    .startObject("isItem").field("type", "boolean").endObject()
//                    .startObject("skuId").field("type", "long").field("index", "analyzed").field("analyzer", "standard").endObject()
                    .startObject("itemId").field("type", "string").field("index", "analyzed").field("analyzer", "standard").endObject()
                    .startObject("itemTags").field("type", "string").field("index", "analyzed").field("analyzer", "standard").endObject()
                    .startObject("itemTitle").field("type", "string").field("index", "analyzed").field("analyzer", "standard").endObject()
                    .startObject("itemDescription").field("type", "string").field("index", "analyzed").field("analyzer", "standard").endObject()
                    .startObject("itemDetail").field("type", "string").field("index", "analyzed").field("analyzer", "standard").endObject()
                    .startObject("itemCode").field("type", "string").field("index", "analyzed").field("analyzer", "standard").endObject()


                    .endObject().endObject();

            String mappingJson = mapping.string();
            System.out.println(mappingJson);

            PutMappingResponse response = client.admin().indices().preparePutMapping("item").setType("item").setSource(mappingJson).execute().actionGet();
            System.out.println(response.isAcknowledged());
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildIndexData() throws Exception {
        List<String> tags = new ArrayList<String>();
        tags.add("漂亮");
        tags.add("被子");

        List<String> itemCategoryNames = new ArrayList<String>();
        itemCategoryNames.add("真丝");
        itemCategoryNames.add("真丝四件套");

        List<String> itemSeriesNames = new ArrayList<String>();
        itemSeriesNames.add("娘娘系列");

        Map<String, String> props = new HashMap<String, String>();
        props.put("等级", "优等品");
        props.put("面料", "蚕丝");

        Map<String, String> usedSpecs = new HashMap<String, String>();
        usedSpecs.put("颜色", "白色");
        usedSpecs.put("颜色", "红色");
        usedSpecs.put("尺码", "180*200");
        usedSpecs.put("尺码", "160*180");


        IndexResponse response = client.prepareIndex("item", "item", "1").setSource(
                XContentFactory.jsonBuilder()
                        .startObject()
                        .field("itemLastCountTime", new Date())
                        .field("isItem", "true")
                        .field("skuId", "")
                        .field("itemId", "1")
                        .field("itemTags", tags)
                        .field("itemTitle", "秋冬被钱皇最新款")
                        .field("itemDescription", "商品描述")
                        .field("itemDetail", "图文描述")
                        .field("itemCode", "02")
                        .field("itemOnSaleTime", new Date())
                        .field("itemAuditedTime", new Date())
                        .field("itemOrigin", new Date())

                        .field("categoryLastCountTime", new Date())
                        .field("itemCategoryNames", itemCategoryNames)
                        .field("itemSeriesNames", itemSeriesNames)

                        .field("itemPropLastCountTime", new Date())
                        .field("props", props)
                        .field("usedSpecLastCountTime", new Date())
                        .field("usedSpecs", usedSpecs)
                        .endObject()
        ).execute().actionGet();


        System.out.println(response);
    }

    public void updateItem() throws Exception {
        List<String> tags = new ArrayList<String>();
        tags.add("漂亮");
        tags.add("被子");

//		item.itemTitle="钱皇手工被100桑蚕长丝被优等品春秋被加厚被子母被手工蚕丝被 ";

        UpdateRequest updateRequest = new UpdateRequest("item", "item", "2");
        updateRequest.doc("{\"itemTitle\":\"钱皇手工被100桑蚕长丝被优等品春秋被加厚被子母被手工蚕丝被\",\"wen\":\"123456\"}");

        UpdateResponse response = client.update(updateRequest).get();

    }

    public void upateBrand() throws Exception {
        SearchResponse response = client.prepareSearch("item")
                .setQuery(QueryBuilders.matchQuery("itemTitle", "钱皇")).execute()
                .actionGet();
    }

    public void deteleIndex() {
        client.admin().indices().prepareDelete("item").execute().actionGet();
    }

    public void searchAll() {
        SearchResponse response = client.prepareSearch("item")
                .setQuery(QueryBuilders.matchAllQuery()).setExplain(true)
                .execute().actionGet();

        System.out.println("searchAll : " + response);
    }

    public void searchKeyword(String keyworkd, String value) {
        SearchResponse response = client.prepareSearch("item")
                .setQuery(QueryBuilders.matchQuery(keyworkd, value)).execute()
                .actionGet();

        System.out.println("searchKeyWord : " + response);
    }
}
