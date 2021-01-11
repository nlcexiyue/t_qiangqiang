package com.qiangqiang.ImageBase;

import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.Base64;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/10/22
 * \* Time: 10:24
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Image {
    /**
     * 将图片转换成Base64编码
     * @param imgFile 待处理图片
     * @return
     */
    public static String imageToBase64(String imgFile) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        try(InputStream in = new FileInputStream(imgFile)){
            data = new byte[in.available()];
            in.read(data);
        } catch (IOException e){
            e.printStackTrace();
        }
        return new String(Base64.getEncoder().encode(data));
    }

    /**
     * 对字节数组字符串进行Base64解码并生成图片
     * @param imgStr 图片数据
     * @return
     */
    public static byte[] base64ToImage(String imgStr) {

        try {
            // Base64解码
            byte[] b = Base64.getDecoder().decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return ArrayUtils.EMPTY_BYTE_ARRAY;
        }
    }


    public static void main(String[] args) {
        String s = Image.imageToBase64("C:\\Users\\cq\\Pictures\\1.png");
        System.out.println(s);
        byte[] bytes = Image.base64ToImage(s);

        System.out.println(bytes);
        String filePath = "C:\\Users\\cq\\Desktop\\";
        String fileName = "1.jpg";
        BufferedOutputStream bos=null;
        FileOutputStream fos=null;
        File file=null;
        try{
            File dir=new File(filePath);
            if(!dir.exists() && !dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file=new File(filePath+fileName);
            fos=new FileOutputStream(file);
            bos=new BufferedOutputStream(fos);
            bos.write(bytes);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            try{
                if(bos != null){
                    bos.close();
                }
                if(fos != null){
                    fos.close();
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

    }

}