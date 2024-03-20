# AuthApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**authJwtLoginApiAuthJwtLoginPost**](AuthApi.md#authJwtLoginApiAuthJwtLoginPost) | **POST** /api/auth/jwt/login | Auth:Jwt.Login
[**registerRegisterApiAuthRegisterPost**](AuthApi.md#registerRegisterApiAuthRegisterPost) | **POST** /api/auth/register | Register:Register


<a id="authJwtLoginApiAuthJwtLoginPost"></a>
# **authJwtLoginApiAuthJwtLoginPost**
> BearerResponse authJwtLoginApiAuthJwtLoginPost(username, password, grantType, scope, clientId, clientSecret)

Auth:Jwt.Login

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AuthApi()
val username : kotlin.String = username_example // kotlin.String | 
val password : kotlin.String = password_example // kotlin.String | 
val grantType : GrantType =  // GrantType | 
val scope : kotlin.String = scope_example // kotlin.String | 
val clientId : ClientId =  // ClientId | 
val clientSecret : ClientSecret =  // ClientSecret | 
try {
    val result : BearerResponse = apiInstance.authJwtLoginApiAuthJwtLoginPost(username, password, grantType, scope, clientId, clientSecret)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#authJwtLoginApiAuthJwtLoginPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#authJwtLoginApiAuthJwtLoginPost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **username** | **kotlin.String**|  |
 **password** | **kotlin.String**|  |
 **grantType** | [**GrantType**](GrantType.md)|  | [optional]
 **scope** | **kotlin.String**|  | [optional] [default to &quot;&quot;]
 **clientId** | [**ClientId**](ClientId.md)|  | [optional]
 **clientSecret** | [**ClientSecret**](ClientSecret.md)|  | [optional]

### Return type

[**BearerResponse**](BearerResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

<a id="registerRegisterApiAuthRegisterPost"></a>
# **registerRegisterApiAuthRegisterPost**
> UserRead registerRegisterApiAuthRegisterPost(userCreate)

Register:Register

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AuthApi()
val userCreate : UserCreate =  // UserCreate | 
try {
    val result : UserRead = apiInstance.registerRegisterApiAuthRegisterPost(userCreate)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#registerRegisterApiAuthRegisterPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#registerRegisterApiAuthRegisterPost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userCreate** | [**UserCreate**](UserCreate.md)|  |

### Return type

[**UserRead**](UserRead.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

