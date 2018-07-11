package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.ErrorDetail;
import io.electrum.giftcard.api.model.LoadConfirmation;
import io.electrum.giftcard.api.model.LoadRequest;
import io.electrum.giftcard.api.model.LoadResponse;
import io.electrum.giftcard.api.model.LoadReversal;
import io.electrum.vas.model.BasicAdviceResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.ResponseHeader;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Path(LoadsResource.PATH)
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(description = "the loads API")
public abstract class LoadsResource {
   protected abstract ILoadsResource getResourceImplementation();

   public static final String PATH = "/loads";

   public class Load {
      public static final String OPERATION = "load";

      public class PathParameters {
         public static final String LOAD_ID = "loadId";
      }
   }

   public class ConfirmLoad {
      public static final String OPERATION = "confirmLoad";

      public class PathParameters {
         public static final String LOAD_ID = "loadId";
         public static final String CONFIRMATION_ID = "confirmationId";
      }
   }

   public class ReverseLoad {
      public static final String OPERATION = "reverseLoad";

      public class PathParameters {
         public static final String LOAD_ID = "loadId";
         public static final String REVERSAL_ID = "reversalId";
      }
   }

   @POST
   @Path("/{" + ConfirmLoad.PathParameters.LOAD_ID + "}/confirmations/{" + ConfirmLoad.PathParameters.CONFIRMATION_ID
         + "}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Confirm a load of funds on a gift card.", notes = "The Load Confirmations endpoint "
         + "registers the confirmation of a prior load on a gift card. Load confirmations are advice type "
         + "messages and should continue to be sent at suitable intervals until a response has been received. "
         + "Multiple confirmation advices may be sent which refer to the same load. The net result is that "
         + "the load is confirmed once.", authorizations = {
               @Authorization(value = "httpBasic") }, tags = { "Confirmations", "Loads", })
   @ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted", response = BasicAdviceResponse.class),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 404, message = "Not Found", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void confirmLoad(
         @ApiParam(value = "The randomly generated loadId UUID as sent in the original load.", required = true) @PathParam("loadId") String loadId,
         @ApiParam(value = "The randomly generated UUID identifying this confirmation, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("confirmationId") String confirmationId,
         @ApiParam(value = "The load confirmation information.", required = true) LoadConfirmation loadConfirmation,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().confirmLoad(
            loadId,
            confirmationId,
            loadConfirmation,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   @POST
   @Path("/{" + Load.PathParameters.LOAD_ID + "}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Request funds to be loaded on a gift card.", notes = "The Loads endpoint "
         + "allows loading of funds on a gift card to be authorized. A load is not considered "
         + "complete until a load confirmation or load reversal has been sent and acknowledged. A "
         + "load request should only be sent once otherwise multiple loads may occur erroneously.", authorizations = {
               @Authorization(value = "httpBasic") }, tags = { "Loads", })
   @ApiResponses(value = {
         @ApiResponse(code = 201, message = "Created", response = LoadResponse.class, responseHeaders = {
               @ResponseHeader(name = "Location", description = "The location of the created load resource", response = String.class) }),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void load(
         @ApiParam(value = "The randomly generated UUID identifying this load, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("loadId") String loadId,
         @ApiParam(value = "The load information.", required = true) LoadRequest loadRequest,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().load(
            loadId,
            loadRequest,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   @POST
   @Path("/{" + ReverseLoad.PathParameters.LOAD_ID + "}/reversals/{" + ReverseLoad.PathParameters.REVERSAL_ID + "}")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @ApiOperation(value = "Simplistically, a load reversal undoes a load if the load "
         + "was successfully processed.", notes = "The Load Reversals endpoint allows "
               + "loads on a gift card to be reversed. If the sender of a load request "
               + "is uncertain of the state of a load request then the sender must "
               + "send a load reversal. Reversals should continue to be sent at "
               + "suitable intervals until a response has been received. Multiple "
               + "reversals may be sent which refer to the same load. The net result "
               + "is that the load is reversed once. Note that a load reversal does not "
               + "equate to a redemption.", authorizations = {
                     @Authorization(value = "httpBasic") }, tags = { "Loads", "Reversals", })
   @ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted", response = BasicAdviceResponse.class),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 404, message = "Not Found", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void reverseLoad(
         @ApiParam(value = "The randomly generated loadId UUID as sent in the original load.", required = true) @PathParam("loadId") String loadId,
         @ApiParam(value = "The randomly generated UUID identifying this reversal, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("reversalId") String reversalId,
         @ApiParam(value = "The redemption reversal information.", required = true) LoadReversal loadReversal,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().reverseLoad(
            loadId,
            reversalId,
            loadReversal,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

}
