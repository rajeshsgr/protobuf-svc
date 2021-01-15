package com.raj.nola.protobuf;

import com.googlecode.protobuf.format.JsonFormat;
import com.raj.nola.account.model.AccountProto.Account;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ProtobufApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProtobufApplicationTests {

    private static final String URL = "http://localhost:8080/students";


    @Test
    void contextLoads() {
    }

    @Test
    public void getStudentsTest() throws IOException {
        InputStream responseStream = executeHttpRequest(URL);
        String jsonOutput = convertProtobufMessageStreamToJsonString(responseStream);
        System.out.println(jsonOutput);
        assertResponse(jsonOutput);
    }

    private String convertProtobufMessageStreamToJsonString(InputStream protobufStream) throws IOException {
        JsonFormat jsonFormat = new JsonFormat();
        Account account = Account.parseFrom((protobufStream));
        return jsonFormat.printToString(account);
    }

    private InputStream executeHttpRequest(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(request);
        return httpResponse.getEntity().getContent();
    }

    private void assertResponse(String response) {
        assertThat(response, containsString("1"));
        assertThat(response, containsString("Denis"));
    }


}
