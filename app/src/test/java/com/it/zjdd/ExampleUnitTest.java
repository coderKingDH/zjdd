package com.it.zjdd;

import com.loopj.android.http.RequestParams;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void s(){
        RequestParams params = new RequestParams();
        params.put("username","username");
        params.put("pwd","pwd");
        params.put("keep_login", 1);
        String loginurl = "action/api/login_validate";

        String API_URL = "http://www.oschina.net/%s";

        String url = String.format(API_URL, loginurl);
        System.out.println(url);
        String s = new StringBuilder("POST ").append(url)
                .append("&").append(params).toString();

        System.out.println(s);
    }
}