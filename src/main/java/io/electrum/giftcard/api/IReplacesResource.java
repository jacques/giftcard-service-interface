package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.ReplaceConfirmation;
import io.electrum.giftcard.api.model.ReplaceRequest;
import io.electrum.giftcard.api.model.ReplaceReversal;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.*;

public interface IReplacesResource {

   void confirmReplace(
         String replaceId,
         String confirmationId,
         ReplaceConfirmation body,
         SecurityContext securityContext,
         Request request,
         HttpHeaders httpHeaders,
         AsyncResponse asyncResponse,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);

   void replace(
         String replaceId,
         ReplaceRequest body,
         SecurityContext securityContext,
         Request request,
         HttpHeaders httpHeaders,
         AsyncResponse asyncResponse,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);

   void reverseReplace(
         String replaceId,
         String reversalId,
         ReplaceReversal body,
         SecurityContext securityContext,
         Request request,
         HttpHeaders httpHeaders,
         AsyncResponse asyncResponse,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);
}
