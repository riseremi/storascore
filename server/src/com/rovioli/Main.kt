package com.rovioli

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.*
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun Application.main() {
    // This feature sets a Date and Server headers automatically.
    install(DefaultHeaders)
    // This feature enables compression automatically when accepted by the client.
    install(Compression)
    // Logs all the requests performed
    install(CallLogging)
    // Automatic '304 Not Modified' Responses
    install(ConditionalHeaders)
    // For each GET header, adds an automatic HEAD handler (checks the headers of the requests
    // without actually getting the payload to be more efficient about resources)
    install(AutoHeadResponse)
    routing {
        post("/save") {
            // val result = call.receive<Record>()
            println(call.receiveText())
            call.respond("Got this, ma boooi!")
        }
        get("/alien") {
            call.respondText {
                """
                        ;;;;;;iiiii;;
                 i!!!!!!!!!!!!!!!~{:!!!!i
             i!~!!))!!!!!!!!!!!!!!!!!!!!!!!!
          i!!!{!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!i
       i!!)!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    '!h!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  '!!`!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!i
   /!!!~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
' ':)!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  ~:!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
..!!!!!\!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 `!!!!!!!!!!!!!!!!!!!!!$!!!!!$!!!!$$$!!!!$!!!!!!!!!!!!!
 ~ ~!!!)!!!!!$!$$$!!$$$!$!!$$!$!!$!!$$$$$!$!!!!!!!!!!!~
~~'~{!!!!!!!!!$$!!$$!!!!!$$!!!!$$!!!!!!!!!!$!!!!!!!!:'~
{-{)!!{!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:!
`!!!!{!~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!':!!!
' {!!!{>)`!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!)!~..
:!{!!!{!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -!!:
    ~:!4~/!!!!!!!!!!!!!!!!!!!~!!!!!!!!!!!!!!!!!!!!!!!!!!
     :~!!~)(!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      ``~!!).~!!!!!!!!!!!!!{!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:
            ~  '!\!!!!!!!!!!(!!!!!!!!!!!!!!!!!!!!!!4!!!~:
           '      '--`!!!!!!!!/:\!!{!!((!~.~!!`?~-      :
              ``-.    `~!{!`)(>~/ \~                   :
   .                \  : `{{`. {-   .-~`              /
    .          !:       .\\?.{\   :`      .          :!
    \ :         `      -~!{:!!!\ ~     :!`         .>!
    '  ~          '    '{!!!{!!!t                 ! !!
     '!  !.            {!!!!!!!!!              .~ {~!
      ~!!..`~:.       {!!!!!!!!!!:          .{~ :LS{
       `!!!!!!h:!?!!!!!!!!!!!!!(!!!!::..-~~` {!!!!.
         4!!!!!!!!!!!!!!!!!!!!!~!{!~!!!!!!!!!!!!'
          `!!!!!!!!!!!!!!!!!!!!(~!!!!!!!!!!!!!~
            `!!!!!!!!!!!{\``!!``(!!!!!!!!!~~  .
             `!!!!!!!!!!!!!!!!!!!!!!!!(!:
               .!!!!!!!!!!!!!!!!!!!!!\~
               .`!!!!!!!/`.;;~;;`~!! '
                 -~!!!!!!!!!!!!!(!!/ .
                    `!!!!!!!!!!!!!!'
                      `\!!!!!!!!!~
                       WAKE UP NEO
            """
            }
        }
    }
}
