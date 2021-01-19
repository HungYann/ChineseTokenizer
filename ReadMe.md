# 中文分词



目的：用来将中文单词分成一个个词组进行后续处理。

编写语言为**Java**





> 分词词库



地址：https://github.com/LIU-HONGYANG/ChineseTokenizer/blob/master/dict.txt



![image-20200527142007478](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf6zix2a48j30iu018dfr.jpg)





> 分词器代码

##### 

```java
package chapter01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



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

```

> 测试代码

##### 

```java
package chapter01;



import java.util.ArrayList;
import java.util.Iterator;

public class TestFenCi {

    public static void main(String[] args) {

        String receive="罗教是中国明清两代流行的民间宗教教派，明代中期由军人罗清创立，以《苦功悟道卷》等“五部六册”为主要经书，主张寻求人心本性的觉悟，反对外在的宗教仪式或造像，适合在家修行，信众以运河水手为主要基础，会堂遍布大江南北，各自为政，信徒素食、念经，作风平和。在民间，罗清被称为“罗祖”，地位崇高，罗教也常被民众视为佛教的一支，信徒众多，分成多个派系，往往与白莲教并称，在山东一度威胁正统佛教的地位，在清代多次受官府取缔，被指斥为邪教，支派流衍成长生教、青莲教、真空教、斋教等多个教派。";
        int total_len = receive.length();
        FenCi fenCi = new FenCi(total_len, receive);
        fenCi.Zheng();

        ArrayList<String> result = fenCi.output();

        System.out.println("---");
        Iterator iterator = result.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println(result.size());

    }
}

```



> 测试结果



1.

![Screenshot 2020-05-28 at 18.22.44](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf8c5vp9ldj306y0dsq4n.jpg)



2.

![Screenshot 2020-05-28 at 18.22.49](https://tva1.sinaimg.cn/large/007S8ZIlgy1gf8c60l2axj30680e4abb.jpg)



> 使用方法



1.核心文件是：

[FenCi.java](https://github.com/LIU-HONGYANG/ChineseTokenizer/blob/master/FenCi.java)

[TestFenCi.java](https://github.com/LIU-HONGYANG/ChineseTokenizer/blob/master/TestFenCi.java)

下载即可使用



2.

- 在分词器Java代码中，“dict.txt”为读取词典的语句, 可以更改

```
 //读取词库文本
 String file = "dict.txt";
```



-  在测试代码中，receive为接收的字符串

```
String receive
```



3. 词库位置是 [词库](https://github.com/LIU-HONGYANG/ChineseTokenizer/tree/master/%E4%B8%AD%E6%96%87%E5%88%86%E8%AF%8D%E8%AF%8D%E5%BA%93%E6%95%B4%E7%90%86) ，建议自行更改或添加词库或单词





