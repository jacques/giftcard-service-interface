# Giftcard Service Interface

The Giftcard Service Interface documents the messaging interface used by Electrum to connect acquirers, processors, and issuers together for performing giftcard transactions.

You can find documentation for this project [here](https://electrumpayments.github.io/giftcard-service-interface-docs/).

## Java projects

To include the service interface into your maven project, include the below dependency.

```xml
<dependency>
    <groupId>io.electrum</groupId>
    <artifactId>service-interface-base</artifactId>
    <version>3.19.0</version>
</dependency>
<dependency>
    <groupId>io.electrum</groupId>
    <artifactId>giftcard-service-interface</artifactId>
    <version>3.12.0</version>
</dependency>
```

Alternatively, you can download the jar from Bintray:
[Service Interface Base]](https://bintray.com/electrumpayments/java-open-source/service-interface-base).
[Giftcard Service Interface]](https://bintray.com/electrumpayments/java-open-source/giftcard-service-interface).

## Other languages

The interface is also available as a swagger (OpenApi) definition, which can be used to generate starter projects in many languages. See more info [here](https://electrumpayments.github.io/giftcard-service-interface-docs/specification/swagger).
