pipeline {
    agent any

    stages {
        stage('List Files') {
            steps {
                script {
                    sh 'ls -ls'
                }
            }
        }
    }
}

