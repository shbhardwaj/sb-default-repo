pipeline {
  agent {
    docker {
      image 'maven:3.5.4-jdk-8-alpine'
    }

  }
  stages {
    stage('Initialize') {
      steps {
        sh '''echo "PATH = ${PATH}"
echo "M2_HOME = ${M2_HOME}"
'''
      }
    }
  }
  environment {
    GIT_SSL_NO_VERIFY = 'true'
  }
}