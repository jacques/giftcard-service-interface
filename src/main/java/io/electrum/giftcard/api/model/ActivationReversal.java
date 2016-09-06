package io.electrum.giftcard.api.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.electrum.vas.model.BasicReversal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information about a gift card activation to be reversed.
 */
@ApiModel(description = "Information about a gift card activation to be reversed.")
public class ActivationReversal extends BasicReversal {
   private ActivationRequest activationRequest = null;

   public ActivationReversal activationRequest(ActivationRequest activationRequest) {
      this.activationRequest = activationRequest;
      return this;
   }

   /**
    * Get activationRequest
    * 
    * @return activationRequest
    **/
   @ApiModelProperty(required = true, value = "The original Activation Request being reversed.")
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
      sb.append("class ActivationReversal {\n");

      sb.append("    id: ").append(Utils.toIndentedString(id)).append("\n");
      sb.append("    requestId: ").append(Utils.toIndentedString(requestId)).append("\n");
      sb.append("    time: ").append(Utils.toIndentedString(time)).append("\n");
      sb.append("    thirdPartyIdentifiers: ").append(Utils.toIndentedString(thirdPartyIdentifiers)).append("\n");
      sb.append("    reversalReason: ").append(Utils.toIndentedString(reversalReason)).append("\n");
      sb.append("    activationRequest: ").append(Utils.toIndentedString(activationRequest)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
