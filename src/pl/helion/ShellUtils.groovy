package pl.helion;

class ShellUtils implements Serializable {
	def steps
	ShellUtils(steps) {
	    this.steps = steps
	}

	def touch(String path) {
		steps.sh(script: "touch ${path}", returnStdout: true).trim()
	}

}

