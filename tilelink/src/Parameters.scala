package tilelink

import chisel3._

case class TLBundleParameter(a: TLChannelAParameter, b: Option[TLChannelBParameter], c: Option[TLChannelCParameter], d: TLChannelDParameter, e: Option[TLChannelEParameter]) {
  require(
    (b.isDefined && c.isDefined && e.isDefined) || (b.isEmpty && c.isEmpty && e.isEmpty),
    "only AD/ABCDE channels is allowed."
  )

  def bundle(): TLBundle = new TLBundle(copy())

  def isTLC = b.isDefined
}

case class TLChannelAParameter(addressWidth: Int, sourceWidth: Int, dataWidth: Int, sizeWidth: Int, maskWidth: Int) {
  def bundle(): TLChannelA = new TLChannelA(copy())

  private def assign(opcode: UInt, param: UInt, size: UInt, source: UInt, address: UInt, mask: UInt, data: UInt, corrupt: Bool): TLChannelA = {
    val a = Wire(bundle())
    a.opcode := opcode
    a.param := param
    a.size := size
    a.source := source
    a.address := address
    a.mask := mask
    a.data := data
    a.corrupt := corrupt
    a
  }

  // Construct transactions
  def Get(size: UInt, source: UInt, address: UInt, mask: UInt): TLChannelA = ???

  def PutFullData(size: UInt, source: UInt, address: UInt, mask: UInt, data: UInt, corrupt: Bool): TLChannelA = ???

  def PutPartialData(size: UInt, source: UInt, address: UInt, mask: UInt, data: UInt, corrupt: Bool): TLChannelA = ???

  def ArithmeticData(param: UInt, size: UInt, source: UInt, address: UInt, mask: UInt, data: UInt, corrupt: Bool): TLChannelA = ???

  def LogicalData(param: UInt, size: UInt, source: UInt, address: UInt, mask: UInt, data: UInt, corrupt: Bool): TLChannelA = ???

  def Intent(param: UInt, size: UInt, source: UInt, address: UInt, mask: UInt, data: UInt, corrupt: Bool): TLChannelA = ???

  def AcquireBlock(param: UInt, size: UInt, source: UInt, address: UInt, mask: UInt): TLChannelA = ???

  def AcquirePerm(param: UInt, size: UInt, source: UInt, address: UInt, mask: UInt): TLChannelA = ???
}

case class TLChannelBParameter(addressWidth: Int, sourceWidth: Int, dataWidth: Int, sizeWidth: Int, maskWidth: Int) {
  def bundle(): TLChannelB = new TLChannelB(copy())

  private def assign(opcode: UInt, param: UInt, size: UInt, source: UInt, address: UInt, mask: UInt, data: UInt, corrupt: Bool): TLChannelB = {
    val b = Wire(bundle())
    b.opcode := opcode
    b.param := param
    b.size := size
    b.source := source
    b.address := address
    b.mask := mask
    b.data := data
    b.corrupt := corrupt
    b
  }

  // Construct transactions
  def Get(size: UInt, source: UInt, address: UInt, mask: UInt): TLChannelB = ???

  def PutFullData(size: UInt, source: UInt, address: UInt, mask: UInt, data: UInt, corrupt: Bool): TLChannelB = ???

  def PutPartialData(size: UInt, source: UInt, address: UInt, mask: UInt, data: UInt, corrupt: Bool): TLChannelB = ???

  def ArithmeticData(param: UInt, size: UInt, source: UInt, address: UInt, mask: UInt, data: UInt, corrupt: Bool): TLChannelB = ???

  def LogicalData(param: UInt, size: UInt, source: UInt, address: UInt, mask: UInt, data: UInt, corrupt: Bool): TLChannelB = ???

  def Intent(param: UInt, size: UInt, source: UInt, address: UInt, mask: UInt, data: UInt, corrupt: Bool): TLChannelB = ???

  def ProbeBlock(param: UInt, size: UInt, source: UInt, address: UInt, mask: UInt): TLChannelB = ???
}

case class TLChannelCParameter(addressWidth: Int, sourceWidth: Int, sinkWidth: Int, dataWidth: Int, sizeWidth: Int) {
  def bundle(): TLChannelC = new TLChannelC(copy())

  private def assign(opcode: UInt, param: UInt, size: UInt, source: UInt, address: UInt, data: UInt, corrupt: Bool): TLChannelC = {
    val c = Wire(bundle())
    c.opcode := opcode
    c.param := param
    c.size := size
    c.source := source
    c.address := address
    c.data := data
    c.corrupt := corrupt
    c
  }

  // Construct transactions
  def ProbeAck(param: UInt, size: UInt, source: UInt, address: UInt): TLChannelC = ???

  def ProbeAckData(param: UInt, size: UInt, source: UInt, address: UInt, data: UInt, corrupt: Bool): TLChannelC = ???

  def Release(param: UInt, size: UInt, source: UInt, address: UInt): TLChannelC = ???

  def ReleaseData(param: UInt, size: UInt, source: UInt, address: UInt, data: UInt, corrupt: Bool): TLChannelC = ???

  def AccessAck(size: UInt, source: UInt, address: UInt): TLChannelC = ???

  def AccessAckData(size: UInt, source: UInt, address: UInt, data: UInt, corrupt: Bool): TLChannelC = ???

  def HintAck(size: UInt, source: UInt, address: UInt): TLChannelC = ???
}

case class TLChannelDParameter(sourceWidth: Int, sinkWidth: Int, dataWidth: Int, sizeWidth: Int) {
  def bundle(): TLChannelD = new TLChannelD(copy())

  private def assign(opcode: UInt, param: UInt, size: UInt, source: UInt, sink: UInt, denied: Bool, data: UInt, corrupt: Bool): TLChannelD = {
    val d = Wire(bundle())
    d.opcode := opcode
    d.param := param
    d.size := size
    d.source := source
    d.sink := sink
    d.denied := denied
    d.data := data
    d.corrupt := corrupt
    d
  }

  // Construct transactions
  def Grant(param: UInt, size: UInt, source: UInt, sink: UInt, denied: Bool): TLChannelD = ???

  def GrantData(param: UInt, size: UInt, source: UInt, sink: UInt, denied: Bool, data: UInt, corrupt: Bool): TLChannelD = ???

  def ReleaseAck(size: UInt, source: UInt): TLChannelD = ???

  def AccessAck(size: UInt, source: UInt, denied: Bool): TLChannelD = ???

  def AccessAckData(size: UInt, source: UInt, denied: Bool, data: UInt, corrupt: Bool): TLChannelD = ???

  def HintAck(size: UInt, source: UInt, denied: Bool): TLChannelD = ???
}

case class TLChannelEParameter(sinkWidth: Int) {
  def bundle(): TLChannelE = new TLChannelE(copy())

  private def assign(sink: UInt): TLChannelE = {
    val e = Wire(bundle())
    e.sink := sink
    e
  }

  // Construct transactions
  def GrantAck(sink: UInt): TLChannelE = ???
}
