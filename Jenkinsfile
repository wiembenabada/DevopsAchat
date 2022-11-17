pipeline{
    agent any
    stages{

        stage('Checkout GIT'){
            steps{
                echo 'Pulling Project from GitHub';
                git branch: 'chaimabenammar', url:'https://github.com/wiembenabada/DevopsAchat.git'

            }
        }
        stage('Maven Build') {
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
       /* stage("Maven Sonarqube") {
            steps {
                script {
                    sh "mvn -f'pom.xml' sonar:sonar -Dsonar.login=admin -Dsonar.password=99204845"
                }
            }
        }*/
        /*stage('Quality Gate') {
            steps {
                timeout(time:2, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true
                }
            }
        }*/
        stage('MVN TEST'){
                steps{
                 sh 'mvn test'
                 }
             }

        stage("nexus deploy"){
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
}