package com.oslid.systools

import org.apache.camel.CamelContext
import org.apache.camel.main.Main
import org.apache.camel.scala.dsl.builder.RouteBuilderSupport


/**
 * A Main to run Camel with MyRouteBuilder
 */
object MainQueue extends RouteBuilderSupport  {

  def main(args: Array[String]) {

    val params = Map[String, String](
      "username"  -> Option(System.getenv("SMTP_USER")).getOrElse("a2zlangmgr@gmail.com"),
      "password"  -> Option(System.getenv("SMTP_PASS")).getOrElse("password"),
      "host"      -> Option(System.getenv("SMTP_HOST")).getOrElse("smtp.gmail.com:465"),
      "rcpt"      -> Option(System.getenv("SMTP_RCPTS")).getOrElse("garrickajo@gmail.com, ronniedz1s@yahoo.com"),

      "sourceFolder"      -> Option(if (args != null && args.length > 0) args(0) else null).getOrElse("data/inbox/")
    )


    val main = new Main()
    val context: CamelContext = main.getOrCreateCamelContext()
    main.addRouteBuilder(new PostinoRoute(context, params ))
    main.run()
  }

}

