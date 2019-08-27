def runNoFail(script) {
    try {
        sh(script: script)
        return true
    } catch(Exception e) {
        return true
    }
}

pipeline {
    agent {
        docker {
            image 'docker:19.03.1'
            args '-v /var/run/docker.sock:/var/run/docker.sock -u root'
        }
    }
    stages{
        stage("Pre") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerlogin', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                            sh "docker login -u $USER -p $PASS registry.gitlab.com"
                }
            }

        }
        stage("Build") {
            steps {
                script {
                    def builded = docker.build("registry.gitlab.com/razikus/helion-kurs/igiserver:latest")
                    builded.push()                    
                }
                
            }
        }
        stage("Redeploy") {
            steps {
                script {
                    runNoFail("docker rm -f mysql")
                    runNoFail("docker rm -f igis")
                    runNoFail("docker network create igis")
                    sh "docker -H unix:///var/run/docker.sock run --name mysql -e MYSQL_ROOT_PASSWORD=test --network=igis -e MYSQL_DATABASE=igiscores -e MYSQL_USER=igi -e MYSQL_PASSWORD=scores -d mysql:5.7"
                    sh "docker run -e MYSQL_HOST=mysql -e MYSQL_PORT=3306 -e MYSQL_USER=igi -e MYSQL_PASSWORD=scores --network=igis -p 8081:8080 --name igis -d registry.gitlab.com/razikus/helion-kurs/igiserver:latest"
                }
            }
        }
    }
}
