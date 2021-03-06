package guru.stefma.artifactorypublish

import closureOf
import org.gradle.api.Project
import org.jfrog.gradle.plugin.artifactory.dsl.ArtifactoryPluginConvention
import org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig
import org.jfrog.gradle.plugin.artifactory.task.ArtifactoryTask
import withGroovyBuilder

class ArtifactoryPublishConfiguration(
        private val project: Project,
        private val extension: ArtifactoryPublishExtension
) {

    private val propertyFinder = ArtifactoryPublishPropertyFinder(project, extension)

    fun configure() {
        project.convention.getPlugin(ArtifactoryPluginConvention::class.java).apply {
            setContextUrl(extension.artifactoryUrl)
            publish(closureOf<PublisherConfig> {
                withGroovyBuilder {
                    "repository" {
                        "setRepoKey"(extension.artifactoryRepo)
                        "setUsername"(propertyFinder.artifactoryUser)
                        "setPassword"(propertyFinder.artifactoryKey)
                    }
                }
            })
        }
        (project.tasks.getByName("artifactoryPublish") as ArtifactoryTask).apply {
            publications(*extension.publications)
            setPublishArtifacts(true)
            setPublishPom(true)
        }
    }

}