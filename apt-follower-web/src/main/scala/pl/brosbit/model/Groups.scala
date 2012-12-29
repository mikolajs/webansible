package pl.brosbit.model

import net.liftweb.mapper._

class Groups extends LongKeyedMapper[Groups] with IdPK {
	def getSingleton = Groups
	object name extends MappedString(this, 40)
}

object Groups extends Groups with LongKeyedMetaMapper[Groups]