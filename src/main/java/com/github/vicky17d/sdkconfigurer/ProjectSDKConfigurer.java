package com.github.vicky17d.sdkconfigurer;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.roots.ProjectRootManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.gradle.settings.GradleProjectSettings;
import org.jetbrains.plugins.gradle.settings.GradleSettings;

public class ProjectSDKConfigurer {

    private static final Logger LOG = Logger.getInstance(ProjectSDKConfigurer.class);

    public ProjectSDKConfigurer(final Project project) {
        ApplicationManager.getApplication().invokeAndWait(() -> {
            ApplicationManager.getApplication().runWriteAction(() -> configureSdk(project));
        });
    }

    private void configureSdk(@NotNull Project project) {

        final ProjectRootManager projectRootMgr = ProjectRootManager.getInstance(project);
        final String productSpecJdkVersion = getProductPecJdkVersion(project);
        projectRootMgr.setProjectSdkName(productSpecJdkVersion, JavaSdk.getInstance().getName());
        setGradleJvm(project, productSpecJdkVersion);
    }

    private void setGradleJvm(@NotNull Project project, String jdkVersion) {
        String basePath = project.getBasePath();
        if (basePath != null) {
            GradleProjectSettings gradleSettings = GradleSettings.getInstance(project).getLinkedProjectSettings(basePath);
            if (gradleSettings != null) {
                gradleSettings.setGradleJvm(jdkVersion);
            }
        }
    }

    private String getProductPecJdkVersion(@NotNull Project project) {
        return "11.0.8-msft";
    }
}
