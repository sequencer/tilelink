package tilelink

case class TLBundleParameter(a: TLChannelAParameter, b: Option[TLChannelBParameter], c: Option[TLChannelCParameter], d: TLChannelDParameter, e: Option[TLChannelEParameter]) {
  require(
    (b.isDefined && c.isDefined && e.isDefined) || (b.isEmpty && c.isEmpty && e.isEmpty),
    "only AD/ABCDE channels is allowed."
  )
  def isTLC = b.isDefined
}
case class TLChannelAParameter(addressWidth: Int, sourceWidth: Int, dataWidth: Int, sizeWidth: Int, maskWidth: Int)
case class TLChannelBParameter(addressWidth: Int, sourceWidth: Int, dataWidth: Int, sizeWidth: Int, maskWidth: Int)
case class TLChannelCParameter(addressWidth: Int, sourceWidth: Int, sinkWidth: Int, dataWidth: Int, sizeWidth: Int)
case class TLChannelDParameter(sourceWidth: Int, dataWidth: Int, sizeWidth: Int)
case class TLChannelEParameter(sinkWidth: Int)
