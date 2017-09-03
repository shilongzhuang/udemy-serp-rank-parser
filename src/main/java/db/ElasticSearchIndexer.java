package db;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticSearchIndexer {


    private TransportClient transportClient;
    private IndexResponse indexResponse;

    public ElasticSearchIndexer() {
        init();
    }

    public void init() {
        try {
            transportClient = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        transportClient.close();
    }
}
