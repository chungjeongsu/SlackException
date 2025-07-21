package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SlackTestController {
    private final SlackService slackService;

    @GetMapping("/slack/error")
    public void error(){
        slackService.sendSlackMessage("슬랙 에러 테스트", "error");
    }

    @PostMapping("/slack/subscribe")
    public void slackMessage(@RequestParam Map<String, String> payload){
        String user = payload.get("user_name");
        String text = payload.get("text");

        System.out.println(user);
        System.out.println(text);
    }
}
