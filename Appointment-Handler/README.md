# sofe4640-final-project

Note:  We had a version problem for some reason running the app and had to include the following code block in the file build.grade:

configurations.all {
    resolutionStrategy.eachDependency { DependencyResolveDetails details ->
        def requested = details.requested
        if (requested.group == 'com.android.support') {
            if (!requested.name.startsWith("multidex")) {
                details.useVersion '25.3.1'
            }
        }
    }
}


If you download everything from github as it is, you shouldn't have to do anything.

