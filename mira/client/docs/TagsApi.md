# TagsApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createTagApiTagsPost**](TagsApi.md#createTagApiTagsPost) | **POST** /api/tags/ | Create Tag
[**deleteLikeFromTagApiTagsTagIdLikesDelete**](TagsApi.md#deleteLikeFromTagApiTagsTagIdLikesDelete) | **DELETE** /api/tags/{tag_id}/likes | Delete Like From Tag
[**deleteTagApiTagsTagIdDelete**](TagsApi.md#deleteTagApiTagsTagIdDelete) | **DELETE** /api/tags/{tag_id} | Delete Tag
[**getTagsApiTagsGet**](TagsApi.md#getTagsApiTagsGet) | **GET** /api/tags/ | Get Tags
[**likeTagApiTagsTagIdLikesPost**](TagsApi.md#likeTagApiTagsTagIdLikesPost) | **POST** /api/tags/{tag_id}/likes | Like Tag


<a id="createTagApiTagsPost"></a>
# **createTagApiTagsPost**
> Tag createTagApiTagsPost(latitude, longitude, description, image)

Create Tag

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TagsApi()
val latitude : java.math.BigDecimal = 8.14 // java.math.BigDecimal | 
val longitude : java.math.BigDecimal = 8.14 // java.math.BigDecimal | 
val description : kotlin.String = description_example // kotlin.String | 
val image : java.io.File = BINARY_DATA_HERE // java.io.File | 
try {
    val result : Tag = apiInstance.createTagApiTagsPost(latitude, longitude, description, image)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TagsApi#createTagApiTagsPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TagsApi#createTagApiTagsPost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **latitude** | **java.math.BigDecimal**|  |
 **longitude** | **java.math.BigDecimal**|  |
 **description** | **kotlin.String**|  |
 **image** | **java.io.File**|  | [optional]

### Return type

[**Tag**](Tag.md)

### Authorization


Configure OAuth2PasswordBearer:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a id="deleteLikeFromTagApiTagsTagIdLikesDelete"></a>
# **deleteLikeFromTagApiTagsTagIdLikesDelete**
> deleteLikeFromTagApiTagsTagIdLikesDelete(tagId)

Delete Like From Tag

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TagsApi()
val tagId : java.util.UUID = 38400000-8cf0-11bd-b23e-10b96e4ef00d // java.util.UUID | 
try {
    apiInstance.deleteLikeFromTagApiTagsTagIdLikesDelete(tagId)
} catch (e: ClientException) {
    println("4xx response calling TagsApi#deleteLikeFromTagApiTagsTagIdLikesDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TagsApi#deleteLikeFromTagApiTagsTagIdLikesDelete")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **tagId** | **java.util.UUID**|  |

### Return type

null (empty response body)

### Authorization


Configure OAuth2PasswordBearer:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="deleteTagApiTagsTagIdDelete"></a>
# **deleteTagApiTagsTagIdDelete**
> deleteTagApiTagsTagIdDelete(tagId)

Delete Tag

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TagsApi()
val tagId : java.util.UUID = 38400000-8cf0-11bd-b23e-10b96e4ef00d // java.util.UUID | 
try {
    apiInstance.deleteTagApiTagsTagIdDelete(tagId)
} catch (e: ClientException) {
    println("4xx response calling TagsApi#deleteTagApiTagsTagIdDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TagsApi#deleteTagApiTagsTagIdDelete")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **tagId** | **java.util.UUID**|  |

### Return type

null (empty response body)

### Authorization


Configure OAuth2PasswordBearer:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="getTagsApiTagsGet"></a>
# **getTagsApiTagsGet**
> kotlin.collections.List&lt;Tag&gt; getTagsApiTagsGet(image, imageNeq, orderBy, userId, userUsername)

Get Tags

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TagsApi()
val image : Image =  // Image | 
val imageNeq : ImageNeq =  // ImageNeq | 
val orderBy : OrderBy =  // OrderBy | 
val userId : UserId =  // UserId | 
val userUsername : UserUsername =  // UserUsername | 
try {
    val result : kotlin.collections.List<Tag> = apiInstance.getTagsApiTagsGet(image, imageNeq, orderBy, userId, userUsername)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TagsApi#getTagsApiTagsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TagsApi#getTagsApiTagsGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **image** | [**Image**](.md)|  | [optional]
 **imageNeq** | [**ImageNeq**](.md)|  | [optional]
 **orderBy** | [**OrderBy**](.md)|  | [optional]
 **userId** | [**UserId**](.md)|  | [optional]
 **userUsername** | [**UserUsername**](.md)|  | [optional]

### Return type

[**kotlin.collections.List&lt;Tag&gt;**](Tag.md)

### Authorization


Configure OAuth2PasswordBearer:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="likeTagApiTagsTagIdLikesPost"></a>
# **likeTagApiTagsTagIdLikesPost**
> Tag likeTagApiTagsTagIdLikesPost(tagId)

Like Tag

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TagsApi()
val tagId : java.util.UUID = 38400000-8cf0-11bd-b23e-10b96e4ef00d // java.util.UUID | 
try {
    val result : Tag = apiInstance.likeTagApiTagsTagIdLikesPost(tagId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TagsApi#likeTagApiTagsTagIdLikesPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TagsApi#likeTagApiTagsTagIdLikesPost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **tagId** | **java.util.UUID**|  |

### Return type

[**Tag**](Tag.md)

### Authorization


Configure OAuth2PasswordBearer:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

