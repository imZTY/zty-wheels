package com.zty.pay.DO.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderInfoDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderInfoDOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoIsNull() {
            addCriterion("third_order_no is null");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoIsNotNull() {
            addCriterion("third_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoEqualTo(String value) {
            addCriterion("third_order_no =", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoNotEqualTo(String value) {
            addCriterion("third_order_no <>", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoGreaterThan(String value) {
            addCriterion("third_order_no >", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("third_order_no >=", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoLessThan(String value) {
            addCriterion("third_order_no <", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoLessThanOrEqualTo(String value) {
            addCriterion("third_order_no <=", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoLike(String value) {
            addCriterion("third_order_no like", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoNotLike(String value) {
            addCriterion("third_order_no not like", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoIn(List<String> values) {
            addCriterion("third_order_no in", values, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoNotIn(List<String> values) {
            addCriterion("third_order_no not in", values, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoBetween(String value1, String value2) {
            addCriterion("third_order_no between", value1, value2, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoNotBetween(String value1, String value2) {
            addCriterion("third_order_no not between", value1, value2, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(Integer value) {
            addCriterion("account_id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(Integer value) {
            addCriterion("account_id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(Integer value) {
            addCriterion("account_id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(Integer value) {
            addCriterion("account_id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(Integer value) {
            addCriterion("account_id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<Integer> values) {
            addCriterion("account_id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<Integer> values) {
            addCriterion("account_id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(Integer value1, Integer value2) {
            addCriterion("account_id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("account_id not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andOrderMethodIsNull() {
            addCriterion("order_method is null");
            return (Criteria) this;
        }

        public Criteria andOrderMethodIsNotNull() {
            addCriterion("order_method is not null");
            return (Criteria) this;
        }

        public Criteria andOrderMethodEqualTo(Byte value) {
            addCriterion("order_method =", value, "orderMethod");
            return (Criteria) this;
        }

        public Criteria andOrderMethodNotEqualTo(Byte value) {
            addCriterion("order_method <>", value, "orderMethod");
            return (Criteria) this;
        }

        public Criteria andOrderMethodGreaterThan(Byte value) {
            addCriterion("order_method >", value, "orderMethod");
            return (Criteria) this;
        }

        public Criteria andOrderMethodGreaterThanOrEqualTo(Byte value) {
            addCriterion("order_method >=", value, "orderMethod");
            return (Criteria) this;
        }

        public Criteria andOrderMethodLessThan(Byte value) {
            addCriterion("order_method <", value, "orderMethod");
            return (Criteria) this;
        }

        public Criteria andOrderMethodLessThanOrEqualTo(Byte value) {
            addCriterion("order_method <=", value, "orderMethod");
            return (Criteria) this;
        }

        public Criteria andOrderMethodIn(List<Byte> values) {
            addCriterion("order_method in", values, "orderMethod");
            return (Criteria) this;
        }

        public Criteria andOrderMethodNotIn(List<Byte> values) {
            addCriterion("order_method not in", values, "orderMethod");
            return (Criteria) this;
        }

        public Criteria andOrderMethodBetween(Byte value1, Byte value2) {
            addCriterion("order_method between", value1, value2, "orderMethod");
            return (Criteria) this;
        }

        public Criteria andOrderMethodNotBetween(Byte value1, Byte value2) {
            addCriterion("order_method not between", value1, value2, "orderMethod");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNull() {
            addCriterion("order_type is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("order_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(Byte value) {
            addCriterion("order_type =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(Byte value) {
            addCriterion("order_type <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(Byte value) {
            addCriterion("order_type >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("order_type >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(Byte value) {
            addCriterion("order_type <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(Byte value) {
            addCriterion("order_type <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<Byte> values) {
            addCriterion("order_type in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<Byte> values) {
            addCriterion("order_type not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(Byte value1, Byte value2) {
            addCriterion("order_type between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("order_type not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIsNull() {
            addCriterion("order_amount is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIsNotNull() {
            addCriterion("order_amount is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountEqualTo(Long value) {
            addCriterion("order_amount =", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotEqualTo(Long value) {
            addCriterion("order_amount <>", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThan(Long value) {
            addCriterion("order_amount >", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("order_amount >=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThan(Long value) {
            addCriterion("order_amount <", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThanOrEqualTo(Long value) {
            addCriterion("order_amount <=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIn(List<Long> values) {
            addCriterion("order_amount in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotIn(List<Long> values) {
            addCriterion("order_amount not in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountBetween(Long value1, Long value2) {
            addCriterion("order_amount between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotBetween(Long value1, Long value2) {
            addCriterion("order_amount not between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIsNull() {
            addCriterion("discount_type is null");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIsNotNull() {
            addCriterion("discount_type is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeEqualTo(Byte value) {
            addCriterion("discount_type =", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotEqualTo(Byte value) {
            addCriterion("discount_type <>", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeGreaterThan(Byte value) {
            addCriterion("discount_type >", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("discount_type >=", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeLessThan(Byte value) {
            addCriterion("discount_type <", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeLessThanOrEqualTo(Byte value) {
            addCriterion("discount_type <=", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIn(List<Byte> values) {
            addCriterion("discount_type in", values, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotIn(List<Byte> values) {
            addCriterion("discount_type not in", values, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeBetween(Byte value1, Byte value2) {
            addCriterion("discount_type between", value1, value2, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("discount_type not between", value1, value2, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountIsNull() {
            addCriterion("discount_amount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountIsNotNull() {
            addCriterion("discount_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountEqualTo(Long value) {
            addCriterion("discount_amount =", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountNotEqualTo(Long value) {
            addCriterion("discount_amount <>", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountGreaterThan(Long value) {
            addCriterion("discount_amount >", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("discount_amount >=", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountLessThan(Long value) {
            addCriterion("discount_amount <", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountLessThanOrEqualTo(Long value) {
            addCriterion("discount_amount <=", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountIn(List<Long> values) {
            addCriterion("discount_amount in", values, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountNotIn(List<Long> values) {
            addCriterion("discount_amount not in", values, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountBetween(Long value1, Long value2) {
            addCriterion("discount_amount between", value1, value2, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountNotBetween(Long value1, Long value2) {
            addCriterion("discount_amount not between", value1, value2, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountIsNull() {
            addCriterion("actual_amount is null");
            return (Criteria) this;
        }

        public Criteria andActualAmountIsNotNull() {
            addCriterion("actual_amount is not null");
            return (Criteria) this;
        }

        public Criteria andActualAmountEqualTo(Long value) {
            addCriterion("actual_amount =", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotEqualTo(Long value) {
            addCriterion("actual_amount <>", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountGreaterThan(Long value) {
            addCriterion("actual_amount >", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("actual_amount >=", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountLessThan(Long value) {
            addCriterion("actual_amount <", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountLessThanOrEqualTo(Long value) {
            addCriterion("actual_amount <=", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountIn(List<Long> values) {
            addCriterion("actual_amount in", values, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotIn(List<Long> values) {
            addCriterion("actual_amount not in", values, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountBetween(Long value1, Long value2) {
            addCriterion("actual_amount between", value1, value2, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotBetween(Long value1, Long value2) {
            addCriterion("actual_amount not between", value1, value2, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andBalanceChangeIsNull() {
            addCriterion("balance_change is null");
            return (Criteria) this;
        }

        public Criteria andBalanceChangeIsNotNull() {
            addCriterion("balance_change is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceChangeEqualTo(Long value) {
            addCriterion("balance_change =", value, "balanceChange");
            return (Criteria) this;
        }

        public Criteria andBalanceChangeNotEqualTo(Long value) {
            addCriterion("balance_change <>", value, "balanceChange");
            return (Criteria) this;
        }

        public Criteria andBalanceChangeGreaterThan(Long value) {
            addCriterion("balance_change >", value, "balanceChange");
            return (Criteria) this;
        }

        public Criteria andBalanceChangeGreaterThanOrEqualTo(Long value) {
            addCriterion("balance_change >=", value, "balanceChange");
            return (Criteria) this;
        }

        public Criteria andBalanceChangeLessThan(Long value) {
            addCriterion("balance_change <", value, "balanceChange");
            return (Criteria) this;
        }

        public Criteria andBalanceChangeLessThanOrEqualTo(Long value) {
            addCriterion("balance_change <=", value, "balanceChange");
            return (Criteria) this;
        }

        public Criteria andBalanceChangeIn(List<Long> values) {
            addCriterion("balance_change in", values, "balanceChange");
            return (Criteria) this;
        }

        public Criteria andBalanceChangeNotIn(List<Long> values) {
            addCriterion("balance_change not in", values, "balanceChange");
            return (Criteria) this;
        }

        public Criteria andBalanceChangeBetween(Long value1, Long value2) {
            addCriterion("balance_change between", value1, value2, "balanceChange");
            return (Criteria) this;
        }

        public Criteria andBalanceChangeNotBetween(Long value1, Long value2) {
            addCriterion("balance_change not between", value1, value2, "balanceChange");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(Integer value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(Integer value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(Integer value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(Integer value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(Integer value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<Integer> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<Integer> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(Integer value1, Integer value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(Integer value1, Integer value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andFldS1IsNull() {
            addCriterion("fld_s1 is null");
            return (Criteria) this;
        }

        public Criteria andFldS1IsNotNull() {
            addCriterion("fld_s1 is not null");
            return (Criteria) this;
        }

        public Criteria andFldS1EqualTo(String value) {
            addCriterion("fld_s1 =", value, "fldS1");
            return (Criteria) this;
        }

        public Criteria andFldS1NotEqualTo(String value) {
            addCriterion("fld_s1 <>", value, "fldS1");
            return (Criteria) this;
        }

        public Criteria andFldS1GreaterThan(String value) {
            addCriterion("fld_s1 >", value, "fldS1");
            return (Criteria) this;
        }

        public Criteria andFldS1GreaterThanOrEqualTo(String value) {
            addCriterion("fld_s1 >=", value, "fldS1");
            return (Criteria) this;
        }

        public Criteria andFldS1LessThan(String value) {
            addCriterion("fld_s1 <", value, "fldS1");
            return (Criteria) this;
        }

        public Criteria andFldS1LessThanOrEqualTo(String value) {
            addCriterion("fld_s1 <=", value, "fldS1");
            return (Criteria) this;
        }

        public Criteria andFldS1Like(String value) {
            addCriterion("fld_s1 like", value, "fldS1");
            return (Criteria) this;
        }

        public Criteria andFldS1NotLike(String value) {
            addCriterion("fld_s1 not like", value, "fldS1");
            return (Criteria) this;
        }

        public Criteria andFldS1In(List<String> values) {
            addCriterion("fld_s1 in", values, "fldS1");
            return (Criteria) this;
        }

        public Criteria andFldS1NotIn(List<String> values) {
            addCriterion("fld_s1 not in", values, "fldS1");
            return (Criteria) this;
        }

        public Criteria andFldS1Between(String value1, String value2) {
            addCriterion("fld_s1 between", value1, value2, "fldS1");
            return (Criteria) this;
        }

        public Criteria andFldS1NotBetween(String value1, String value2) {
            addCriterion("fld_s1 not between", value1, value2, "fldS1");
            return (Criteria) this;
        }

        public Criteria andFldN1IsNull() {
            addCriterion("fld_n1 is null");
            return (Criteria) this;
        }

        public Criteria andFldN1IsNotNull() {
            addCriterion("fld_n1 is not null");
            return (Criteria) this;
        }

        public Criteria andFldN1EqualTo(Long value) {
            addCriterion("fld_n1 =", value, "fldN1");
            return (Criteria) this;
        }

        public Criteria andFldN1NotEqualTo(Long value) {
            addCriterion("fld_n1 <>", value, "fldN1");
            return (Criteria) this;
        }

        public Criteria andFldN1GreaterThan(Long value) {
            addCriterion("fld_n1 >", value, "fldN1");
            return (Criteria) this;
        }

        public Criteria andFldN1GreaterThanOrEqualTo(Long value) {
            addCriterion("fld_n1 >=", value, "fldN1");
            return (Criteria) this;
        }

        public Criteria andFldN1LessThan(Long value) {
            addCriterion("fld_n1 <", value, "fldN1");
            return (Criteria) this;
        }

        public Criteria andFldN1LessThanOrEqualTo(Long value) {
            addCriterion("fld_n1 <=", value, "fldN1");
            return (Criteria) this;
        }

        public Criteria andFldN1In(List<Long> values) {
            addCriterion("fld_n1 in", values, "fldN1");
            return (Criteria) this;
        }

        public Criteria andFldN1NotIn(List<Long> values) {
            addCriterion("fld_n1 not in", values, "fldN1");
            return (Criteria) this;
        }

        public Criteria andFldN1Between(Long value1, Long value2) {
            addCriterion("fld_n1 between", value1, value2, "fldN1");
            return (Criteria) this;
        }

        public Criteria andFldN1NotBetween(Long value1, Long value2) {
            addCriterion("fld_n1 not between", value1, value2, "fldN1");
            return (Criteria) this;
        }

        public Criteria andFldN2IsNull() {
            addCriterion("fld_n2 is null");
            return (Criteria) this;
        }

        public Criteria andFldN2IsNotNull() {
            addCriterion("fld_n2 is not null");
            return (Criteria) this;
        }

        public Criteria andFldN2EqualTo(Integer value) {
            addCriterion("fld_n2 =", value, "fldN2");
            return (Criteria) this;
        }

        public Criteria andFldN2NotEqualTo(Integer value) {
            addCriterion("fld_n2 <>", value, "fldN2");
            return (Criteria) this;
        }

        public Criteria andFldN2GreaterThan(Integer value) {
            addCriterion("fld_n2 >", value, "fldN2");
            return (Criteria) this;
        }

        public Criteria andFldN2GreaterThanOrEqualTo(Integer value) {
            addCriterion("fld_n2 >=", value, "fldN2");
            return (Criteria) this;
        }

        public Criteria andFldN2LessThan(Integer value) {
            addCriterion("fld_n2 <", value, "fldN2");
            return (Criteria) this;
        }

        public Criteria andFldN2LessThanOrEqualTo(Integer value) {
            addCriterion("fld_n2 <=", value, "fldN2");
            return (Criteria) this;
        }

        public Criteria andFldN2In(List<Integer> values) {
            addCriterion("fld_n2 in", values, "fldN2");
            return (Criteria) this;
        }

        public Criteria andFldN2NotIn(List<Integer> values) {
            addCriterion("fld_n2 not in", values, "fldN2");
            return (Criteria) this;
        }

        public Criteria andFldN2Between(Integer value1, Integer value2) {
            addCriterion("fld_n2 between", value1, value2, "fldN2");
            return (Criteria) this;
        }

        public Criteria andFldN2NotBetween(Integer value1, Integer value2) {
            addCriterion("fld_n2 not between", value1, value2, "fldN2");
            return (Criteria) this;
        }

        public Criteria andFldN3IsNull() {
            addCriterion("fld_n3 is null");
            return (Criteria) this;
        }

        public Criteria andFldN3IsNotNull() {
            addCriterion("fld_n3 is not null");
            return (Criteria) this;
        }

        public Criteria andFldN3EqualTo(Byte value) {
            addCriterion("fld_n3 =", value, "fldN3");
            return (Criteria) this;
        }

        public Criteria andFldN3NotEqualTo(Byte value) {
            addCriterion("fld_n3 <>", value, "fldN3");
            return (Criteria) this;
        }

        public Criteria andFldN3GreaterThan(Byte value) {
            addCriterion("fld_n3 >", value, "fldN3");
            return (Criteria) this;
        }

        public Criteria andFldN3GreaterThanOrEqualTo(Byte value) {
            addCriterion("fld_n3 >=", value, "fldN3");
            return (Criteria) this;
        }

        public Criteria andFldN3LessThan(Byte value) {
            addCriterion("fld_n3 <", value, "fldN3");
            return (Criteria) this;
        }

        public Criteria andFldN3LessThanOrEqualTo(Byte value) {
            addCriterion("fld_n3 <=", value, "fldN3");
            return (Criteria) this;
        }

        public Criteria andFldN3In(List<Byte> values) {
            addCriterion("fld_n3 in", values, "fldN3");
            return (Criteria) this;
        }

        public Criteria andFldN3NotIn(List<Byte> values) {
            addCriterion("fld_n3 not in", values, "fldN3");
            return (Criteria) this;
        }

        public Criteria andFldN3Between(Byte value1, Byte value2) {
            addCriterion("fld_n3 between", value1, value2, "fldN3");
            return (Criteria) this;
        }

        public Criteria andFldN3NotBetween(Byte value1, Byte value2) {
            addCriterion("fld_n3 not between", value1, value2, "fldN3");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}