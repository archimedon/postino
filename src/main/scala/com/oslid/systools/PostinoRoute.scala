package com.oslid.systools

import org.apache.camel.{CamelContext, Exchange}
import org.apache.camel.scala.dsl.builder.ScalaRouteBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory

//import org.apache.logging.log4j.scala.Logging

/**
  * Route from source directory to smtp
  */
class PostinoRoute(override val context: CamelContext, params: Map[String, String]) extends ScalaRouteBuilder(context) {

  protected val log: Logger = LoggerFactory.getLogger (classOf[PostinoRoute] )

  private val subject = "OSLID - Web Mail"

  private val username = params.get("username").get
  private val password = params.get("password").get
  private val host = params.get("host").get
  private val rcpt = params.get("rcpt").get

  val opts = "?include=.*.txt&delay=1000&delete=true"
  private val sourceFolder =  s"""file:${params.get("sourceFolder").get}$opts"""
  private val destinationFolder =  s"""file:${params.get("destinationFolder").getOrElse("data/outbox")}"""


  val smtp = s"smtps://$host?username=$username&password=$password&to=$rcpt"

  params.map(p => if (p._1.equals("password")) ("password","XXXXX") else p).foreach(p => log.info(p.toString))
  log.info(s"""(destinationFolder, $destinationFolder)""")

  sourceFolder ==>
    setHeader("subject", constant(subject)) --> destinationFolder --> smtp

}
