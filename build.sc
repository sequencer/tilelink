import mill._
import mill.scalalib._
import mill.scalalib.publish._
import mill.scalalib.scalafmt._
import coursier.maven.MavenRepository
import $file.dependencies.chisel.build
import $file.common
//import $file.dependencies.cde.build
//import $file.dependencies.`berkeley-hardfloat`.build
//import $file.dependencies.`rocket-chip`.common

object v {
  val scala = "2.13.11"
}
object mychisel extends dependencies.chisel.build.Chisel(v.scala) {
  override def millSourcePath = os.pwd / "dependencies" / "chisel"
}
object tilelink extends common.TileLinkModule with ScalafmtModule { m =>
  def scalaVersion = T(v.scala)
  def chiselModule = Some(mychisel)
  def chiselPluginJar = T { Some(mychisel.pluginModule.jar()) }
}
object busip extends common.BusIPModule { m =>
  def scalaVersion = T(v.scala)
  def tileLinkModule = tilelink
}

// TODO: remove and switch to diplomacy
//object mycde extends dependencies.cde.build.cde(v.scala) with PublishModule {
//  override def millSourcePath = os.pwd /  "dependencies" / "cde" / "cde"
//}
//object myhardfloat extends dependencies.`berkeley-hardfloat`.build.hardfloat {
//  override def millSourcePath = os.pwd /  "dependencies" / "berkeley-hardfloat"
//  override def scalaVersion = v.scala
//  def chisel3Module: Option[PublishModule] = Some(mychisel3)
//  override def scalacPluginClasspath = T { super.scalacPluginClasspath() ++ Agg(mychisel3.pluginModule.jar()) }
//  override def scalacOptions = T { Seq("-Xsource:2.11", s"-Xplugin:${mychisel3.pluginModule.jar().path}", "-P:chiselplugin:genBundleElements") }
//}
//object myrocketchip extends dependencies.`rocket-chip`.common.CommonRocketChip {
//  def chisel3Module: Option[PublishModule] = Some(mychisel3)
//  def hardfloatModule: PublishModule = myhardfloat
//  def configModule: PublishModule = mycde
//  override def scalaVersion = v.scala
//  override def millSourcePath = os.pwd /  "dependencies" / "rocket-chip"
//  override def scalacPluginClasspath = T { super.scalacPluginClasspath() ++ Agg(mychisel3.pluginModule.jar()) }
//  override def scalacOptions = T { Seq("-Xsource:2.11", s"-Xplugin:${mychisel3.pluginModule.jar().path}", "-P:chiselplugin:genBundleElements") }
//}
//object diplomatic extends common.DiplomaticModule { m =>
//  def scalaVersion = T { v.scala }
//  def busIPModule = busip
//  def rocketchipModule = myrocketchip
//}
