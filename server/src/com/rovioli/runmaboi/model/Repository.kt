package com.rovioli.runmaboi.model

class Repository(private val helper: RequestAndScoreDao) {

    fun putScore(score: Score) = helper.insertScore(score)

    fun lastScores(last: Int = 5) = helper.readHighScores(last)

    fun register(apiKey: String) = helper.insertApiKey(apiKey)

    fun isRegistered(apiKey: String) = helper.findApiKey(apiKey).isNotEmpty()

    fun getAlien() = """
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
            """
}