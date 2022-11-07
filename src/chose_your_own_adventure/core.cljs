(ns chose-your-own-adventure.core
  (:require [chose-your-own-adventure.voyage.data :as data]
            [goog.dom :as gdom]
            [reagent.dom :as rdom]
            [reagent.core :as r]))

(def app-state (r/atom {:game data/game
                        :current :start
                        :help false}))

(declare next-scene)

(defn title []
  (if (get @app-state :help)
    [:h2 "Available actions"]
    [:h2 (get-in @app-state [:game (get @app-state :current) :title])]))

(defn dialog []
  (if (get @app-state :help)
    [:div {:class "dialog"}
     [:p "Yes: affirmative answer."]
     [:p "No: negative answer."]
     [:p "Continue: continue the adventure."]
     [:p "Reset: start a fresh game."]
     [:p "Help: display this message."]]
    [:p (get-in @app-state [:game (get @app-state :current) :dialog])]))

(defn answer []
  (if (get @app-state :help)
    [:div
     [:button {:class "button-single" :on-click #(next-scene "continue")} "Continue"]]
    (condp = (get-in @app-state [:game (get @app-state :current) :type])
      :skip [:div
             [:button {:class "button-single" :on-click #(next-scene "on-continue")} "Continue"]]
      :win [:div]
      :lose [:div]
      [:div
       [:button {:class "button" :on-click #(next-scene "yes")} "Yes"]
       [:button {:class "button" :on-click #(next-scene "no")} "No"]])))

(defn app []
  [:div {:class "app"}
   [:h1 "The Voyage"]
   [:div {:class "input"}
    [answer]
    [:div
     [:button {:class "button" :on-click #(next-scene "reset")} "Reset"]
     [:button {:class "button" :on-click #(next-scene "help")} "Help"]]]
   [:div
    [title]
    [dialog]]])

(defn next-scene [answer]
  (condp = answer
    "yes" (swap! app-state update :current
                 (constantly (get-in @app-state [:game (get @app-state :current) :transitions "yes"])))
    "no" (swap! app-state update :current
                (constantly (get-in @app-state [:game (get @app-state :current) :transitions "no"])))
    "on-continue" (swap! app-state update :current
                         (constantly (get-in @app-state [:game (get @app-state :current) :on-continue])))
    "reset" (swap! app-state update :current (constantly :start))
    "help" (swap! app-state update :help (constantly true))
    "continue" (swap! app-state update :help (constantly false))))

(defn mount-app-element []
  (rdom/render [app] (gdom/getElement "app")))

(mount-app-element)

(defn ^:after-load on-reload []
  (mount-app-element))
