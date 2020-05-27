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
