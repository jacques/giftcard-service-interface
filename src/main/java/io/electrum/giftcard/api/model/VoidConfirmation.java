package io.electrum.giftcard.api.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.electrum.vas.model.BasicAdvice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information about a gift card void to be confirmed.
 */
@ApiModel(description = "Information about a gift card void to be confirmed.")
public class VoidConfirmation extends BasicAdvice {
   private VoidRequest voidRequest = null;

   public VoidConfirmation voidRequest(VoidRequest voidRequest) {
      this.voidRequest = voidRequest;
      return this;
   }

   /**
    * Get voidRequest
    * 
    * @return voidRequest
    **/
   @ApiModelProperty(required = true, value = "The original Void Request being confirmed.")
   @JsonProperty("voidRequest")
   @NotNull
   public VoidRequest getVoidRequest() {
      return voidRequest;
   }

   public void setVoidRequest(VoidRequest voidRequest) {
      this.voidRequest = voidRequest;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class VoidConfirmation {\n");

      sb.append("    id: ").append(Utils.toIndentedString(id)).append("\n");
      sb.append("    requestId: ").append(Utils.toIndentedString(requestId)).append("\n");
      sb.append("    time: ").append(Utils.toIndentedString(time)).append("\n");
      sb.append("    thirdPartyIdentifiers: ").append(Utils.toIndentedString(thirdPartyIdentifiers)).append("\n");
      sb.append("    voidRequest: ").append(Utils.toIndentedString(voidRequest)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
