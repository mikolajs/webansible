package pl.brosbit.model

import net.liftweb.mapper._

class Hosts extends LongKeyedMapper[Hosts] with IdPK {
	def getSingleton = Hosts
	object hostName extends MappedString(this, 50) {
	  override def dbIndexed_? = true
	  override def dbNotNull_? = true
	}
	object group extends MappedLongForeignKey(this, Hosts) 
	object installs extends MappedText(this) {
	  override def defaultValue = ""
	}
	object deletes extends MappedText(this) {
	  override def defaultValue = ""
	}
	object upgradeRequire extends MappedBoolean(this) {
	  override def defaultValue = true
	}
	object packages extends MappedText(this) {
	  override def defaultValue = ""
	}
	object lastUpgrade extends MappedDate(this)
	object lastConnect extends MappedDate(this)
}

object Hosts extends Hosts with LongKeyedMetaMapper[Hosts] {
    //override def dbIndexes = UniqueIndex(hostName) :: super.dbIndexes
}