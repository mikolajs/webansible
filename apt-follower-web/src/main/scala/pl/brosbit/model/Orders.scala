package pl.brosbit.model

import net.liftweb.mapper._

class Orders extends LongKeyedMapper[Orders] with IdPK {
	def getSingleton = Orders
	object host extends MappedLongForeignKey(this, Hosts)
	object orders extends MappedText(this)
}

object Orders extends Orders with LongKeyedMetaMapper[Orders]