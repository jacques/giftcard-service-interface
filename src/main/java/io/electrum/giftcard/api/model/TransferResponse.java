package io.electrum.giftcard.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.electrum.vas.Utils;
import io.electrum.vas.model.Transaction;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class TransferResponse extends Transaction {

   private GiftcardAmounts amounts = null;
   private Card sourceCard = null;
   private Card targetCard = null;
   private PosInfo posInfo = null;
   private Product product = null;
   private SlipData slipData = null;

   public TransferResponse amounts(GiftcardAmounts amounts) {
      this.amounts = amounts;
      return this;
   }

   /**
    * Specifies the amount which is being transferred from a source giftcard to a target giftcard.
    *
    * @return amounts
    **/
   @ApiModelProperty(required = true, value = "Specifies the amount which is being transferred from a source giftcard to a target giftcard.")
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
      sb.append("}");
      return sb.toString();
   }
}
