package com.souche.utils;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.utils
 * @date 17/4/27
 **/

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author guishangquan
 * @Description http/https 请求工具类
 * @Package com.souche.utils
 * @date 17/4/27
 **/
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final String HTTP_CONTENT_CHARSET = "utf-8";

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(15000)
            .setConnectTimeout(15000)
            .setConnectionRequestTimeout(15000)
            .build();

    static ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 发送 Post 请求
     *
     * @param httpUrl 地址
     * @return
     */
    public static String sendPost(String httpUrl) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        String expendTime = "";
        try {
            startTime.set(System.currentTimeMillis());
            String response = postHttpOrHttps(httpPost);
            expendTime = "[POST] "
                    + "[请求地址]" + httpUrl
                    + "[参数]"
                    + "[返回数据]"+ response
                    + "[耗时] " + (System.currentTimeMillis() - startTime.get()) + "（毫秒）...";
            logger.info(expendTime);
            return response;
        } catch (Exception e) {
            logger.error(expendTime, e);
            return null;
        }
    }

    /**
     * 发送 Post 请求
     *
     * @param httpUrl 地址
     * @param params 参数(格式:key1=value1&key2=value2)
     * @return
     */
    public static String sendPost(String httpUrl, String params) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        String expendTime = "";
        try {
            // 设置参数
            StringEntity stringEntity = new StringEntity(params, "UTF-8");
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);

            startTime.set(System.currentTimeMillis());
            String response = postHttpOrHttps(httpPost);
            expendTime = "[POST] "
                    + "[请求地址]" + httpUrl
                    + "[参数]" + params
                    + "[返回数据]"+ response
                    + "[耗时] " + (System.currentTimeMillis() - startTime.get()) + "（毫秒）...";
            logger.info(expendTime);
            return response;
        } catch (Exception e) {
            logger.error(expendTime, e);
            return null;
        }
    }

    /**
     * 发送 Post 请求
     *
     * @param httpUrl 地址
     * @param maps 参数
     * @return
     */
    public static String sendPost(String httpUrl, Map<String, String> maps) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        String expendTime = "";
        // 创建参数队列
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (Entry<String, String> entry : maps.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

            startTime.set(System.currentTimeMillis());
            String response = postHttpOrHttps(httpPost);
            expendTime = "[POST] "
                    + "[请求地址]" + httpUrl
                    + "[参数]" + maps
                    + "[返回数据]"+ response
                    + "[耗时] " + (System.currentTimeMillis() - startTime.get()) + "（毫秒）...";
            logger.info(expendTime);
            return response;
        } catch (Exception e) {
            logger.error(expendTime, e);
            return null;
        }
    }

    /**
     * 发送 Post 请求
     *
     * @param httpUrl 地址
     * @param maps 参数
     * @param headers 请求头参数
     * @return
     */
    public static String sendPost(String httpUrl, Map<String, String> maps, Map<String, String> headers) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        String expendTime = "";
        // 创建参数队列
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (Entry<String, String> entry : maps.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        if (MapUtils.isNotEmpty(headers)) {
            for (Entry<String, String> header : headers.entrySet()) {
                httpPost.addHeader(new BasicHeader(header.getKey(), header.getValue()));
            }
        }

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

            startTime.set(System.currentTimeMillis());
            String response = postHttpOrHttps(httpPost);
            expendTime = "[POST] "
                    + "[请求地址]" + httpUrl
                    + "[头参数]" + headers
                    + "[参数]" + maps
                    + "[返回数据]"+ response
                    + "[耗时] " + (System.currentTimeMillis() - startTime.get()) + "（毫秒）...";
            logger.info(expendTime);
            return response;
        } catch (Exception e) {
            logger.error(expendTime, e);
            return null;
        }
    }

    /**
     * 发送 Post 请求
     *
     * @param httpUrl 地址
     * @param maps 参数
     * @param headers 请求头参数
     * @return
     */
    public static String sendPost(String httpUrl, Map<String, String> maps, List<Header> headers) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        String expendTime = "";
        // 创建参数队列
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (Entry<String, String> entry : maps.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        if (CollectionUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                httpPost.addHeader(header);
            }
        }

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

            startTime.set(System.currentTimeMillis());
            String response = postHttpOrHttps(httpPost);
            expendTime = "[POST] "
                    + "[请求地址]" + httpUrl
                    + "[头参数]" + headers
                    + "[参数]" + maps
                    + "[返回数据]"+ response
                    + "[耗时] " + (System.currentTimeMillis() - startTime.get()) + "（毫秒）...";
            logger.info(expendTime);
            return response;
        } catch (Exception e) {
            logger.error(expendTime, e);
            return null;
        }
    }

    /**
     * 发送 Post 请求，带附件
     *
     * @param httpUrl 地址
     * @param maps 参数
     * @param fileLists 附件
     * @return
     */
    public static String sendPostFile(String httpUrl, Map<String, String> maps, List<File> fileLists) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        String expendTime = "";
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        for (String key : maps.keySet()) {
            meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
        }
        for (File file : fileLists) {
            FileBody fileBody = new FileBody(file);
            meBuilder.addPart("files", fileBody);
        }

        try {
            HttpEntity reqEntity = meBuilder.build();
            httpPost.setEntity(reqEntity);

            startTime.set(System.currentTimeMillis());
            String response = postHttpOrHttps(httpPost);
            expendTime = "[POST] "
                    + "[请求地址]" + httpUrl
                    + "[文件参数]" + fileLists
                    + "[参数]" + maps
                    + "[返回数据]"+ response
                    + "[耗时] " + (System.currentTimeMillis() - startTime.get()) + "（毫秒）...";
            logger.info(expendTime);
            return response;
        } catch (Exception e) {
            logger.error(expendTime, e);
            return null;
        }
    }

    /**
     * 根据 host 判断发送 http 请求还是 https 请求
     *
     * @param httpPost
     * @return
     * @throws Exception
     */
    private static String postHttpOrHttps(HttpPost httpPost) throws Exception {
        if(httpPost.getURI().getHost().contains("https")) {
            return sendHttpsPost(httpPost);
        } else {
            return sendHttpPost(httpPost);
        }
    }

    /**
     * 发送 http 的 Post 请求
     *
     * @param httpPost
     * @return
     * @throws Exception
     */
    private static String sendHttpPost(HttpPost httpPost) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, HTTP_CONTENT_CHARSET);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
        return responseContent;
    }

    /**
     * 发送 https 的 Post 请求
     *
     * @param httpPost
     * @return
     * @throws Exception
     */
    private static String sendHttpsPost(HttpPost httpPost) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            // 创建默认的httpClient实例.
            PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader.load(new URL(httpPost.getURI().toString()));
            DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);
            httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, HTTP_CONTENT_CHARSET);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
        return responseContent;
    }

    /**
     * 发送 Get 请求
     *
     * @param httpUrl
     * @return
     */
    public static String sendGet(String httpUrl) {
        // 创建get请求
        HttpGet httpGet = new HttpGet(httpUrl);
        String expendTime = "";
        try {
            startTime.set(System.currentTimeMillis());
            String response = getHttpOrHttps(httpGet);
            expendTime = "[GET] "
                    + "[请求地址]" + httpUrl
                    + "[返回数据]"+ response
                    + "[耗时] " + (System.currentTimeMillis() - startTime.get()) + "（毫秒）...";
            logger.info(expendTime);
            return response;
        } catch (Exception e) {
            logger.error(expendTime, e);
            return null;
        }
    }

    /**
     * 根据 host 判断发送 http 请求还是 https 请求
     *
     * @param httpGet
     * @return
     * @throws Exception
     */
    private static String getHttpOrHttps(HttpGet httpGet) throws Exception {
        if(httpGet.getURI().getHost().contains("https")) {
            return sendHttpsGet(httpGet);
        } else {
            return sendHttpGet(httpGet);
        }
    }

    /**
     * 发送 http 的 Get 请求
     *
     * @param httpGet
     * @return
     * @throws Exception
     */
    private static String sendHttpGet(HttpGet httpGet) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, HTTP_CONTENT_CHARSET);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
        return responseContent;
    }

    /**
     * 发送 https 的 Get 请求
     *
     * @param httpGet
     * @return
     * @throws Exception
     */
    private static String sendHttpsGet(HttpGet httpGet) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader.load(new URL(httpGet.getURI().toString()));
            DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);
            httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, HTTP_CONTENT_CHARSET);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
        return responseContent;
    }

    public static void main(String[] args) {
//        sendGet("http://coupon-api.sqaproxy.souche-inc.com/api/coupon/couponbatch/getById.json?id=133");

//        sendPost("http://coupon-api.sqaproxy.souche-inc.com/api/coupon/couponbatch/delById.json", "id=131");

//        Map<String, String> map1 = new HashMap<>();
//        map1.put("id", "131");
//        sendPost("http://coupon-api.sqaproxy.souche-inc.com/api/coupon/couponbatch/delById.json", map1);

//        Map<String, String> map2 = new HashMap<>();
//        map2.put("id", "131");
//        sendPost("http://coupon-api.sqaproxy.souche-inc.com/api/coupon/couponbatch/delById.json", map2, new HashMap<String, String>());

//        Map<String, String> map3 = new HashMap<>();
//        map3.put("id", "131");
//        sendPost("http://coupon-api.sqaproxy.souche-inc.com/api/coupon/couponbatch/delById.json", map3, new ArrayList<Header>());

//        Map<String, String> map4 = new HashMap<>();
//        map4.put("id", "131");
//        sendPostFile("http://coupon-api.sqaproxy.souche-inc.com/api/coupon/couponbatch/delById.json", map4, new ArrayList<File>());
    }
}
