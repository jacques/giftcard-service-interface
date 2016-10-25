package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.LookupRequest;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

public interface ILookupGiftcardsResource {
   public Response lookupGiftcard(
         UUID lookupId,
         LookupRequest body,
         SecurityContext securityContext,
         HttpHeaders httpHeaders,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);
}
