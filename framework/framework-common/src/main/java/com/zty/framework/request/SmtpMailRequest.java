package com.zty.framework.request;

import java.util.List;

/**
 * 基于smtp协议发送邮件的请求类.
 */
public class SmtpMailRequest {

    /**
     * 寄件人邮箱.
     */
    private String fromMail;
    /**
     * 寄件人邮箱密码.
     */
    private String fromMailPassword;

    /**
     * 收件人邮箱.
     */
    private List<String> toMails;

    /**
     * 抄送人邮箱.
     */
    private List<String> ccMails;

    /**
     * 密抄人邮箱.
     */
    private List<String> bccMails;

    /**
     * 邮件主题.
     */
    private String subject;

    /**
     * 邮件正文.
     */
    private String content;

    public SmtpMailRequest() {

    }

    public SmtpMailRequest fromMail(String fromMail) {
        this.fromMail = fromMail;
        return this;
    }

    public SmtpMailRequest toMails(List<String> toMails) {
        this.toMails = toMails;
        return this;
    }

    public SmtpMailRequest ccMails(List<String> ccMails) {
        this.ccMails = ccMails;
        return this;
    }

    public SmtpMailRequest bccMails(List<String> bccMails) {
        this.bccMails = bccMails;
        return this;
    }

    public SmtpMailRequest subject(String subject) {
        this.subject = subject;
        return this;
    }

    public SmtpMailRequest content(String content) {
        this.content = content;
        return this;
    }

    public String getFromMail() {
        return fromMail;
    }

    public void setFromMail(String fromMail) {
        this.fromMail = fromMail;
    }

    public String getFromMailPassword() {
        return fromMailPassword;
    }

    public void setFromMailPassword(String fromMailPassword) {
        this.fromMailPassword = fromMailPassword;
    }

    public List<String> getToMails() {
        return toMails;
    }

    public void setToMails(List<String> toMails) {
        this.toMails = toMails;
    }

    public List<String> getCcMails() {
        return ccMails;
    }

    public void setCcMails(List<String> ccMails) {
        this.ccMails = ccMails;
    }

    public List<String> getBccMails() {
        return bccMails;
    }

    public void setBccMails(List<String> bccMails) {
        this.bccMails = bccMails;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
