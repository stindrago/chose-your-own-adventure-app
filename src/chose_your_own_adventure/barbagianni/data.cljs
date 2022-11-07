(ns chose-your-own-adventure.barbagianni.data)

(def game {:start {:type :start
                   :title "La Fame"
                   :dialog (str "In una casa vicino ad un bosco ci abita un piccolo Barbagianni."
                                " Un giorno Barbagianni senti' un brontolio alla pancia e"
                                " decise di andare a controllare il frigo per vedere se fosse rimasto del cibo."
                                " Aperto il frigo non trovo' nulla, forse il fratellino Baby-Barbagianni"
                                " ha mangiato tutto quello che c'era all'interno."
                                " (Vuoi andare a comprare il cibo?)")
                   :transitions {"yes" :compro-il-cibo
                                 "no" :soffri-la-fame}}
           :compro-il-cibo {:type :skip
                            :title "A Fare La Spesa"
                            :dialog (str "\"Hmm... Non so cosa comprare tra queste due cose\", disse Barbagianni indeciso."
                                         " In fine ha deciso prende una buonissima pizza che mangera stasera.")
                            :on-continue :in-viaggio-a-scuola}
           :soffri-la-fame {:type :skip
                            :title "La Grande Fame"
                            :dialog (str "\"Non mi va di andare al centro commerciale per comprare il cibo"
                                         " anche se ho una grande fame\", disse Barbagianni.")
                            :on-continue :in-viaggio-a-scuola}
           :in-viaggio-a-scuola {:type :continue
                                 :title "In Viaggio a Scuola"
                                 :dialog (str "Il giorno dopo dopo Barbagianni va a scuola"
                                 " ma deve scegliere se arrivare più in fretta attraversando la strada subito"
                                 " oppure fare altri 50 metri ed attraversare sulle strisce pedonali."
                                 " (Vuoi attraversare sulle strisce pedonali?)")
                                 :transitions {"yes" :attraversa-sulle-strisce
                                               "no" :attraversa-sulla-strada}}
           :attraversa-sulle-strisce {:type :skip
                                      :title "Sulle Srisce"
                                      :dialog (str "Barbagianni attraversa le strisce,"
                                                   " e dopo 50 metri vide la scuola lontanissima."
                                                   " \"Povero me\", disse Barbagianni.")
                                      :on-continue :in-ritardo-a-scuola}
           :attraversa-sulla-strada {:type :lose
                                     :title "Game Over"
                                     :dialog (str "\"Bene, ora posso finalmente attraversare non vedo nessuna macchina... Go\", disse Barbagianni."
                                                  " Ma Barbagianni non ha notato che dalla destra sta passando un camion e subito si senti' un bel buum!"
                                                  " Un Barbagianni morto.")}
           :in-ritardo-a-scuola {:type :win
                                 :title "Hai Vinto"
                                 :dialog (str "Barbagianni pensa che sia meglio partire non stare fermi imbambolati."
                                              " Finalmente arrivò all'entrata della scuola ma vide che i bambocci stavano gia uscendo."
                                              " La maestra quando vide Barbagianni gli diede ben 5 note."
                                              " \"Però, dai, facciamolo almeno mangiare\", disse la maestra."
                                              " La maestra e Barbagianni si sedettero e tirarono fuori la merenda,"
                                              " la maestra una bella barretta energetica e invece Barbagianni solo l'aria,"
                                              " quindi la maestra lo bocciò."
                                              " Dopo... arrivo' un orso e si mangiò entrambi."
                                              " Se pensate che la storia continui, vi sbagliate!"
                                              " (With ❤️ by Isabelle).")}})
