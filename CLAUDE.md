# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Test Commands

```bash
./mvnw clean package            # Build the project
./mvnw test                     # Run all tests
./mvnw test -Dtest=HelloControllerTest  # Run single test class
./mvnw spring-boot:run          # Run the application
```

## Architecture Overview

This is a Spring Boot 2.7.9 demo application integrating multiple technologies:

- **REST API**: `HelloController` provides endpoints (`/sendMsg`, `/sendMsgDirect`, `/testLogstash`)
- **RabbitMQ Messaging**: Uses direct exchange with manual acknowledgment. `RabbitMqConfig` sets up the template with `Jackson2JsonMessageConverter`. `DirectQueueListener` consumes from `DIRECT_QUEUE`.
- **MySQL + MyBatis Plus**: Database persistence layer (configured but not heavily used in current code)
- **Redis**: Caching via Spring Data Redis
- **Netty**: Network I/O operations
- **Logstash Integration**: `logstash-logback-encoder` pushes logs to Logstash via `logs/` directory

### Key Configuration Classes
- `config/RabbitMqConfig.java` — RabbitMQ template factory with confirm/failed callbacks
- `config/ConfirmCallbackHandler.java` — Called when message is confirmed at broker
- `config/FailedCallBackHandler.java` — Called when message fails to route
- `config/InitExchangeAndBinding.java` — Initializes exchanges and queue bindings

### Enums
- `RabbitMqExchangeEnum` — Defines exchange types (DIRECT, TOPIC, FANOUT)
- `RoutingKeyEnum` — Route keys for message routing
- `RabbitMqQueueEnum` — Queue definitions (not shown but referenced)

### Message Flow
1. Client calls `/sendMsg` or `/sendMsgDirect`
2. `MessageQueueService` sends via `RabbitTemplate` to configured exchange
3. `DirectQueueListener` receives and manually acknowledges via `channel.basicAck()`