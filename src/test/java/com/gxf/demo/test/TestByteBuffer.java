package com.gxf.demo.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 测试IO读写文件
 *
 * @author GXF
 */
@Slf4j
class TestByteBuffer {

    @Test
    void test() {
        String path = Objects.requireNonNull(getClass().getResource("/")).getPath();
        log.info(path);
        try (FileInputStream stream = new FileInputStream(path + "test.txt")) {
            FileChannel channel = stream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(4);
            while (-1 != channel.read(buffer)) {
                buffer.flip();
                printBuffer(buffer);
                buffer.compact();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("读取文件测试失败！", e);
        }
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 1, j = 10, k = 0; i < 6; i++, j--, k++) {
            map.put(i + "+" + j, k);
        }
    }

    private void printBuffer(ByteBuffer buffer) {
        StringBuilder sb = new StringBuilder();
        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            sb.append((char) b);
        }
        log.info("read string:{}", sb);
    }
}
