package io.electrum.giftcard.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.electrum.vas.Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Information about a club; its name, description, customer membership and products.
 */
@ApiModel(description = "Information about a club; its name, description, customer membership and products.")
public class Club {
   private String name = null;
   private String description = null;
   private Boolean member = null;
   private Boolean marketToCustomer = null;
   private List<Product> products = new ArrayList<>();

   public Club name(String name) {
      this.name = name;
      return this;
   }

   /**
    * The name of this club.
    *
    * @return name
    **/
   @ApiModelProperty(value = "The name of this club.")
   @JsonProperty("name")
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Club description(String description) {
      this.description = description;
      return this;
   }

   /**
    * The description of this club.
    *
    * @return description
    **/
   @ApiModelProperty(value = "The description of this club.")
   @JsonProperty("description")
   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Club member(Boolean member) {
      this.member = member;
      return this;
   }

   /**
    * If the customer associated with this card is a member of this club.
    *
    * @return member
    **/
   @ApiModelProperty(value = "If the customer associated with this card is a member of this club.")
   @JsonProperty("member")
   public Boolean getMember() {
      return member;
   }

   public void setMember(Boolean member) {
      this.member = member;
   }

   public Club marketToCustomer(Boolean marketToCustomer) {
      this.marketToCustomer = marketToCustomer;
      return this;
   }

   /**
    * Should the customer associated with card be subjected to marketing for this club.
    *
    * @return marketToCustomer
    **/
   @ApiModelProperty(value = "Should the customer associated with card be subjected to marketing for this club.")
   @JsonProperty("marketToCustomer")
   public Boolean getMarketToCustomer() {
      return marketToCustomer;
   }

   public void setMarketToCustomer(Boolean marketToCustomer) {
      this.marketToCustomer = marketToCustomer;
   }

   public Club products(List<Product> products) {
      this.products = products;
      return this;
   }

   /**
    * The products for which this club is associated with.
    *
    * @return products
    **/
   @ApiModelProperty(value = "The products for which this club is associated with.")
   @JsonProperty("products")
   @Valid
   public List<Product> getProducts() {
      return products;
   }

   public void setProducts(List<Product> products) {
      this.products = products;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Club)) return false;
      Club club = (Club) o;
      return Objects.equals(name, club.name) &&
            Objects.equals(description, club.description) &&
            Objects.equals(member, club.member) &&
            Objects.equals(marketToCustomer, club.marketToCustomer) &&
            Objects.equals(products, club.products);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name, description, member, marketToCustomer, products);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class Club {\n");

      sb.append("    name: ").append(Utils.toIndentedString(name)).append("\n");
      sb.append("    description: ").append(Utils.toIndentedString(description)).append("\n");
      sb.append("    member: ").append(Utils.toIndentedString(member)).append("\n");
      sb.append("    marketToCustomer: ").append(Utils.toIndentedString(marketToCustomer)).append("\n");
      sb.append("    products: ").append(Utils.toIndentedString(products)).append("\n");
      sb.append("}");
      return sb.toString();
   }

}
