node() {
    
    stage("Checkout") {
        slackSend color: 'good', message: "Starting build $JOB_NAME, commiter: $gitlabUserName "
        updateGitlabCommitStatus  name: 'Checkout', state: 'pending'
        git url: 'https://gitlab.com/Razniewski/volume-exporter.git', branch: 'master', credentialsId: 'apitoken'
        updateGitlabCommitStatus  name: 'Checkout', state: 'success'
        slackSend color: 'good', message: 'Checkout good'

        
    }
    stage("Build") {
        updateGitlabCommitStatus  name: 'Build', state: 'pending'
        sh "bash build.sh"
        updateGitlabCommitStatus  name: 'Build', state: 'success'
        slackSend color: 'good', message: 'Build good'
    }
    stage("Test") {
        updateGitlabCommitStatus  name: 'Tests', state: 'pending'
        sh "bash build.sh"
        updateGitlabCommitStatus  name: 'Tests', state: 'success'
        slackSend color: 'good', message: 'Test good'
    }
}
