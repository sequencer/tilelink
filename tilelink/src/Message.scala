package tilelink

import chisel3._
/** TileLink Spec 1.8.1
  * Table 13
  */
object Message {
  private val width = 3.W
  val PutFullData: UInt = 0.U(width)
  val PutPartialData: UInt = 1.U(width)
  val ArithmeticData: UInt = 2.U(width)
  val LogicalData: UInt = 3.U(width)
  val Get: UInt = 4.U(width)
  val Intent: UInt = 5.U(width)
  val AcquireBlock: UInt = 6.U(width)
  val AcquirePerm: UInt = 7.U(width)
  val ProbePerm: UInt = 7.U(width)
  val AccessAck: UInt = 0.U(width)
  val AccessAckData: UInt = 1.U(width)
  val HintAck: UInt = 2.U(width)
  val ProbeAck: UInt = 4.U(width)
  val ProbeAckData: UInt = 5.U(width)
  val Release: UInt = 6.U(width)
  val ReleaseData: UInt = 7.U(width)
  val Grant: UInt = 4.U(width)
  val GrantData: UInt = 5.U(width)
  val ProbeBlock: UInt = 6.U(width)
  val ReleaseAck: UInt = 6.U(width)
  // GrantAck has no Opcode
}
