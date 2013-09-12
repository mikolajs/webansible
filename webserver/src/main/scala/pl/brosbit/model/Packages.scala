package pl.brosbit.model

import net.liftweb.mapper._

class Packages extends LongKeyedMapper[Packages] with IdPK {
	def getSingleton = Packages
	object host extends MappedLongForeignKey(this, Hosts)
	object packages extends MappedText(this)
}

object Packages extends Packages with LongKeyedMetaMapper[Packages]