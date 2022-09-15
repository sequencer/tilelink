package tilelink

import chisel3._
import chisel3.util.DecoupledIO

import scala.collection.immutable.SeqMap

private class NoTLCException(
  channel:           String,
  tlBundleParameter: TLBundleParameter)
    extends ChiselException(
      s"call $channel in TLBundle is not allowed with parameter:\n $tlBundleParameter"
    )

class TLBundle(val parameter: TLBundleParameter) extends Record {
  def a: DecoupledIO[TLChannelA] =
    elements("a").asInstanceOf[DecoupledIO[TLChannelA]]

  def b: DecoupledIO[TLChannelB] =
    elements
      .getOrElse("b", throw new NoTLCException("b", parameter))
      .asInstanceOf[DecoupledIO[TLChannelB]]

  def c: DecoupledIO[TLChannelC] =
    elements
      .getOrElse("c", throw new NoTLCException("c", parameter))
      .asInstanceOf[DecoupledIO[TLChannelC]]

  def d: DecoupledIO[TLChannelD] =
    elements("d").asInstanceOf[DecoupledIO[TLChannelD]]

  def e: DecoupledIO[TLChannelE] =
    elements
      .getOrElse("e", throw new NoTLCException("e", parameter))
      .asInstanceOf[DecoupledIO[TLChannelE]]

  val elements: SeqMap[String, DecoupledIO[Bundle]] =
    SeqMap[String, DecoupledIO[Bundle]]() ++
      Seq("a" -> DecoupledIO(parameter.a.bundle())) ++
      parameter.b.map(p => "b" -> Flipped(DecoupledIO(p.bundle()))) ++
      parameter.c.map(p => "c" -> DecoupledIO(p.bundle())) ++
      Seq("d" -> Flipped(DecoupledIO(parameter.d.bundle()))) ++
      parameter.e.map(p => "e" -> DecoupledIO(p.bundle()))

  override def cloneType: this.type =
    new TLBundle(parameter).asInstanceOf[this.type]
}

class TLChannelA(val parameter: TLChannelAParameter) extends Bundle {
  // TODO: in spec, this is 'code' in spec
  val opcode:  UInt = UInt(Message.width)
  val param:   UInt = UInt(Param.width)
  val size:    UInt = UInt(parameter.sizeWidth.W)
  val source:  UInt = UInt(parameter.sourceWidth.W)
  val address: UInt = UInt(parameter.addressWidth.W)
  val mask:    UInt = UInt(parameter.maskWidth.W)
  val data:    UInt = UInt(parameter.dataWidth.W)
  val corrupt: Bool = Bool()
}

class TLChannelB(val parameter: TLChannelBParameter) extends Bundle {
  val opcode:  UInt = UInt(Message.width)
  val param:   UInt = UInt(Param.width)
  val size:    UInt = UInt(parameter.sizeWidth.W)
  val source:  UInt = UInt(parameter.sourceWidth.W)
  val address: UInt = UInt(parameter.addressWidth.W)
  val mask:    UInt = UInt(parameter.maskWidth.W)
  val data:    UInt = UInt(parameter.dataWidth.W)
  val corrupt: Bool = Bool()
}

class TLChannelC(val parameter: TLChannelCParameter) extends Bundle {
  val opcode:  UInt = UInt(Message.width)
  val param:   UInt = UInt(Param.width)
  val size:    UInt = UInt(parameter.sizeWidth.W)
  val source:  UInt = UInt(parameter.sourceWidth.W)
  val address: UInt = UInt(parameter.addressWidth.W)
  val data:    UInt = UInt(parameter.dataWidth.W)
  val corrupt: Bool = Bool()
}

class TLChannelD(val parameter: TLChannelDParameter) extends Bundle {
  val opcode:  UInt = UInt(Message.width)
  val param:   UInt = UInt(Param.width)
  val size:    UInt = UInt(parameter.sizeWidth.W)
  val source:  UInt = UInt(parameter.sourceWidth.W)
  val sink:    UInt = UInt(parameter.sinkWidth.W)
  val denied:  Bool = Bool()
  val data:    UInt = UInt(parameter.dataWidth.W)
  val corrupt: Bool = Bool()
}

class TLChannelE(val parameter: TLChannelEParameter) extends Bundle {
  val sink: UInt = UInt(parameter.sinkWidth.W)
}
