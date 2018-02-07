package com.mjm.workflowkami.auth_classes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mjm.workflowkami.model_classes.UserClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SpringBootClient {

    private WebClient webClient = new OkHttpComponent();
    private static final Logger logger = LoggerFactory.getLogger(SpringBootClient.class);
    private SpringIdentity identity = null;

    public String getSecured(String url) {
        Map<String, String> headers = new HashMap<>();

        String csrf = webClient.getToken();
        headers.put("_csrf", csrf);
        headers.put("X-XSRF-TOKEN", csrf);
        headers.put("Cookie", "XSRF-TOKEN=" + csrf + ";JSESSIONID=" + webClient.getSessionId());
        headers.put("Content-Type", "application/json");

        return webClient.get(url, headers);
    }

    public SpringIdentity login(String url, String username, String password) {
        SpringIdentity identity = new SpringIdentity();
        try {
            String result = webClient.get(url);

            Map<String, String> properties = JSON.parseObject(result, new TypeReference<Map<String, String>>(){}.getType());

            String csrf = properties.get("_csrf.token").trim();


            Map<String, String> headers = new HashMap<>();
            headers.put("_csrf", csrf);
            headers.put("X-XSRF-TOKEN", csrf);
            headers.put("Cookie", "XSRF-TOKEN=" + csrf);
            headers.put("Content-Type", "application/json");

            UserClass loginObj = new UserClass();
            loginObj.setPassword(password);
            loginObj.setEmail(username);

            String body = JSON.toJSONString(loginObj, SerializerFeature.BrowserCompatible);
            String response = webClient.post(url, body, headers);

            identity = JSON.parseObject(response, SpringIdentity.class);

            Map<String, String> tokenInfo = identity.getTokenInfo();

            logger.info("response: {}", response);
            logger.info("XSRF: {}", webClient.getToken());
            logger.info("JSESSIONID: {}", webClient.getSessionId());

            tokenInfo.put("XSRF", webClient.getToken());
            tokenInfo.put("JSESSIONID", webClient.getSessionId());

            if(identity.isAuthenticated()){
                this.identity = identity;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return identity;
    }
}
