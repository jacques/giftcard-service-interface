package io.electrum.giftcard.api.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.electrum.vas.model.TenderAdvice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information about a gift card activation to be confirmed.
 */
@ApiModel(description = "Information about a gift card activation to be confirmed.")
public class ActivationConfirmation extends TenderAdvice {
   private ActivationRequest activationRequest = null;

   public ActivationConfirmation activationRequest(ActivationRequest activationRequest) {
      this.activationRequest = activationRequest;
      return this;
   }

   /**
    * Get activationRequest
    * 
    * @return activationRequest
    **/
   @ApiModelProperty(required = true, value = "The original Activation Request being confirmed.")
   @JsonProperty("activationRequest")
   @NotNull
   public ActivationRequest getActivationRequest() {
      return activationRequest;
   }

   public void setActivationRequest(ActivationRequest activationRequest) {
      this.activationRequest = activationRequest;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class ActivationConfirmation {\n");

      sb.append("    id: ").append(Utils.toIndentedString(id)).append("\n");
      sb.append("    requestId: ").append(Utils.toIndentedString(requestId)).append("\n");
      sb.append("    time: ").append(Utils.toIndentedString(time)).append("\n");
      sb.append("    thirdPartyIdentifiers: ").append(Utils.toIndentedString(thirdPartyIdentifiers)).append("\n");
      sb.append("    tenders: ").append(Utils.toIndentedString(tenders)).append("\n");
      sb.append("    activationRequest: ").append(Utils.toIndentedString(activationRequest)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
