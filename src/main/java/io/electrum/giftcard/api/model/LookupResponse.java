package io.electrum.giftcard.api.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.electrum.vas.model.Customer;
import io.electrum.vas.model.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Information about the result of processing the gift card lookup request.
 */
@ApiModel(description = "Information about the result of processing the gift card lookup request.")
public class LookupResponse extends Transaction implements IGiftCardExtTransaction{

   private GiftcardAmounts amounts = null;
   private Card card = null;
   private PosInfo posInfo = null;
   private Product product = null;
   private Customer cardHolder = null;
   private PointAmounts points = null;
   private List<Club> clubs = new ArrayList<>();
   private List<MarketingAttribute> marketingAttributes = new ArrayList<>();

   public LookupResponse amounts(GiftcardAmounts amounts) {
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

   public LookupResponse cardHolder(Customer cardHolder) {
      this.cardHolder = cardHolder;
      return this;
   }

   /**
    * Indicates the balance of the gift card at the time of the request.
    *
    * @return amounts
    **/
   @ApiModelProperty(required = true, value = "Indicates the balance of the gift card at the time of the request.")
   @JsonProperty("amounts")
   @NotNull
   public GiftcardAmounts getAmounts() {
      return amounts;
   }

   public void setAmounts(GiftcardAmounts amounts) {
      this.amounts = amounts;
   }

   /**
    * Information about the gift card for which information is being requested.
    *
    * @return card
    **/
   @ApiModelProperty(required = true, value = "Information about the gift card for which information is being requested.")
   @JsonProperty("card")
   @NotNull
   public Card getCard() {
      return card;
   }

   public void setCard(Card card) {
      this.card = card;
   }

   public LookupResponse card(Card card) {
      this.card = card;
      return this;
   }

   public LookupResponse posInfo(PosInfo posInfo) {
      this.posInfo = posInfo;
      return this;
   }

   /**
    * Information about how card details were captured at the POS.
    *
    * @return posInfo
    **/
   @ApiModelProperty(required = true, value = "Information about how card details were captured at the POS.")
   @JsonProperty("posInfo")
   @NotNull
   public PosInfo getPosInfo() {
      return posInfo;
   }

   public void setPosInfo(PosInfo posInfo) {
      this.posInfo = posInfo;
   }

   public LookupResponse product(Product product) {
      this.product = product;
      return this;
   }

   /**
    * Information about the product associated with the gift card if the gift card has been activated.
    *
    * @return product
    **/
   @ApiModelProperty(value = "Information about the product associated with the gift card if the gift card has been activated.")
   @JsonProperty("product")
   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public LookupResponse points(PointAmounts points) {
      this.points = points;
      return this;
   }

   /**
    * Information on points associated with this card. For example: balance, currency value.
    *
    * @return points
    **/
   @ApiModelProperty(value = "Information on points associated with this card. For example: balance, currency value.")
   @JsonProperty("points")
   @Valid
   public PointAmounts getPoints() {
      return points;
   }

   public void setPoints(PointAmounts points) {
      this.points = points;
   }

   public LookupResponse clubs(List<Club> clubs) {
      this.clubs = clubs;
      return this;
   }

   /**
    * The clubs associated with this card.
    *
    * @return clubs
    **/
   @ApiModelProperty(value = "The clubs associated with this card.")
   @JsonProperty("clubs")
   @Valid
   public List<Club> getClubs() {
      return clubs;
   }

   public void setClubs(List<Club> clubs) {
      this.clubs = clubs;
   }

   public LookupResponse marketingAttributes(List<MarketingAttribute> marketingAttributes) {
      this.marketingAttributes = marketingAttributes;
      return this;
   }

   /**
    * The marketing attributes associated with this card.
    *
    * @return list of marketing attributes
    **/
   @ApiModelProperty(value = "The marketing attributes associated with this card.")
   @JsonProperty("marketingAttributes")
   @Valid
   public List<MarketingAttribute> getMarketingAttributes() {
      return marketingAttributes;
   }

   public void setMarketingAttributes(List<MarketingAttribute> marketingAttributes) {
      this.marketingAttributes = marketingAttributes;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class LookupResponse {\n");

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
      sb.append("    cardHolder: ").append(Utils.toIndentedString(cardHolder)).append("\n");
      sb.append("    points: ").append(Utils.toIndentedString(points)).append("\n");
      sb.append("    clubs: ").append(Utils.toIndentedString(clubs)).append("\n");
      sb.append("    marketingAttributes: ").append(Utils.toIndentedString(marketingAttributes)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
