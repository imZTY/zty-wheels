import com.zty.framework.util.dataSave.CsvDancer;
import com.zty.framework.util.dataSave.DanceEditor;
import com.zty.framework.util.dataSave.DataDancer;
import com.zty.framework.util.picture.FileDancer;
import com.zty.framework.util.picture.PictureDancer;
import com.zty.framework.util.read.ReadTextUtil;
import com.zty.framework.util.request.ApacheHttpRequest;
import com.zty.framework.util.request.HttpRequestor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author tianyi
 * @date 2020-12-20 08:11
 */
public class BasicTest {

    Logger logger = LoggerFactory.getLogger(BasicTest.class);

//    @Test
    public void dealTest(){
        String newDirPath = "F:\\study\\jidi\\career\\2021\\01\\1231-_阿里开放平台选项\\result";
        String dirPath = "F:\\study\\jidi\\career\\2021\\01\\1231-_阿里开放平台选项\\新1";
        File dir = new File(dirPath);
        String[] filenames = dir.list(new FilenameFilter() {
            private final String pattern = "^[^(]+(_[^(]+)+.csv$";
            @Override
            public boolean accept(File dir, String name) {
                // 只保留满足正则表达式的文件
                return Pattern.matches(pattern, name);
            }
        });
        for (int i = 0; i < filenames.length; i++) {
            String filename = filenames[i];
            try {
                List<String> data0 = ReadTextUtil.getLineList(dirPath + "/" + filename);
                List<String> data = new ArrayList<>();
                for (String row : data0) {
                    int number = 1;
                    int nextNum = 2;
                    if ((row.indexOf(number + ",") == -1 || row.indexOf(number + ",") != 0)){
                        data.add(row);
                    }else {
                        int beginIndex = 0;
                        int nextIndex = row.indexOf(","+nextNum+",");
                        try{
                            while (nextIndex != -1) {
                                data.add(row.substring(beginIndex, nextIndex));
                                nextNum++;
                                beginIndex = nextIndex + 1;
                                nextIndex = row.indexOf(","+nextNum+",\"");
                            }
                        }catch (Exception e){
                            System.out.println(beginIndex + "," + nextIndex + "," + nextNum + "," + filename);
                            e.printStackTrace();
                            throw e;
                        }
                        data.add(row.substring(beginIndex));
                    }
                }
                DataDancer<String> dataDancer = new CsvDancer<>(data.stream().map((per) -> {
                    return per + "\r";
                }).collect(Collectors.toList()));
                dataDancer.setHeader("");
                DanceEditor editor = new DanceEditor(newDirPath + "/" + filename, dataDancer);
                editor.dance();
                System.out.println(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void http404Test(){
        HttpRequestor httpRequestor = new ApacheHttpRequest();
        FileDancer fileDancer = new PictureDancer();
        try {
            fileDancer.saveFile("test.html",
                    httpRequestor.sendGet("http://www.ccgp-gansu.gov.cn/web/article/402882817665002701768e3b13445fcc.html", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
