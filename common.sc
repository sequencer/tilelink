import mill._
import mill.scalalib._
import mill.scalalib.publish._
import coursier.maven.MavenRepository

import $ivy.`de.tototec::de.tobiasroeser.mill.vcs.version::0.1.4`
import de.tobiasroeser.mill.vcs.version.VcsVersion

trait TileLinkPublishModule extends ScalaModule with PublishModule {m =>
  def publishVersion = de.tobiasroeser.mill.vcs.version.VcsVersion.vcsState().format()
  def pomSettings = T {
    PomSettings(
      description = artifactName(),
      organization = "org.chipsalliance",
      url = "https://github.com/chipsalliance/tilelink",
      licenses = Seq(License.`Apache-2.0`, License.`BSD-3-Clause`),
      versionControl = VersionControl.github("chipsalliance", "tilelink"),
      developers = Seq(
        Developer("sequencer", "Jiuyang Liu", "https://github.com/sequencer")
      )
    )
  }
}

// This only contains the ChiselEnum, Bundle，Record and general small utils for TileLink
trait TileLinkModule extends TileLinkPublishModule {
  def chiselModule: Option[PublishModule] = None
  def chiselPluginJar: T[Option[PathRef]] = T(None)

  def chiselIvyDep: T[Option[Dep]] = None
  def chiselPluginIvyDep: T[Option[Dep]] = None

  override def moduleDeps = Seq() ++ chiselModule
  override def scalacPluginClasspath = T(super.scalacPluginClasspath() ++ chiselPluginJar())
  override def scalacPluginIvyDeps = T(Agg() ++ chiselPluginIvyDep())
  override def scalacOptions = T(super.scalacOptions() ++ chiselPluginJar().map(path => s"-Xplugin:${path.path}"))
  override def ivyDeps = T(Agg() ++ chiselIvyDep())
}

trait BusIPModule extends TileLinkPublishModule {
  def tileLinkModule: PublishModule
  override def moduleDeps = Seq(tileLinkModule)
}

// Currently DiplomaticModule will depend on rocket-chip, but in the future, it will depend on diplomacy
trait DiplomaticModule extends TileLinkPublishModule {
  def busIPModule: PublishModule
  // TODO: remove this module after splitting is done.
  def rocketchipModule: PublishModule
  override def moduleDeps = super.moduleDeps :+ rocketchipModule :+ busIPModule
}
