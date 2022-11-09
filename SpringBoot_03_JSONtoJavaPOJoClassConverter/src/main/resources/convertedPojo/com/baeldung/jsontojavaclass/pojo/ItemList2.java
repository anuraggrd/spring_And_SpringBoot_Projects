
package com.baeldung.jsontojavaclass.pojo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "item",
    "amount",
    "name",
    "regularPrice",
    "itemState",
    "couponApplicable"
})
public class ItemList2 {

    @JsonProperty("item")
    private Integer item;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("name")
    private String name;
    @JsonProperty("regularPrice")
    private Double regularPrice;
    @JsonProperty("itemState")
    private String itemState;
    @JsonProperty("couponApplicable")
    private Boolean couponApplicable;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("item")
    public Integer getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(Integer item) {
        this.item = item;
    }

    public ItemList2 withItem(Integer item) {
        this.item = item;
        return this;
    }

    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ItemList2 withAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public ItemList2 withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("regularPrice")
    public Double getRegularPrice() {
        return regularPrice;
    }

    @JsonProperty("regularPrice")
    public void setRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public ItemList2 withRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
        return this;
    }

    @JsonProperty("itemState")
    public String getItemState() {
        return itemState;
    }

    @JsonProperty("itemState")
    public void setItemState(String itemState) {
        this.itemState = itemState;
    }

    public ItemList2 withItemState(String itemState) {
        this.itemState = itemState;
        return this;
    }

    @JsonProperty("couponApplicable")
    public Boolean getCouponApplicable() {
        return couponApplicable;
    }

    @JsonProperty("couponApplicable")
    public void setCouponApplicable(Boolean couponApplicable) {
        this.couponApplicable = couponApplicable;
    }

    public ItemList2 withCouponApplicable(Boolean couponApplicable) {
        this.couponApplicable = couponApplicable;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public ItemList2 withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(item).append(amount).append(name).append(regularPrice).append(itemState).append(couponApplicable).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ItemList2) == false) {
            return false;
        }
        ItemList2 rhs = ((ItemList2) other);
        return new EqualsBuilder().append(item, rhs.item).append(amount, rhs.amount).append(name, rhs.name).append(regularPrice, rhs.regularPrice).append(itemState, rhs.itemState).append(couponApplicable, rhs.couponApplicable).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
