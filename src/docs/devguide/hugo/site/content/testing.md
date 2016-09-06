---
title: "Testing"
menu:
  main:
    weight: 0
---

The Giftcard Service Interface test pack is not currently available for use. This message will be removed when the test pack is available.

When developing a server or client implementation of the Giftcard Service Interface it is important to verify that the implementation conforms to the Giftcard Service Interface specification. To test client implementations a test server is available at https://giftcard-service-test-pack.herokuapp.com. To test server implementations a test pack containing Postman scripts is available [here](https://github.com/electrumpayments/giftcard-service-test-pack/tree/master/test/postman).

Note that the test pack is made available simply as a tool to help test and investigate common implementation issues. However, the test pack is not intended to imply certification of the tested implementation's conformance to the Giftcard Service Interface.

## Important Security Considerations

The test pack does not claim to implement any security features and should never be considered safe to use in a production environment or with real, sensitive data.

Users of the test pack must bear in mind that any data sent from the test client or to the test server may be logged in plain text and without sufficient access control restrictions. Furthermore, the manner in which the test server handles requests does not guarantee records created by one entity cannot be viewed or modified by another. While the Postman scripts submit basic HTTP Authentication and the test server reads the Authentication header, these credentials are not mandatory in the test environment, are never verified and do not safely segregate test clients' messages from one another in all instances.

## Test Client
The test client consists of a [Postman](https://www.getpostman.com) environment with pre-built requests which can be sent to the server implementation to be tested. The Postman requests build valid Giftcard Service Interface messages, send them to the server implementation being tested and validate that the responses are formatted as expected.

In order to test server implementations the tester requires only the Postman files.

### Operation

Requests are built dynamically with cross-referenced values (e.g. the ID of an original request within a reversal advice) kept in Postman environment variables. For example, a load request script will generate the necessary values and store them, as well as the necessary response fields, which are then used in the next advice message. The tester should ensure that the correct request script is run before an advice script otherwise unintended field values may be submitted.

### Validation

The test client makes use of Postman's test checks to validate responses from the server. The tester should inspect the result of the test checks upon receipt of a response message to ensure the server being tested responded with the correct values.

## Test server

The test server is provided as a web service constantly listening at https://giftcard-service-test-pack.herokuapp.com.

### Operation

The test server listens on https://giftcard-service-test-pack.herokuapp.com for Giftcard Service Interface messages. When a message is received it is cached and an appropriate response is returned. Normally, if the client implementation has sent a valid Giftcard Service Interface message, the test server will respond with a successful response. If the test server could not process the client's message due to a client error (e.g. a formatting error) then the test server will respond with an [errorDetail](/specification/operations/#errorDetail) object describing the error encountered. If some other error prevents the test server from processing the message (e.g. two successive requests with the same UUID) the test server will also attempt to describe such errors in the response to the client.

### Validation

The test server defines a DetailMessage model. The test server returns a DetailMessage object in the detailMessage field of the [errorDetail](/specification/operations/#errorDetail) object returned in error responses. The DetailMessage model's fields are all of the models defined in the Giftcard Service Interface as well as an extra field called formatErrors. The formatErrors field is an array of FormatError models. A FormatError has three fields:

- field - The name of the field which failed validation.
- msg - A description of the expected format of the field.
- value - The invalid value received in the field.

The test server will validate the format of received messages. If the received message fails validation the test server will return a list of fields which failed validation.
