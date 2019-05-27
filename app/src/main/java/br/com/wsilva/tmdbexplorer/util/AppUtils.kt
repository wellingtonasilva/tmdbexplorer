package br.com.wsilva.tmdbexplorer.util

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

class AppUtils {

    companion object {
        fun createRetrofit(url: String): Retrofit {
            val gson = GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setPrettyPrinting()
                .setVersion(1.0)
                .create()

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(1, TimeUnit.MINUTES)
                .build()

            return Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        fun formatNumber(value: Number): String {
            val format = NumberFormat.getCurrencyInstance(Locale.US)
            return format.format(value)
        }

        fun convertToHourMinute(value: Int): String {
            val hours = value / 60
            val minutes = value % 60
            val formatHour = if (hours > 0) hours.toString() + "h " else ""
            val formatMinute = if (minutes > 0) minutes.toString() + "m" else ""

            return formatHour + formatMinute
        }

        fun convertToDate(value: String): Date {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            return sdf.parse(value)
        }

        fun getYearOfDate(value: Date): Int {
            val cal = Calendar.getInstance()
            cal.time = value
            return cal.get(Calendar.YEAR)
        }
    }
}