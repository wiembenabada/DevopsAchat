pipeline{
  agent any
  environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.33.10:8081"
        NEXUS_REPOSITORY = "maven-releases"
        NEXUS_CREDENTIAL_ID = "nexus"
        registry = "siwarguermassi/devopsrepo" 
        registryCredential = 'dockerhub' 
        dockerImage = '' 
    }
  
  
    
    stages {
       stage('GIT'){
            steps {
                echo  'getting project from git';
                 git branch :'siwarguermassi' , url : 'https://github.com/wiembenabada/DevopsAchat.git'
            }
            
        }
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
       
    
    stage("Maven Sonarqube") {
            steps {
                script {
                    sh "mvn -f'pom.xml' sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
                }
            }
        }
    stage("Maven Build") {
            steps {
                script {
                    sh "mvn -f'pom.xml' package -DskipTests=false"
                }
                echo ":$BUILD_NUMBER"
            }
        }
      Stage ( 'unit test') { 
    Steps { 
           sh 'mvn test'
}

}
     stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                    pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
     }
      stage('Building our image') { 
15
            steps { 
16
                script { 
17
                    dockerImage = docker.build registry + ":$BUILD_NUMBER" 
18
                }
19
            } 
20
        }
21
        stage('Deploy our image') { 
22
            steps { 
23
                script { 
24
                    docker.withRegistry( '', registryCredential ) { 
25
                        dockerImage.push() 
26
                    }
27
                } 
28
            }
29
        } 
30
        stage('Cleaning up') { 
31
            steps { 
32
                sh "docker rmi $registry:$BUILD_NUMBER" 
33
            }
34
        } 
      
    stage('docker compose') {

            steps {

                sh "docker-compose up -d"

            }
    }

   
      
      
      
      
     
  }
}
