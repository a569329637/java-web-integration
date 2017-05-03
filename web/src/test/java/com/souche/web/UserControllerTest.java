package com.souche.web;

import com.souche.BaseTest;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.web
 * @date 17/5/3
 **/
public class UserControllerTest extends BaseTest {

    @Test
    public void testHandle5() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("userName", "a569329637");
        form.add("password", "mima");
        form.add("realName", "guishangquan");
        restTemplate.postForLocation("http://localhost:8080/user/handle5", form);
    }

    @Test
    public void testHandle6() {
        RestTemplate restTemplate = new RestTemplate();
        byte[] response = restTemplate.postForObject("http://localhost:8080/user/handle6", null, byte[].class);
        Resource outFile = new FileSystemResource("/Users/dasouche/tmp/image.jpg");
        try {
            FileCopyUtils.copy(response, outFile.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHandle7() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("userName", "a569329637");
        form.add("password", "mima");
        form.add("realName", "guishangquan");
        restTemplate.postForLocation("http://localhost:8080/user/handle7", form);
    }

    @Test
    public void testHandle8() {
        RestTemplate restTemplate = new RestTemplate();
        byte[] response = restTemplate.postForObject("http://localhost:8080/user/handle8", null, byte[].class);
        Resource outFile = new FileSystemResource("/Users/dasouche/tmp/image_1.jpg");
        try {
            FileCopyUtils.copy(response, outFile.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void testHandle9() {
//        RestTemplate restTemplate = buildRestTemplate();
//
//        User user = new User();
//        user.setUserName("a569329637");
//        user.setPassword("mima");
//        user.setRealName("guishangquan");
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.valueOf("application/xml;UTF-8"));
//        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
//
//        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
//        ResponseEntity<User> responseEntity = restTemplate.exchange("http://localhost:8080/user/handle9", HttpMethod.POST, httpEntity, User.class);
//
//        User responseUser = responseEntity.getBody();
//        System.out.println("responseUser = " + responseUser);
//    }
//
//    private RestTemplate buildRestTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        XStreamMarshaller xmlMarshaller = new XStreamMarshaller();
//        xmlMarshaller.setStreamDriver(new StaxDriver());
//        xmlMarshaller.setAnnotatedClasses(new Class[]{ User.class });
//
//        MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
//        xmlConverter.setMarshaller(xmlMarshaller);
//        xmlConverter.setUnmarshaller(xmlMarshaller);
//        restTemplate.getMessageConverters().add(xmlConverter);
//
////        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
////        restTemplate.getMessageConverters().add(jsonConverter);
//        return restTemplate;
//    }
}
