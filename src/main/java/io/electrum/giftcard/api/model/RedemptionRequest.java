package io.electrum.giftcard.api.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.electrum.vas.model.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information about the redemption being made on the gift card.
 */
@ApiModel(description = "Information about the redemption being made on the gift card.")
public class RedemptionRequest extends Transaction implements IGiftCardExtTransaction{

   private GiftcardAmounts amounts = null;
   private Card card = null;
   private PosInfo posInfo = null;
   private Product product = null;
   private Basket basket = null;
   private PointAmounts points = null;

   public RedemptionRequest amounts(GiftcardAmounts amounts) {
      this.amounts = amounts;
      return this;
   }

   /**
    * Specifies the amount which the gift card is being redeemed against.
    * 
    * @return amounts
    **/
   @ApiModelProperty(required = true, value = "Specifies the amount which the gift card is being redeemed against.")
   @JsonProperty("amounts")
   @NotNull
   public GiftcardAmounts getAmounts() {
      return amounts;
   }

   public void setAmounts(GiftcardAmounts amounts) {
      this.amounts = amounts;
   }

   public RedemptionRequest card(Card card) {
      this.card = card;
      return this;
   }

   /**
    * Information about the gift card being used for redemption.
    * 
    * @return card
    **/
   @ApiModelProperty(required = true, value = "Information about the gift card being used for redemption.")
   @JsonProperty("card")
   @NotNull
   public Card getCard() {
      return card;
   }

   public void setCard(Card card) {
      this.card = card;
   }

   public RedemptionRequest posInfo(PosInfo posInfo) {
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

   public RedemptionRequest product(Product product) {
      this.product = product;
      return this;
   }

   /**
    * Information about the gift card product being used for redemption.
    * 
    * @return product
    **/
   @ApiModelProperty(value = "Information about the gift card product being used for redemption.")
   @JsonProperty("product")
   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public RedemptionRequest basket(Basket basket) {
      this.basket = basket;
      return this;
   }

   /**
    * The collection of products for which the gift card is being redeemed.
    * 
    * @return basket
    **/
   @ApiModelProperty(value = "The collection of products for which the gift card is being redeemed.")
   @JsonProperty("basket")
   public Basket getBasket() {
      return basket;
   }

   public void setBasket(Basket basket) {
      this.basket = basket;
   }

   public RedemptionRequest points(PointAmounts points) {
      this.points = points;
      return this;
   }

   /**
    * Specifies the points amount which the gift card is being redeemed against.
    *
    * @return points
    **/
   @ApiModelProperty(value = "Specifies the points amount which the gift card is being redeemed against.")
   @JsonProperty("points")
   @Valid
   public PointAmounts getPoints() {
      return points;
   }

   public void setPoints(PointAmounts points) {
      this.points = points;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class RedemptionRequest {\n");
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
      sb.append("    basket: ").append(Utils.toIndentedString(basket)).append("\n");
      sb.append("    tranType: ").append(Utils.toIndentedString(tranType)).append("\n");
      sb.append("    srcAccType: ").append(Utils.toIndentedString(srcAccType)).append("\n");
      sb.append("    destAccType: ").append(Utils.toIndentedString(destAccType)).append("\n");
      sb.append("    points: ").append(Utils.toIndentedString(points)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
