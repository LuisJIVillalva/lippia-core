node{
    
    //ATENCION: para que este job funcione es necesario lo siguiente:
    //    - Crear credenciales para bitbucket con id "bitbucket"
    //    - En global configuration definir una instancia de maven 3.6.0 con nombre "M3"
    //    - En managed files, definir un user setting, agregarle las credenciales para los servers de nexus, y modificar el id generado en el script.
    
    
    stage('checkout source code') {
     	 checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'bitbucket', url: 'git@bitbucket.org:crowdarautomation/crowdCoreframework.git']]]) 
    }
    
    stage('maven deploy') {
        
        withMaven(
            // Maven installation declared in the Jenkins "Global Tool Configuration"
            maven: 'M3',
            // Maven settings.xml file defined with the Jenkins Config File Provider Plugin
            // Maven settings and global settings can also be defined in Jenkins Global Tools Configuration
            mavenSettingsConfig: 'eb77f0b6-c98e-47ab-8237-4feff8453cc2',
            mavenLocalRepo: '.repository') {
     
            // Run the maven deploy
            sh "mvn clean deploy -DskipTests=true -U"
     
        }

    }
}