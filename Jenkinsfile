pipeline{
    agent any
    stages{

        stage('Checkout GIT'){
            steps{
                echo 'Pulling Project from GitHub';
                git branch: 'chaimabenammar', url:'https://github.com/wiembenabada/DevopsAchat.git'

            }
        }
        stage('Maven Clean And Compile') {
            steps {
                echo 'maven version'
                sh 'mvn -version'
                sh 'mvn clean'
                sh 'mvn compile'
            }
        }
        stage("Maven Sonarqube") {
            steps {
                withSonarQubeEnv(installationName :'SonarQube'){
                    sh "mvn  sonar:sonar  "
                    //sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
                }
            }
        }
      
        stage('Artifact Construction'){
                        steps{
                         sh 'mvn package -Dmaven.test.skip=true'
                         }
                     }
        stage('Maven TEST'){
                steps{
                 sh 'mvn test'
                 }
             }

        stage("Nexus Deploy"){
                steps{
                  sh 'mvn deploy -Dmaven.test.skip=true '

                 }
              }
        stage('Build docker image'){
                steps{
                     script{
                          sh 'docker build -t chaimabenammar/springproject .'
                    }
                    }
             }

        stage('Docker login') {

                steps {
                        sh 'echo "login Docker ...."'
                        sh 'docker login -u chaimabenammar -p adminadmin'
                    }  }


        stage('Docker push') {

             steps {
             sh 'echo "Docker is pushing ...."'
            sh 'docker push chaimabenammar/springproject'
                }  }

         stage('Docker compose') {

                steps {
               sh 'docker-compose up -d'
                 }  }






    }

 post {
                      success {
                        //  mail bcc: '', body: 'Pipeline build successfully', cc: '', from: 'chaima.benammar@esprit.tn', replyTo: '', subject: 'The Pipeline success', to: 'chaima.benammar@esprit.tn'
                            emailext body: 'Pipeline build successfully', subject: 'Pipeline build', to: 'chaima.benammar@esprit.tn'
                      }
                      failure {
                         // mail bcc: '', body: 'Pipeline build not success', cc: '', from: 'chaima.benammar@esprit.tn', replyTo: '', subject: 'The Pipeline failed', to: 'chaima.benammar@esprit.tn'
                            emailext body: 'Pipeline build not success', subject: 'Pipeline build', to: 'chaima.benammar@esprit.tn'
                      }
              }
          }