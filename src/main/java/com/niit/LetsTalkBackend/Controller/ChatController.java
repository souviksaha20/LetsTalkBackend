package com.niit.LetsTalkBackend.Controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.LetsTalkBackend.Model.Message;
import com.niit.LetsTalkBackend.Model.OutputMessage;

@Controller
public class ChatController {
	 private Logger logger = LoggerFactory.getLogger(getClass());

	  @MessageMapping("/chat")
	  @SendTo("/topic/message")
	  public OutputMessage sendMessage(Message message) {
	    logger.info("Message sent");
	    return new OutputMessage(message ,new Date());
	  }
}
