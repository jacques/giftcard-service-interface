package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.RedemptionRequest;
import io.electrum.giftcard.api.model.RedemptionReversal;
import io.electrum.giftcard.api.model.TransferRequest;
import io.electrum.giftcard.api.model.TransferReversal;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.*;

public interface ITransfersResource {

   public Response transfer(
         String transferId,
         TransferRequest body,
         SecurityContext securityContext,
         Request request,
         HttpHeaders httpHeaders,
         AsyncResponse asyncResponse,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);

   public Response reverseTransfer(
         String transferId,
         String reversalId,
         TransferReversal body,
         SecurityContext securityContext,
         Request request,
         HttpHeaders httpHeaders,
         AsyncResponse asyncResponse,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);
}
