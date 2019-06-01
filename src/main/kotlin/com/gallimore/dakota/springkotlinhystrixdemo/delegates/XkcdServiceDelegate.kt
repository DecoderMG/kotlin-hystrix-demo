package com.gallimore.dakota.springkotlinhystrixdemo.delegates

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.web.client.RestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import java.net.URL
import java.util.*

/**
 * Delegate Service to fetch Xkcd posts from Xkcd
 */
@Service
class XkcdServiceDelegate {

    // If connection to Xkcd is interrupted, call fallback method of callXkcdServiceAndGetData_Fallback
    @HystrixCommand(fallbackMethod = "callXkcdServiceAndGetData_Fallback")
    fun callXkcdServiceAndGetData(comicId: String): String {

        var url: URL = URL("https://xkcd.com/info.0.json")
        if (comicId == "") {
            println("Fetching daily xkcd comic")
        } else {
            println("Fetching xkcd comic with id: $comicId")
            url = URL("https://xkcd.com/$comicId/info.0.json")
        }
        var xkcdResponse: String = url.readText()

        println(xkcdResponse)
        println("Response from xkcd received on " + Date() + " with contents: " + xkcdResponse)

        return "Normal data flow occurring at " + Date() +  "  - comic $comicId from xkcd is :::" + xkcdResponse
    }

    /**
     * Fallback method should main method be interrupted
     */
    @SuppressWarnings("unused")
    private fun callXkcdServiceAndGetData_Fallback(comicId: String): String {
        println("Xkcd is down!! PANIC!! How are we to get good comics!?!? Fallback route enabled...")

        return "${Date()} Circuit breaker enabled, we didn't hear back from the xkcd server, they must be down at the moment"
    }

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}