package com.semenov.spring.rest.UserManegmentClient;

import com.semenov.spring.rest.model.User;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.List;
import java.util.stream.Collectors;

public class UserManegmetClint {

    static RestTemplate restTemplate = new RestTemplate();
    static String baseURL = "http://94.198.50.185:7081/api/users";



    public static void main(String[] args) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity <Object> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List> responseEntity = getListOfUsers(requestEntity); ;
        headers.set("Cookie",responseEntity.getHeaders().get("Set-Cookie").stream().collect(Collectors.joining(";")));
        System.out.println(headers);


        User userForJSON = new User();
        userForJSON.setId(3L);
        userForJSON.setName("James");
        userForJSON.setLastName("Brown");
        userForJSON.setAge((byte)38);
        HttpEntity <User> requestEntity1 = new HttpEntity<>(userForJSON, headers);


        addNewUserJSON(requestEntity1);

        userForJSON.setName("Thomas");
        userForJSON.setLastName("Shelby");

        requestEntity1 = new HttpEntity<>(userForJSON, headers);

        uppDateUserJSON(requestEntity1);
        deleteUserJSON(requestEntity1);

    }

    private static void deleteUserJSON(HttpEntity<User> requestEntity) {
        ResponseEntity <String> responseEntity = restTemplate.exchange(baseURL+"/3 ",
                HttpMethod.DELETE,
                requestEntity,
                String.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println("Status code " + statusCode);
        String userDetails = responseEntity.getBody();
        System.out.println("responce body " + userDetails);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        System.out.println("responce Headers" + responseHeaders);
    }

    private static void uppDateUserJSON(HttpEntity<User> requestEntity) {
        ResponseEntity <String> responseEntity = restTemplate.exchange(baseURL,
                HttpMethod.PUT,
                requestEntity,
                String.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println("Status code " + statusCode);
        String userDetails = responseEntity.getBody();
        System.out.println("responce body " + userDetails);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        System.out.println("responce Headers" + responseHeaders);
    }

    private static void addNewUserJSON(HttpEntity<User> requestEntity) {

        ResponseEntity <String> responseEntity = restTemplate.exchange(baseURL,
                HttpMethod.POST,
                requestEntity,
                String.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println("Status code " + statusCode);
        String userDetails = responseEntity.getBody();
        System.out.println("responce body " + userDetails);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        System.out.println("responce Headers" + responseHeaders);
    }

    private static void userExchangeMethode() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity <Object> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> forEntity = restTemplate.getForEntity(baseURL, String.class);
        forEntity.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);

        System.out.println("Полученный результат forEntity " + forEntity);
        System.out.println("Полученные результат headers " + headers);


    }


    private static ResponseEntity<List> getListOfUsers(HttpEntity <Object> requestEntity) {
        ResponseEntity <List> responseEntity = restTemplate.exchange(baseURL,
                HttpMethod.GET,
                requestEntity,
                List.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println("Status code " + statusCode);
        List user =responseEntity.getBody();
        System.out.println("responce body " + user);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        System.out.println("responce Headers" + responseHeaders);
        responseEntity.getHeaders().get("Set-Cookie");
        System.out.println("Полученный set-Cookie" + responseEntity);
        return responseEntity;
    }

}

