import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

public class Main {

    public static ObjectMapper mapper = new ObjectMapper();
    public static final String REMOTE_SERVICE_URI =
            "https://api.nasa.gov/planetary/apod?api_key=NMgTbkmRd8lSwauTAfvOCz2gLxUyPUJz65zslc5L";

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
        HttpGet request = new HttpGet(REMOTE_SERVICE_URI);
        CloseableHttpResponse response = httpClient.execute(request);
        Post post = mapper.readValue(response.getEntity().getContent(), new TypeReference<Post>() {});
        System.out.println(post);

        HttpGet request2 = new HttpGet(post.getUrl());
        CloseableHttpResponse response2 = httpClient.execute(request2);



    }
}