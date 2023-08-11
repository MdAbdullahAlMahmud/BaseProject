package com.example.baseprojectsetup.core.base

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseprojectsetup.core.base.data.model.BaseResponse
import com.example.baseprojectsetup.core.base.data.model.WebSocketNotificationModel
import com.example.baseprojectsetup.core.base.interfaces.CommunicatorImpl
import com.example.baseprojectsetup.core.base.utils.ResponseCode
import com.example.baseprojectsetup.core.base.utils.SingleLiveEvent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import java.net.HttpURLConnection
import java.util.Locale
import javax.inject.Inject

abstract class BaseViewModel : ViewModel(){
    private val _showLoader = MutableLiveData<SingleLiveEvent<Boolean>>()
    val showLoader: LiveData<SingleLiveEvent<Boolean>> = _showLoader

    private val _showNotification = MutableLiveData<SingleLiveEvent<String>>()
    val showNotification: LiveData<SingleLiveEvent<String>> = _showNotification
    private val _showMessage = MutableLiveData<SingleLiveEvent<String>>()
    val showMessage: LiveData<SingleLiveEvent<String>> = _showMessage

    private val _isLanguageUpdateNeeded = MutableLiveData<Boolean>()
    val isLanguageUpdateNeeded: LiveData<Boolean> = _isLanguageUpdateNeeded

    private val _showGifLoader = MutableLiveData<Boolean>()
    val showGifLoader: LiveData<Boolean> = _showGifLoader



    @Inject
    @ApplicationContext
    lateinit var appContext: Context
    private val defaultErrorMessage = "Something went wrong! Please try again later"
    private val timeOutErrorMessage = "The request timed out"

    suspend fun <T> callService(
        isShowMessage: Boolean = false,
        isShowErrorMessage: Boolean = true,
        isShowLoader: Boolean = true,
        api: suspend () -> Response<BaseResponse<T>>
    ): BaseResponse<T>? {
        try {
            if (isShowLoader)
                showLoader()

            delay(500)
            val response = api.invoke()

            if (isShowLoader)
                hideLoader()

            if (!response.isSuccessful) {

                val gson = Gson()
                val type = object : TypeToken<BaseResponse<T>>() {}.type
                val errorResponse: BaseResponse<T>? =
                    gson.fromJson(response.errorBody()?.charStream(), type)

                val message = errorResponse?.responseMessage?.ifEmpty { defaultErrorMessage }
                    ?: defaultErrorMessage
                showMessage(message)

                if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    CommunicatorImpl.callback?.authenticationFailed(message)
                }

                return null
            }
            val apiResponse = response.body() as BaseResponse<T>

            when (response.code()) {
                ResponseCode.SUCCESSFULL.value -> {
                    if (isShowMessage)
                        showMessage(apiResponse.responseMessage)
                    return apiResponse
                }
                ResponseCode.RECORD_NOT_FOUND.value -> {
                    if (isShowErrorMessage)
                        showMessage(apiResponse.responseMessage)
                    return apiResponse
                }
                else -> {
                    showMessage(apiResponse.responseMessage)
                }
            }

            return null
        } catch (e: TimeoutCancellationException) {
            hideLoader()
            hideGifLoader()
            showMessage(timeOutErrorMessage)
            e.printStackTrace()
        }catch (e: Exception) {
            hideLoader()
            hideGifLoader()
            showMessage(defaultErrorMessage)
            e.printStackTrace()
        }
        return null
    }

    fun showLoader() {
        _showLoader.value = SingleLiveEvent(true)
    }

    fun hideLoader() {
        _showLoader.value = SingleLiveEvent(false)
    }

    fun processNotification(notification: WebSocketNotificationModel?) {
        if (notification != null) {


            showNotification(notification)
        }

    }

    fun showMessage(message: String?) {
        if (message != null && message.isNotEmpty())
            _showMessage.value = SingleLiveEvent(message)
    }





    private fun showNotification(notification: WebSocketNotificationModel) {
        var channelId = notification.messageTitle.replace(" ", "_").uppercase(Locale.ROOT)
        val notificationManager =
            appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(notificationManager, channelId)


        var builder = NotificationCompat.Builder(appContext, channelId)
            .setSmallIcon(android.R.drawable.stat_notify_chat)
            .setContentTitle(notification.messageTitle)
            .setDefaults(Notification.DEFAULT_ALL)
            .setAutoCancel(true)
            .setAllowSystemGeneratedContextualActions(true)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(notification.messageText)
            )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.priority = NotificationManager.IMPORTANCE_MAX
        } else {
            builder.priority = NotificationCompat.PRIORITY_MAX
        }
        /**
         *Please comment out below code to  Show Notification TODO
         */
       /* try {

            with(NotificationManagerCompat.from(appContext)) {
                notify(System.currentTimeMillis().toInt(), builder.build())
            }
        } catch (exp: Exception) {
            println("::::::::::::::: ERROR : $exp")
        }*/


    }

    private fun createNotificationChannel(
        notificationManager: NotificationManager,
        channelId: String
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                channelId,
                channelId,
                NotificationManager.IMPORTANCE_HIGH
            )
            mChannel.description = "This channel used for $channelId type of notifications"


            notificationManager.createNotificationChannel(mChannel)
        }
    }

    fun showGifLoader() {
        _showGifLoader.value = true
    }

    fun hideGifLoader() {
        _showGifLoader.value = false
    }

}