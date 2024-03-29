# org.openapitools.client - Kotlin client library for Recruit Maps

No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)

## Overview
This API client was generated by the [OpenAPI Generator](https://openapi-generator.tech) project.  By using the [openapi-spec](https://github.com/OAI/OpenAPI-Specification) from a remote server, you can easily generate an API client.

- API version: 0.1.0
- Package version: 
- Generator version: 7.4.0
- Build package: org.openapitools.codegen.languages.KotlinClientCodegen

## Requires

* Kotlin 1.7.21
* Gradle 7.5

## Build

First, create the gradle wrapper script:

```
gradle wrapper
```

Then, run:

```
./gradlew check assemble
```

This runs all tests and packages the library.

## Features/Implementation Notes

* Supports JSON inputs/outputs, File inputs, and Form inputs.
* Supports collection formats for query parameters: csv, tsv, ssv, pipes.
* Some Kotlin and Java types are fully qualified to avoid conflicts with types defined in OpenAPI definitions.
* Implementation of ApiClient is intended to reduce method counts, specifically to benefit Android targets.

<a id="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *http://localhost*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*AuthApi* | [**authJwtLoginApiAuthJwtLoginPost**](docs/AuthApi.md#authjwtloginapiauthjwtloginpost) | **POST** /api/auth/jwt/login | Auth:Jwt.Login
*AuthApi* | [**registerRegisterApiAuthRegisterPost**](docs/AuthApi.md#registerregisterapiauthregisterpost) | **POST** /api/auth/register | Register:Register
*TagsApi* | [**createTagApiTagsPost**](docs/TagsApi.md#createtagapitagspost) | **POST** /api/tags/ | Create Tag
*TagsApi* | [**deleteLikeFromTagApiTagsTagIdLikesDelete**](docs/TagsApi.md#deletelikefromtagapitagstagidlikesdelete) | **DELETE** /api/tags/{tag_id}/likes | Delete Like From Tag
*TagsApi* | [**deleteTagApiTagsTagIdDelete**](docs/TagsApi.md#deletetagapitagstagiddelete) | **DELETE** /api/tags/{tag_id} | Delete Tag
*TagsApi* | [**getTagsApiTagsGet**](docs/TagsApi.md#gettagsapitagsget) | **GET** /api/tags/ | Get Tags
*TagsApi* | [**likeTagApiTagsTagIdLikesPost**](docs/TagsApi.md#liketagapitagstagidlikespost) | **POST** /api/tags/{tag_id}/likes | Like Tag


<a id="documentation-for-models"></a>
## Documentation for Models

 - [org.openapitools.client.models.BearerResponse](docs/BearerResponse.md)
 - [org.openapitools.client.models.ClientId](docs/ClientId.md)
 - [org.openapitools.client.models.ClientSecret](docs/ClientSecret.md)
 - [org.openapitools.client.models.Detail](docs/Detail.md)
 - [org.openapitools.client.models.ErrorModel](docs/ErrorModel.md)
 - [org.openapitools.client.models.GrantType](docs/GrantType.md)
 - [org.openapitools.client.models.HTTPValidationError](docs/HTTPValidationError.md)
 - [org.openapitools.client.models.Image](docs/Image.md)
 - [org.openapitools.client.models.ImageNeq](docs/ImageNeq.md)
 - [org.openapitools.client.models.OrderBy](docs/OrderBy.md)
 - [org.openapitools.client.models.Tag](docs/Tag.md)
 - [org.openapitools.client.models.TagUser](docs/TagUser.md)
 - [org.openapitools.client.models.User](docs/User.md)
 - [org.openapitools.client.models.UserCreate](docs/UserCreate.md)
 - [org.openapitools.client.models.UserId](docs/UserId.md)
 - [org.openapitools.client.models.UserRead](docs/UserRead.md)
 - [org.openapitools.client.models.UserUsername](docs/UserUsername.md)
 - [org.openapitools.client.models.ValidationError](docs/ValidationError.md)
 - [org.openapitools.client.models.ValidationErrorLocInner](docs/ValidationErrorLocInner.md)


<a id="documentation-for-authorization"></a>
## Documentation for Authorization


Authentication schemes defined for the API:
<a id="OAuth2PasswordBearer"></a>
### OAuth2PasswordBearer

- **Type**: OAuth
- **Flow**: password
- **Authorization URL**: 
- **Scopes**: N/A

