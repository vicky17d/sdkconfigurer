package com.github.vicky17d.sdkconfigurer.services

import com.intellij.openapi.project.Project
import com.github.vicky17d.sdkconfigurer.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
