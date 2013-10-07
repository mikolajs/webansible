package pl.brosbit

import akka.actor.{Actor, ActorLogging}

import java.io.{File, BufferedWriter, FileWriter}


case class HostInfo(host:String, ip:String)


class DBInsertActor extends Actor with ActorLogging {

  
 
  
  def receive = {
    case HostInfo(host, ip) =>
       
  }
 
 }