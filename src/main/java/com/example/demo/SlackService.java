package com.example.demo;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackService {

    @Value(value = "${slack.token}")
    private String slackToken;

    private final TestRepository testRepository;

    public void sendSlackMessage(String message, String channel){
        String channelAddress = "";

        if(channel.equals("error")){
            channelAddress = SlackConstant.ERROR_CHANNEL;
        }

        try{
            MethodsClient methods = Slack.getInstance().methods(slackToken);

            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(channelAddress)
                    .text(message)
                    .build();

            methods.chatPostMessage(request);
        } catch (SlackApiException | IOException e) {
            log.error(e.getMessage());
        }
    }
}