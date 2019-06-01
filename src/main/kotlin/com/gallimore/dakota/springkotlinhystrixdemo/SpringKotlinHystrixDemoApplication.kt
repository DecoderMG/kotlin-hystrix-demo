package com.gallimore.dakota.springkotlinhystrixdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard

@SpringBootApplication
@EnableHystrixDashboard
@EnableCircuitBreaker
class SpringKotlinHystrixDemoApplication

fun main(args: Array<String>) {
	runApplication<SpringKotlinHystrixDemoApplication>(*args)
}
