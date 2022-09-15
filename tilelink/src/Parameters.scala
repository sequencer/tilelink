package tilelink

import chisel3._

case class TLBundleParameter(
  a: TLChannelAParameter,
  b: Option[TLChannelBParameter],
  c: Option[TLChannelCParameter],
  d: TLChannelDParameter,
  e: Option[TLChannelEParameter]) {
  require(
    (b.isDefined && c.isDefined && e.isDefined) || (b.isEmpty && c.isEmpty && e.isEmpty),
    "only AD/ABCDE channels is allowed."
  )

  def bundle(): TLBundle = new TLBundle(copy())

  def isTLC = b.isDefined
}

case class TLChannelAParameter(
  addressWidth: Int,
  sourceWidth:  Int,
  dataWidth:    Int,
  sizeWidth:    Int,
  maskWidth:    Int) {
  def bundle(): TLChannelA = new TLChannelA(copy())

  private def assign(
    opcode:  UInt,
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelA = {
    val a: TLChannelA = Wire(bundle())
    a.opcode  := opcode
    a.param   := param
    a.size    := size
    a.source  := source
    a.address := address
    a.mask    := mask
    a.data    := data
    a.corrupt := corrupt
    a
  }

  // Construct transactions
  def Get(
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt
  ): TLChannelA = assign(
    Message.Get,
    Param.tieZero,
    size,
    source,
    address,
    mask,
    0.U(dataWidth.W),
    false.B
  )

  def PutFullData(
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelA = assign(
    Message.PutFullData,
    Param.tieZero,
    size,
    source,
    address,
    mask,
    data,
    corrupt
  )

  def PutPartialData(
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelA = assign(
    Message.PutPartialData,
    Param.tieZero,
    size,
    source,
    address,
    mask,
    data,
    corrupt
  )

  def ArithmeticData(
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelA = assign(
    Message.ArithmeticData,
    param,
    size,
    source,
    address,
    mask,
    data,
    corrupt
  )

  def LogicalData(
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelA = assign(
    Message.LogicalData,
    param,
    size,
    source,
    address,
    mask,
    data,
    corrupt
  )

  def Intent(
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelA = assign(
    Message.Intent,
    param,
    size,
    source,
    address,
    mask,
    data,
    corrupt
  )

  def AcquireBlock(
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt
  ): TLChannelA = assign(
    Message.AcquireBlock,
    param,
    size,
    source,
    address,
    mask,
    0.U(dataWidth.W),
    false.B
  )

  def AcquirePerm(
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt
  ): TLChannelA = assign(
    Message.AcquirePerm,
    param,
    size,
    source,
    address,
    mask,
    0.U(dataWidth.W),
    false.B
  )
}

case class TLChannelBParameter(
  addressWidth: Int,
  sourceWidth:  Int,
  dataWidth:    Int,
  sizeWidth:    Int,
  maskWidth:    Int) {
  def bundle(): TLChannelB = new TLChannelB(copy())

  private def assign(
    opcode:  UInt,
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelB = {
    val b: TLChannelB = Wire(bundle())
    b.opcode  := opcode
    b.param   := param
    b.size    := size
    b.source  := source
    b.address := address
    b.mask    := mask
    b.data    := data
    b.corrupt := corrupt
    b
  }

  // Construct transactions
  def Get(
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt
  ): TLChannelB = assign(
    Message.Get,
    Param.tieZero,
    size,
    source,
    address,
    mask,
    0.U(dataWidth.W),
    false.B
  )

  def PutFullData(
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelB = assign(
    Message.PutFullData,
    Param.tieZero,
    size,
    source,
    address,
    mask,
    data,
    corrupt
  )

  def PutPartialData(
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelB = assign(
    Message.PutPartialData,
    Param.tieZero,
    size,
    source,
    address,
    mask,
    data,
    corrupt
  )

  def ArithmeticData(
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelB = assign(
    Message.ArithmeticData,
    param,
    size,
    source,
    address,
    mask,
    data,
    corrupt
  )

  def LogicalData(
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelB = assign(
    Message.LogicalData,
    param,
    size,
    source,
    address,
    mask,
    data,
    corrupt
  )

  def Intent(
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelB = assign(
    Message.Intent,
    param,
    size,
    source,
    address,
    mask,
    data,
    corrupt
  )

  def ProbeBlock(
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt,
    mask:    UInt
  ): TLChannelB = assign(
    Message.ProbeBlock,
    param,
    size,
    source,
    address,
    mask,
    0.U(dataWidth.W),
    false.B
  )
}

case class TLChannelCParameter(
  addressWidth: Int,
  sourceWidth:  Int,
  sinkWidth:    Int,
  dataWidth:    Int,
  sizeWidth:    Int) {
  def bundle(): TLChannelC = new TLChannelC(copy())

  private def assign(
    opcode:  UInt,
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelC = {
    val c: TLChannelC = Wire(bundle())
    c.opcode  := opcode
    c.param   := param
    c.size    := size
    c.source  := source
    c.address := address
    c.data    := data
    c.corrupt := corrupt
    c
  }

  // Construct transactions
  def ProbeAck(
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt
  ): TLChannelC = assign(
    Message.ProbeAck,
    param,
    size,
    source,
    address,
    0.U(dataWidth.W),
    false.B
  )

  def ProbeAckData(
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelC = assign(
    Message.ProbeAckData,
    param,
    size,
    source,
    address,
    data,
    corrupt
  )

  def Release(
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt
  ): TLChannelC = assign(
    Message.Release,
    param,
    size,
    source,
    address,
    0.U(dataWidth.W),
    false.B
  )

  def ReleaseData(
    param:   UInt,
    size:    UInt,
    source:  UInt,
    address: UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelC = assign(
    Message.ReleaseData,
    param,
    size,
    source,
    address,
    data,
    corrupt
  )

  def AccessAck(
    size:    UInt,
    source:  UInt,
    address: UInt
  ): TLChannelC = assign(
    Message.AccessAck,
    Param.tieZero,
    size,
    source,
    address,
    0.U(dataWidth.W),
    false.B
  )

  def AccessAckData(
    size:    UInt,
    source:  UInt,
    address: UInt,
    data:    UInt,
    corrupt: Bool
  ): TLChannelC =
    assign(
      Message.AccessAckData,
      Param.tieZero,
      size,
      source,
      address,
      data,
      corrupt
    )

  def HintAck(
    size:    UInt,
    source:  UInt,
    address: UInt
  ): TLChannelC = assign(
    Message.HintAck,
    Param.tieZero,
    size,
    source,
    address,
    0.U(dataWidth.W),
    false.B
  )
}

case class TLChannelDParameter(
  sourceWidth: Int,
  sinkWidth:   Int,
  dataWidth:   Int,
  sizeWidth:   Int) {
  def bundle(): TLChannelD = new TLChannelD(copy())

  private def assign(
    opcode:  UInt,
    param:   UInt,
    size:    UInt,
    source:  UInt,
    sink:    UInt,
    denied:  Bool,
    data:    UInt,
    corrupt: Bool
  ): TLChannelD = {
    val d: TLChannelD = Wire(bundle())
    d.opcode  := opcode
    d.param   := param
    d.size    := size
    d.source  := source
    d.sink    := sink
    d.denied  := denied
    d.data    := data
    d.corrupt := corrupt
    d
  }

  // Construct transactions
  def Grant(
    param:  UInt,
    size:   UInt,
    source: UInt,
    sink:   UInt,
    denied: Bool
  ): TLChannelD =
    assign(
      Message.Grant,
      param,
      size,
      source,
      sink,
      denied,
      0.U(dataWidth.W),
      false.B
    )

  def GrantData(
    param:   UInt,
    size:    UInt,
    source:  UInt,
    sink:    UInt,
    denied:  Bool,
    data:    UInt,
    corrupt: Bool
  ): TLChannelD = assign(
    Message.GrantData,
    param,
    size,
    source,
    sink,
    denied,
    data,
    corrupt
  )

  def ReleaseAck(size: UInt, source: UInt): TLChannelD = assign(
    Message.ReleaseAck,
    Param.tieZero,
    size,
    source,
    0.U(sinkWidth.W),
    false.B,
    0.U(dataWidth.W),
    false.B
  )

  def AccessAck(size: UInt, source: UInt, denied: Bool): TLChannelD = assign(
    Message.AccessAck,
    Param.tieZero,
    size,
    source,
    0.U(sinkWidth.W),
    denied,
    0.U(dataWidth.W),
    false.B
  )

  def AccessAckData(
    size:    UInt,
    source:  UInt,
    denied:  Bool,
    data:    UInt,
    corrupt: Bool
  ): TLChannelD = assign(
    Message.AccessAckData,
    Param.tieZero,
    size,
    source,
    0.U(dataWidth.W),
    denied,
    data,
    corrupt
  )

  def HintAck(
    size:   UInt,
    source: UInt,
    denied: Bool
  ): TLChannelD = assign(
    Message.HintAck,
    Param.tieZero,
    size,
    source,
    Param.tieZero,
    denied,
    0.U(dataWidth.W),
    false.B
  )
}

case class TLChannelEParameter(
  sinkWidth: Int) {
  def bundle(): TLChannelE = new TLChannelE(copy())

  private def assign(sink: UInt): TLChannelE = {
    val e: TLChannelE = Wire(bundle())
    e.sink := sink
    e
  }

  // Construct transactions
  def GrantAck(
    sink: UInt
  ): TLChannelE = assign(
    sink
  )
}
