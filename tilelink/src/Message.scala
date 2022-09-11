package tilelink

import chisel3._
/** TileLink Spec 1.8.1
  * Table 13
  */
object Message {
  val PutFullData: UInt = 0.U(3.W)
  val PutPartialData: UInt = 1.U(3.W)
  val ArithmeticData: UInt = 2.U(3.W)
  val LogicalData: UInt = 3.U(3.W)
  val Get: UInt = 4.U(3.W)
  val Intent: UInt = 5.U(3.W)
  val AcquireBlock: UInt = 6.U(3.W)
  val AcquirePerm: UInt = 7.U(3.W)
  val ProbePerm: UInt = 7.U(3.W)
  val AccessAck: UInt = 0.U(3.W)
  val AccessAckData: UInt = 1.U(3.W)
  val HintAck: UInt = 2.U(3.W)
  val ProbeAck: UInt = 4.U(3.W)
  val ProbeAckData: UInt = 5.U(3.W)
  val Release: UInt = 6.U(3.W)
  val ReleaseData: UInt = 7.U(3.W)
  val Grant: UInt = 4.U(3.W)
  val GrantData: UInt = 5.U(3.W)
  val ProbeBlock: UInt = 6.U(3.W)
  val ReleaseAck: UInt = 6.U(3.W)
  // GrantAck has no Opcode
}
