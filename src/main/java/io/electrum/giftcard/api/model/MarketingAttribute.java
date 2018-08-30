package io.electrum.giftcard.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Relevant marketing information related to the consumer
 */
@ApiModel(description = "Relevant marketing information related to the consumer.")
public class MarketingAttribute {
   protected String name = null;
   protected String description = null;
   protected Boolean member = null;
   protected Boolean marketToCustomer = null;

   public MarketingAttribute name(String name) {
      this.name = name;
      return this;
   }

   /**
    * The name of this marketing attribute.
    *
    * @return name
    **/
   @ApiModelProperty(required = true,value = "The name of this marketing attribute.")
   @JsonProperty("name")
   @NotNull
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public MarketingAttribute description(String description) {
      this.description = description;
      return this;
   }

   /**
    * The description of this marketing attribute.
    *
    * @return description
    **/
   @ApiModelProperty(value = "The description of this marketing attribute.")
   @JsonProperty("description")
   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public MarketingAttribute member(Boolean member) {
      this.member = member;
      return this;
   }

   /**
    * If the customer associated with this card is a member of this marketing attribute.
    *
    * @return member
    **/
   @ApiModelProperty(value = "If the customer associated with this card is a member of this marketing attribute.")
   @JsonProperty("member")
   public Boolean getMember() {
      return member;
   }

   public void setMember(Boolean member) {
      this.member = member;
   }

   public MarketingAttribute marketToCustomer(Boolean marketToCustomer) {
      this.marketToCustomer = marketToCustomer;
      return this;
   }

   /**
    * Should the customer associated with card be subjected to marketing for this specific marketing attribute.
    *
    * @return marketToCustomer
    **/
   @ApiModelProperty(value = "Should the customer associated with card be subjected to marketing for this specific marketing attribute.")
   @JsonProperty("marketToCustomer")
   public Boolean getMarketToCustomer() {
      return marketToCustomer;
   }

   public void setMarketToCustomer(Boolean marketToCustomer) {
      this.marketToCustomer = marketToCustomer;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof MarketingAttribute)) return false;
      MarketingAttribute club = (MarketingAttribute) o;
      return Objects.equals(name, club.name) &&
            Objects.equals(description, club.description) &&
            Objects.equals(member, club.member) &&
            Objects.equals(marketToCustomer, club.marketToCustomer);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name, description, member, marketToCustomer);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class MarketingAttribute {\n");

      sb.append("    name: ").append(Utils.toIndentedString(name)).append("\n");
      sb.append("    description: ").append(Utils.toIndentedString(description)).append("\n");
      sb.append("    member: ").append(Utils.toIndentedString(member)).append("\n");
      sb.append("    marketToCustomer: ").append(Utils.toIndentedString(marketToCustomer)).append("\n");
      sb.append("}");
      return sb.toString();
   }

}
