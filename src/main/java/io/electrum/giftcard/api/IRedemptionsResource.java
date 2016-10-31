package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.RedemptionConfirmation;
import io.electrum.giftcard.api.model.RedemptionRequest;
import io.electrum.giftcard.api.model.RedemptionReversal;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

public interface IRedemptionsResource {
   public Response confirmRedemption(
         String redemptionId,
         String confirmationId,
         RedemptionConfirmation body,
         SecurityContext securityContext,
         HttpHeaders httpHeaders,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);

   public Response redeem(
         String redemptionId,
         RedemptionRequest body,
         SecurityContext securityContext,
         HttpHeaders httpHeaders,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);

   public Response reverseRedemption(
         String redemptionId,
         String reversalId,
         RedemptionReversal body,
         SecurityContext securityContext,
         HttpHeaders httpHeaders,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);
}
