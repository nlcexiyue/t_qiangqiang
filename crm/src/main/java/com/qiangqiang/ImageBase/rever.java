package com.qiangqiang.ImageBase;

import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/10/22
 * \* Time: 15:33
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class rever {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\cq\\Desktop\\1.txt"));
        List<String> list = new ArrayList<>();
        String line = null;
        while ((line = br.readLine())!= null){
            list.add(line);
        }
        Collections.reverse(list);

        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\cq\\Desktop\\2.txt"));
        for (String s : list) {
            bw.write(s);
            bw.write("\n");
        }
        bw.close();
        br.close();






    }



}