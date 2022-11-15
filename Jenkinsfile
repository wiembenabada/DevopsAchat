pipeline{
    environment {
        registry = "wiemabada/backend"
        registryCredential = 'dockerhub_id'
        dockerImage = ''
    }
    agent any
stages{
stage('GIT'){
steps {
    echo  'getting project from git';
    git branch :'wiemabada' , url : 'https://github.com/wiembenabada/DevopsAchat.git'
}}
stage('Maven clean'){
            steps {
               sh 'mvn clean'
            }
}
stage('Maven compile'){
            steps {
               sh 'mvn compile'
            }

}
stage('Maven SonarQube'){
     steps {
               sh 'mvn sonar:sonar \
  -Dsonar.projectKey=achat \
  -Dsonar.host.url=http://192.168.33.10:9000 \
  -Dsonar.login=55365ff940e3410e5d1517b15889818bc28ef0ef'
            }}
stage('build'){
      steps {
            sh 'mvn package -Dmaven.test.skip=true'
        }
    }
    stage('nexus package '){
            steps {
 sh 'mvn deploy:deploy-file -DgroupId=tn.esprit.rh \
  -DartifactId=achat \
  -Dversion=1.0 \
  -Dpackaging=jar\
  -Dfile=target/achat-1.0.jar  \
  -DgeneratePom=true \
  -DrepositoryId=achat.repo\
  -Durl=http://192.168.33.10:8081/repository/maven-releases/ '
            }
        }
    /*stage('Building our image') {

    steps {

        script {

          dockerImage = docker.build registry + ":$BUILD_NUMBER"

        }

    }
}
     stage('Deploy our image') {
    steps {

        script {

            docker.withRegistry( '', registryCredential ) {

                dockerImage.push()
            }

        }

    }

}

stage('Cleaning up') {

    steps {

        sh "docker rmi $registry:$BUILD_NUMBER"

    }

}*/
stage('docker compose') {

            steps {

                sh "docker-compose up -d"

            }

        }

}}pipeline{
      environment {
          registry = "wiemabada/backend"
          registryCredential = 'dockerhub_id'
          dockerImage = ''
      }
      agent any
  stages{
  stage('GIT'){
  steps {
      echo  'getting project from git';
      git branch :'wiemabada' , url : 'https://github.com/wiembenabada/DevopsAchat.git'
  }}
  stage('Maven clean'){
              steps {
                 sh 'mvn clean'
              }
  }
  stage('Maven compile'){
              steps {
                 sh 'mvn compile'
              }

  }
  stage('Maven SonarQube'){
       steps {
                 sh 'mvn sonar:sonar \
    -Dsonar.projectKey=achat \
    -Dsonar.host.url=http://192.168.33.10:9000 \
    -Dsonar.login=55365ff940e3410e5d1517b15889818bc28ef0ef'
              }}
  stage('build'){
        steps {
              sh 'mvn package -Dmaven.test.skip=true'
          }
      }
      stage('nexus package '){
              steps {
   sh 'mvn deploy:deploy-file -DgroupId=tn.esprit.rh \
    -DartifactId=achat \
    -Dversion=1.0 \
    -Dpackaging=jar\
    -Dfile=target/achat-1.0.jar  \
    -DgeneratePom=true \
    -DrepositoryId=achat.repo\
    -Durl=http://192.168.33.10:8081/repository/maven-releases/ '
              }
          }
      /*stage('Building our image') {

      steps {

          script {

            dockerImage = docker.build registry + ":$BUILD_NUMBER"

          }

      }
  }
       stage('Deploy our image') {
      steps {

          script {

              docker.withRegistry( '', registryCredential ) {

                  dockerImage.push()
              }

          }

      }

  }

  stage('Cleaning up') {

      steps {

          sh "docker rmi $registry:$BUILD_NUMBER"

      }

  }*/
  stage('docker compose') {

              steps {

                  sh "docker-compose up -d"

              }

          }

  }}