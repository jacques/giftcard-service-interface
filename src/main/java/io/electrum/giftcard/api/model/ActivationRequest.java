package io.electrum.giftcard.api.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.electrum.vas.model.Customer;
import io.electrum.vas.model.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Information about the gift card activation.
 */
@ApiModel(description = "Information about the gift card activation.")
public class ActivationRequest extends Transaction implements IGiftCardExtTransaction {

   private GiftcardAmounts amounts = null;
   private Card card = null;
   private PosInfo posInfo = null;
   private Product product = null;
   private Customer cardHolder = null;

   public ActivationRequest amounts(GiftcardAmounts amounts) {
      this.amounts = amounts;
      return this;
   }

   /**
    * Information about the card holder of the gift card.
    */
   @ApiModelProperty(value = "Information about the card holder of the gift card.")
   @JsonProperty("cardHolder")
   public Customer getCardHolder() {
      return cardHolder;
   }

   public void setCardHolder(Customer cardHolder) {
      this.cardHolder = cardHolder;
   }

   public ActivationRequest cardHolder(Customer cardHolder) {
      this.cardHolder = cardHolder;
      return this;
   }

   /**
    * Specifies an amount which should be loaded onto the card as part of the activation.
    * 
    * @return amounts
    **/
   @ApiModelProperty(value = "Specifies an amount which should be loaded onto the card as part of the activation.")
   @JsonProperty("amounts")
   public GiftcardAmounts getAmounts() {
      return amounts;
   }

   public void setAmounts(GiftcardAmounts amounts) {
      this.amounts = amounts;
   }

   public ActivationRequest card(Card card) {
      this.card = card;
      return this;
   }

   /**
    * Information about the gift card being activated.
    * 
    * @return card
    **/
   @ApiModelProperty(required = true, value = "Information about the gift card being activated.")
   @JsonProperty("card")
   @NotNull
   public Card getCard() {
      return card;
   }

   public void setCard(Card card) {
      this.card = card;
   }

   public ActivationRequest posInfo(PosInfo posInfo) {
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

   public ActivationRequest product(Product product) {
      this.product = product;
      return this;
   }

   /**
    * Information about the gift card product being activated.
    * 
    * @return product
    **/
   @ApiModelProperty(required = true, value = "Information about the gift card product being activated.")
   @JsonProperty("product")
   @NotNull
   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (!(o instanceof ActivationRequest))
         return false;
      if (!super.equals(o))
         return false;
      ActivationRequest that = (ActivationRequest) o;
      return Objects.equals(amounts, that.amounts) && Objects.equals(card, that.card)
            && Objects.equals(posInfo, that.posInfo) && Objects.equals(product, that.product)
            && Objects.equals(cardHolder, that.cardHolder);
   }

   @Override
   public int hashCode() {
      return Objects.hash(super.hashCode(), amounts, card, posInfo, product, cardHolder);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class ActivationRequest {\n");

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
      sb.append("    cardHolder: ").append(Utils.toIndentedString(cardHolder)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
