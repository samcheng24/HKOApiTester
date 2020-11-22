package com.sam.utility;

import com.sam.enums.ResponseKeyEnum;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.http.HttpMethod;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClient {
    private static final Logger LOGGER = LogManager.getLogger(HttpClient.class.getName());

    public static Map<ResponseKeyEnum, Object> send(String URL, HttpMethod httpMethod, String requestParams){//Map<String, Object> headerMap, String method, String urlString, String payload){
        // Store response as a Map
        Map<ResponseKeyEnum, Object> responseObj = new HashMap<>();

        try {
            // configure the SSLContext with a TrustManager
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
            SSLContext.setDefault(ctx);

            URL url = new URL(URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(httpMethod.toString());
            urlConnection.setDoOutput(true);

            // Header Stuff - not implemented
//            Map<String, Object> headerMap = new HashMap<>();
//            headerMap.put("Content-Type","application/x-www-form-urlencoded");
//            headerMap.put("Connection", "keep-alive");
//            headerMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//            headerMap.put("Accept-Encoding", "gzip, deflate, br");
//            headerMap.put("Accept-Language", "en-US,en;q=0.5");
//
//            for (String key : headerMap.keySet()) {
//                urlConnection.setRequestProperty(key, (String) headerMap.get(key));
//            }

            try (DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream())) {
                wr.writeBytes(requestParams);
            }

            LOGGER.info("HTTP Client on this URL: " + URL+"?"+requestParams);

            // Save Response Code
            int responseCode = urlConnection.getResponseCode();
            responseObj.put(ResponseKeyEnum.RESPONSE_CODE, responseCode);
            LOGGER.debug("Response code received: " + responseCode);

            // Save Response JSON (Content)
            if (responseCode == HttpStatus.SC_OK) {

                StringBuilder responsePayload = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        responsePayload.append(inputLine);
                    }
                }
                responseObj.put(ResponseKeyEnum.RESPONSE_CONTENT, responsePayload.toString());
                LOGGER.debug("Response content received: " + responsePayload.toString());
            }

            // Save Response Headers
            Map<String, List<String>> headerMap = urlConnection.getHeaderFields();
            // For testing
//            for (Map.Entry<String, List<String>> entry : headerMap.entrySet()) {
//                System.out.println("Key : " + entry.getKey() +
//                        " ,Value : " + entry.getValue());
//            }
            responseObj.put(ResponseKeyEnum.RESPONSE_HEADER, headerMap);
            LOGGER.debug("Response header received: " + headerMap);
        } catch (Exception e) {
            LOGGER.error("[ERROR] HTTP Client caught Exception " + e);
        }
        return responseObj;
    }

    public static String formatPayload(List<NameValuePair> paramsList){
        return URLEncodedUtils.format(paramsList, "UTF-8");
    }

    public static List<NameValuePair> parsePayload(String payload){
        return URLEncodedUtils.parse(payload, StandardCharsets.UTF_8);
    }

    public static NameValuePair createParam(String name, String value){
        return new BasicNameValuePair(name, value);
    }

    // For SSL - Accepts all - TODO: find a secure way
    private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}


