dynamicLibrary = library identifier: 'utilities@master', retriever: modernSCM(
  [$class: 'GitSCMSource',
   remote: 'https://github.com/Razikus/helion-devops.git',
   credentialsId: 'libraryCredentials'])


def utilsLib = dynamicLibrary.pl.helion
def shellUtils = utilsLib.ShellUtils.new(this)
pipeline {
	agent any
	stages {
		stage("Test") {
			steps {
				script{
					shellUtils.touch("x")
				}
			}
		}
	}
}
