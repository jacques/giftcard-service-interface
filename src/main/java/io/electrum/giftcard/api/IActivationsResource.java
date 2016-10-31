package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.ActivationConfirmation;
import io.electrum.giftcard.api.model.ActivationRequest;
import io.electrum.giftcard.api.model.ActivationReversal;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

public interface IActivationsResource {
   public Response confirmActivation(
         String activationId,
         String confirmationId,
         ActivationConfirmation body,
         SecurityContext securityContext,
         HttpHeaders httpHeaders,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);

   public Response activate(
         String activationId,
         ActivationRequest body,
         SecurityContext securityContext,
         HttpHeaders httpHeaders,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);

   public Response reverseActivation(
         String activationId,
         String reversalId,
         ActivationReversal body,
         SecurityContext securityContext,
         HttpHeaders httpHeaders,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);
}
