package tilelink

import chisel3._

object Param {
  /** TileLink Spec 1.8.1
    * Table 23
    */
  object Arithmetic {
    val MIN: UInt = 0.U(3.W)
    val MAX: UInt = 1.U(3.W)
    val MINU: UInt = 2.U(3.W)
    val MAXU: UInt = 3.U(3.W)
    val ADD: UInt = 4.U(3.W)
  }

  /** TileLink Spec 1.8.1
    * Table 25
    */
  object Logical {
    val XOR: UInt = 0.U(3.W)
    val OR: UInt = 1.U(3.W)
    val AND: UInt = 2.U(3.W)
    val SWAP: UInt = 3.U(3.W)
  }

  /** TileLink Spec 1.8.1
    * Table 27 Intent
    */
  object Intent {
    val PrefetchRead = 0.U(3.W)
    val PrefetchWrite = 1.U(3.W)
  }

  /** TileLink Spec 1.8.1
    * Table 31 Cap
    */
  object Cap {
    val toT: UInt = 0.U(3.W)
    val toB: UInt = 1.U(3.W)
    val toN: UInt = 2.U(3.W)
  }

  /** TileLink Spec 1.8.1
    * Table 31 Grow
    */
  object Grow {
    val NtoB: UInt = 0.U(3.W)
    val NtoT: UInt = 1.U(3.W)
    val BtoT: UInt = 2.U(3.W)
  }

  /** TileLink Spec 1.8.1
    * Table 31 Prune
    */
  object Prune {
    val TtoB = 0.U(3.W)
    val TtoN = 1.U(3.W)
    val BtoN = 2.U(3.W)
  }

  /** TileLink Spec 1.8.1
    * Table 31 Report
    */
  object Report {
    val TtoT = 3.U(3.W)
    val BtoB = 4.U(3.W)
    val NtoN = 5.U(3.W)
  }
}
