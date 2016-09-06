package io.electrum.giftcard.api.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.electrum.vas.Utils;
import io.electrum.vas.model.BasicReversal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information about the gift card void being reversed.
 */
@ApiModel(description = "Information about the gift card void being reversed.")
public class VoidReversal extends BasicReversal {
   private VoidRequest voidRequest = null;

   public VoidReversal voidRequest(VoidRequest voidRequest) {
      this.voidRequest = voidRequest;
      return this;
   }

   /**
    * Get voidRequest
    * 
    * @return voidRequest
    **/
   @ApiModelProperty(required = true, value = "The original Void Request being reversed.")
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
      sb.append("class VoidReversal {\n");

      sb.append("    id: ").append(Utils.toIndentedString(id)).append("\n");
      sb.append("    requestId: ").append(Utils.toIndentedString(requestId)).append("\n");
      sb.append("    time: ").append(Utils.toIndentedString(time)).append("\n");
      sb.append("    thirdPartyIdentifiers: ").append(Utils.toIndentedString(thirdPartyIdentifiers)).append("\n");
      sb.append("    reversalReason: ").append(Utils.toIndentedString(reversalReason)).append("\n");
      sb.append("    voidRequest: ").append(Utils.toIndentedString(voidRequest)).append("\n");
      sb.append("}");
      return sb.toString();
   }
}
