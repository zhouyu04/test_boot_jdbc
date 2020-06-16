package com.zzyy.utils.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Auther: zhouyu
 * @Date: 2020/6/11 14:59
 * @Description:
 */
public class Uploadfile {

    private static Logger logger = LoggerFactory.getLogger(Uploadfile.class);


    public static void main(String[] args) throws Exception {

        String attachmentAndImageFullUrl = "http://tf-feature1.jdy.com/fileserver/jdyattachmentimage/19204/jdy/19204/202006/mb/card_style/images/of16maPXrHqpOOy7tJ/Koala.jpg";

        URL url = new URL(attachmentAndImageFullUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStream inputStream = connection.getInputStream();
        byte[] bytes = readInputStream(inputStream);

        String wxUrl = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" +
                "34_WrMTrp1-z1JUIemkoRMoKDf2AqWdPuJrAdC708iXQb7PHmHpVKLqFRaTh85LzLsi8svQRkxUflQ1H9XOkhDyG8HHekBBnxiX-VD1LMCylzhgVFJ4IyvagaVd3aj7EXSa7xgU3H6s1y2cgtGhXFAiAGDMUP";

        String upload = upload(wxUrl, bytes);

        System.out.println(upload);

    }


    public static String doPost(String u, String parm) {
        String contentStr = "";
        HttpURLConnection connection = null;
        try {
            URL url = new URL(u);
            connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json;encoding=utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();

            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            out.append(parm);

            out.flush();
            out.close();

//            int responseCode = connection.getResponseCode();
            InputStream urlStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream, "UTF-8"));
            String sCurrentLine = "";
            StringBuffer sTotalString = new StringBuffer("");
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                sTotalString.append(sCurrentLine);
            }
            contentStr = sTotalString.toString();
            urlStream.close();
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }


        return contentStr;
    }


    public static String uploadFile(String uploadUrl, byte[] bbyte) {
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "---------------------------823928434";
        try {
            URL url = new URL(uploadUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);


            DataOutputStream dos = new DataOutputStream(httpURLConnection.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + end);
            dos.writeBytes("Content-Disposition: form-data; name=\"file1\"; filename=\"test.jpg\"" + end);
            dos.writeBytes(end);
            dos.write(bbyte);
            dos.writeBytes(end);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
            dos.flush();

            // 读取服务器返回结果
            InputStream is = httpURLConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String result = br.readLine();
            logger.info("response", "" + result);
            is.close();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String upload(String url, byte[] btImg) throws Exception {
        String result = null;

        /**
         * 第一部分
         */
        URL urlObj = new URL(url);
        // 连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false); // post方式不能使用缓存


        // 设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");

        // 设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);


        // 请求正文信息


        // 第一部分：
        StringBuilder sb = new StringBuilder();
        sb.append("--"); // 必须多两道线
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"newfile\";filename=\""
                + "test00001.jpg" + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");


        byte[] head = sb.toString().getBytes("utf-8");


        // 获得输出流
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        // 输出表头
        out.write(head);


        out.write(btImg);

        // 结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线


        out.write(foot);


        out.flush();
        out.close();


        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {

            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line = null;
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new IOException("数据读取异常");
        } finally {
            if (reader != null) {
                reader.close();
            }

        }
        return result;


    }


    /**
     * 从输入流中获取数据
     *
     * @param inStream 输入流
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
