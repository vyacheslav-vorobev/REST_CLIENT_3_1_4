package com.slavik;

import com.slavik.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://91.241.64.178:7081/api/users";
    private static List<String> set_cookie;
    private static HttpHeaders httpHeaders;

    public List<User> getAllUsers() {

        ResponseEntity<List<User>> responseEntity = restTemplate
                .exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        HttpHeaders httpHeadersGet = responseEntity.getHeaders();
        set_cookie =  httpHeadersGet.get("set-cookie");
        System.out.println(set_cookie);
        httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie",set_cookie.get(0));
        return responseEntity.getBody();
    }

    public User getUser(Long id) {

        return restTemplate.getForObject(URL + "/" + id, User.class);
    }

    public void postUser(User user) {
        Long id = user.getId();
        HttpEntity requestEntity = new HttpEntity(user, httpHeaders);
        System.out.println(restTemplate.postForEntity(URL, requestEntity, String.class));
    }
    public void putUser(User user) {
        HttpEntity requestEntity = new HttpEntity(user, httpHeaders);
       // restTemplate.put(URL, requestEntity, String.class);
        System.out.println(restTemplate.exchange(URL,HttpMethod.PUT, requestEntity, String.class));
    }
    public void deleteUser(Long id) {
        HttpEntity requestEntity = new HttpEntity(httpHeaders);
        System.out.println(restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, requestEntity, String.class));
    }
}
