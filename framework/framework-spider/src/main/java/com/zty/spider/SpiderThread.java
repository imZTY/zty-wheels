package com.zty.spider;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author tianyi
 * @date 2021-01-28 21:09
 */
public abstract class SpiderThread extends Thread {

    protected String threadName;

    protected CountDownLatch latch;

    protected List<String> dataStrList;

    public SpiderThread(String threadName, CountDownLatch latch, List<String> dataStrList){
        this.threadName = threadName;
        if (CollectionUtils.isEmpty(dataStrList)){
            logWithThreadName("输入数据为空");
            throw new RuntimeException("输入数据为空");
        }
        this.latch = latch;
        this.dataStrList = dataStrList;
    }

    @Override
    public abstract void run();

    protected void mkdirIfNotExist(String path){
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdirs();
        }
    }

    protected String getCsvRow(String... fields){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            sb.append("\"" + fields[i] + "\",");
        }
        return sb.deleteCharAt(sb.length() - 1).toString() + "\r";
    }

    protected String getHrefByDriverAndCss(WebDriver driver, String cssSelector) {
        try {
            return driver.findElement(By.cssSelector(cssSelector)).getAttribute("href").replaceAll("\n","");
        }catch (NoSuchElementException e){
            logWithThreadName("driver找不到链接标签:{}"+ cssSelector);
            return "";
        }
    }

    protected String getByDriverAndCss(WebDriver driver, String cssSelector) {
        try {
            return driver.findElement(By.cssSelector(cssSelector)).getText().replaceAll("\n","");
        }catch (NoSuchElementException e){
            logWithThreadName("driver找不到标签:{}"+ cssSelector);
            return "";
        }
    }

    protected String getByElementAndCss(WebElement webElement, String cssSelector) {
        try {
            return webElement.findElement(By.cssSelector(cssSelector)).getText().replaceAll("\n","");
        } catch (NoSuchElementException e) {
            logWithThreadName("webElement找不到标签:{}" + cssSelector);
            return "";
        }
    }

    protected boolean detectDomByCss(WebDriver driver, String selector) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return driver.findElements(By.cssSelector(selector)).size() > 0;
    }

    protected void logWithThreadName(String logStr, Object ...args) {
        for (Object obj : args) {
            logStr = logStr.replaceFirst("\\{\\}", String.valueOf(obj));
        }
        System.out.println(new Date().toString() + "["+threadName+"]_"+logStr);
    }
}
