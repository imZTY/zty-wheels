package com.zty.framework.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.zty.framework.util.dataSave.CsvDancer;
import com.zty.framework.util.dataSave.DanceEditor;
import com.zty.framework.util.dataSave.DataDancer;
import com.zty.framework.util.read.ReadTextUtil;

/**
 * @author tianyi
 * @date 2021-03-24 11:30
 */
public class DealCsv {

    public static void main(String[] args) {
        String newDirPath = "F:\\study\\jidi\\career\\2021\\04\\0331-_kdd冲刺\\test";
        String dirPath = "F:\\study\\jidi\\career\\2021\\04\\0331-_kdd冲刺\\直迈网-类目属性（转换后）";
        File dir = new File(dirPath);
        String[] filenames = dir.list(new FilenameFilter() {
            private final String pattern = "^[^(]+(_[^(]+)+.csv$";

            public boolean accept(File dir, String name) {
                return Pattern.matches("^[^(]+(_[^(]+)+.csv$", name);
            }
        });

        for(int i = 0; i < filenames.length; ++i) {
            String filename = filenames[i];

            try {
                List<String> data0 = ReadTextUtil.getLineList(dirPath + "/" + filename);
                List<String> data = new ArrayList();
                Iterator var9 = data0.iterator();

                while(true) {
                    while(var9.hasNext()) {
                        String row = (String)var9.next();
                        int number = 1;
                        int nextNum = 2;
                        if (row.indexOf(number + ",") != -1 && row.indexOf(number + ",") == 0) {
                            int beginIndex = 0;
                            int nextIndex = row.indexOf("," + nextNum + ",");

                            try {
                                while(nextIndex != -1) {
                                    data.add(row.substring(beginIndex, nextIndex));
                                    ++nextNum;
                                    beginIndex = nextIndex + 1;
                                    nextIndex = row.indexOf("," + nextNum + ",\"");
                                }
                            } catch (Exception var16) {
                                System.out.println(beginIndex + "," + nextIndex + "," + nextNum + "," + filename);
                                var16.printStackTrace();
                                throw var16;
                            }

                            data.add(row.substring(beginIndex));
                        } else {
                            data.add(row);
                        }
                    }

                    DataDancer<String> dataDancer = new CsvDancer((List)data.stream().map((per) -> {
                        return per + "\r";
                    }).collect(Collectors.toList()));
                    dataDancer.setHeader("");
                    DanceEditor editor = new DanceEditor(newDirPath + "/" + filename, dataDancer);
                    editor.dance();
                    System.out.println(i);
                    break;
                }
            } catch (IOException var17) {
                var17.printStackTrace();
            }
        }
    }
}
