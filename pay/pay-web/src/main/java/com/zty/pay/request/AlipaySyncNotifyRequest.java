package com.zty.pay.request;

/**
 * 阿里支付同步回调参数(支付完成后跳转至return_url时携带的参数)
 * @author: tianyi.zeng
 * @create: 12/2/2022 - 下午 6:58
 */
public class AlipaySyncNotifyRequest {

    private String charset;

    private String method;

    private String auth_app_id;

    private String version;

    private String timestamp;

    // 以上是某次沙箱环境同步回调的不在异步回调文档参数里的参数

    /**
     * trade_status_sync
     */
    private String notify_type;

    /**
     * 91722adff935e8cfa58b3aabf4dead6ibe
     */
    private String notify_id;

    /**
     * 2017-02-16 21:46:15
     */
    private String notify_time;

    /**
     * RSA2
     */
    private String sign_type;

    /**
     * WcO t3D8Kg71dTlKwN7r9PzUOXeaBJwp8/FOuSxcuSkXsoVYxBpsAidprySCjHCjmaglNcjoKJQLJ28/Asl93joTW39FX6i07lXhnbPknezAlwmvPdnQuI01HZsZF9V1i6ggZjBiAd5lG8bZtTxZOJ87ub2i9GuJ3Nr/NUc9VeY=
     */
    private String sign;

    /**
     *
     */
    private String fund_bill_list;

    /**
     *
     */
    private String receipt_amount;

    /**
     *
     */
    private String invoice_amount;

    /**
     *
     */
    private String buyer_pay_amount;

    /**
     *
     */
    private String point_amount;

    /**
     *
     */
    private String voucher_detail_list;

    /**
     *
     */
    private String passback_params;

    /**
     *
     */
    private String out_channel_type;

    /**
     * 必含
     * 支付宝交易号，支付宝交易凭证号。最大64
     * 2013112011001004330000121536
     */
    private String trade_no;

    /**
     * 必含
     * 支付宝应用的APPID。支付宝分配给开发折的应用 ID。最大32
     * 2019082200007148
     */
    private String app_id;

    /**
     * 必含
     * 商家订单号。原支付请求的商家订单号。最大64
     * 6823789339978248
     */
    private String out_trade_no;

    /**
     * 商家业务号。商家业务ID，通常是退款通知中返回的退款申请流水号。最大64
     * HZRF001
     */
    private String out_biz_no;

    /**
     * 买家支付宝账号 ID。以 2088 开头的纯 16 位数字。
     * 20881***524333
     */
    private String buyer_id;

    /**
     * 卖家支付宝账号 ID。以 2088 开头的纯 16 位数字。
     * 20881***2239364
     */
    private String seller_id;

    /**
     * 交易状态。交易目前所处状态，详见下表 交易状态说明。最长32
     *
     * WAIT_BUYER_PAY 交易创建，等待买家付款。
     * TRADE_CLOSED 未付款交易超时关闭，或支付完成后全额退款。
     * TRADE_SUCCESS 交易支付成功。
     * TRADE_FINISHED 交易结束，不可退款。
     */
    private String trade_status;

    /**
     * 订单金额。本次交易支付订单金额，单位为人民币（元），精确到小数点后 2 位。长度11
     * 20.00
     */
    private String total_amount;

    /**
     * 总退款金额。退款通知中，返回总退款金额，单位为人民币（元），精确到小数点后 2 位。长度11
     * 2.58
     */
    private String refund_fee;

    /**
     * 订单标题/商品标题/交易标题/订单关键字等，是请求时对应参数，会在通知中原样传回。最长256
     * XXXX交易
     */
    private String subject;

    /**
     * 商品描述。该订单的备注、描述、明细等。对应请求时的 body 参数，会在通知中原样传回。最长400
     * XXX交易内容
     */
    private String body;

    /**
     * 交易创建时间。格式为 yyyy-MM-dd HH:mm:ss。
     * 2018-08-25 15:34:42
     */
    private String gmt_create;

    /**
     * 交易付款时间。格式为 yyyy-MM-dd HH:mm:ss。
     * 2018-08-25 15:34:42
     */
    private String gmt_payment;

    /**
     * 交易退款时间。格式为 yyyy-MM-dd HH:mm:ss.S
     * 2018-08-26 10:34:44.340
     */
    private String gmt_refund;

    /**
     * 交易结束时间。格式为 yyyy-MM-dd HH:mm:ss
     * 2018-08-26 16:32:30
     */
    private String gmt_close;

    /**
     * 180****0062
     */
    private String buyer_logon_id;

    /**
     * 8.88
     */
    private String charge_amount;

    /**
     * bluesea_1
     */
    private String charge_flags;

    /**
     * 2018101610032004620239146945
     */
    private String settlement_id;

    /**
     * DC
     */
    private String receipt_currency_type;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAuth_app_id() {
        return auth_app_id;
    }

    public void setAuth_app_id(String auth_app_id) {
        this.auth_app_id = auth_app_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNotify_type() {
        return notify_type;
    }

    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }

    public String getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getFund_bill_list() {
        return fund_bill_list;
    }

    public void setFund_bill_list(String fund_bill_list) {
        this.fund_bill_list = fund_bill_list;
    }

    public String getReceipt_amount() {
        return receipt_amount;
    }

    public void setReceipt_amount(String receipt_amount) {
        this.receipt_amount = receipt_amount;
    }

    public String getInvoice_amount() {
        return invoice_amount;
    }

    public void setInvoice_amount(String invoice_amount) {
        this.invoice_amount = invoice_amount;
    }

    public String getBuyer_pay_amount() {
        return buyer_pay_amount;
    }

    public void setBuyer_pay_amount(String buyer_pay_amount) {
        this.buyer_pay_amount = buyer_pay_amount;
    }

    public String getPoint_amount() {
        return point_amount;
    }

    public void setPoint_amount(String point_amount) {
        this.point_amount = point_amount;
    }

    public String getVoucher_detail_list() {
        return voucher_detail_list;
    }

    public void setVoucher_detail_list(String voucher_detail_list) {
        this.voucher_detail_list = voucher_detail_list;
    }

    public String getPassback_params() {
        return passback_params;
    }

    public void setPassback_params(String passback_params) {
        this.passback_params = passback_params;
    }

    public String getOut_channel_type() {
        return out_channel_type;
    }

    public void setOut_channel_type(String out_channel_type) {
        this.out_channel_type = out_channel_type;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_biz_no() {
        return out_biz_no;
    }

    public void setOut_biz_no(String out_biz_no) {
        this.out_biz_no = out_biz_no;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }

    public String getGmt_payment() {
        return gmt_payment;
    }

    public void setGmt_payment(String gmt_payment) {
        this.gmt_payment = gmt_payment;
    }

    public String getGmt_refund() {
        return gmt_refund;
    }

    public void setGmt_refund(String gmt_refund) {
        this.gmt_refund = gmt_refund;
    }

    public String getGmt_close() {
        return gmt_close;
    }

    public void setGmt_close(String gmt_close) {
        this.gmt_close = gmt_close;
    }

    public String getBuyer_logon_id() {
        return buyer_logon_id;
    }

    public void setBuyer_logon_id(String buyer_logon_id) {
        this.buyer_logon_id = buyer_logon_id;
    }

    public String getCharge_amount() {
        return charge_amount;
    }

    public void setCharge_amount(String charge_amount) {
        this.charge_amount = charge_amount;
    }

    public String getCharge_flags() {
        return charge_flags;
    }

    public void setCharge_flags(String charge_flags) {
        this.charge_flags = charge_flags;
    }

    public String getSettlement_id() {
        return settlement_id;
    }

    public void setSettlement_id(String settlement_id) {
        this.settlement_id = settlement_id;
    }

    public String getReceipt_currency_type() {
        return receipt_currency_type;
    }

    public void setReceipt_currency_type(String receipt_currency_type) {
        this.receipt_currency_type = receipt_currency_type;
    }
}
