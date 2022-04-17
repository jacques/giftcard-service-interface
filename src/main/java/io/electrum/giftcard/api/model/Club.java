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
public class Club extends MarketingAttribute {
   private List<Product> products = new ArrayList<>();

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
      if (!super.equals(o)) return false;
      Club club = (Club) o;
      return Objects.equals(products, club.products);
   }

   @Override
   public int hashCode() {
      return Objects.hash(super.hashCode(), products);
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
