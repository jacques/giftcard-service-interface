package io.electrum.giftcard.api.model;

import java.util.Objects;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * A unique product and quantity thereof which is part of a customer's basket. This should not be confused with the
 * Product model which describes a gift card product sold by retailers.
 */
@ApiModel(description = "A unique product and quantity thereof which is part of a customer's basket. This should not be confused with the Product model which describes a gift card product sold by retailers.")
public class Item {

   private String productId = null;
   private String productName = null;
   private String productType = null;
   private Float quantity = null;

   /**
    * The unit the quantity is measured in.
    */
   public enum UnitEnum {
      UNIT("UNIT"), LITRE("LITRE"), KILOGRAM("KILOGRAM");

      private String value;

      UnitEnum(String value) {
         this.value = value;
      }

      @Override
      public String toString() {
         return String.valueOf(value);
      }
   }

   private UnitEnum unit = null;

   public Item productId(String productId) {
      this.productId = productId;
      return this;
   }

   /**
    * An identifier for the product such as a barcode.
    * 
    * @return productId
    **/
   @ApiModelProperty(value = "An identifier for the product such as a barcode.")
   @JsonProperty("productId")
   @Pattern(regexp = "[0-9A-Za-z]{1,20}")
   public String getProductId() {
      return productId;
   }

   public void setProductId(String productId) {
      this.productId = productId;
   }

   public Item productName(String productName) {
      this.productName = productName;
      return this;
   }

   /**
    * The name of the product.
    * 
    * @return productName
    **/
   @ApiModelProperty(value = "The name of the product.")
   @JsonProperty("productName")
   @Pattern(regexp = "[0-9A-Za-z]{1,50}")
   public String getProductName() {
      return productName;
   }

   public void setProductName(String productName) {
      this.productName = productName;
   }

   public Item productType(String productType) {
      this.productType = productType;
      return this;
   }

   /**
    * The type of the product e.g. food, clothing etc.
    * 
    * @return productType
    **/
   @ApiModelProperty(value = "The type of the product e.g. food, clothing etc.")
   @JsonProperty("productType")
   @Pattern(regexp = "[0-9A-Za-z]{1,30}")
   public String getProductType() {
      return productType;
   }

   public void setProductType(String productType) {
      this.productType = productType;
   }

   public Item quantity(Float quantity) {
      this.quantity = quantity;
      return this;
   }

   /**
    * The number of units of product bought. This could be whole units if suitable for the product (e.g. tins of beans)
    * or a fractional value in the case of a volume or mass value.
    * 
    * @return quantity
    **/
   @ApiModelProperty(value = "The number of units of product bought. This could be whole units if suitable for the product (e.g. tins of beans) or a fractional value in the case of a volume or mass value.")
   @JsonProperty("quantity")
   public Float getQuantity() {
      return quantity;
   }

   public void setQuantity(Float quantity) {
      this.quantity = quantity;
   }

   public Item unit(UnitEnum unit) {
      this.unit = unit;
      return this;
   }

   /**
    * The unit the quantity is measured in.
    * 
    * @return unit
    **/
   @ApiModelProperty(value = "The unit the quantity is measured in.")
   @JsonProperty("unit")
   public UnitEnum getUnit() {
      return unit;
   }

   public void setUnit(UnitEnum unit) {
      this.unit = unit;
   }

   @Override
   public boolean equals(java.lang.Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Item item = (Item) o;
      return Objects.equals(this.productId, item.productId) && Objects.equals(this.productName, item.productName)
            && Objects.equals(this.productType, item.productType) && Objects.equals(this.quantity, item.quantity)
            && Objects.equals(this.unit, item.unit);
   }

   @Override
   public int hashCode() {
      return Objects.hash(productId, productName, productType, quantity, unit);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class Item {\n");

      sb.append("    productId: ").append(Utils.toIndentedString(productId)).append("\n");
      sb.append("    productName: ").append(Utils.toIndentedString(productName)).append("\n");
      sb.append("    productType: ").append(Utils.toIndentedString(productType)).append("\n");
      sb.append("    quantity: ").append(Utils.toIndentedString(quantity)).append("\n");
      sb.append("    unit: ").append(Utils.toIndentedString(unit)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
