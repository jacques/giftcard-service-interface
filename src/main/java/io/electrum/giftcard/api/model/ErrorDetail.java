package io.electrum.giftcard.api.model;

import io.electrum.vas.Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the outcome of a completed transaction
 **/
@ApiModel(description = "Represents the outcome of a completed transaction")
public class ErrorDetail {

   /**
    * Every failure must be classified into one of the following failure types
    */
   public enum ErrorType {
      DUPLICATE_RECORD("DUPLICATE_RECORD"),
      FORMAT_ERROR("FORMAT_ERROR"),
      FUNCTION_NOT_SUPPORTED("FUNCTION_NOT_SUPPORTED"),
      GENERAL_ERROR("GENERAL_ERROR"),
      INVALID_AMOUNT("INVALID_AMOUNT"),
      ROUTING_ERROR("ROUTING_ERROR"),
      TRANSACTION_NOT_SUPPORTED("TRANSACTION_NOT_SUPPORTED"),
      UNABLE_TO_LOCATE_RECORD("UNABLE_TO_LOCATE_RECORD"),
      UPSTREAM_UNAVAILABLE("UPSTREAM_UNAVAILABLE"),
      INVALID_PRODUCT("INVALID_PRODUCT"),
      CARD_NOT_ACTIVATED("CARD_NOT_ACTIVATED"),
      CARD_ACTIVATED("CARD_ACTIVATED"),
      CARD_VOIDED("CARD_VOIDED"),
      INSUFFICIENT_FUNDS("INSUFFICIENT_FUNDS"),
      INVALID_CARD_NUMBER("INVALID_CARD_NUMBER"),
      CARD_EXPIRED("CARD_EXPIRED"),
      INCORRECT_PIN("INCORRECT_PIN"),
      TEMPORARY_FAILURE("TEMPORARY_FAILURE"),
      DO_NOT_HONOUR("DO_NOT_HONOUR");

      private String value;

      ErrorType(String value) {
         this.value = value;
      }

      @Override
      @JsonValue
      public String toString() {
         return String.valueOf(value);
      }
   }

   public enum RequestType {
      ACTIVATION_CONFIRMATION("ACTIVATION_CONFIRMATION"),
      ACTIVATION_REQUEST("ACTIVATION_REQUEST"),
      ACTIVATION_REVERSAL("ACTIVATION_REVERSAL"),
      LOAD_CONFIRMATION("LOAD_CONFIRMATION"),
      LOAD_REQUEST("LOAD_REQUEST"),
      LOAD_REVERSAL("LOAD_REVERSAL"),
      LOOKUP_REQUEST("LOOKUP_REQUEST"),
      REDEMPTION_CONFIRMATION("REDEMPTION_CONFIRMATION"),
      REDEMPTION_REQUEST("REDEMPTION_REQUEST"),
      REDEMPTION_REVERSAL("REDEMPTION_REVERSAL"),
      REPLACE_CONFIRMATION("REPLACE_CONFIRMATION"),
      REPLACE_REQUEST("REPLACE_REQUEST"),
      REPLACE_REVERSAL("REPLACE_REVERSAL"),
      TRANSFER_CONFIRMATION("TRANSFER_CONFIRMATION"),
      TRANSFER_REQUEST("TRANSFER_REQUEST"),
      TRANSFER_REVERSAL("TRANSFER_REVERSAL"),
      VOID_CONFIRMATION("VOID_CONFIRMATION"),
      VOID_REQUEST("VOID_REQUEST"),
      VOID_REVERSAL("VOID_REVERSAL");

      private String value;

      RequestType(String value) {
         this.value = value;
      }

      @Override
      @JsonValue
      public String toString() {
         return String.valueOf(value);
      }
   }

   private ErrorType errorType = null;
   private String errorMessage = null;
   private Object detailMessage = null;
   private RequestType requestType = null;
   private String id = null;
   private String originalId = null;

   /**
    * The type of error that occurred
    **/
   public ErrorDetail errorType(ErrorType errorType) {
      this.errorType = errorType;
      return this;
   }

   @ApiModelProperty(required = true, value = "The type of error that occurred")
   @JsonProperty("errorType")
   @NotNull
   public ErrorType getErrorType() {
      return errorType;
   }

   public void setErrorType(ErrorType errorType) {
      this.errorType = errorType;
   }

   public ErrorDetail requestType(RequestType requestType) {
      this.requestType = requestType;
      return this;
   }

   @ApiModelProperty(required = true, value = "The type of request being processed when the error occurred.")
   @JsonProperty("requestType")
   @NotNull
   public RequestType getRequestType() {
      return requestType;
   }

   public void setRequestType(RequestType requesType) {
      this.requestType = requesType;
   }

   /**
    * The UUID of the message for which the error occurred.
    * 
    * @param id
    *           The UUID
    * @return this object
    */
   public ErrorDetail id(String id) {
      this.id = id;
      return this;
   }

   @ApiModelProperty(required = true, value = "The UUID of the message for which the error occurred.")
   @JsonProperty("id")
   @NotNull
   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   /**
    * The UUID of the original request message int he case of an error occurring for an advice message
    * 
    * @param originalId
    *           The original request UUID
    * @return this object
    */
   public ErrorDetail originalId(String originalId) {
      this.originalId = originalId;
      return this;
   }

   @ApiModelProperty(value = "The UUID of the original request message int he case of an error occurring for an advice message.")
   @JsonProperty("originalId")
   public String getOriginalId() {
      return originalId;
   }

   public void setOriginalId(String originalId) {
      this.originalId = originalId;
   }

   /**
    * A short description of the error
    **/
   public ErrorDetail errorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
      return this;
   }

   @ApiModelProperty(required = true, value = "A short description of the error")
   @JsonProperty("errorMessage")
   @NotNull
   @Length(max = 20)
   public String getErrorMessage() {
      return errorMessage;
   }

   public void setErrorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
   }

   /**
    * A free form detailed description of a particular failure condition may optionally be supplied
    **/
   public ErrorDetail detailMessage(Object detailMessage) {
      this.detailMessage = detailMessage;
      return this;
   }

   @ApiModelProperty(value = "A free form detailed description of a particular failure condition may optionally be supplied")
   @JsonProperty("detailMessage")
   public Object getDetailMessage() {
      return detailMessage;
   }

   public void setDetailMessage(Object detailMessage) {
      this.detailMessage = detailMessage;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      ErrorDetail errorDetail = (ErrorDetail) o;
      return Objects.equals(errorType, errorDetail.errorType) && Objects.equals(errorMessage, errorDetail.errorMessage)
            && Objects.equals(detailMessage, errorDetail.detailMessage)
            && Objects.equals(requestType, errorDetail.requestType);
   }

   @Override
   public int hashCode() {
      return Objects.hash(requestType, errorType, errorMessage, detailMessage);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class ErrorDetail {\n");

      sb.append("    errorType: ").append(Utils.toIndentedString(errorType)).append("\n");
      sb.append("    responseMessage: ").append(Utils.toIndentedString(errorMessage)).append("\n");
      sb.append("    requestType: ").append(Utils.toIndentedString(requestType)).append("\n");
      sb.append("    id: ").append(Utils.toIndentedString(id)).append("\n");
      sb.append("    originalId: ").append(Utils.toIndentedString(originalId)).append("\n");
      sb.append("    detailMessage: ").append(Utils.toIndentedString(detailMessage)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
