import mill._
import mill.scalalib._
import mill.scalalib.publish._
import coursier.maven.MavenRepository

import $ivy.`de.tototec::de.tobiasroeser.mill.vcs.version::0.1.4`
import de.tobiasroeser.mill.vcs.version.VcsVersion

trait TileLinkModule extends ScalaModule with PublishModule {
  m =>
  // SNAPSHOT of Chisel is published to the SONATYPE
  override def repositories = super.repositories ++ Seq(
    MavenRepository("https://oss.sonatype.org/content/repositories/snapshots"),
    MavenRepository("https://oss.sonatype.org/content/repositories/releases")
  )
  // override to build from source, see the usage of chipsalliance/playground
  def chisel3Module: Option[PublishModule] = None
  // override to build from source, see the usage of chipsalliance/playground
  def chisel3PluginJar: T[Option[PathRef]] = T{ None }
  // Use SNAPSHOT chisel by default, downstream users should override this for their own project versions.
  def chisel3IvyDep: T[Option[Dep]] = None
  def chisel3PluginIvyDep: T[Option[Dep]] = None

  // TODO: remove this module after splitting is done.
  def rocketchipModule: PublishModule

  // User should not override lines below
  override def moduleDeps = Seq(rocketchipModule) ++ chisel3Module
  override def scalacPluginClasspath = T { super.scalacPluginClasspath() ++ chisel3PluginJar() }
  override def scalacPluginIvyDeps = T { Agg() ++ chisel3PluginIvyDep() }
  override def ivyDeps = T { Agg() ++ chisel3IvyDep() }

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
