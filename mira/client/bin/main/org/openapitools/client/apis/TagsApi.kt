/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package org.openapitools.client.apis

import java.io.IOException
import okhttp3.OkHttpClient
import okhttp3.HttpUrl

import org.openapitools.client.models.HTTPValidationError
import org.openapitools.client.models.Image
import org.openapitools.client.models.ImageNeq
import org.openapitools.client.models.OrderBy
import org.openapitools.client.models.Tag
import org.openapitools.client.models.UserId
import org.openapitools.client.models.UserUsername

import com.squareup.moshi.Json

import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.ApiResponse
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ClientError
import org.openapitools.client.infrastructure.ServerException
import org.openapitools.client.infrastructure.ServerError
import org.openapitools.client.infrastructure.MultiValueMap
import org.openapitools.client.infrastructure.PartConfig
import org.openapitools.client.infrastructure.RequestConfig
import org.openapitools.client.infrastructure.RequestMethod
import org.openapitools.client.infrastructure.ResponseType
import org.openapitools.client.infrastructure.Success
import org.openapitools.client.infrastructure.toMultiValue

class TagsApi(basePath: kotlin.String = defaultBasePath, client: OkHttpClient = ApiClient.defaultClient) : ApiClient(basePath, client) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "http://localhost")
        }
    }

    /**
     * Create Tag
     * 
     * @param latitude 
     * @param longitude 
     * @param description 
     * @param image  (optional)
     * @return Tag
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun createTagApiTagsPost(latitude: java.math.BigDecimal, longitude: java.math.BigDecimal, description: kotlin.String, image: java.io.File? = null) : Tag {
        val localVarResponse = createTagApiTagsPostWithHttpInfo(latitude = latitude, longitude = longitude, description = description, image = image)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as Tag
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()} ${localVarError.body}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
     * Create Tag
     * 
     * @param latitude 
     * @param longitude 
     * @param description 
     * @param image  (optional)
     * @return ApiResponse<Tag?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun createTagApiTagsPostWithHttpInfo(latitude: java.math.BigDecimal, longitude: java.math.BigDecimal, description: kotlin.String, image: java.io.File?) : ApiResponse<Tag?> {
        val localVariableConfig = createTagApiTagsPostRequestConfig(latitude = latitude, longitude = longitude, description = description, image = image)

        return request<Map<String, PartConfig<*>>, Tag>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation createTagApiTagsPost
     *
     * @param latitude 
     * @param longitude 
     * @param description 
     * @param image  (optional)
     * @return RequestConfig
     */
    fun createTagApiTagsPostRequestConfig(latitude: java.math.BigDecimal, longitude: java.math.BigDecimal, description: kotlin.String, image: java.io.File?) : RequestConfig<Map<String, PartConfig<*>>> {
        val localVariableBody = mapOf(
            "latitude" to PartConfig(body = latitude, headers = mutableMapOf()),
            "longitude" to PartConfig(body = longitude, headers = mutableMapOf()),
            "description" to PartConfig(body = description, headers = mutableMapOf()),
            "image" to PartConfig(body = image, headers = mutableMapOf()),)
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf("Content-Type" to "multipart/form-data")
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/tags/",
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = true,
            body = localVariableBody
        )
    }

    /**
     * Delete Like From Tag
     * 
     * @param tagId 
     * @return void
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun deleteLikeFromTagApiTagsTagIdLikesDelete(tagId: java.util.UUID) : Unit {
        val localVarResponse = deleteLikeFromTagApiTagsTagIdLikesDeleteWithHttpInfo(tagId = tagId)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()} ${localVarError.body}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
     * Delete Like From Tag
     * 
     * @param tagId 
     * @return ApiResponse<Unit?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Throws(IllegalStateException::class, IOException::class)
    fun deleteLikeFromTagApiTagsTagIdLikesDeleteWithHttpInfo(tagId: java.util.UUID) : ApiResponse<Unit?> {
        val localVariableConfig = deleteLikeFromTagApiTagsTagIdLikesDeleteRequestConfig(tagId = tagId)

        return request<Unit, Unit>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation deleteLikeFromTagApiTagsTagIdLikesDelete
     *
     * @param tagId 
     * @return RequestConfig
     */
    fun deleteLikeFromTagApiTagsTagIdLikesDeleteRequestConfig(tagId: java.util.UUID) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.DELETE,
            path = "/api/tags/{tag_id}/likes".replace("{"+"tag_id"+"}", encodeURIComponent(tagId.toString())),
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = true,
            body = localVariableBody
        )
    }

    /**
     * Delete Tag
     * 
     * @param tagId 
     * @return void
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun deleteTagApiTagsTagIdDelete(tagId: java.util.UUID) : Unit {
        val localVarResponse = deleteTagApiTagsTagIdDeleteWithHttpInfo(tagId = tagId)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()} ${localVarError.body}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
     * Delete Tag
     * 
     * @param tagId 
     * @return ApiResponse<Unit?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Throws(IllegalStateException::class, IOException::class)
    fun deleteTagApiTagsTagIdDeleteWithHttpInfo(tagId: java.util.UUID) : ApiResponse<Unit?> {
        val localVariableConfig = deleteTagApiTagsTagIdDeleteRequestConfig(tagId = tagId)

        return request<Unit, Unit>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation deleteTagApiTagsTagIdDelete
     *
     * @param tagId 
     * @return RequestConfig
     */
    fun deleteTagApiTagsTagIdDeleteRequestConfig(tagId: java.util.UUID) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.DELETE,
            path = "/api/tags/{tag_id}".replace("{"+"tag_id"+"}", encodeURIComponent(tagId.toString())),
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = true,
            body = localVariableBody
        )
    }

    /**
     * Get Tags
     * 
     * @param image  (optional)
     * @param imageNeq  (optional)
     * @param orderBy  (optional)
     * @param userId  (optional)
     * @param userUsername  (optional)
     * @return kotlin.collections.List<Tag>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun getTagsApiTagsGet(image: Image? = null, imageNeq: ImageNeq? = null, orderBy: OrderBy? = null, userId: UserId? = null, userUsername: UserUsername? = null) : kotlin.collections.List<Tag> {
        val localVarResponse = getTagsApiTagsGetWithHttpInfo(image = image, imageNeq = imageNeq, orderBy = orderBy, userId = userId, userUsername = userUsername)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as kotlin.collections.List<Tag>
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()} ${localVarError.body}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
     * Get Tags
     * 
     * @param image  (optional)
     * @param imageNeq  (optional)
     * @param orderBy  (optional)
     * @param userId  (optional)
     * @param userUsername  (optional)
     * @return ApiResponse<kotlin.collections.List<Tag>?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun getTagsApiTagsGetWithHttpInfo(image: Image?, imageNeq: ImageNeq?, orderBy: OrderBy?, userId: UserId?, userUsername: UserUsername?) : ApiResponse<kotlin.collections.List<Tag>?> {
        val localVariableConfig = getTagsApiTagsGetRequestConfig(image = image, imageNeq = imageNeq, orderBy = orderBy, userId = userId, userUsername = userUsername)

        return request<Unit, kotlin.collections.List<Tag>>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation getTagsApiTagsGet
     *
     * @param image  (optional)
     * @param imageNeq  (optional)
     * @param orderBy  (optional)
     * @param userId  (optional)
     * @param userUsername  (optional)
     * @return RequestConfig
     */
    fun getTagsApiTagsGetRequestConfig(image: Image?, imageNeq: ImageNeq?, orderBy: OrderBy?, userId: UserId?, userUsername: UserUsername?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (image != null) {
                    put("image", listOf(image.toString()))
                }
                if (imageNeq != null) {
                    put("image__neq", listOf(imageNeq.toString()))
                }
                if (orderBy != null) {
                    put("order_by", listOf(orderBy.toString()))
                }
                if (userId != null) {
                    put("user__id", listOf(userId.toString()))
                }
                if (userUsername != null) {
                    put("user__username", listOf(userUsername.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/tags/",
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = true,
            body = localVariableBody
        )
    }

    /**
     * Like Tag
     * 
     * @param tagId 
     * @return Tag
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun likeTagApiTagsTagIdLikesPost(tagId: java.util.UUID) : Tag {
        val localVarResponse = likeTagApiTagsTagIdLikesPostWithHttpInfo(tagId = tagId)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as Tag
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()} ${localVarError.body}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
     * Like Tag
     * 
     * @param tagId 
     * @return ApiResponse<Tag?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun likeTagApiTagsTagIdLikesPostWithHttpInfo(tagId: java.util.UUID) : ApiResponse<Tag?> {
        val localVariableConfig = likeTagApiTagsTagIdLikesPostRequestConfig(tagId = tagId)

        return request<Unit, Tag>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation likeTagApiTagsTagIdLikesPost
     *
     * @param tagId 
     * @return RequestConfig
     */
    fun likeTagApiTagsTagIdLikesPostRequestConfig(tagId: java.util.UUID) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/tags/{tag_id}/likes".replace("{"+"tag_id"+"}", encodeURIComponent(tagId.toString())),
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = true,
            body = localVariableBody
        )
    }


    private fun encodeURIComponent(uriComponent: kotlin.String): kotlin.String =
        HttpUrl.Builder().scheme("http").host("localhost").addPathSegment(uriComponent).build().encodedPathSegments[0]
}