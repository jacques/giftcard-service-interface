package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.*;

public interface ITransfersResource {

   Response confirmTransfer(
         String transferId,
         String confirmationId,
         TransferConfirmation body,
         SecurityContext securityContext,
         Request request,
         HttpHeaders httpHeaders,
         AsyncResponse asyncResponse,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);

   Response transfer(
         String transferId,
         TransferRequest body,
         SecurityContext securityContext,
         Request request,
         HttpHeaders httpHeaders,
         AsyncResponse asyncResponse,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);

   Response reverseTransfer(
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
