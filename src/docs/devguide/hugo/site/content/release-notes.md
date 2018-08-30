This page describes changes to the Giftcard Service Interface implemented across different releases of the interface.

## v3.6.0

Released 30 August 2018

- Added new MarketingAttribute entity to model
- Added list of MarketingAttributes to LookupResponse

## v3.5.0

Released 23 August 2018

- Added new Club entity to model
- Added a PointsAmount entity to describe relevant information about points in a transaction.
- Added a PointAmounts entity to hold relevant point amounts in a transaction.
- Added optional rank field to Card entity.
- Added optional points (PointAmounts)  and clubs fields to the LookupResponse.
- Added points (PointAmounts) field to the RedemptionRequest and RedemptionResponse.


## v3.4.0

Released 23 July 2018

- Added optional cardHolder field to LookupResponse, ActivationRequest and Activation Response.

## v3.3.2

Released 6 July 2018

- Added `Operations` nested class to each Resource which provides a concrete `String` representation of each operation.

## v3.3.1

Released 30 May

- Added TEMPORARY_FAILURE errorType.

## v3.3.0

Released 09 February 2018

- Added Transfer functionality - allow funds to be transferred between gift cards.
    - Added TransferRequest, TransferResponse, TransferConfirmation and TransferReversal.
- Added Replace functionality - allows an old gift card to be replaced with a new gift card
    - Added ReplaceRequest, ReplaceResponse, ReplaceConfirmation and ReplaceReversal.
- Update ErrorDetail RequestTypes with new Replace and Transfer request operations
- Updated giftcard-service-interface to latest service-base-interface (version 3.6.0)
    - Updated GiftcardAmounts toString() method to include the additionalAmounts field
 
## v3.2.0

Released 22 January 2018

- Updated Giftcard Service Interface to make use of base interface v3.5.0 to include new TranTypes and AccountTypes for Transactions.

## v3.1.0

Released 15 January 2018

- Align with v3.4.0 of base interface spec
- Don’t re-declare slipData in ActivationResponse

## v3.0.3

Released 06 March 2017

- Remove incorrect calls to AsyncResponse.resume in resources classes.

## v3.0.2

Released 06 March 2017

- Updated Giftcard Service Interface abstract resource classes. The classes were changed to pass all parameters to the User's custom resource implementation, as opposed to resuming the AsyncResponse from within the resource. The User should now call resume on the AsyncResponse. This allows for more flexibility, proper asynchronicity and allows the user to choose their own threading model.
- Added Jax RS Core Request to the expected parameters from @Context. This is passed through as above with the rest of the parameters.

## v3.0.1

Released 31 October 2016

- Updated Giftcard Service Interface to make use of base interface v3.1.0 to include new TenderTypes

## v3.0.0

Released 26 October 2016

- Aligned Giftcard Service Interface with base service interface (v3.0.0).
  - SlipData moved to basic service interface
  - Defined BasicAdviceResponse returned when acknowledging an advice.
  - Added request IDs and request type to ErrorDetail responses.

## v2.1.0

Released 23 September 2016

- Added a second balance (available balance) to gift card requests and responses.

## v2.0.0

Released 15 September 2016

- Gift card look ups have been made HTTP POST operations containing a LookupRequest in the body. This aligns gift card look up operations with other gift card operations.
- The original request has been removed from the subsequent advice messages (confirmations and reversals)
- Card and Product fields of Activation Responses made mandatory.
- Card field of Void Requests and Void Responses made mandatory.
- Updated ErrorType values.

## v1.1.0

Released 8 September 2016

- Card and Product fields of Activation Requests made mandatory.

## v1.0.0

Released 6 September 2016

Initial release.
