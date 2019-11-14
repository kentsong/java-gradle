import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        System.out.println(StringUtils.leftPad("0",10, "a"));

        LinkedList l = new LinkedList();
        l.get(6);
        l.add(1,1);

        ArrayList al = new ArrayList();
        al.get(6);
        al.add(1,1);
    }
}
