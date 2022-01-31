package io.electrum.giftcard.api.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.electrum.vas.model.Customer;
import io.electrum.vas.model.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information about the void of the gift card.
 */
@ApiModel(description = "Information about the void of the gift card.")
public class VoidRequest extends Transaction implements IGiftCardTransaction{
   private Card card = null;
   private PosInfo posInfo = null;
   private Product product = null;
   private Customer cardHolder = null;

   public VoidRequest card(Card card) {
      this.card = card;
      return this;
   }

   /**
    * Information about the gift card being voided.
    * 
    * @return card
    **/
   @ApiModelProperty(required = true, value = "Information about the gift card being voided.")
   @JsonProperty("card")
   @NotNull
   public Card getCard() {
      return card;
   }

   public void setCard(Card card) {
      this.card = card;
   }

   public VoidRequest posInfo(PosInfo posInfo) {
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

   public VoidRequest product(Product product) {
      this.product = product;
      return this;
   }

   /**
    * Information about the gift card product being voided.
    * 
    * @return product
    **/
   @ApiModelProperty(value = "Information about the gift card product being voided.")
   @JsonProperty("product")
   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public VoidRequest cardHolder(Customer cardHolder) {
      this.cardHolder = cardHolder;
      return this;
   }

   /**
    * Information about the card holder.
    * 
    * @return cardHolder
    **/
   @ApiModelProperty(value = "Information about the card holder.")
   @JsonProperty("cardHolder")
   public Customer getCardHolder() {
      return cardHolder;
   }

   public void setCardHolder(Customer cardHolder) {
      this.cardHolder = cardHolder;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class VoidRequest {\n");

      sb.append("    id: ").append(Utils.toIndentedString(id)).append("\n");
      sb.append("    time: ").append(Utils.toIndentedString(time)).append("\n");
      sb.append("    originator: ").append(Utils.toIndentedString(originator)).append("\n");
      sb.append("    client: ").append(Utils.toIndentedString(client)).append("\n");
      sb.append("    settlementEntity: ").append(Utils.toIndentedString(settlementEntity)).append("\n");
      sb.append("    receiver: ").append(Utils.toIndentedString(receiver)).append("\n");
      sb.append("    thirdPartyIdentifiers: ").append(Utils.toIndentedString(thirdPartyIdentifiers)).append("\n");
      sb.append("    card: ").append(Utils.toIndentedString(card)).append("\n");
      sb.append("    posInfo: ").append(Utils.toIndentedString(posInfo)).append("\n");
      sb.append("    product: ").append(Utils.toIndentedString(product)).append("\n");
      sb.append("    customer: ").append(Utils.toIndentedString(cardHolder)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
