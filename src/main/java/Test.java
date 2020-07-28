import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {
    public static void main(String[] args) {
        String json = "{\"oid\":\"4041\",\"url\":\"http://g3com.cp21.ott.cibntv.net/vod/v1/MTI1LzQzLzYxL2xldHYtZ3VnLzE3L3Zlcl8wMF8yMi0xMTI2OTA0Njc2LWF2Yy0xNDc3NTE1LWFhYy05NjAwMC0xNTA0MC0yOTgzOTYwLWJiZmRmNTllMjczY2VkZDQ4MTI5OTEzYWEzMTExZjdmLTE1ODM5MDExMjQ1MDIubXA0?platid=100&splatid=10004&gugtype=1&mmsid=67088358&type=tv_1080p_mp4\",\"materialType\":\"mp4\",\"position\":\"{\\\"x\\\":550,\\\"y\\\":550,\\\"width\\\":550,\\\"height\\\":550}\",\"skipTime\":\"10\"}";
        Gson gson = new Gson();
        BootAdVO vo = gson.fromJson(json, BootAdVO.class);
        System.out.println(vo.toString());
        BootAdVO.PositionData posData = gson.fromJson(vo.getPosition(), BootAdVO.PositionData.class);
        System.out.println(posData.toString());



        List<String> mSmartAdBeanList = new CopyOnWriteArrayList<>(new ArrayList<String>(6));
        mSmartAdBeanList.add("1");
        mSmartAdBeanList.add("2");
        mSmartAdBeanList.add("3");
        Iterator<String> it = mSmartAdBeanList.iterator();

        List<String> list = new ArrayList<>();

        while(it.hasNext()){
            String s = it.next();
//            it.remove();
            list.add(s);
        }

        for(String s : list){
            mSmartAdBeanList.remove(s);
        }
        System.out.println(mSmartAdBeanList);


    }
}
