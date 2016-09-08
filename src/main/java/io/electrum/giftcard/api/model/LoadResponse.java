package io.electrum.giftcard.api.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.electrum.vas.model.Amounts;
import io.electrum.vas.model.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information about the load made on the gift card.
 */
@ApiModel(description = "Information about the load made on the gift card.")
public class LoadResponse extends Transaction {

   private Amounts amounts = null;
   private Card card = null;
   private PosInfo posInfo = null;
   private Product product = null;
   private SlipData slipData = null;

   public LoadResponse amounts(Amounts amounts) {
      this.amounts = amounts;
      return this;
   }

   /**
    * Indicates the amount which was requested to be loaded onto the card, the amount which was loaded and the balance
    * of the gift card after the load.
    * 
    * @return amounts
    **/
   @ApiModelProperty(required = true, value = "Indicates the amount which was requested to be loaded onto the card, the amount which was loaded and the balance of the gift card after the load.")
   @JsonProperty("amounts")
   @NotNull
   public Amounts getAmounts() {
      return amounts;
   }

   public void setAmounts(Amounts amounts) {
      this.amounts = amounts;
   }

   /**
    * Information about the gift card being loaded.
    * 
    * @return card
    **/
   @ApiModelProperty(required = true, value = "Information about the gift card being loaded.")
   @JsonProperty("card")
   @NotNull
   public Card getCard() {
      return card;
   }

   public void setCard(Card card) {
      this.card = card;
   }

   public LoadResponse posInfo(PosInfo posInfo) {
      this.posInfo = posInfo;
      return this;
   }

   /**
    * Information about the POS.
    * 
    * @return posInfo
    **/
   @ApiModelProperty(value = "Information about the POS.")
   @JsonProperty("posInfo")
   public PosInfo getPosInfo() {
      return posInfo;
   }

   public void setPosInfo(PosInfo posInfo) {
      this.posInfo = posInfo;
   }

   public LoadResponse product(Product product) {
      this.product = product;
      return this;
   }

   /**
    * Information about the gift card product being loaded.
    * 
    * @return product
    **/
   @ApiModelProperty(value = "Information about the gift card product being loaded.")
   @JsonProperty("product")
   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public LoadResponse slipData(SlipData slipData) {
      this.slipData = slipData;
      return this;
   }

   /**
    * Text to be printed on the customer receipt.
    * 
    * @return slipData
    **/
   @ApiModelProperty(value = "Text to be printed on the customer receipt.")
   @JsonProperty("slipData")
   public SlipData getSlipData() {
      return slipData;
   }

   public void setSlipData(SlipData slipData) {
      this.slipData = slipData;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class LoadResponse {\n");

      sb.append("    id: ").append(Utils.toIndentedString(id)).append("\n");
      sb.append("    time: ").append(Utils.toIndentedString(time)).append("\n");
      sb.append("    originator: ").append(Utils.toIndentedString(originator)).append("\n");
      sb.append("    client: ").append(Utils.toIndentedString(client)).append("\n");
      sb.append("    settlementEntity: ").append(Utils.toIndentedString(settlementEntity)).append("\n");
      sb.append("    receiver: ").append(Utils.toIndentedString(receiver)).append("\n");
      sb.append("    thirdPartyIdentifiers: ").append(Utils.toIndentedString(thirdPartyIdentifiers)).append("\n");
      sb.append("    amounts: ").append(Utils.toIndentedString(amounts)).append("\n");
      sb.append("    card: ").append(Utils.toIndentedString(card)).append("\n");
      sb.append("    posInfo: ").append(Utils.toIndentedString(posInfo)).append("\n");
      sb.append("    product: ").append(Utils.toIndentedString(product)).append("\n");
      sb.append("    slipData: ").append(Utils.toIndentedString(slipData)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}