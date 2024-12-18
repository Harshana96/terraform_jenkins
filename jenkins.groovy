pipeline {
    agent any

    stages {
        stage('List Files') {
            steps {
                script {
                    // For Unix-based systems (Linux, macOS)
                    if (isUnix()) {
                        sh 'ls -al'
                    } 
                    // For Windows systems
                    else {
                        bat 'dir'
                    }
                }
            }
        }
    }
}

