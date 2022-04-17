package io.electrum.giftcard.api.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Product related data. This is the description of the gift card sold as a product at a retailer. This should not be
 * confused with the products which form Items in a Basket.
 */
@ApiModel(description = "Product related data. This is the description of the gift card sold as a product at a retailer. This should not be confused with the products which form Items in a Basket.")
public class Product {

   private String id = null;
   private String barcode = null;
   private String type = null;

   public Product id(String id) {
      this.id = id;
      return this;
   }

   /**
    * An identifier of the product which the gift card pertains to. This code is sent by the originator and allows the
    * receiver to identify the gift card product.
    * 
    * @return id
    **/
   @ApiModelProperty(required = true, value = "An identifier of the product which the gift card pertains to. This code is sent by the originator and allows the receiver to identify the gift card product")
   @JsonProperty("id")
   @NotNull
   @Pattern(regexp = "[0-9A-Za-z]{1,100}")
   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public Product barcode(String barcode) {
      this.barcode = barcode;
      return this;
   }

   /**
    * A barcode code identifying the product the gift card pertains to.
    * 
    * @return barcode
    **/
   @ApiModelProperty(value = "A barcode code identifying the product the gift card pertains to.")
   @JsonProperty("barcode")
   @Pattern(regexp = "[0-9A-Za-z]{1,13}")
   public String getBarcode() {
      return barcode;
   }

   public void setBarcode(String barcode) {
      this.barcode = barcode;
   }

   public Product type(String type) {
      this.type = type;
      return this;
   }

   /**
    * An indication of the type of product offered through the gift card.
    * 
    * @return type
    **/
   @ApiModelProperty(value = "An indication of the type of product offered through the gift card.")
   @JsonProperty("type")
   @Pattern(regexp = ".{1,50}")
   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   @Override
   public boolean equals(java.lang.Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Product product = (Product) o;
      return Objects.equals(this.id, product.id) && Objects.equals(this.barcode, product.barcode)
            && Objects.equals(this.type, product.type);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, barcode, type);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class Product {\n");

      sb.append("    id: ").append(Utils.toIndentedString(id)).append("\n");
      sb.append("    barcode: ").append(Utils.toIndentedString(barcode)).append("\n");
      sb.append("    type: ").append(Utils.toIndentedString(type)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
