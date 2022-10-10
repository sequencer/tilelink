package tilelink.busip

import chisel3._
import chisel3.util.experimental.BitSet
import tilelink.{TLBundle, TLBundleParameter}

/** A [[CrossBar]] is a router for n to m connection matrix. all the master and
  * slaves shares a same [[TLBundleParameter]], if the parameters are not same,
  * use other busips to convert bus to a unified parameter.
  *
  * @param arbitrationPolicy
  *   select a arbitration policy from [[ArbitrationPolicy]]
  * @param masterPortSize
  *   how many masters do this crossbar have
  * @param slavePortSize
  *   how many slaves do this crossbar have
  * @param addressRoutingTables
  *   give a map of ([[chisel3.util.experimental.BitSet]] -> slave port) for
  *   each master ports
  */
case class CrossBarParameter(
  arbitrationPolicy:     ArbitrationPolicy,
  masterPortParameters:  Seq[TLBundleParameter],
  slavePortParameters:   Seq[TLBundleParameter],
  addressRoutingTablesCacheable: Seq[Map[BitSet, Int]],
  addressRoutingTablesNonCacheable: Seq[Map[BitSet, Int]],
  bundleParameter:       TLBundleParameter) {
  def masterPortSize = masterPortParameters.size
  def slavePortSize  = slavePortParameters.size
  def reachabilityCacheable: Seq[(Int, Seq[Int])] =
    addressRoutingTablesCacheable.zipWithIndex.map { case (table, source) =>
      source -> table.values.toSeq.sorted
    }
  def reachabilityNonCacheable: Seq[(Int, Seq[Int])] =
    addressRoutingTablesNonCacheable.zipWithIndex.map { case (table, source) =>
      source -> table.values.toSeq.sorted
    }
  assert(false, "address overlap")
}

class CrossBar(parameter: CrossBarParameter) extends Module {
  val masters: Seq[TLBundle] = Seq.fill(parameter.masterPortSize) {
    IO(parameter.bundleParameter.master())
  }
  val slaves:  Seq[TLBundle] = Seq.fill(parameter.slavePortSize) {
    IO(parameter.bundleParameter.slave())
  }
}
