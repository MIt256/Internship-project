package com.example.taskmanager.ui.menu.adapter

import com.example.taskmanager.ui.newTask.entities.Project

sealed class ProjectItem {
    class PlainProject(val projectItem: Project) : ProjectItem()
    object AddItem : ProjectItem()
}