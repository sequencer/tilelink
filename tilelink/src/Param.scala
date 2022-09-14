package tilelink

import chisel3._

object Param {
  private val width = 3.W
  val tieZero: UInt = 0.U(width)
  /** TileLink Spec 1.8.1
    * Table 23
    */
  object Arithmetic {
    val MIN: UInt = 0.U(width)
    val MAX: UInt = 1.U(width)
    val MINU: UInt = 2.U(width)
    val MAXU: UInt = 3.U(width)
    val ADD: UInt = 4.U(width)
  }

  /** TileLink Spec 1.8.1
    * Table 25
    */
  object Logical {
    val XOR: UInt = 0.U(width)
    val OR: UInt = 1.U(width)
    val AND: UInt = 2.U(width)
    val SWAP: UInt = 3.U(width)
  }

  /** TileLink Spec 1.8.1
    * Table 27 Intent
    */
  object Intent {
    val PrefetchRead = 0.U(width)
    val PrefetchWrite = 1.U(width)
  }

  /** TileLink Spec 1.8.1
    * Table 31 Cap
    */
  object Cap {
    val toT: UInt = 0.U(width)
    val toB: UInt = 1.U(width)
    val toN: UInt = 2.U(width)
  }

  /** TileLink Spec 1.8.1
    * Table 31 Grow
    */
  object Grow {
    val NtoB: UInt = 0.U(width)
    val NtoT: UInt = 1.U(width)
    val BtoT: UInt = 2.U(width)
  }

  /** TileLink Spec 1.8.1
    * Table 31 Prune
    */
  object Prune {
    val TtoB = 0.U(width)
    val TtoN = 1.U(width)
    val BtoN = 2.U(width)
  }

  /** TileLink Spec 1.8.1
    * Table 31 Report
    */
  object Report {
    val TtoT = 3.U(width)
    val BtoB = 4.U(width)
    val NtoN = 5.U(width)
  }
}
