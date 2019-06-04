package com.zzyy.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Random;


public class UIDUtils {
    private static final Logger log = LoggerFactory.getLogger(UIDUtils.class);

    public static long getUID() {
        Random random = new Random();
        int[] sed = new int[6];
        sed[0] = random.nextInt(10);
        sed[1] = random.nextInt(10);
        sed[2] = random.nextInt(10);
        sed[3] = random.nextInt(10);
        sed[4] = random.nextInt(10);

        String uid = String.valueOf(Calendar.getInstance().getTimeInMillis())
                .substring(4) + NumberRadomUtil.getUnsignNumber(sed);

        return Long.parseLong(uid);

    }

    public static long getUID(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        Random random = new Random();
        int[] sed = new int[6];
        sed[0] = random.nextInt(10);
        sed[1] = random.nextInt(10);
        sed[2] = random.nextInt(10);
        sed[3] = random.nextInt(10);
        sed[4] = random.nextInt(10);

        String uid = String.valueOf(Calendar.getInstance().getTimeInMillis())
                .substring(4) + NumberRadomUtil.getUnsignNumber(sed);

        return Long.parseLong(uid);

    }

    public static long createUIDValue() {
        Random random = new Random();
        int[] sed = new int[6];
        sed[0] = random.nextInt(10);
        sed[1] = random.nextInt(10);
        sed[2] = random.nextInt(10);
        sed[3] = random.nextInt(10);
        sed[4] = random.nextInt(10);
        sed[5] = random.nextInt(10);

        // String uid = String.valueOf(Calendar.getInstance().getTimeInMillis())
        // + random.nextInt(9) + NumberRadomUtil.getUnsignNumber(sed);
        String uid = String.valueOf(Calendar.getInstance().getTimeInMillis()).substring(4)
                + NumberRadomUtil.getUnsignNumber(sed);

        return Long.parseLong(uid);
    }

    /**
     * 产生UID增加1秒休眠时间，避免UID冲突，用于系统启用等其他系统操作业务
     *
     * @param millis
     * @return
     */
    public static long createUIDValue(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        Random random = new Random();
        int[] sed = new int[6];
        sed[0] = random.nextInt(10);
        sed[1] = random.nextInt(10);
        sed[2] = random.nextInt(10);
        sed[3] = random.nextInt(10);
        sed[4] = random.nextInt(10);
        sed[5] = random.nextInt(10);

        String uid = String.valueOf(Calendar.getInstance().getTimeInMillis()).substring(4)
                + NumberRadomUtil.getUnsignNumber(sed);

        return Long.parseLong(uid);
    }

}