pipeline{
  agent any
  
  stages{
        stage("Maven Clean") {
            steps {
                script {
                    sh "mvn -f'pom.xml' clean -DskipTests=true"
                }
            }
        }
        stage("Maven Compile") {
            steps {
                script {
                    sh "mvn -f'pom.xml' compile -DskipTests=true"
                }
            }
        }
        stage("Maven test") {
            steps {
                script {
                    sh "mvn -f'pom.xml' test"
                }
            }
        }
    
    stage("Maven Sonarqube") {
            steps {
                script {
                    sh "mvn -f'pom.xml' sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
                }
            }
        }
    
  }
}
