pipeline {
    agent any

    environment {
        TERRAFORM_DIR = "./terraform" // Directory where Terraform will be downloaded and executed
    }

    stages {
        stage('List Files') {
            steps {
                script {
                    sh 'ls -ls'
                }
            }
        }

        stage('check terrafrom version') {
            steps {
                 sh '''
                    # Verify Terraform installation
                    terraform version
                '''
            }
        }

        stage('Initiate and execute terraform') {
                steps {
                    dir(TERRAFORM_DIR) {
                    sh '''
                    terraform init
                    terraform plan -out=tfplan
                    '''
                }
            }
        }

    }

    post {
        always {
            echo 'Cleaning workspace...'
            cleanWs()
        }
    }
}

