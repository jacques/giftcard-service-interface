package io.electrum.giftcard.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.electrum.vas.Utils;
import io.electrum.vas.model.LedgerAmount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Points related information. This is the description of the points involved in a transaction.
 */
@ApiModel(description = "Points related information. This is the description of the points involved in a transaction.")
public class PointsAmount {

   private Long amount = null;
   private LedgerAmount currencyEquivalent = null;
   private Long pointsDueToExpire = null;
   private DateTime expiryDate = null;

   public PointsAmount amount(Long amount) {
      this.amount = amount;
      return this;
   }

   /**
    * The number of points.
    *
    * @return amount
    **/
   @ApiModelProperty(required = true, value = "The number of points.")
   @JsonProperty("amount")
   @NotNull
   public Long getAmount() {
      return amount;
   }

   public void setAmount(Long amount) {
      this.amount = amount;
   }

   public PointsAmount currencyEquivalent(LedgerAmount currencyEquivalent) {
      this.currencyEquivalent = currencyEquivalent;
      return this;
   }

   /**
    * The value of the points amount in a given currency.
    *
    * @return currencyEquivalent
    **/
   @ApiModelProperty(value = "The value of the points amount in a given currency.")
   @JsonProperty("currencyEquivalent")
   @Valid
   public LedgerAmount getCurrencyEquivalent() {
      return currencyEquivalent;
   }

   public void setCurrencyEquivalent(LedgerAmount currencyEquivalent) {
      this.currencyEquivalent = currencyEquivalent;
   }

   public PointsAmount pointsDueToExpire(Long pointsDueToExpire) {
      this.pointsDueToExpire = pointsDueToExpire;
      return this;
   }

   /**
    * Number of points due for expiry next.
    *
    * @return pointsDueToExpire
    **/
   @ApiModelProperty(value = "Number of points due for expiry next.")
   @JsonProperty("pointsDueToExpire")
   public Long getPointsDueToExpire() {
      return pointsDueToExpire;
   }

   public void setPointsDueToExpire(Long pointsDueToExpire) {
      this.pointsDueToExpire = pointsDueToExpire;
   }

   public PointsAmount expiryDate(DateTime expiryDate) {
      this.expiryDate = expiryDate;
      return this;
   }

   /**
    * The date at which the next expiry of points will occur.
    *
    * @return expiryDate
    **/
   @ApiModelProperty(value = "The date at which the next expiry of points will occur.")
   @JsonProperty("expiryDate")
   @Valid
   public DateTime getExpiryDate() {
      return expiryDate;
   }

   public void setExpiryDate(DateTime expiryDate) {
      this.expiryDate = expiryDate;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof PointsAmount)) return false;
      PointsAmount that = (PointsAmount) o;
      return Objects.equals(amount, that.amount) &&
            Objects.equals(currencyEquivalent, that.currencyEquivalent) &&
            Objects.equals(pointsDueToExpire, that.pointsDueToExpire) &&
            Objects.equals(expiryDate, that.expiryDate);
   }

   @Override
   public int hashCode() {
      return Objects.hash(amount, currencyEquivalent, pointsDueToExpire, expiryDate);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class PointsAmount {\n");

      sb.append("    amount: ").append(Utils.toIndentedString(amount)).append("\n");
      sb.append("    currencyEquivalent: ").append(Utils.toIndentedString(currencyEquivalent)).append("\n");
      sb.append("    pointsDueToExpire: ").append(Utils.toIndentedString(pointsDueToExpire)).append("\n");
      sb.append("    expiryDate: ").append(Utils.toIndentedString(expiryDate)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
