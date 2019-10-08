package com.zzyy.batch;

import com.zzyy.entity.Message;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static java.text.MessageFormat.format;

/**
 * @Auther: zhouyu
 * @Date: 2019/8/12 17:18
 * @Description:
 */
public class MessageWriteListener implements ItemWriteListener<Message> {

    @Autowired
    private Writer errorWriter;



    @Override
    public void beforeWrite(List<? extends Message> list) {

    }

    @Override
    public void afterWrite(List<? extends Message> list) {

    }

    @Override
    public void onWriteError(Exception e, List<? extends Message> list) {
        try {
            errorWriter.write(format("%s%n", e.getMessage()));

            for (Message message : list) {
                errorWriter.write(format("Failed writing message id: %s", message.getObjectId()));
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
