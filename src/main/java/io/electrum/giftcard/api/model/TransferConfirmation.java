package io.electrum.giftcard.api.model;

import io.electrum.vas.Utils;
import io.electrum.vas.model.BasicAdvice;
import io.swagger.annotations.ApiModel;

/**
 * Information about a gift card transfer to be confirmed.
 */
@ApiModel(description = "Information about a gift card transfer to be confirmed.")
public class TransferConfirmation extends BasicAdvice {

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class TransferConfirmation {\n");

      sb.append("    id: ").append(Utils.toIndentedString(id)).append("\n");
      sb.append("    requestId: ").append(Utils.toIndentedString(requestId)).append("\n");
      sb.append("    time: ").append(Utils.toIndentedString(time)).append("\n");
      sb.append("    thirdPartyIdentifiers: ").append(Utils.toIndentedString(thirdPartyIdentifiers)).append("\n");
      sb.append("}");
      return sb.toString();
   }

}
