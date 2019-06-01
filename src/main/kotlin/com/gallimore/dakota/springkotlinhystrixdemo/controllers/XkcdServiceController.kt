package com.gallimore.dakota.springkotlinhystrixdemo.controllers

import com.gallimore.dakota.springkotlinhystrixdemo.delegates.XkcdServiceDelegate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Rest Controller for routing to specified delegates on REST endpoint trigger
 */
@RestController
class XkcdServiceController {

    // Autowire Delegate service when it's available
    @Autowired
    lateinit var xkcdServiceDelegate: XkcdServiceDelegate

    // Fetch comic of a specifi cxkcd id
    @RequestMapping(value = "/getXkcdComic/{comicId}", method = [RequestMethod.GET])
    fun getXkcdComic(@PathVariable comicId: String): String {
        println("Fetching data from Xkcd delegate service")
        return xkcdServiceDelegate.callXkcdServiceAndGetData(comicId)
    }

    // Fetch Daily comic if no id is provided.
    @RequestMapping(value = "/getXkcdComic/", method = [RequestMethod.GET])
    fun getDailyXkcdComic(): String {
        println("Fetching data from Xkcd delegate service")
        return xkcdServiceDelegate.callXkcdServiceAndGetData("")
    }

}