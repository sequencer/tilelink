package tilelink

import chisel3._
import chisel3.util.DecoupledIO

import scala.collection.immutable.SeqMap

private class NoTLCException(channel: String, tlBundleParameter: TLBundleParameter) extends ChiselException(s"call $channel in TLBundle is not allowed with parameter:\n $tlBundleParameter")

class TLBundle(val parameter: TLBundleParameter) extends Record {
  def a: DecoupledIO[TLChannelA] = elements("a").asInstanceOf[DecoupledIO[TLChannelA]]

  def b: DecoupledIO[TLChannelB] = elements.getOrElse("b", throw new NoTLCException("b", parameter)).asInstanceOf[DecoupledIO[TLChannelB]]

  def c: DecoupledIO[TLChannelC] = elements.getOrElse("c", throw new NoTLCException("c", parameter)).asInstanceOf[DecoupledIO[TLChannelC]]

  def d: DecoupledIO[TLChannelD] = elements("d").asInstanceOf[DecoupledIO[TLChannelD]]

  def e: DecoupledIO[TLChannelE] = elements.getOrElse("e", throw new NoTLCException("e", parameter)).asInstanceOf[DecoupledIO[TLChannelE]]

  val elements: SeqMap[String, DecoupledIO[Bundle]] = SeqMap[String, DecoupledIO[Bundle]]() ++
    Seq("a" -> DecoupledIO(new TLChannelA(parameter.a))) ++
    parameter.b.map(p => "b" -> Flipped(DecoupledIO(new TLChannelB(p)))) ++
    parameter.c.map(p => "c" -> DecoupledIO(new TLChannelC(p))) ++
    Seq("d" -> Flipped(DecoupledIO(new TLChannelD(parameter.d)))) ++
    parameter.e.map(p => "e" -> DecoupledIO(new TLChannelE(p)))

  override def cloneType: this.type = new TLBundle(parameter).asInstanceOf[this.type]
}

class TLChannelA(val parameter: TLChannelAParameter) extends Bundle {
  val address: UInt = UInt(parameter.addressWidth.W)
  val source: UInt = UInt(parameter.sourceWidth.W)

  val opcode: UInt = UInt(3.W)
  val param: UInt = UInt(3.W)

  val data: UInt = UInt(parameter.dataWidth.W)
  val size: UInt = UInt(parameter.sizeWidth.W)

  val mask: UInt = UInt(parameter.maskWidth.W)

  val corrupt: Bool = Bool()
}

class TLChannelB(val parameter: TLChannelBParameter) extends Bundle {
  val address: UInt = UInt(parameter.addressWidth.W)
  val source: UInt = UInt(parameter.sourceWidth.W)

  val opcode: UInt = UInt(3.W)
  val param: UInt = UInt(3.W)

  val data: UInt = UInt(parameter.dataWidth.W)
  val size: UInt = UInt(parameter.sizeWidth.W)

  val mask: UInt = UInt(parameter.maskWidth.W)

  val corrupt: Bool = Bool()
}

class TLChannelC(val parameter: TLChannelCParameter) extends Bundle {
  val address: UInt = UInt(parameter.addressWidth.W)
  val source: UInt = UInt(parameter.sourceWidth.W)
  val sink: UInt = UInt(parameter.sinkWidth.W)

  val opcode: UInt = UInt(3.W)
  val param: UInt = UInt(3.W)

  val data: UInt = UInt(parameter.dataWidth.W)
  val size: UInt = UInt(parameter.sizeWidth.W)

  val corrupt: Bool = Bool()
}

class TLChannelD(val parameter: TLChannelDParameter) extends Bundle {
  val source: UInt = UInt(parameter.sourceWidth.W)

  val opcode: UInt = UInt(3.W)
  val param: UInt = UInt(3.W)

  val data: UInt = UInt(parameter.dataWidth.W)
  val size: UInt = UInt(parameter.sizeWidth.W)

  val corrupt: Bool = Bool()
  val denied: Bool = Bool()
}

class TLChannelE(val parameter: TLChannelEParameter) extends Bundle {
  val sink: UInt = UInt(parameter.sinkWidth.W)
}
