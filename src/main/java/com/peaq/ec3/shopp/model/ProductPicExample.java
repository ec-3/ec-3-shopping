package com.peaq.ec3.shopp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductPicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductPicExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
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

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Long value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Long value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Long value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Long value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Long value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Long> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Long> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Long value1, Long value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Long value1, Long value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andPicDescIsNull() {
            addCriterion("pic_desc is null");
            return (Criteria) this;
        }

        public Criteria andPicDescIsNotNull() {
            addCriterion("pic_desc is not null");
            return (Criteria) this;
        }

        public Criteria andPicDescEqualTo(String value) {
            addCriterion("pic_desc =", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescNotEqualTo(String value) {
            addCriterion("pic_desc <>", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescGreaterThan(String value) {
            addCriterion("pic_desc >", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescGreaterThanOrEqualTo(String value) {
            addCriterion("pic_desc >=", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescLessThan(String value) {
            addCriterion("pic_desc <", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescLessThanOrEqualTo(String value) {
            addCriterion("pic_desc <=", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescLike(String value) {
            addCriterion("pic_desc like", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescNotLike(String value) {
            addCriterion("pic_desc not like", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescIn(List<String> values) {
            addCriterion("pic_desc in", values, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescNotIn(List<String> values) {
            addCriterion("pic_desc not in", values, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescBetween(String value1, String value2) {
            addCriterion("pic_desc between", value1, value2, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescNotBetween(String value1, String value2) {
            addCriterion("pic_desc not between", value1, value2, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicUrlIsNull() {
            addCriterion("pic_url is null");
            return (Criteria) this;
        }

        public Criteria andPicUrlIsNotNull() {
            addCriterion("pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andPicUrlEqualTo(String value) {
            addCriterion("pic_url =", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotEqualTo(String value) {
            addCriterion("pic_url <>", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlGreaterThan(String value) {
            addCriterion("pic_url >", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pic_url >=", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLessThan(String value) {
            addCriterion("pic_url <", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLessThanOrEqualTo(String value) {
            addCriterion("pic_url <=", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLike(String value) {
            addCriterion("pic_url like", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotLike(String value) {
            addCriterion("pic_url not like", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlIn(List<String> values) {
            addCriterion("pic_url in", values, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotIn(List<String> values) {
            addCriterion("pic_url not in", values, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlBetween(String value1, String value2) {
            addCriterion("pic_url between", value1, value2, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotBetween(String value1, String value2) {
            addCriterion("pic_url not between", value1, value2, "picUrl");
            return (Criteria) this;
        }

        public Criteria andIsMasterIsNull() {
            addCriterion("is_master is null");
            return (Criteria) this;
        }

        public Criteria andIsMasterIsNotNull() {
            addCriterion("is_master is not null");
            return (Criteria) this;
        }

        public Criteria andIsMasterEqualTo(Byte value) {
            addCriterion("is_master =", value, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterNotEqualTo(Byte value) {
            addCriterion("is_master <>", value, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterGreaterThan(Byte value) {
            addCriterion("is_master >", value, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_master >=", value, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterLessThan(Byte value) {
            addCriterion("is_master <", value, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterLessThanOrEqualTo(Byte value) {
            addCriterion("is_master <=", value, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterIn(List<Byte> values) {
            addCriterion("is_master in", values, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterNotIn(List<Byte> values) {
            addCriterion("is_master not in", values, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterBetween(Byte value1, Byte value2) {
            addCriterion("is_master between", value1, value2, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterNotBetween(Byte value1, Byte value2) {
            addCriterion("is_master not between", value1, value2, "isMaster");
            return (Criteria) this;
        }

        public Criteria andPicOrderIsNull() {
            addCriterion("pic_order is null");
            return (Criteria) this;
        }

        public Criteria andPicOrderIsNotNull() {
            addCriterion("pic_order is not null");
            return (Criteria) this;
        }

        public Criteria andPicOrderEqualTo(Byte value) {
            addCriterion("pic_order =", value, "picOrder");
            return (Criteria) this;
        }

        public Criteria andPicOrderNotEqualTo(Byte value) {
            addCriterion("pic_order <>", value, "picOrder");
            return (Criteria) this;
        }

        public Criteria andPicOrderGreaterThan(Byte value) {
            addCriterion("pic_order >", value, "picOrder");
            return (Criteria) this;
        }

        public Criteria andPicOrderGreaterThanOrEqualTo(Byte value) {
            addCriterion("pic_order >=", value, "picOrder");
            return (Criteria) this;
        }

        public Criteria andPicOrderLessThan(Byte value) {
            addCriterion("pic_order <", value, "picOrder");
            return (Criteria) this;
        }

        public Criteria andPicOrderLessThanOrEqualTo(Byte value) {
            addCriterion("pic_order <=", value, "picOrder");
            return (Criteria) this;
        }

        public Criteria andPicOrderIn(List<Byte> values) {
            addCriterion("pic_order in", values, "picOrder");
            return (Criteria) this;
        }

        public Criteria andPicOrderNotIn(List<Byte> values) {
            addCriterion("pic_order not in", values, "picOrder");
            return (Criteria) this;
        }

        public Criteria andPicOrderBetween(Byte value1, Byte value2) {
            addCriterion("pic_order between", value1, value2, "picOrder");
            return (Criteria) this;
        }

        public Criteria andPicOrderNotBetween(Byte value1, Byte value2) {
            addCriterion("pic_order not between", value1, value2, "picOrder");
            return (Criteria) this;
        }

        public Criteria andPicStatusIsNull() {
            addCriterion("pic_status is null");
            return (Criteria) this;
        }

        public Criteria andPicStatusIsNotNull() {
            addCriterion("pic_status is not null");
            return (Criteria) this;
        }

        public Criteria andPicStatusEqualTo(Byte value) {
            addCriterion("pic_status =", value, "picStatus");
            return (Criteria) this;
        }

        public Criteria andPicStatusNotEqualTo(Byte value) {
            addCriterion("pic_status <>", value, "picStatus");
            return (Criteria) this;
        }

        public Criteria andPicStatusGreaterThan(Byte value) {
            addCriterion("pic_status >", value, "picStatus");
            return (Criteria) this;
        }

        public Criteria andPicStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("pic_status >=", value, "picStatus");
            return (Criteria) this;
        }

        public Criteria andPicStatusLessThan(Byte value) {
            addCriterion("pic_status <", value, "picStatus");
            return (Criteria) this;
        }

        public Criteria andPicStatusLessThanOrEqualTo(Byte value) {
            addCriterion("pic_status <=", value, "picStatus");
            return (Criteria) this;
        }

        public Criteria andPicStatusIn(List<Byte> values) {
            addCriterion("pic_status in", values, "picStatus");
            return (Criteria) this;
        }

        public Criteria andPicStatusNotIn(List<Byte> values) {
            addCriterion("pic_status not in", values, "picStatus");
            return (Criteria) this;
        }

        public Criteria andPicStatusBetween(Byte value1, Byte value2) {
            addCriterion("pic_status between", value1, value2, "picStatus");
            return (Criteria) this;
        }

        public Criteria andPicStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("pic_status not between", value1, value2, "picStatus");
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