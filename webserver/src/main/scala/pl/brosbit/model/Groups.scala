package pl.brosbit.model

import net.liftweb.mapper._

class Groups extends LongKeyedMapper[Groups] with IdPK {
	def getSingleton = Groups
	object name extends MappedString(this, 40) {
	  override def dbIndexed_? = true
	  override def dbNotNull_? = true
	}
	object requiredPackages extends MappedText(this)
	object unwantedPackages extends MappedText(this)
}

object Groups extends Groups with LongKeyedMetaMapper[Groups]