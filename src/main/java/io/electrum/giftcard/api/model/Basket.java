package io.electrum.giftcard.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information about the items paid for using the gift card.
 */
@ApiModel(description = "Information about the items paid for using the gift card.")
public class Basket {

   private String basketId = null;
   private List<Item> items = new ArrayList<Item>();

   public Basket basketId(String basketId) {
      this.basketId = basketId;
      return this;
   }

   /**
    * An identifier for the collection of items being paid for.
    * 
    * @return basketId
    **/
   @ApiModelProperty(required = true, value = "An identifier for the collection of items being paid for.")
   @JsonProperty("basketId")
   @NotNull
   @Pattern(regexp = "[0-9A-Za-z]{1,20}")
   public String getBasketId() {
      return basketId;
   }

   public void setBasketId(String basketId) {
      this.basketId = basketId;
   }

   public Basket items(List<Item> items) {
      this.items = items;
      return this;
   }

   public Basket addItemsItem(Item itemsItem) {
      this.items.add(itemsItem);
      return this;
   }

   /**
    * The items being purchased.
    * 
    * @return items
    **/
   @ApiModelProperty(required = true, value = "The items being purchased.")
   @JsonProperty("items")
   public List<Item> getItems() {
      return items;
   }

   public void setItems(List<Item> items) {
      this.items = items;
   }

   @Override
   public boolean equals(java.lang.Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Basket basket = (Basket) o;
      return Objects.equals(this.basketId, basket.basketId) && Objects.equals(this.items, basket.items);
   }

   @Override
   public int hashCode() {
      return Objects.hash(basketId, items);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class Basket {\n");

      sb.append("    basketId: ").append(Utils.toIndentedString(basketId)).append("\n");
      sb.append("    items: ").append(Utils.toIndentedString(items)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
