package pl.brosbit.model

import net.liftweb.mapper._

class Hosts extends LongKeyedMapper[Hosts] with IdPK {
	def getSingleton = Hosts
	object hostName extends MappedString(this, 50)
	object group extends MappedLongForeignKey(this, Groups)
}

object Hosts extends Hosts with LongKeyedMetaMapper[Hosts]