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

import org.openapitools.client.models.BearerResponse
import org.openapitools.client.models.ClientId
import org.openapitools.client.models.ClientSecret
import org.openapitools.client.models.ErrorModel
import org.openapitools.client.models.GrantType
import org.openapitools.client.models.HTTPValidationError
import org.openapitools.client.models.UserCreate
import org.openapitools.client.models.UserRead

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

class AuthApi(basePath: kotlin.String = defaultBasePath, client: OkHttpClient = ApiClient.defaultClient) : ApiClient(basePath, client) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "https://maps.rtuitlab.dev/docs#/auth")
        }
    }

    /**
     * Auth:Jwt.Login
     * 
     * @param username 
     * @param password 
     * @param grantType  (optional)
     * @param scope  (optional, default to "")
     * @param clientId  (optional)
     * @param clientSecret  (optional)
     * @return BearerResponse
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun authJwtLoginApiAuthJwtLoginPost(username: kotlin.String, password: kotlin.String, grantType: GrantType? = null, scope: kotlin.String? = "", clientId: ClientId? = null, clientSecret: ClientSecret? = null) : BearerResponse {
        val localVarResponse = authJwtLoginApiAuthJwtLoginPostWithHttpInfo(username = username, password = password, grantType = grantType, scope = scope, clientId = clientId, clientSecret = clientSecret)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as BearerResponse
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
     * Auth:Jwt.Login
     * 
     * @param username 
     * @param password 
     * @param grantType  (optional)
     * @param scope  (optional, default to "")
     * @param clientId  (optional)
     * @param clientSecret  (optional)
     * @return ApiResponse<BearerResponse?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun authJwtLoginApiAuthJwtLoginPostWithHttpInfo(username: kotlin.String, password: kotlin.String, grantType: GrantType?, scope: kotlin.String?, clientId: ClientId?, clientSecret: ClientSecret?) : ApiResponse<BearerResponse?> {
        val localVariableConfig = authJwtLoginApiAuthJwtLoginPostRequestConfig(username = username, password = password, grantType = grantType, scope = scope, clientId = clientId, clientSecret = clientSecret)

        return request<Map<String, PartConfig<*>>, BearerResponse>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation authJwtLoginApiAuthJwtLoginPost
     *
     * @param username 
     * @param password 
     * @param grantType  (optional)
     * @param scope  (optional, default to "")
     * @param clientId  (optional)
     * @param clientSecret  (optional)
     * @return RequestConfig
     */
    fun authJwtLoginApiAuthJwtLoginPostRequestConfig(username: kotlin.String, password: kotlin.String, grantType: GrantType?, scope: kotlin.String?, clientId: ClientId?, clientSecret: ClientSecret?) : RequestConfig<Map<String, PartConfig<*>>> {
        val localVariableBody = mapOf(
            "grant_type" to PartConfig(body = grantType, headers = mutableMapOf()),
            "username" to PartConfig(body = username, headers = mutableMapOf()),
            "password" to PartConfig(body = password, headers = mutableMapOf()),
            "scope" to PartConfig(body = scope, headers = mutableMapOf()),
            "client_id" to PartConfig(body = clientId, headers = mutableMapOf()),
            "client_secret" to PartConfig(body = clientSecret, headers = mutableMapOf()),)
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf("Content-Type" to "application/x-www-form-urlencoded")
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/auth/jwt/login",
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = false,
            body = localVariableBody
        )
    }

    /**
     * Register:Register
     * 
     * @param userCreate 
     * @return UserRead
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun registerRegisterApiAuthRegisterPost(userCreate: UserCreate) : UserRead {
        val localVarResponse = registerRegisterApiAuthRegisterPostWithHttpInfo(userCreate = userCreate)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as UserRead
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
     * Register:Register
     * 
     * @param userCreate 
     * @return ApiResponse<UserRead?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun registerRegisterApiAuthRegisterPostWithHttpInfo(userCreate: UserCreate) : ApiResponse<UserRead?> {
        val localVariableConfig = registerRegisterApiAuthRegisterPostRequestConfig(userCreate = userCreate)

        return request<UserCreate, UserRead>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation registerRegisterApiAuthRegisterPost
     *
     * @param userCreate 
     * @return RequestConfig
     */
    fun registerRegisterApiAuthRegisterPostRequestConfig(userCreate: UserCreate) : RequestConfig<UserCreate> {
        val localVariableBody = userCreate
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/auth/register",
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = false,
            body = localVariableBody
        )
    }


    private fun encodeURIComponent(uriComponent: kotlin.String): kotlin.String =
        HttpUrl.Builder().scheme("http").host("localhost").addPathSegment(uriComponent).build().encodedPathSegments[0]
}
