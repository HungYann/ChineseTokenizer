package chapter01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;







//public static String getString() {
//
//        String str = "";
//        for (i = 0; i < words.size(); i++) {
//        if (i < words.size() - 1)
//        str = str + words.get(i) + "\\";
//        else
//        str = str + words.get(i);
//        }
//
//        return str;
//        }


public class FenCi {

    int start = 0;
    int total_len;
    int max_len = 4;
    int tmp_len; // tmp_len用来存放当前切取的词语的长度
    int i;
    String receive;





    //构造函数

    public FenCi() {
    }

    public FenCi(int total_len, String receive) {
        this.total_len = total_len;
        this.receive = receive;  //接收词的长度
    }

    /*
     * public FenCi(int total_len, String receive) { this.total_len = 13;
     * this.receive = "李迪安说要买个四百万的老婆"; }
     */

    ArrayList<String> words = new ArrayList<>();// 存放分好词的字符
    ArrayList<String> temp = new ArrayList<>();// 存放分词词库文件


    public void Zheng() {


        //读取分词词库文件
        try {
            BufferedReader bre = null;


            //读取词库文本
            String file = "dict.txt";
            bre = new BufferedReader(new FileReader(file));
            String line;
            try {
                while ((line = bre.readLine()) != null) // 判断最后一行不存在，为空结束循环
                {
                    temp.add(line);
                    // System.out.println(line);// 原样输出读到的内容
                }

                bre.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            System.out.println("未成功读取文件");
            e.printStackTrace();
        }


        String sub_str;

        while (start < total_len) {

            boolean flag = false;

            tmp_len = start + max_len <= total_len ? max_len : total_len - start;

            label: while (tmp_len > 0) {

                sub_str = receive.substring(start, start + tmp_len);
                // 与词库文件进行匹配，此处可以改成hashmap提高效率
                for (i = 0; i < temp.size(); i++) {
                    if (sub_str.equals(temp.get(i))) {
                        //如果遇到词库中的词语，则将找到
                        flag = true;
                    }
                }


                if (flag == true) {
                    words.add(receive.substring(start, start + tmp_len));
                    start = start + tmp_len;
                    break label;
                } else {
                    //否则flag=false，将tmp_len的长度减1
                    tmp_len = tmp_len - 1;
                    // System.out.println(tmp_len);
                }
            }

            if (tmp_len == 0) {
                words.add(receive.substring(start, start + 1));
                start = start + 1;
            }


        }

    }

    public ArrayList<String> output(){
        return words;
    }



}
