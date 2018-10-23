package io.electrum.giftcard.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.electrum.vas.Utils;
import io.electrum.vas.model.Customer;
import io.electrum.vas.model.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Information about the transfer made from a source gift card to a target gift card.
 */
@ApiModel(description = "Information about the transfer made from a source gift card to a target gift card.")
public class TransferResponse extends Transaction implements IGiftCardTargetTransaction {

   private GiftcardAmounts amounts = null;
   private Card sourceCard = null;
   private Card targetCard = null;
   private PosInfo posInfo = null;
   private Product product = null;
   private Customer cardHolder = null;

   public TransferResponse amounts(GiftcardAmounts amounts) {
      this.amounts = amounts;
      return this;
   }

   /**
    * Indicates the amount which was being transferred from a source gift card to a target gift card, the amount which
    * was transferred and the balance of the source gift card after the transfer
    *
    * @return amounts
    **/
   @ApiModelProperty(required = true, value = "Indicates the amount which was being transferred from a source gift card to a target gift card, the amount which was transferred and the balance of the source gift card after the transfer")
   @JsonProperty("amounts")
   @NotNull
   public GiftcardAmounts getAmounts() {
      return amounts;
   }

   public void setAmounts(GiftcardAmounts amounts) {
      this.amounts = amounts;
   }

   /**
    * Information about the gift card that funds are being transferred from.
    *
    * @return sourceCard
    **/
   @ApiModelProperty(required = true, value = "Information about the gift card that funds are being transferred from.")
   @JsonProperty("sourceCard")
   @NotNull
   public Card getSourceCard() {
      return sourceCard;
   }

   public void setSourceCard(Card sourceCard) {
      this.sourceCard = sourceCard;
   }

   /**
    * Information about the gift card that funds are being transferred to.
    *
    * @return targetCard
    **/
   @ApiModelProperty(required = true, value = "Information about the gift card that funds are being transferred to.")
   @JsonProperty("targetCard")
   @NotNull
   public Card getTargetCard() {
      return targetCard;
   }

   public void setTargetCard(Card targetCard) {
      this.targetCard = targetCard;
   }

   public TransferResponse posInfo(PosInfo posInfo) {
      this.posInfo = posInfo;
      return this;
   }

   @Override
   public Card getCard() {
      return getSourceCard();
   }

   @Override
   public void setCard(Card card) {
      setSourceCard(card);
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

   public TransferResponse product(Product product) {
      this.product = product;
      return this;
   }

   /**
    * Information about the gift card product being used for the giftcard transfer.
    *
    * @return product
    **/
   @ApiModelProperty(value = "Information about the gift card product being used for the transfer.")
   @JsonProperty("product")
   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public TransferResponse cardHolder(Customer cardHolder) {
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
      sb.append("class TransferResponse {\n");
      sb.append("    id: ").append(Utils.toIndentedString(id)).append("\n");
      sb.append("    time: ").append(Utils.toIndentedString(time)).append("\n");
      sb.append("    originator: ").append(Utils.toIndentedString(originator)).append("\n");
      sb.append("    client: ").append(Utils.toIndentedString(client)).append("\n");
      sb.append("    settlementEntity: ").append(Utils.toIndentedString(settlementEntity)).append("\n");
      sb.append("    receiver: ").append(Utils.toIndentedString(receiver)).append("\n");
      sb.append("    thirdPartyIdentifiers: ").append(Utils.toIndentedString(thirdPartyIdentifiers)).append("\n");
      sb.append("    amounts: ").append(Utils.toIndentedString(amounts)).append("\n");
      sb.append("    sourceCard: ").append(Utils.toIndentedString(sourceCard)).append("\n");
      sb.append("    targetCard: ").append(Utils.toIndentedString(targetCard)).append("\n");
      sb.append("    posInfo: ").append(Utils.toIndentedString(posInfo)).append("\n");
      sb.append("    product: ").append(Utils.toIndentedString(product)).append("\n");
      sb.append("    slipData: ").append(Utils.toIndentedString(slipData)).append("\n");
      sb.append("    tranType: ").append(Utils.toIndentedString(tranType)).append("\n");
      sb.append("    srcAccType: ").append(Utils.toIndentedString(srcAccType)).append("\n");
      sb.append("    destAccType: ").append(Utils.toIndentedString(destAccType)).append("\n");
      sb.append("    customer: ").append(Utils.toIndentedString(cardHolder)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
