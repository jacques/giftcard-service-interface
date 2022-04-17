package io.electrum.giftcard.api.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.sdk.masking2.DoNotPersist;
import io.electrum.sdk.masking2.MaskAll;
import io.electrum.sdk.masking2.MaskPan;
import io.electrum.sdk.masking2.Masked;
import io.electrum.vas.Utils;
import io.electrum.vas.model.Pin;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information about a gift card; its number, rank, expiry date and optionally the PIN either unencrypted or encrypted.
 */
@ApiModel(description = "Information about a gift card; its number, rank, expiry date and optionally the PIN either unencrypted or encrypted.")
public class Card {

   private String pan = null;
   private String expiryDate = null;
   /**
    * clearPin
    * 
    * @deprecated As of version 3.12.0, due to the addition of the {@link Pin} model, use {@link Card#pin Pin} with type
    *             CLEAR_PIN instead
    */
   @Deprecated
   private String clearPin = null;
   /**
    * encryptedPin
    * 
    * @deprecated As of version 3.12.0, due to the addition of the {@link Pin} model, use {@link Card#pin Pin} with type
    *             CLEAR_PIN instead
    */
   @Deprecated
   private String encryptedPin = null;
   private String rank;
   private Pin pin = null;

   public Card pin(Pin pin) {
      this.pin = pin;
      return this;
   }

   @ApiModelProperty(value = "Pin that can either be of type clear PIN or encryted PIN. This value takes precendence over"
         + "encryptedPin and clearPin fields.")
   @JsonProperty("pin")
   @Valid
   public Pin getPin() {
      return pin;
   }

   public void setPin(Pin pin) {
      this.pin = pin;
   }

   public Card pan(String pan) {
      this.pan = pan;
      return this;
   }

   /**
    * Primary account number that uniquely identifies this card.
    * 
    * @return pan
    **/
   @ApiModelProperty(required = true, value = "Primary account number that uniquely identifies this card.")
   @JsonProperty("pan")
   @NotNull
   @Pattern(regexp = "[0-9]{1,19}")
   @Masked(MaskPan.class)
   @DoNotPersist(replacementValue = "000000000000")
   public String getPan() {
      return pan;
   }

   public void setPan(String pan) {
      this.pan = pan;
   }

   public Card expiryDate(String expiryDate) {
      this.expiryDate = expiryDate;
      return this;
   }

   /**
    * The card expiry date, in YYMM format.
    * 
    * @return expiryDate
    **/
   @ApiModelProperty(required = true, value = "The card expiry date, in YYMM format.")
   @JsonProperty("expiryDate")
   @Pattern(regexp = "[0-9]{4}")
   public String getExpiryDate() {
      return expiryDate;
   }

   public void setExpiryDate(String expiryDate) {
      this.expiryDate = expiryDate;
   }

   @Deprecated
   public Card clearPin(String clearPin) {
      this.clearPin = clearPin;
      return this;
   }

   /**
    * The pin number associated with the card unencrypted. {@link Card#getPin() Pin} with type CLEAR_PIN takes
    * precedence
    * 
    * @return clearPin
    * @deprecated As of version 3.12.0, due to the addition of the {@link Pin} model, use {@link Card#getPin() Pin} with
    *             type CLEAR_PIN.
    **/
   @ApiModelProperty(value = "The pin number associated with the card unencrypted.")
   @JsonProperty("clearPin")
   @Masked
   @DoNotPersist(replacementValue = "00000")
   @Deprecated
   public String getClearPin() {
      return clearPin;
   }

   /**
    * Sets the clearPin field
    *
    * @deprecated As of version 3.12.0, due to the addition of the {@link Pin} model, use {@link Card#setPin(Pin pin)
    *             Pin} with type CLEAR_PIN.
    **/
   @Deprecated
   public void setClearPin(String clearPin) {
      this.clearPin = clearPin;
   }

   /**
    * Sets the encryptedPin field and returns the CardPayment object.
    *
    * @param encryptedPin
    * @return The Card with encryptedPin set
    * @deprecated As of version 3.12.0, due to the addition of the {@link Pin} model, use {@link Card#setPin(Pin pin)
    *             Pin} with type CLEAR_PIN.
    */
   @Deprecated
   public Card encryptedPin(String encryptedPin) {
      this.encryptedPin = encryptedPin;
      return this;
   }

   /**
    * The encrypted PIN associated with the card in HEX format.
    * 
    * @return encryptedPin
    * @deprecated As of version 3.12.0, due to the addition of the {@link Pin} model, use {@link Card#getPin() Pin} with
    *             type ENCRYPTED_PIN instead
    **/
   @ApiModelProperty(value = "The encrypted pin number associated with the card in HEX format.")
   @JsonProperty("encryptedPin")
   @Pattern(regexp = "[0-9ABCDEF]+")
   @Masked
   @DoNotPersist(replacementValue = "0000000000000000")
   @Deprecated
   public String getEncryptedPin() {
      return encryptedPin;
   }

   /**
    * Sets the encryptedPin
    *
    * @deprecated As of version 3.12.0, due to the addition of the {@link Pin} model, use {@link Card#setPin(Pin pin)
    *             Pin} with type ENCRYPTED_PIN instead
    **/
   @Deprecated
   public void setEncryptedPin(String encryptedPin) {
      this.encryptedPin = encryptedPin;
   }

   public Card rank(String rank) {
      this.rank = rank;
      return this;
   }

   /**
    * The rank of the card on the Giftcard system. For example: primary, secondary, collector.
    *
    * @return rank
    **/
   @ApiModelProperty(value = "The rank of the card on the Giftcard system. For example: primary, secondary, collector.")
   @JsonProperty("rank")
   public String getRank() {
      return rank;
   }

   public void setRank(String rank) {
      this.rank = rank;
   }

   @Override
   public boolean equals(java.lang.Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Card card = (Card) o;
      return Objects.equals(this.pan, card.pan) && Objects.equals(this.expiryDate, card.expiryDate)
            && Objects.equals(this.clearPin, card.clearPin) && Objects.equals(this.encryptedPin, card.encryptedPin)
            && Objects.equals(this.rank, card.rank);
   }

   @Override
   public int hashCode() {
      return Objects.hash(pan, expiryDate, clearPin, encryptedPin, rank);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class Card {\n");

      sb.append("    pan: ").append(Utils.toIndentedString(new MaskPan().mask(pan))).append("\n");
      sb.append("    expiryDate: ").append(Utils.toIndentedString(expiryDate)).append("\n");
      sb.append("    clearPin: ").append(Utils.toIndentedString(new MaskAll().mask(clearPin))).append("\n");
      sb.append("    encryptedPin: ").append(Utils.toIndentedString(new MaskAll().mask(encryptedPin))).append("\n");
      sb.append("    rank: ").append(Utils.toIndentedString(rank)).append("\n");
      sb.append("    pin: ").append(Utils.toIndentedString(pin)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
