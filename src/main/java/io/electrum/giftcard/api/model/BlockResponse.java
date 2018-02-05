package io.electrum.giftcard.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.electrum.vas.Utils;
import io.electrum.vas.model.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;


/**
 * Information about the block request made on the gift card.
 */
@ApiModel(description = "Information about the block made on the gift card.")
public class BlockResponse extends Transaction {

   private Card card = null;
   private PosInfo posInfo = null;
   private Product product = null;
   private BlockRequest.BlockReason blockReason = null;
   private SlipData slipData = null;

   /**
    * Information about the gift card being blocked.
    *
    * @return card
    **/
   @ApiModelProperty(required = true, value = "Information about the gift card being blocked.")
   @JsonProperty("card")
   @NotNull
   public Card getCard() {
      return card;
   }

   public void setCard(Card card) {
      this.card = card;
   }

   public BlockResponse posInfo(PosInfo posInfo) {
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

   public BlockResponse product(Product product) {
      this.product = product;
      return this;
   }

   /**
    * Information about the gift card product being blocked.
    *
    * @return product
    **/
   @ApiModelProperty(value = "Information about the gift card product being blocked.")
   @JsonProperty("product")
   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public BlockResponse blockReason(BlockRequest.BlockReason blockReason) {
      this.blockReason = blockReason;
      return this;
   }

   /**
    * The reason stated for blocking the card.
    *
    * @return blockReason
    **/
   @ApiModelProperty(value = "The reason stated for blocking the card.")
   @JsonProperty("blockReason")
   public BlockRequest.BlockReason getBlockReason() {
      return blockReason;
   }

   public void setBlockReason(BlockRequest.BlockReason blockReason) {
      this.blockReason = blockReason;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class RedemptionResponse {\n");
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
      sb.append("    slipData: ").append(Utils.toIndentedString(slipData)).append("\n");
      sb.append("    tranType: ").append(Utils.toIndentedString(tranType)).append("\n");
      sb.append("    srcAccType: ").append(Utils.toIndentedString(srcAccType)).append("\n");
      sb.append("    destAccType: ").append(Utils.toIndentedString(destAccType)).append("\n");
      sb.append("    blockReason: ").append(Utils.toIndentedString(blockReason)).append("\n");
      sb.append("}");
      return sb.toString();
   }

}
