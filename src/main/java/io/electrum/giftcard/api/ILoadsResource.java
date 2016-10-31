package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.LoadConfirmation;
import io.electrum.giftcard.api.model.LoadRequest;
import io.electrum.giftcard.api.model.LoadReversal;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

public interface ILoadsResource {
   public Response confirmLoad(
         String loadId,
         String confirmationId,
         LoadConfirmation body,
         SecurityContext securityContext,
         HttpHeaders httpHeaders,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);

   public Response load(
         String loadId,
         LoadRequest body,
         SecurityContext securityContext,
         HttpHeaders httpHeaders,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);

   public Response reverseLoad(
         String loadId,
         String reversalId,
         LoadReversal body,
         SecurityContext securityContext,
         HttpHeaders httpHeaders,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);
}
