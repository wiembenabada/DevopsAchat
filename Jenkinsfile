pipeline{
  agent any
  tools {
        maven "MAVEN"
        jdk "JDK"
    }
  stages{
        stage("Maven Build") {
            steps {
                script {
                    sh 'mvn -B -DskipTests clean package'
                }
            }
        }
    
    
    
  }
}
