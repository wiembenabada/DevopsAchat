pipeline{
  agent any
  
  stages{
        stage("Maven Build") {
            steps {
                script {
                    sh 'mvn clean package'
                }
            }
        }
    
    stage("Maven Sonarqube") {
            steps {
                script {
                    sh "mvn -f'spring/pom.xml' sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
                }
            }
        }
    
  }
}
