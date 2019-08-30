node() {
    
    stage("Checkout") {
        updateGitlabCommitStatus  name: 'Checkout', state: 'pending'
        git url: 'https://gitlab.com/Razniewski/volume-exporter.git', branch: 'master'
        updateGitlabCommitStatus  name: 'Checkout', state: 'success'
        
    }
    stage("Build") {
        updateGitlabCommitStatus  name: 'Build', state: 'pending'
        sh "bash build.sh"
        updateGitlabCommitStatus  name: 'Build', state: 'success'
    }
    stage("Test") {
        updateGitlabCommitStatus  name: 'Tests', state: 'pending'
        sh "bash build.sh"
        updateGitlabCommitStatus  name: 'Tests', state: 'success'
    }
}
