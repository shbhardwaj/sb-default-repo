pipeline {
  agent {
    docker {
      image '3.5.4-jdk-8-alpine'
    }

  }
  stages {
    stage('Initialize') {
      steps {
        sh '''sh \'\'\'
    echo "PATH = ${PATH}"
    echo "M2_HOME = ${M2_HOME}"
\'\'\''''
      }
    }
  }
}