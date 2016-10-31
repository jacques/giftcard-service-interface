package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.VoidConfirmation;
import io.electrum.giftcard.api.model.VoidRequest;
import io.electrum.giftcard.api.model.VoidReversal;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

public interface IVoidsResource {
   public Response confirmVoid(
         String voidId,
         String confirmationId,
         VoidConfirmation body,
         SecurityContext securityContext,
         HttpHeaders httpHeaders,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);

   public Response voidGiftcard(
         String voidId,
         VoidRequest body,
         SecurityContext securityContext,
         HttpHeaders httpHeaders,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);

   public Response reverseVoid(
         String voidId,
         String reversalId,
         VoidReversal body,
         SecurityContext securityContext,
         HttpHeaders httpHeaders,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);
}
