def name = "nazwa"
node {
   stage('Clone') {
      git url: 'https://repo', credentialsId: "idcredentials"
   }
   stage('Build') {
	  def image = docker.build("${name}:latest")
	  try{
	    sh "docker rm -f ${name}"
	  } catch(Exception e) {
	    println "can't remove ${name}"
	  }
	  image.run(--name ${name} -p 80:80")
   }
}
