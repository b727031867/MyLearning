package com.gxf.demo.controller;

import com.gxf.demo.enumeration.RabbitMqExchangeEnum;
import com.gxf.demo.enumeration.RoutingKeyEnum;
import com.gxf.demo.service.MessageQueueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class HelloControllerTest {

    @Mock
    private MessageQueueService messageQueueService;

    @InjectMocks
    private HelloController helloController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }

    @Nested
    @DisplayName("GET / - helloWorld")
    class HelloWorldTest {

        @Test
        @DisplayName("should return 'Hello Word!'")
        void shouldReturnHelloWord() {
            String result = helloController.helloWorld();
            org.junit.jupiter.api.Assertions.assertEquals("Hello Word!", result);
        }

        @Test
        @DisplayName("should return 200 with 'Hello Word!'")
        void shouldReturn200WithHelloWord() throws Exception {
            mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Hello Word!"));
        }
    }

    @Nested
    @DisplayName("GET /testLogstash")
    class TestLogstashTest {

        @Test
        @DisplayName("should return 'Hello World!'")
        void shouldReturnHelloWorld() {
            String result = helloController.testLogstash();
            org.junit.jupiter.api.Assertions.assertEquals("Hello World!", result);
        }

        @Test
        @DisplayName("should return 200 with 'Hello World!'")
        void shouldReturn200WithHelloWorld() throws Exception {
            mockMvc.perform(get("/testLogstash"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Hello World!"));
        }
    }

    @Nested
    @DisplayName("GET /sendMsg")
    class SendMsgTest {

        @Test
        @DisplayName("should send message and return 'OK'")
        void shouldSendMessageAndReturnOk() {
            doNothing().when(messageQueueService)
                    .sendMessageWithRoutingKeyAndExchangeKey(any(), any(), any());

            String result = helloController.testMessageQueue();

            org.junit.jupiter.api.Assertions.assertEquals("OK", result);
            verify(messageQueueService, times(1))
                    .sendMessageWithRoutingKeyAndExchangeKey(
                            eq(RabbitMqExchangeEnum.DIRECT_EXCHANGE),
                            eq(RoutingKeyEnum.DIRECT_KEY),
                            eq("Hello Word!")
                    );
        }

        @Test
        @DisplayName("should return 200 with 'OK'")
        void shouldReturn200WithOk() throws Exception {
            doNothing().when(messageQueueService)
                    .sendMessageWithRoutingKeyAndExchangeKey(any(), any(), any());

            mockMvc.perform(get("/sendMsg"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("OK"));
        }

        @Test
        @DisplayName("should call messageQueueService with correct parameters")
        void shouldCallServiceWithCorrectParams() {
            doNothing().when(messageQueueService)
                    .sendMessageWithRoutingKeyAndExchangeKey(any(), any(), any());

            helloController.testMessageQueue();

            verify(messageQueueService).sendMessageWithRoutingKeyAndExchangeKey(
                    RabbitMqExchangeEnum.DIRECT_EXCHANGE,
                    RoutingKeyEnum.DIRECT_KEY,
                    "Hello Word!"
            );
        }
    }

    @Nested
    @DisplayName("GET /sendMsgDirect")
    class SendMsgDirectTest {

        @Test
        @DisplayName("should send direct message and return 'OK'")
        void shouldSendDirectMessageAndReturnOk() {
            doNothing().when(messageQueueService).sendDirectMessage(any());

            String result = helloController.testConvertAndSendDirectMessageQueue();

            org.junit.jupiter.api.Assertions.assertEquals("OK", result);
            verify(messageQueueService, times(1)).sendDirectMessage("Hello Word! 666 ");
        }

        @Test
        @DisplayName("should return 200 with 'OK'")
        void shouldReturn200WithOk() throws Exception {
            doNothing().when(messageQueueService).sendDirectMessage(any());

            mockMvc.perform(get("/sendMsgDirect"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("OK"));
        }

        @Test
        @DisplayName("should call sendDirectMessage with correct message")
        void shouldCallServiceWithCorrectMessage() {
            doNothing().when(messageQueueService).sendDirectMessage(any());

            helloController.testConvertAndSendDirectMessageQueue();

            verify(messageQueueService).sendDirectMessage("Hello Word! 666 ");
        }
    }
}