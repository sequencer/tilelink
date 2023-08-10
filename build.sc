import mill._
import mill.scalalib._
import mill.scalalib.scalafmt._
import $file.common

object v {
  val scala = "2.13.10"
  val chiselCrossVersions = Map(
    "3.6.0" -> (ivy"edu.berkeley.cs::chisel3:3.6.0", ivy"edu.berkeley.cs:::chisel3-plugin:3.6.0"),
    "5.0.0" -> (ivy"org.chipsalliance::chisel:5.0.0", ivy"org.chipsalliance:::chisel-plugin:5.0.0"),
  )
}

object tilelink extends Cross[TileLink](v.chiselCrossVersions.keys.toSeq)

trait TileLink
  extends common.TileLinkModule
    with ScalafmtModule 
    with Cross.Module[String] {
  override def scalaVersion = T(v.scala)

  override def millSourcePath = os.pwd / "tilelink"

  def chiselModule = None

  def chiselPluginJar = None

  def chiselIvy = Some(v.chiselCrossVersions(crossValue)._1)

  def chiselPluginIvy = Some(v.chiselCrossVersions(crossValue)._2)
}
