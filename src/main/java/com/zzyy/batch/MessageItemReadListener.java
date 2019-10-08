package com.zzyy.batch;

import com.zzyy.entity.Message;
import org.springframework.batch.core.ItemReadListener;

import java.io.Writer;

/**
 * @Auther: zhouyu
 * @Date: 2019/8/12 17:16
 * @Description:
 */
public class MessageItemReadListener implements ItemReadListener<Message> {

    private Writer errorWriter;

    public MessageItemReadListener(Writer errorWriter) {
        this.errorWriter = errorWriter;
    }

    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(Message message) {

    }

    @Override
    public void onReadError(Exception e) {

    }
}
