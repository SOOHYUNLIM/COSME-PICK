package org.jarvis.jarvis_1.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushFcmOptions;

import org.jarvis.jarvis_1.domain.Product;
import org.jarvis.jarvis_1.dto.CustomMessage;
import org.jarvis.jarvis_1.repositories.ProductRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * RestController
 */
@CrossOrigin
@RestController
@RequestMapping("/cosme-pick")
@AllArgsConstructor
@Slf4j
public class RestAPIController {

    private final ProductRepository productRepository;

    @PostMapping("/pick")
    public ResponseEntity<CustomMessage> pick(@RequestBody final Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(CustomMessage.SUSSESS, HttpStatus.CREATED);
    }

    @GetMapping(value = "/check")
    public ResponseEntity<String> check(@RequestParam final String url) {
        RestTemplate template = new RestTemplate();
        template.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        String response = template.getForObject("http://127.0.0.1:8000/check?url=" + url, String.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/test", produces = "text/plain;charset=UTF-8")
    public void fcmTest() throws Exception {
        Notification notification = null;

        for (int i = 1; i <= 10; i++){

        
            notification = Notification.builder().setTitle("COSME-PICK").setBody("[아워홈][아워홈] 지리산수 2L x 6병" + i)
                    .setImage("C:/cosmepick/icon.png").build();

        Message message = Message.builder().putData("score", "850").putData("time", "2:45")
                .setWebpushConfig(WebpushConfig.builder().setFcmOptions(WebpushFcmOptions.withLink(
                        "http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=2625079984&trTypeCd=22&trCtgrNo=895019"))
                        .build())
                .setNotification(notification)
                .setToken(
                        "f5IRW9HOrFq1SVX4ZKWYji:APA91bEI51gVf0z9z-M2nYP2IawzIkzei-rqRPTaVAe6cms_C01J07HOkL3pt6ypTn0Pp6ismL_uyGolfr23Jec_EUoP523XiYL0F6b0f1YXFvSxhUuCI272arVnbNOC1pMs-fDwEFt3")
                .build();
        try {
            FirebaseMessaging.getInstance(FirebaseApp.getInstance("options")).send(message);
        } catch (final FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }
    }

    @GetMapping("/lambda")
    public void rambdaTest() {
        RestTemplate template = new RestTemplate();
        template.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        String response = template.getForObject(
                "https://ecyva6zsv6.execute-api.ap-northeast-2.amazonaws.com/default/Crawling", String.class);
        log.info(response);
    }


}