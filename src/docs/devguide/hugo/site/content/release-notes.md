This page describes changes to the Giftcard Service Interface implemented across different releases of the interface.

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
