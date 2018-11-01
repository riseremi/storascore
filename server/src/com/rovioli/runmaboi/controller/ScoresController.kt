package com.rovioli.runmaboi.controller

import com.google.gson.Gson
import com.rovioli.runmaboi.model.Request
import com.rovioli.runmaboi.model.ScoreDao
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import io.ktor.http.HttpStatusCode.Companion.UnprocessableEntity
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing

class ScoresController(
        private val scoreDao: ScoreDao,
        private val gson: Gson
) : KtorController {
    private val highscoresAmount = 5

    override fun attach(application: Application) = application.routing {
        post("/save") {
            // TODO: can we use call.receive<Request>() instead?
            val record = gson.fromJson<Request>(call.receiveText(), Request::class.java)
            println("recorod is $record")
            val code = validate(record)
            if (code == Accepted) {
                scoreDao.insert(record.score)
                call.respond(code, "Adding!\n")
            } else {
                call.respond(code, "Error!\n")
            }
        }

        get("/alien") {
            call.respondText("""
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
' {!!!{>)`!!!!!!!I AM AN ALIEN!!!!!!!!!!!!!!!!!!!!!!!)!~..
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
            """)
        }

        get("/highscores") {
            call.respondText(gson.toJson(scoreDao.findAll(highscoresAmount)))
        }
    }

    private fun validate(request: Request): HttpStatusCode = with(request) {
        if (apiKey == null || score.name.isEmpty()) return UnprocessableEntity
        if (apiKey.isEmpty()) return Unauthorized
        // if (!keyRegistered(apiKey)) return Forbidden
        return Accepted
    }
}