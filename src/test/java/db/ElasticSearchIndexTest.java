package db;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class ElasticSearchIndexTest {
    private TransportClient client;
    private IndexResponse response;

    @Before
    public void createClient() {
        try {
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void indexString() {

        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        String contentType = XContentType.JSON.mediaType();
        response = client.prepareIndex("twitter", "tweet" , "1")
                .setSource(json, contentType).get();

        String _index = response.getIndex();
        System.out.println(_index);

    }

    @Test
    public void index02() {

        try {
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("user", "kibana")
                //    .field("postDate", "2017-07-23")
                    .field("postDate", new Date())
                    .field("message", "trying out Kibana")
                    .endObject();
            response = client.prepareIndex("twitter", "tweet", "4")
                    .setSource(builder).get();

            String _id = response.getId();
            System.out.println(_id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void shutdownClient() {
        client.close();

    }
}
