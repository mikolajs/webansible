package pl.brosbit

import spray.routing._
import spray.http._
import spray.routing.directives.CachingDirectives._
import MediaTypes._
import spray.json._
import DefaultJsonProtocol._

trait JsonAnswer extends HttpService {
    val jsonRoute =
        path("api" / "getgroups") {
            get {
                respondWithMediaType(`application/json`) {
                    complete {
                        val jsonText = """ [{name:"Klasa",id:23}, {name:"Sala",id:22},{name:"Hala",id:34}]"""
                        jsonText
                    }
                }
            }
        } ~
            path("api" / "setgroup") {
                get {
                    parameters("id", "name") { (idGroup, name) =>
                        {
                            respondWithMediaType(`application/json`) {
                                complete {
                                    val jsonText = """{id:%s,name:"group"}""".format(idGroup)
                                    jsonText
                                }
                            }
                        }
                    }
                }
            } ~
            path("api" / "delgroup") {
                get {
                    parameters("id") { idGroup =>
                        {
                            respondWithMediaType(`application/json`) {
                                complete {
                                    val jsonText = """[{id:%s,name:"PC2", ip:"10.0.0.1", firm:"Nie", ping:"23.12.2014 14:30"},
		                                 {id:224,name:"PC4", ip:"10.0.0.2", firm:"Nie", ping:"23.12.2014 14:30"}, 
		                                 {id:134,name:"PC3", ip:"10.0.0.3", firm:"Nie", ping:"23.12.2014 14:30"}]""".format(idGroup)
                                    jsonText
                                }
                            }
                        }
                    }
                }
            } ~
            path("api" / "gethosts") {
                get {
                    parameters("id") { idGroup =>
                        {
                            respondWithMediaType(`application/json`) {
                                complete {
                                    val jsonText = """[{id:%s,name:"PC2", ip:"10.0.0.1", firm:"Nie", ping:"23.12.2014 14:30"},
		                                 {id:224,name:"PC4", ip:"10.0.0.2", firm:"Nie", ping:"23.12.2014 14:30"}, 
		                                 {id:134,name:"PC3", ip:"10.0.0.3", firm:"Nie", ping:"23.12.2014 14:30"}]""".format(idGroup)
                                    jsonText
                                }
                            }
                        }
                    }
                }
            } ~
            path("api" / "sethost") {
                get {
                    parameters("id") { idGroup =>
                        {
                            respondWithMediaType(`application/json`) {
                                complete {
                                    val jsonText = """[{id:%s,name:"PC2", ip:"10.0.0.1", firm:"Nie", ping:"23.12.2014 14:30"},
		                                 {id:224,name:"PC4", ip:"10.0.0.2", firm:"Nie", ping:"23.12.2014 14:30"}, 
		                                 {id:134,name:"PC3", ip:"10.0.0.3", firm:"Nie", ping:"23.12.2014 14:30"}]""".format(idGroup)
                                    jsonText
                                }
                            }
                        }
                    }
                }
            } ~
            path("api" / "delhost") {
                get {
                    parameters("id") { idGroup =>
                        {
                            respondWithMediaType(`application/json`) {
                                complete {
                                    val jsonText = """[{id:%s,name:"PC2", ip:"10.0.0.1", firm:"Nie", ping:"23.12.2014 14:30"},
		                                 {id:224,name:"PC4", ip:"10.0.0.2", firm:"Nie", ping:"23.12.2014 14:30"}, 
		                                 {id:134,name:"PC3", ip:"10.0.0.3", firm:"Nie", ping:"23.12.2014 14:30"}]""".format(idGroup)
                                    jsonText
                                }
                            }
                        }
                    }
                }
            } ~
            path("api" / "getcommands") {
                get {
                    parameters("id") { idGroup =>
                        {
                            respondWithMediaType(`application/json`) {
                                complete {
                                    val jsonText = """[{id:%s,name:"PC2", ip:"10.0.0.1", firm:"Nie", ping:"23.12.2014 14:30"},
		                                 {id:224,name:"PC4", ip:"10.0.0.2", firm:"Nie", ping:"23.12.2014 14:30"}, 
		                                 {id:134,name:"PC3", ip:"10.0.0.3", firm:"Nie", ping:"23.12.2014 14:30"}]""".format(idGroup)
                                    jsonText
                                }
                            }
                        }
                    }
                }
            } ~
            path("api" / "setcommand") {
                get {
                    parameters("id") { idGroup =>
                        {
                            respondWithMediaType(`application/json`) {
                                complete {
                                    val jsonText = """[{id:%s,name:"PC2", ip:"10.0.0.1", firm:"Nie", ping:"23.12.2014 14:30"},
		                                 {id:224,name:"PC4", ip:"10.0.0.2", firm:"Nie", ping:"23.12.2014 14:30"}, 
		                                 {id:134,name:"PC3", ip:"10.0.0.3", firm:"Nie", ping:"23.12.2014 14:30"}]""".format(idGroup)
                                    jsonText
                                }
                            }
                        }
                    }
                }
            } ~
            path("api" / "delcommand") {
                get {
                    parameters("id") { idGroup =>
                        {
                            respondWithMediaType(`application/json`) {
                                complete {
                                    val jsonText = """[{id:%s,name:"PC2", ip:"10.0.0.1", firm:"Nie", ping:"23.12.2014 14:30"},
		                                 {id:224,name:"PC4", ip:"10.0.0.2", firm:"Nie", ping:"23.12.2014 14:30"}, 
		                                 {id:134,name:"PC3", ip:"10.0.0.3", firm:"Nie", ping:"23.12.2014 14:30"}]""".format(idGroup)
                                    jsonText
                                }
                            }
                        }
                    }
                }
            } ~
            path("api" / "runcommand") {
                get {
                    parameters("id",  "user", "pass") { (idGroup, user, pass) =>
                        {
                            respondWithMediaType(`application/json`) {
                                complete {
                                    val jsonText = """[{id:%s,name:"PC2", ip:"10.0.0.1", firm:"Nie", ping:"23.12.2014 14:30"},
		                                 {id:224,name:"PC4", ip:"10.0.0.2", firm:"Nie", ping:"23.12.2014 14:30"}, 
		                                 {id:134,name:"PC3", ip:"10.0.0.3", firm:"Nie", ping:"23.12.2014 14:30"}]""".format(idGroup)
                                    jsonText
                                }
                            }
                        }
                    }
                }
            } ~
            path("api" / "getcommandhistory") {
                get {
                    parameters("id") { idGroup =>
                        {
                            respondWithMediaType(`application/json`) {
                                complete {
                                    val jsonText = """[{id:%s,name:"PC2", ip:"10.0.0.1", firm:"Nie", ping:"23.12.2014 14:30"},
		                                 {id:224,name:"PC4", ip:"10.0.0.2", firm:"Nie", ping:"23.12.2014 14:30"}, 
		                                 {id:134,name:"PC3", ip:"10.0.0.3", firm:"Nie", ping:"23.12.2014 14:30"}]""".format(idGroup)
                                    jsonText
                                }
                            }
                        }
                    }
                }
            }

}
            